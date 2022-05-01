package com.example.demo.dao;

import com.example.demo.model.GaussianUnitTuple;
import com.example.demo.model.Person;
import com.example.demo.model.RCVisTuple;
import com.example.demo.p4p.user.UserVector2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.UUID;

// dependency injection
public interface ServerDao {
    int insertUiandProof(UUID userid, UUID id, long[] ui, UserVector2.L2NormBoundProof2 serverProof);

    int insertGaussParamsandSampleRange(UUID userid, UUID id, long[] gaussian_params, long[][]xy);

    int insertVSum(UUID peerID, long[] v);

    int distanceofReceiveRquestSumCount(UUID uid, long[] di_sample_sum, long sample_count);

//    default int insertVi(Person person){
//        UUID id = UUID.randomUUID();
//        return insertVi(id, person);
//    }
    // return zero or one depends on whether data is persisted

    long[] sumUi(Person person);

    long[] sumUandV(Person person);

//    Optional<long[]> selectPeerVById(UUID id);

//    int deletePersonById(UUID id);
//
//    int updatePersonById(UUID id, Person person);


}
