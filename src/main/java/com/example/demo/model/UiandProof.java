package com.example.demo.model;

import com.example.demo.p4p.crypto.BitCommitment;
import com.example.demo.p4p.crypto.ThreeWayCommitment;
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
    private String bcjson_str;
//    private BitCommitment.BitCommitmentProof bcList;
//    private ThreeWayCommitment.ThreeWayCommitmentProof tcList;
    //    private ThreeWayCommitment.ThreeWayCommitmentProof[] tcProof;
    @JsonCreator
    public UiandProof(@JsonProperty("userid") UUID userid, @JsonProperty("ui") long[] ui, @JsonProperty("Y") BigInteger[] Y, @JsonProperty("serverProof")  UserVector2.L2NormBoundProof2 serverProof,
                    @JsonProperty("bcjson_str") String bcjson_str) {
        this.userid = userid;
        this.ui = ui;
        this.Y = Y;
        this.serverProof = serverProof;
        this.bcjson_str = bcjson_str;
//        this.bcList = bcList;
//        this.tcProof = tcProof;
    }


//    public BitCommitment.BitCommitmentProof getBcList() {
//        return bcList;
//    }
//
//    public void setBcList(BitCommitment.BitCommitmentProof bcList) {
//        this.bcList = bcList;
//    }
//    public ThreeWayCommitment.ThreeWayCommitmentProof[] getTcProof() {
//        return tcProof;
//    }

//    public void setTcProof(ThreeWayCommitment.ThreeWayCommitmentProof[] tcProof) {
//        this.tcProof = tcProof;
//    }

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
