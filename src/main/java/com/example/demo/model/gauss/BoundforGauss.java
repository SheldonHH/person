package com.example.demo.model.gauss;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class BoundforGauss {
    UUID user_id;
    public long maxX1;
    public long maxX2;
    public long minX1;
    public long minX2;
    String gaussian_params;
    ArrayList<String> availGaussUnits;
    String batch_time;

    public BoundforGauss(@JsonProperty("user_id") UUID user_id, @JsonProperty("maxX1") long maxX1, @JsonProperty("maxX2") long maxX2, @JsonProperty("minX1") long minX1, @JsonProperty("minX2") long minX2, @JsonProperty("gaussian_params") String gaussian_params, @JsonProperty("availGaussUnits") ArrayList<String> availGaussUnits, @JsonProperty("batch_time") String batch_time) {
        this.user_id = user_id;
        this.maxX1 = maxX1;
        this.maxX2 = maxX2;
        this.minX1 = minX1;
        this.minX2 = minX2;
        this.gaussian_params = gaussian_params;
        this.availGaussUnits = availGaussUnits;
        this.batch_time = batch_time;
    }

    public String getBatch_time() {
        return batch_time;
    }

    public void setBatch_time(String batch_time) {
        this.batch_time = batch_time;
    }

    public ArrayList<String> getAvailGaussUnits() {
        return availGaussUnits;
    }

    public void setAvailGaussUnits(ArrayList<String> availGaussUnits) {
        this.availGaussUnits = availGaussUnits;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getGaussian_params() {
        return gaussian_params;
    }

    public void setGaussian_params(String gaussian_params) {
        this.gaussian_params = gaussian_params;
    }

    public long getMaxX1() {
        return maxX1;
    }

    public void setMaxX1(long maxX1) {
        this.maxX1 = maxX1;
    }

    public long getMaxX2() {
        return maxX2;
    }

    public void setMaxX2(long maxX2) {
        this.maxX2 = maxX2;
    }

    public long getMinX1() {
        return minX1;
    }

    public void setMinX1(long minX1) {
        this.minX1 = minX1;
    }

    public long getMinX2() {
        return minX2;
    }

    public void setMinX2(long minX2) {
        this.minX2 = minX2;
    }
}
