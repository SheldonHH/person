package com.example.demo.model;

import java.util.UUID;

public class GaussParamsandSampleRange {
    UUID userid;
    long[] gaussian_params;
    long[][]xy;

    public GaussParamsandSampleRange(UUID userid, long[] gaussian_params, long[][] xy) {
        this.userid = userid;
        this.gaussian_params = gaussian_params;
        this.xy = xy;
    }
}
