package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.model.RCVisTuple;
import com.example.demo.model.RowColHash;
import com.example.demo.p4p.user.UserVector2;
import com.example.demo.service.PeerService;
import com.example.demo.service.PersonService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/peer")
@RestController
public class PeerController {
    private final PeerService peerService;

    public PeerController(PeerService peerService) {
        this.peerService = peerService;
    }


    @PostMapping("/viandproof")
    public void addViandProof(@Valid @NonNull @RequestBody UUID userid, UUID id, long[] vi, UserVector2.L2NormBoundProof2 peerProof){
        peerService.addViandProof(userid, id,  vi,  peerProof);
    }

    @PostMapping("/vhashmatrix")
    public void addVHashMatrix(@Valid @NonNull @RequestBody UUID userid, long[][] v_matrix, RowColHash sg){
        peerService.addVHashMatrix(userid, v_matrix, sg);
    }

    @PostMapping("/rcvituples")
    public void sendRquestRCVisTuple (@Valid @NonNull @RequestBody UUID userid,  RCVisTuple requestedVisTuple){
        peerService.hashVerifywithReceiveRquestRCVisTuple(userid, requestedVisTuple);
    }



}
