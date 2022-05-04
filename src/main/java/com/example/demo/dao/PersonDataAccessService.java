package com.example.demo.dao;

import com.example.demo.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{
    private final String url = "jdbc:postgresql://localhost:5432/client1";
    private final String user = "postgres";
    private final String password = "password";
    UUID userid = UUID.fromString("1fa4fd06-34f0-49a4-baf9-a4073bca0292");
    final static CloseableHttpClient httpClient = HttpClients.createDefault();
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private static List<Person> DB = new ArrayList<>();

//    https://www.postgresqltutorial.com/postgresql-jdbc/insert/
    @Override
    public int insertPerson(UUID person_uuid, Person person){
        String SQL = "INSERT INTO PERSON(person_id,name) "
                + "VALUES(?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setObject (1, person_uuid);
            pstmt.setString(2, person.getName());
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
        return 0;
    }

    @Override
    public int insertPerson(Person person) {
        return PersonDao.super.insertPerson(person);
    }

    @Override
    public int requestVifromSQMatrix(P_VifromSQMatrix p_vifromSQMatrix){
        System.out.println("P_VifromSQMatrix: "+p_vifromSQMatrix.getRow());
        System.out.println("P_VifromSQMatrix: "+p_vifromSQMatrix.getCol());
        int requestRow = p_vifromSQMatrix.getRow();
        int requestCol = p_vifromSQMatrix.getCol();

        ArrayList<String> rowViList = new ArrayList<>();
        ArrayList<String> colViList = new ArrayList<>();

        String rowSQL = "SELECT vi "
                + "FROM VHashMatrix "
                + "WHERE row = ?";
        String colSQL = "SELECT vi "
                + "FROM VHashMatrix "
                + "WHERE col = ?";
        try{
            Connection conn = connect();
            PreparedStatement preparedStatementRow = conn.prepareStatement(rowSQL);
            PreparedStatement preparedStatementCol = conn.prepareStatement(colSQL);
            preparedStatementRow.setString(1, ""+requestRow);
            preparedStatementCol.setString(1, ""+requestCol);
            ResultSet rsRow = preparedStatementRow.executeQuery();
            while(rsRow.next()){
                rowViList = new ArrayList<>( Arrays.asList((String[]) rsRow.getArray("vi").getArray()));
                System.out.println("rowViList:"+rowViList);
            }
//            if (rsRow.next()) {
//                System.out.println(rsRow.getString(1));
//            }
//            rsRow.close();

            ResultSet rsCol = preparedStatementRow.executeQuery();
            while(rsCol.next()){
                colViList = new ArrayList<>(  Arrays.asList((String[]) rsCol.getArray("vi").getArray()));
                System.out.println("colViList:"+colViList);
            }
//            if (rsCol.next()) {
//                System.out.println(rsCol.getString(1));
//            }
//            rsCol.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            HttpPost request = new HttpPost("http://localhost:" +
                    "9001/api/v1/peer/rcvituples");
            RCVisTupleUser rcVisTupleUser = new RCVisTupleUser(userid, new RCVisTuple(rowViList, colViList));
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
            StringEntity json = new StringEntity(mapper.writeValueAsString(rcVisTupleUser), ContentType.APPLICATION_JSON);
            request.setEntity(json);
            CloseableHttpResponse response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() != 200){
                System.out.println("requested rcViTuples not added! "+response.getStatusLine().getStatusCode() );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
//        return null;
    }

    @Override
    public int requestSumandCountforUnit(String requestedUnitRange) {
//        String SQL = "SELECT distinct di "
//                + "FROM DiUnitRange "
//                + "WHERE unitrange = ?;";
        String SQL = "SELECT distinct di "
                + "FROM DiUnitRange "
                + "WHERE unitrange=?";
//        String CountSQL = "SELECT COUNT(*) FROM ( "
//                + "SELECT DISTINCT * "
//                + "FROM DiUnitRange "
//                + "WHERE unitrange = ? )";
        System.out.println("Reach here: "+requestedUnitRange.substring(1,requestedUnitRange.length()-1));
        ArrayList<String> stringList = new ArrayList<String>();
        try{
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, requestedUnitRange.substring(1,requestedUnitRange.length()-1));
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<ArrayList<Long>> TwoDResultList = new ArrayList<ArrayList<Long>>();
            System.out.println("rs.getRow()"+rs.getRow());
            while(rs.next()){
//                stringList = new ArrayList<>( Arrays.asList((String[]) rs.getArray("di").getArray()));
                    System.out.println(Arrays.asList((String[]) rs.getArray("di").getArray()));
////                ArrayList<Long> resultIntList = new ArrayList<Long>();
////                for(String stringValue: stringList) {
////                    try {
////                        //Convert String to long, and store it into long array list.
////                        resultIntList.add(Long.parseLong(stringValue));
////                    } catch(NumberFormatException nfe) {
////                        System.out.println("Could not parse " + nfe);
//////                        Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
////                    }
////                }
////                TwoDResultList.add(resultIntList);
//            }
//            ArrayList<Long> sum = new ArrayList<Long>(TwoDResultList.get(0).size());
//            for(ArrayList<Long> sg: TwoDResultList){
//                for(int i = 0; i < sg.size(); i++){
//                    sum.set(i,sum.get(i)+sg.get(i));
//                }
            }
            rs.close();
//
//            ResultSet rsCount = preparedStatementCount.executeQuery();
//            int countForRequestUnit = 0;
//            while(rsCount.next()){
//                countForRequestUnit = rsCount.getInt(0);
//            }
//            HttpPost request = new HttpPost("http://localhost:" + "8080/api/v1/server/samplesumcount");
//            ResponseSumCount responseSumCount = new ResponseSumCount(sum, countForRequestUnit);
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
//            StringEntity json = new StringEntity(mapper.writeValueAsString(responseSumCount), ContentType.APPLICATION_JSON);
//            request.setEntity(json);
//            CloseableHttpResponse response = httpClient.execute(request);
//            if(response.getStatusLine().getStatusCode() != 200){
//                System.out.println("requested rcViTuples not added! "+response.getStatusLine().getStatusCode() );
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return 0;
    }

    @Override
    public List<Person> selectAllPeople(){
        List<Person> personList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * from PERSON")) {
            while(rs.next()){
                UUID person_id = (UUID) rs.getObject("person_id");
                String username = rs.getString("name");
                personList.add(new Person(person_id,username));
            }
            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personList;
//        return List.of(new Person(UUID.randomUUID(), " SELECT FROM POSTGRES DB"));
    }
//  List.of()  Returns an immutable list containing zero elements.

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person ->person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());

        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id)
                .map(p -> {
                    int indexOfPersonToUpdate = DB.indexOf(update);
                    if(indexOfPersonToUpdate >=0 ){
                        DB.set(indexOfPersonToUpdate, new  Person(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
