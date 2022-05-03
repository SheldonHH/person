package com.example.demo.model;

import java.util.ArrayList;

public class ResponseSumCount {
    public ArrayList<Long> diList;
    int count;

    public ResponseSumCount(ArrayList<Long> diList, int count) {
        this.diList = diList;
        this.count = count;
    }

    public ArrayList<Long> getDiList() {
        return diList;
    }

    public void setDiList(ArrayList<Long> diList) {
        this.diList = diList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
