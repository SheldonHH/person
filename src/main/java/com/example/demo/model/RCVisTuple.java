package com.example.demo.model;

// response to server's request of the specified row and col number
public class RCVisTuple {
    public final long[][] row_vi;
    public final long[][] col_vi;
    public RCVisTuple(long[][] row_vi, long[][] col_vi) {
        this.row_vi = row_vi;
        this.col_vi = col_vi;
    }
}
