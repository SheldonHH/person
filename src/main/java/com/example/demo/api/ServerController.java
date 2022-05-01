package com.example.demo.api;

import com.example.demo.dao.ServerDataAccessService;
import com.example.demo.model.RCVisTuple;
import com.example.demo.model.RowColHash;
import com.example.demo.p4p.user.UserVector2;
import com.example.demo.service.PeerService;
import com.example.demo.service.ServerService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("api/v1/server")
@RestController
public class ServerController {
    private final ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @PostMapping("/addGaussParamsSampleRange")
    public void addGaussParamsSampleRange(@Valid @NonNull @RequestBody UUID userid, UUID id, long[] gaussian_params, long[][]xy){
        serverService.insertGaussParamsandSampleRange(userid, id,  gaussian_params,  xy);
    }

    @PostMapping("/uiandproof")
    public void addUiandProof(@Valid @NonNull @RequestBody UUID userid, UUID id, long[] ui, UserVector2.L2NormBoundProof2 serverProof){
        serverService.insertUiandProof(userid, id,  ui,  serverProof);
    }

    @PostMapping("/samplesumcount")
    public void sendRquestSampleSumCount (@Valid @NonNull @RequestBody UUID userid,  long[] di_sum, long di_count){
        serverService.sendRquestSampleSumCount(userid, di_sum, di_count);
    }

    @PostMapping("/addvsum") // peerID
    public void addVSum(@Valid @NonNull @RequestBody UUID peerID, long[] v_sum) {
        serverService.insertVSum(peerID, v_sum);
    }

}
