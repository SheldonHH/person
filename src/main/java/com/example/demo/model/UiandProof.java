package com.example.demo.model;

import com.example.demo.p4p.user.UserVector2;

import java.util.UUID;

public class UiandProof {
    private UUID userid;
    private long[] ui;
    private UserVector2.L2NormBoundProof2 serverProof;

    public UiandProof(UUID userid, long[] ui, UserVector2.L2NormBoundProof2 serverProof) {
        this.userid = userid;
        this.ui = ui;
        this.serverProof = serverProof;
    }
}
