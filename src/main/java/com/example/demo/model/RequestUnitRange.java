package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RequestUnitRange {
    String requestedUnitRange;
    String batch_time;

    @NotBlank
    public RequestUnitRange(@JsonProperty("requestedUnitRange")String requestedUnitRange, @JsonProperty("batch_time")String batch_time) {
        this.requestedUnitRange = requestedUnitRange;
        this.batch_time = batch_time;
    }

    public String getRequestedUnitRange() {
        return requestedUnitRange;
    }

    public void setRequestedUnitRange(String requestedUnitRange) {
        this.requestedUnitRange = requestedUnitRange;
    }

    public String getBatch_time() {
        return batch_time;
    }

    public void setBatch_time(String batch_time) {
        this.batch_time = batch_time;
    }
}
