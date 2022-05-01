package com.example.demo.model;

import com.example.demo.p4p.user.UserVector2;

import java.util.UUID;

public class ViandProof {
    private UUID userid;
    private long[] vi;
    private UserVector2.L2NormBoundProof2 peerProof;

    public ViandProof(UUID userid, long[] vi, UserVector2.L2NormBoundProof2 peerProof) {
        this.userid = userid;
        this.vi = vi;
        this.peerProof = peerProof;
    }
}
