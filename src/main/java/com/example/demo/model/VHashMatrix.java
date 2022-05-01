package com.example.demo.model;

import java.util.UUID;

public class VHashMatrix {
    UUID userid;
    long[][]vi;
    RowColHash rowColHash;

    public VHashMatrix(UUID userid, long[][] vi, RowColHash rowColHash) {
        this.userid = userid;
        this.vi = vi;
        this.rowColHash = rowColHash;
    }
}
