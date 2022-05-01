package com.example.demo.dao;

import com.example.demo.model.Person;
import com.example.demo.model.RowColHash;
import com.example.demo.p4p.user.UserVector2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("postgres0")
public class ServerDataAccessService implements ServerDao{
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertUiandProof(UUID userid, UUID id, long[] ui, UserVector2.L2NormBoundProof2 serverProof) {
        return 0;
    }

    @Override
    public int insertGaussParamsandSampleRange(UUID userid, UUID id, long[] gaussian_params, long[][] xy) {
        return 0;
    }

    @Override
    public int insertVSum(UUID peerID, long[] v) {
        return 0;
    }

    @Override
    public int distanceofReceiveRquestSumCount(UUID uid, long[] di_sample_sum, long sample_count) {
        return 0;
    }

    @Override
    public long[] sumUi(Person person) {
        return new long[0];
    }

    @Override
    public long[] sumUandV(Person person) {
        return new long[0];
    }
}
