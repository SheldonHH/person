package com.example.demo.p4p.user;

import com.example.demo.p4p.crypto.BitCommitment;
import com.example.demo.p4p.crypto.SquareCommitment;
import com.example.demo.p4p.crypto.ThreeWayCommitment;

public class JointCommitments {
    public BitCommitment[] bcs;
    public ThreeWayCommitment[] tcs;
    public SquareCommitment[] scs;

    public JointCommitments(BitCommitment[] bcs, ThreeWayCommitment[] tcs, SquareCommitment[] scs) {
        this.bcs = bcs;
        this.tcs = tcs;
        this.scs = scs;
    }

    public BitCommitment[] getBcs() {
        return bcs;
    }

    public void setBcs(BitCommitment[] bcs) {
        this.bcs = bcs;
    }

    public ThreeWayCommitment[] getTcs() {
        return tcs;
    }

    public void setTcs(ThreeWayCommitment[] tcs) {
        this.tcs = tcs;
    }

    public SquareCommitment[] getScs() {
        return scs;
    }

    public void setScs(SquareCommitment[] scs) {
        this.scs = scs;
    }
}
