package com.example.demo.dao;

import com.example.demo.model.Person;
import com.example.demo.model.RCVisTuple;
import com.example.demo.model.RowColHash;
import com.example.demo.p4p.user.UserVector2;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// dependency injection
public interface PeerDao {
    int insertViandProof(UUID userid, UUID id, long[] vi, UserVector2.L2NormBoundProof2 peerProof);
    int insertVHashMatrix(UUID id, long[][]vi, RowColHash rowColHash);
    int hashVerifywithReceiveRquestRCVisTuple(UUID uid, RCVisTuple rcVisTuple);
//    default int insertVi(Person person){
//        UUID id = UUID.randomUUID();
//        return insertVi(id, person);
//    }
    // return zero or one depends on whether data is persisted

    long sumVi(Person person);

//    Optional<long[]> selectPeerVById(UUID id);

//    int deletePersonById(UUID id);
//
//    int updatePersonById(UUID id, Person person);


}
