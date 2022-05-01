package com.example.demo.model;

import java.util.UUID;

public class VSum
{
    UUID peerID;
    long[] v_sum;

    public VSum(UUID peerID, long[] v_sum) {
        this.peerID = peerID;
        this.v_sum = v_sum;
    }
}
