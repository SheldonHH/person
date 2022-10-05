package com.example.demo.model;

import com.example.demo.p4p.crypto.ThreeWayCommitment;
import com.example.demo.p4p.user.UserVector2;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.UUID;

public class UiandProof {
    private UUID userid;
    private long[] ui;
    private BigInteger[] Y;
    private UserVector2.L2NormBoundProof2 serverProof;
    private String batch_timestamp;
    //    private ThreeWayCommitment.ThreeWayCommitmentProof[] tcProof;
    @JsonCreator
    public UiandProof(@JsonProperty("userid") UUID userid, @JsonProperty("ui") long[] ui, @JsonProperty("Y") BigInteger[] Y,@JsonProperty("serverProof")  UserVector2.L2NormBoundProof2 serverProof, @JsonProperty("batch_timestamp") String batch_timestamp) {
        this.userid = userid;
        this.ui = ui;
        this.Y = Y;
        this.serverProof = serverProof;
        this.batch_timestamp = batch_timestamp;
//        this.tcProof = tcProof;
    }

    public String getBatch_timestamp() {
        return batch_timestamp;
    }

    public void setBatch_timestamp(String batch_timestamp) {
        this.batch_timestamp = batch_timestamp;
    }
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
