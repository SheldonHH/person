package com.example.demo.service;

import com.example.demo.dao.PeerDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.dao.ServerDao;
import com.example.demo.model.Person;
import com.example.demo.model.RowColHash;
import com.example.demo.p4p.user.UserVector2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServerService {
    private final ServerDao serverDao;

    @Autowired
    public ServerService( @Qualifier("postgres0") ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    public int insertUiandProof(UUID userid, UUID id, long[] ui, UserVector2.L2NormBoundProof2 serverProof) {
        return serverDao.insertUiandProof(userid, id, ui, serverProof);
    }

    public int insertGaussParamsandSampleRange( UUID userid, UUID id, long[] gaussian_params, long[][]xy){
        return serverDao.insertGaussParamsandSampleRange(userid, id, gaussian_params, xy);
    }

    public int insertVSum( UUID peerID, long[] v_sum){
        return serverDao.insertVSum(peerID, v_sum);
    }

    public int sendRquestSampleSumCount(UUID userid,  long[] di_sum, long di_count){
        return serverDao.distanceofReceiveRquestSumCount(userid, di_sum, di_count);
    }

}