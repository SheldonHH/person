/*
 * Copyright (c) 2007 Regents of the University of California.
 * All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * <p>
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * <p>
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * <p>
 * 3. The name of the University may not be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */

package com.example.demo.p4p.sim;


import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.dao.PersonDataAccessService;
import com.example.demo.model.*;
import com.example.demo.model.gauss.BoundforGauss;
import com.example.demo.model.vmatrixhash.RowColTreeHMaps;
import com.example.demo.p4p.crypto.BitCommitment;
import com.example.demo.p4p.crypto.Proof;
import com.example.demo.p4p.crypto.SquareCommitment;
import com.example.demo.p4p.crypto.ThreeWayCommitment;
import com.example.demo.p4p.user.UserVector2;
import com.example.demo.p4p.util.P4PParameters;
import com.example.demo.p4p.util.StopWatch;
import com.example.demo.net.i2p.util.NativeBigInteger;

import com.example.demo.p4p.peer.P4PPeer;
import com.example.demo.p4p.server.P4PServer;
import com.example.demo.util.InputStreamConsumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/*
 * Providing a simulation framework for a P4P system. This allows one to debug
 * and benchmark the cryptographic primitives without having to provide other
 * components necessary for a real deployment (e.g. secure communication).
 *
 * @author ET 12/10/2005
 */

public class P4PSim extends P4PParameters {
    private final static String url = "jdbc:postgresql://localhost:5432/client1";
    private final static String user = "client1";
    private final static String password = "password";
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    private static NativeBigInteger g = new NativeBigInteger("3182089256208329047054709904358973599639052582169128376753217579641056697166499158386824120768854848163132851742558842187976312344846648732546791352223868");
    private static NativeBigInteger h = new NativeBigInteger("9793143674503176705343368747667288665355699962542491643750752248068073537700661368128860976203407269976279596607505206660360515029147205303637405777467078");


//    static UUID userid = UUID.fromString("1fa4fd06-34f0-49a4-baf9-a4073bca0292");
    private static int k = 512;     // Security parameter
    private static int m = 2;      // User vector dimension
    private static int n = 1;      // Number of users
    private static int l = 40;      // Bit length of L
    final static CloseableHttpClient httpClient = HttpClients.createDefault();

    /* Start a simulation.
     */
    public static ArrayList<Integer> listofV(String RorC, int num_index, double israel){
        ArrayList<Integer> list = new ArrayList<>();
        int squareWidth = (int)Math.ceil(Math.sqrt(israel));
        if(RorC.equals("col")){
            for(int i = 0; i< israel; i++){
                if(i % (int)Math.ceil(Math.sqrt(israel)) == num_index){
                    System.out.print(i+", ");
                    list.add(i);
                }
            }
        }
        if(RorC.equals("row")){
            int[][] matrix = printMatrix(israel);
            list = (ArrayList<Integer>) Arrays.stream(matrix[num_index]).boxed().collect(Collectors.toList());
        }
        return list;
    }

    public static int sqWidth(double israel){
        return (int)Math.ceil(Math.sqrt(israel));
    }

    public static void truncatediunitRange(){
        String SQL = "TRUNCATE TABLE diunitrange; ";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    System.out.println(rs);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void truncateHashList(){
        String SQL = "TRUNCATE TABLE HashList; ";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    System.out.println(rs);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void truncateHMatrixDB(){
        String SQL = "TRUNCATE TABLE VHashMatrix; ";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    System.out.println(rs);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static int insertDiwithUnitRange(String range, long[] di) {
        String SQL = "INSERT INTO DiUnitRange(unitrange, di) "
                + "VALUES(?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setObject (1, range);
            String[] strArray = Arrays.stream(di)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);
            Array sg = conn.createArrayOf("TEXT", strArray);
            pstmt.setArray (2, sg);
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    System.out.println(rs);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static void persistHashColRow(ArrayList<List<Long>> allViList, UUID userid){
        int totalLine = allViList.size();
        String curRow = new String(), curCol=new String();
        TreeMap<Integer, String> rowMap = new TreeMap<>();
        TreeMap<Integer, String> colMap = new TreeMap<>();
        TreeMap<Integer, Integer> colHashMap = new TreeMap<>();
        TreeMap<Integer, Integer> rowHashMap = new TreeMap<>();
        for(int i = 0; i<totalLine; i++){
            curCol = colMap.get(i%sqWidth(totalLine));
            if(curCol == null){
                colMap.put(i%sqWidth(totalLine), ""+allViList.get(i).toString());
            }else{
                colMap.put(i%sqWidth(totalLine), curCol+", "+allViList.get(i).toString());
            }
            curRow = rowMap.get(i/sqWidth(totalLine));
            if(curRow== null){
                rowMap.put(i/sqWidth(totalLine), ""+allViList.get(i).toString());
            }else{
                rowMap.put (i/sqWidth(totalLine), curRow+", "+allViList.get(i).toString());
            }
        }
        System.out.println("rowMap"+rowMap);
        for(int i = 0; i < rowMap.size(); i++){
            System.out.println("rowMap.get(i)"+rowMap.get(i));
            rowHashMap.put(i, ("["+rowMap.get(i)+"]").hashCode());
        }
        for(int i = 0; i < colMap.size(); i++){
            colHashMap.put(i, ("["+colMap.get(i)+"]").hashCode());
        }


        String SQL = "INSERT INTO " +
                "HashList(hash_id, rowOrCol, index, concatedResult, HashResult) "
                + "VALUES(?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            for(int i = 0; i < rowMap.size(); i++){
                pstmt.setObject (1, UUID.randomUUID());
                pstmt.setString(2, "row");
                pstmt.setInt(3, i);
                pstmt.setString(4, rowMap.get(i));
                pstmt.setInt(5, rowHashMap.get(i));
                int affectedRows = pstmt.executeUpdate();
                // check the affected rows
                if (affectedRows > 0) {
                    // get the ID back
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        System.out.println(rs);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            for(int i = 0; i < colMap.size(); i++){
                pstmt.setObject (1, UUID.randomUUID());
                pstmt.setString(2, "col");
                pstmt.setInt(3, i);
                pstmt.setString(4, colMap.get(i));
                pstmt.setInt(5, colHashMap.get(i));
                int affectedRows = pstmt.executeUpdate();
                // check the affected rows
                if (affectedRows > 0) {
                    // get the ID back
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        System.out.println(rs);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            HttpPost request = new HttpPost("http://localhost:9002/api/v1/peer/rowcoltreehashmaps");
            RowColTreeHMaps rowColTreeMaps = new RowColTreeHMaps(userid, colHashMap,rowHashMap);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
            StringEntity json = new StringEntity(mapper.writeValueAsString(rowColTreeMaps), ContentType.APPLICATION_JSON);
            request.setEntity(json);
            CloseableHttpResponse response = httpClient.execute(request);
            System.out.println("response.getEntity()");
            System.out.println(response.getEntity());
            if(response.getStatusLine().getStatusCode() != 200){
                System.out.println("Student is not added! "+response.getStatusLine().getStatusCode() );
            }
            response.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    static ArrayList<List<Long>> allViList = new ArrayList<>();
    public static int persistViwithColRow(long lineNum, long[] vi, long totalLine){
        List<Long> viList = Arrays.stream(vi)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        String SQL = "INSERT INTO VHashMatrix(v_id, row, col, vi) "
                + "VALUES(?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setObject (1, UUID.randomUUID());
            pstmt.setString(2, ""+lineNum/sqWidth(totalLine));
            pstmt.setString(3, ""+lineNum%sqWidth(totalLine));
            String[] strArray = Arrays.stream(vi)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);
            Array sg = conn.createArrayOf("TEXT", strArray);
            pstmt.setArray (4, sg);
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    System.out.println(rs);
//                    if (rs.next()) {
////                        id = rs.getLong(1);
//                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        allViList.add(viList);
        return 0;
    }
    public static int[][] printMatrix(double israel){
        int sqWidth = (int)Math.ceil(Math.sqrt(israel));
        int[][] matrixToReturn = new int[sqWidth][sqWidth];
        int col = 0;
        int row = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i< israel; i++){
            System.out.print(i+", ");
            matrixToReturn[col][row] = i;
            row++;
            if((i+1) % (int)Math.ceil(Math.sqrt(israel)) == 0 ){
                System.out.println("");
                col++;
                row=0;
            }
        }    System.out.println("");
        return matrixToReturn;
    }

    public static String gauss_params;
    public static void readGaussParams() {
        try {
            String fileName = "/Users/mac/Desktop/FedBFT/voting/gauss_param.txt";
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                gauss_params = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String findRange(String dimension,long a){
        double LowerRange=0, UpperRange=0;
        if(Math.abs(a) >= 10){
            if(a > 0){
               LowerRange = ((double)a - (double)(a%10));
               UpperRange = LowerRange+10;
            }else{
               UpperRange = ((double)a - (double)(a%10));
               LowerRange = UpperRange-10;
            }
        }else if(Math.abs(a) >= 0 && Math.abs(a) < 10){
            if(a >= 0){
                LowerRange = 0;
                UpperRange = 10;
            }else{
                LowerRange = -10;
                UpperRange = 0;
            }
        }
       return dimension+":"+LowerRange+"-"+UpperRange;
    }
    static String preprocess = "/Users/mac/singapore/person1/src/main/python/preprocess.py";
    static String joint = "/Users/mac/singapore/person1/src/main/python/joint_frequency.py";
    static String gauss = "/Users/mac/singapore/person1/src/main/python/2d.py";
    public static void main(String[] args) throws InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("/Users/mac/opt/anaconda3/bin/python3", preprocess, args[0], "python3", joint, "python3", gauss);
//        pb.directory(new File("/Users/mac/FedBFT/KL_Divergence/"));
        System.out.println(args[0]);
        UUID userid = UUID.fromString(args[1]);
        PersonDataAccessService.userid = userid;
        if(args[0].equals("/Users/mac/singapore/person1/src/main/resources/data_sample/user_1_data.csv")){
            System.out.println("yesssss!!!!!~!");
        }
//        pb.directory(new File("src"));
        Process p = null;
        try {
            p = pb.start();
            InputStreamConsumer consumer = new InputStreamConsumer(p.getInputStream());
            consumer.start();
            int result = p.waitFor();
            consumer.join();
            System.out.println(consumer.getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }


        readGaussParams();
//        double israel = 70;
//        printMatrix(israel);
////        System.out.println((int)Math.ceil(Math.sqrt(israel)));
//        System.out.println(listofV("row", 1, israel));
        long maxX1=Long.MIN_VALUE, maxX2=Long.MIN_VALUE, minX1=Long.MAX_VALUE, minX2=Long.MAX_VALUE;
        truncateHMatrixDB();
        truncateHashList();
        truncatediunitRange();

        ArrayList<String> rangeforX12s = new ArrayList<String>();
        int nLoops = 1;
        boolean doBench = false;
        boolean worstcase = false;
        int zkpIterations = 50;

        for (int i = 0; i < args.length; ) {
            String arg = args[i++];
            /* Test the worst case cost. i.e. every vector should pass. This is
             * when the verifier spends longest time.
             */
            // Definie the number of iterations that the bound ZKP must have:
            if (arg.length() > 0 && arg.charAt(0) == '-') {
                if (arg.equals("-k")) {
                    try {
                        k = Integer.parseInt(args[i++]);
                    } catch (NumberFormatException e) {
                        k = 512;
                    }
                } else if (arg.equals("-m")) {
                    try {
                        m = Integer.parseInt(args[i++]);
                    } catch (NumberFormatException e) {
                        m = 10;
                    }
                } else if (arg.equals("-n")) {
                    try {
                        n = Integer.parseInt(args[i++]);
                    } catch (NumberFormatException e) {
                        n = 10;
                    }
                } else if (arg.equals("-N")) {
                    try {
                        zkpIterations = Integer.parseInt(args[i++]);
                    } catch (NumberFormatException e) {
                        zkpIterations = 50;
                    }
                } else if (arg.equals("-o")) {
                    try {
                        nLoops = Integer.parseInt(args[i++]);
                    } catch (NumberFormatException e) {
                        nLoops = 10;
                    }
                } else if (arg.equals("-l")) {
                    try {
                        l = Integer.parseInt(args[i++]);
                    } catch (NumberFormatException e) {
                        l = 40;
                    }
                } else if (arg.equals("-d")) {
                    debug = true;
                } else if (arg.equals("-w")) {
                    worstcase = true;
                } else if (arg.equals("-bench")) {
                    doBench = true;
                }
            }
        }

        System.out.println("k = " + k);
        System.out.println("m = " + m);
        System.out.println("n = " + n);
        System.out.println("nLoops = " + nLoops);

        // Setup the parameters:
        P4PParameters.initialize(k, false);
        SecureRandom rand = null;
        try {
            rand = SecureRandom.getInstance("SHA1PRNG");
        } catch (java.security.NoSuchAlgorithmException e) {
            System.err.println("NoSuchAlgorithmException!");
            e.printStackTrace();
            rand = new SecureRandom();
        }

        rand.nextBoolean();

        long L = ((long) 2) << l - 1;
        L = 1099511627776L;
        long F = BigInteger.probablePrime(Math.min(l + 30, 62), rand).longValue();
        F = 3871393383742833983L;
        // Make the field size to be 10 bits larger than l

        // Or just make F 62 bits? Note that we can't use 64 bit since there is no
        // unsigned long in java.
        //F = BigInteger.probablePrime(62, rand).longValue();

        int N = zkpIterations;
        System.out.println("l = " + l + ", L = " + L);
        System.out.println("F = " + F);
        System.out.println("zkpIterations = " + zkpIterations);
        int num_of_element = 12;
        // Generate the data and the checksum coefficient vector:
        try {
            String fileName = ""+args[0];
            File myObj = new File(fileName);
            Path path = Paths.get(fileName);
            long dataLineNum = 0;
            System.out.println("sqWidth:"+sqWidth(dataLineNum));
            try {
                dataLineNum = Files.lines(path).skip(1).count();
                num_of_element = Math.toIntExact(dataLineNum);
            } catch (IOException e) {
                System.out.println("error of read line");
            }
            System.out.println("dataLineNum: " + dataLineNum);
            Scanner myReader = new Scanner(myObj);
            long lineNum = 0;
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String rangeforX1="", rangeforX2="";
                String strdata = myReader.nextLine();
                String[] arrOfStr = strdata.split(",");
                long[] data = new long[m];
//                char[] charArray = strdata.toCharArray();
                for (int did = 0; did < m; did++) {
                    double a_double = Double.parseDouble(arrOfStr[did]);
                    long a = (long) a_double;
                    data[did] = a;
                    if(did == 0){
                        if(a > maxX1){
                            maxX1=a;
                        }
                        if(a < minX1){
                            minX1=a;
                        }
                        rangeforX1 = findRange("X1",a);
                    }
                    if(did == 1){
                        if(a > maxX2){
                            maxX2=a;
                        }
                        if(a < minX2){
                            minX2=a;
                        }
                        rangeforX2 = findRange("X2",a);
                    }
                }
                rangeforX12s.add(rangeforX1+"--"+rangeforX2);
                insertDiwithUnitRange(rangeforX1+"--"+rangeforX2,data);
                System.out.println(data);

//                int[][] c = new int[zkpIterations][];
                NativeBigInteger[] bi = P4PParameters.getGenerators(2);
//                g = bi[0];
//                h = bi[1];

                g = new NativeBigInteger("3459276026518079674568408512735917085876933054878224377582397778495423201743627684916338757642004215208935956214764216182555928533733818616652879775932081");
                h = new NativeBigInteger("1815409602493030510804268646246184547552449386433387561905816534248675443892847368541434018303659631380097127756952567150690215332149993674119991116919571");

//                System.out.println("g:");
//                System.out.println(g);
//                System.out.println("g.signum()"+g.signum());
//
//                System.out.println("g.getMagnitude()"+g.signum());
//                System.out.println("h.signum()"+g.signum());
//                System.out.println("h:");
//                System.out.println(h);

                P4PServer server = new P4PServer(m, F, l, zkpIterations, g, h);
                P4PPeer peer = new P4PPeer(m, F, l, zkpIterations, g, h);
                long[] s = new long[m];
                long[] v = new long[m];

                StopWatch proverWatch = new StopWatch();
                StopWatch verifierWatch = new StopWatch();
                double delta = 1.5;
                int nfails = 0;

                for (int kk = 0; kk < nLoops; kk++) {
                    int nQulaifiedUsers = 0;
                    boolean passed = true;
                    server.init(); // Must clear old states and data
                    server.generateChallengeVectors();
                    for (int i = 0; i < m; i++) {
                        s[i] = 0;
                        v[i] = 0;
                    }
// for each user
                    for (int i = 0; i < n; i++) {
                        long start = System.currentTimeMillis();
                        long innerProductTime = 0;
                        long randChallengeTime = 0;
                        boolean shouldPass;
                        // We should create a vector that passes the zkp
                        if (worstcase) shouldPass = true;     // Test the worst case
                        else shouldPass = rand.nextBoolean();
                        //System.out.println("Loop " + kk + ", user " + i + ". shouldPass = " + shouldPass);
                        if (shouldPass) delta = 0.5;
                        else delta = 2.0;
                        double l2 = (double) L * delta;

// 1️⃣. Generate Data & 🌛UserVector2
//                    data = Util.randVector(m, F, l2);
                        UserVector2 uv = new UserVector2(data, F, l, g, h);
// UserVector2(data, F, l, g, h)
// 2️⃣. Shares: u, v
                        uv.generateShares();
// 2.1 Set checkCoVector through server challenge_vector for each user 🐢
                        uv.setChecksumCoefficientVectors(server.getChallengeVectors());

// 🌟 prover start 🌟
                        proverWatch.start();
// peerProof, serverProof construct() 🐰 SquareSum.add()
                        UserVector2.L2NormBoundProof2 peerProof =
                                (UserVector2.L2NormBoundProof2) uv.getL2NormBoundProof2(false);
                        UserVector2.L2NormBoundProof2 serverProof =
                                (UserVector2.L2NormBoundProof2) uv.getL2NormBoundProof2(true);
                        proverWatch.pause();
// 🌟 prover pause 🌟

// 2.2 server.setUV(U),
                        server.setUserVector(i, uv.getU());
// 2.3 server.setProof
                        server.setProof(i, serverProof);
                        HttpPost request = new HttpPost("http://localhost:8080/api/v1/server/uiandproof");
                        uv.setForServer(true);
                        System.out.println("isForServer: "+serverProof.isForServer());
                        UserVector2.L2NormBoundProof2 newServerProof = serverProof;
                        System.out.println("newServerProof.tcProof"+newServerProof.getThreeWayCommitmentProofs());
                        UiandProof uiandProof = new UiandProof(userid, uv.getU(), peerProof.getChecksumRandomness(), newServerProof);

                        ObjectMapper mapper = new ObjectMapper();
                        mapper.registerSubtypes(ThreeWayCommitment.ThreeWayCommitmentProof.class, Proof.class, BitCommitment.BitCommitmentProof.class, SquareCommitment.SquareCommitmentProof.class);
                        mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
                        StringEntity json = new StringEntity(mapper.writeValueAsString(uiandProof), ContentType.APPLICATION_JSON);
                        request.setEntity(json);
                        CloseableHttpResponse response = httpClient.execute(request);
                        if(response.getStatusLine().getStatusCode() != 200){
                            System.out.println("Student is not added! "+response.getStatusLine().getStatusCode() );
                        }


                        HttpPost request_viProof = new HttpPost("http://localhost:9002/api/v1/peer/viandproof");
                        uv.setForServer(false); // since Proof is a self-circuited class
                        System.out.println("peer isForServer: "+serverProof.isForServer());
                        ViandProof viandProof = new ViandProof(userid, uv.getV(), peerProof);
                        StringEntity vi_json = new StringEntity(mapper.writeValueAsString(viandProof), ContentType.APPLICATION_JSON);
                        request_viProof.setEntity(vi_json);
                        CloseableHttpResponse response_viProof = httpClient.execute(request_viProof);
                        if(response_viProof.getStatusLine().getStatusCode() != 200){
                            System.out.println("viProof is not sent! "+response_viProof.getStatusLine().getStatusCode() );
                        }

//  3️⃣. The peer:
                        long[] vv = uv.getV();
                        persistViwithColRow(lineNum, vv , dataLineNum);
//  3️⃣.1 The peer: setV(); // 🌛 UserVector2(m, F, l, g, h)
                        UserVector2 pv = new UserVector2(m, F, l, g, h);
                        pv.setV(vv);

// 3.2 set CheCoVectors through server.ChallVector for Each User 🐢
                        pv.setChecksumCoefficientVectors(server.getChallengeVectors());
//                        verifierWatch.start();
//
//// 3.2 setChecksumCoefficientVectors through server Challenge_Vector for Each User
//                        boolean peerPassed = pv.verify2(peerProof); // 🌟 verify2 🌟
//                        verifierWatch.pause();
//
//
//// 4️⃣ !peerPassed  disqualifyUser(i),
//// server.setY():  commitments to the peer's share of the checksums, and forward to server
//// verify the proof,  // peerVerification returns true
//                        if (!peerPassed)
//                            server.disqualifyUser(i);
//                        else
//                            server.setY(i, pv.getY());
                        /**
                         * Note that peer's verification simply computes some
                         * commitments the peer's shares of the checksums (i.e. the
                         * Y's) which should be forwarded to the server. We simulate
                         * this by the server.setY call. The server then use them to
                         * verify the proof. This is where the real verification
                         * happens. The peer's verification actually always returns
                         * true.
                         */

                        // 5️⃣ l2 < L = shouldPass
//                        shouldPass = l2 < L;   // Correct shouldPass using actual data.
//                        shouldPass = true;
//                        if (shouldPass) {
//                            nQulaifiedUsers++;
//                            Util.vectorAdd(s, data, s, F);
//                            Util.vectorAdd(v, vv, v, F);
//                        }
                    }

// 6️⃣ server prepare to verify
// server.setPeerSum()
// server.compute()
//                    peer.setPeerSum(v);
//                    verifierWatch.start();
//                    server.compute(peer);          // 🌟 serverVerify 🐢🌟
//                    verifierWatch.pause();
//
//// 7️⃣ VectorSum()
//                    long[] result = server.getVectorSum();
//                    for (int ii = 0; ii < m; ii++) {
//// 7.1 res[ii] != Util.mod(s[ii], F)
//                        if (result[ii] != Util.mod(s[ii], F)) {
//                            System.out.println("\tElement " + ii
//                                    + " don't agree. Computed: "
//                                    + result[ii] + ", should be "
//                                    + Util.mod(s[ii], F));
//                            passed = false;
//                            nfails++;
//                            break;
//                        }
//                    }
//                    if (passed)
//                        System.out.println("Test " + kk + " passed. Number of qualified users "
//                                + " should be " + nQulaifiedUsers + ". Server reported "
//                                + server.getNQulaifiedUsers());
//                    else
//                        System.out.println("Test " + kk + " failed. Number of qualified users should be "
//                                + nQulaifiedUsers + ". Server reported "
//                                + server.getNQulaifiedUsers());
//
                }
//
//                verifierWatch.stop();
//                proverWatch.stop();
//                long end = System.currentTimeMillis();
//
//                System.out.println("Total tests run: " + nLoops + ". Failed: " + nfails);
//                System.out.println("\n  Prover time            Verifier time           Total");
//                System.out.println("============================================================");
//                System.out.println("    " + (double) proverWatch.getElapsedTime() / nLoops
//                        + "                 "
//                        + (double) verifierWatch.getElapsedTime() / nLoops
//                        + "              "
//                        + ((double) (proverWatch.getElapsedTime()
//                        + verifierWatch.getElapsedTime())) / nLoops);
//                System.out.println("Note that the time is for all " + n + " users in ms.");
//                System.out.println("Also note that the prover needs to compute proofs"
//                        + " for both the server and the privacy peer.");
                lineNum++;
            }
            myReader.close();

            persistHashColRow(allViList, userid);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
            HttpPost request_finishVi = new HttpPost("http://localhost:9002/api/v1/peer/finishvi");
            StringEntity finishVi_json = null;
            finishVi_json = new StringEntity(mapper.writeValueAsString(new PersonCount(num_of_element, userid)), ContentType.APPLICATION_JSON);
            request_finishVi.setEntity(finishVi_json);
            CloseableHttpResponse response_finishVi = httpClient.execute(request_finishVi);
            if(response_finishVi.getStatusLine().getStatusCode() != 200){
                System.out.println("finishVi is not sent! "+response_finishVi.getStatusLine().getStatusCode() );
            }


            HttpPost request_BoundforGauss = new HttpPost("http://localhost:8080/api/v1/server/addGaussParamsSampleRange");
            StringEntity boundforGauss_json = new StringEntity(mapper.writeValueAsString(new BoundforGauss(userid,maxX1,maxX2,minX1,minX2, gauss_params, rangeforX12s)), ContentType.APPLICATION_JSON);
            request_BoundforGauss.setEntity(boundforGauss_json);
            CloseableHttpResponse response_boundforGauss = httpClient.execute(request_BoundforGauss);
            System.out.println("response_boundforGauss.getEntity()");
            System.out.println(response_boundforGauss.getEntity());
            if(response_boundforGauss.getStatusLine().getStatusCode() != 200){
                System.out.println("boundforGauss is not sent! "+response_boundforGauss.getStatusLine().getStatusCode() );
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            finishVi_json = new StringEntity(mapper.writeValueAsString(new PersonCount(num_of_element, userid)), ContentType.APPLICATION_JSON);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        request_finishVi.setEntity(finishVi_json);
//        CloseableHttpResponse response_viProof = null;
//        try {
//            response_viProof = httpClient.execute(request_finishVi);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if(response_viProof.getStatusLine().getStatusCode() != 200){
//            System.out.println("finishVi is not sent! "+response_viProof.getStatusLine().getStatusCode() );
//        }
    }


}