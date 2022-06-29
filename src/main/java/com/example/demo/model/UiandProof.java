package com.example.demo.model;

import com.example.demo.p4p.crypto.BitCommitment;
import com.example.demo.p4p.crypto.SquareCommitment;
import com.example.demo.p4p.crypto.ThreeWayCommitment;
import com.example.demo.p4p.user.JointCommitments;
import com.example.demo.p4p.user.UserVector2;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class UiandProof {
    private UUID userid;
    private long[] ui;
    private BigInteger[] Y;
    private UserVector2.L2NormBoundProof2 serverProof;
    private String bcpjson_str;
    private String tcpjson_str;
    private String scpjson_str;
    private String jcsjson_str;
//    private JointCommitments jointCommitments;
//    private BitCommitment[] bcs;
//    private SquareCommitment[] scs;
//    private ThreeWayCommitment[] tcs;
    //    private BitCommitment.BitCommitmentProof bcList;
//    private ThreeWayCommitment.ThreeWayCommitmentProof tcList;
    //    private ThreeWayCommitment.ThreeWayCommitmentProof[] tcProof;
    @JsonCreator
    public UiandProof(@JsonProperty("userid") UUID userid, @JsonProperty("ui") long[] ui, @JsonProperty("Y") BigInteger[] Y, @JsonProperty("serverProof")  UserVector2.L2NormBoundProof2 serverProof,
                      @JsonProperty("bcpjson_str") String bcpjson_str, @JsonProperty("tcpjson_str") String tcpjson_str,
                      @JsonProperty("scpjson_str") String scpjson_str, @JsonProperty("jcsjson_str") String jcsjson_str){
//                      @JsonProperty("jointCommitments") JointCommitments jointCommitments, @JsonProperty("bcs")BitCommitment[] bcs, @JsonProperty("scs")SquareCommitment[] scs, @JsonProperty("tcs")ThreeWayCommitment[]tcs) {
        this.userid = userid;
        this.ui = ui;
        this.Y = Y;
        this.serverProof = serverProof;
        this.bcpjson_str = bcpjson_str;
        this.tcpjson_str = tcpjson_str;
        this.scpjson_str = scpjson_str;
        this.jcsjson_str = jcsjson_str;
//        this.jointCommitments = jointCommitments;
//        this.bcs = bcs;
//        this.tcs = tcs;
//        this.scs = scs;
    }

//    public BitCommitment[] getBcs() {
//        return bcs;
//    }
//
//    public void setBcs(BitCommitment[] bcs) {
//        this.bcs = bcs;
//    }
//
//    public SquareCommitment[] getScs() {
//        return scs;
//    }
//
//    public void setScs(SquareCommitment[] scs) {
//        this.scs = scs;
//    }
//
//    public ThreeWayCommitment[] getTcs() {
//        return tcs;
//    }
//
//    public void setTcs(ThreeWayCommitment[] tcs) {
//        this.tcs = tcs;
//    }

    public String getJcsjson_str() {
        return jcsjson_str;
    }

    public void setJcsjson_str(String jcsjson_str) {
        this.jcsjson_str = jcsjson_str;
    }

//    public JointCommitments getJointCommitments() {
//        return jointCommitments;
//    }
//
//    public void setJointCommitments(JointCommitments jointCommitments) {
//        this.jointCommitments = jointCommitments;
//    }

    public String getBcpjson_str() {
        return bcpjson_str;
    }

    public void setBcpjson_str(String bcpjson_str) {
        this.bcpjson_str = bcpjson_str;
    }

    public String getTcpjson_str() {
        return tcpjson_str;
    }

    public void setTcpjson_str(String tcpjson_str) {
        this.tcpjson_str = tcpjson_str;
    }

    public String getScpjson_str() {
        return scpjson_str;
    }

    public void setScpjson_str(String scpjson_str) {
        this.scpjson_str = scpjson_str;
    }

    public BigInteger[] getY() {
        return Y;
    }

    public void setY(BigInteger[] y) {
        Y = y;
    }

    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    public long[] getUi() {
        return ui;
    }

    public void setUi(long[] ui) {
        this.ui = ui;
    }

    public UserVector2.L2NormBoundProof2 getServerProof() {
        return serverProof;
    }

    public void setServerProof(UserVector2.L2NormBoundProof2 serverProof) {
        this.serverProof = serverProof;
    }
}
