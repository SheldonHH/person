package com.example.demo.api;

import com.example.demo.model.vmatrixhash.P_VifromSQMatrix;
import com.example.demo.model.Person;
import com.example.demo.p4p.sim.P4PSim;
import com.example.demo.service.PersonService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import uploadingfiles.StorageService;

import javax.validation.Valid;
import java.sql.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/st")
    @ResponseBody
    public int stt(){
//        try {
        System.out.println("sg");
//            P4PSim.main(new String[]{"/Users/mac/singapore/person1/src/main/python/data_sample"});
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return 0;
    }


    @GetMapping("{datapath}")
//    @ResponseBody
    public String start(@PathVariable("datapath") String datapath){
        System.out.println(datapath);
//        System.out.println(uuidstr);
        String correct_data_path = datapath.replace("!","/");

        System.out.println(correct_data_path.split("=")[1]);
        System.out.println(correct_data_path.split("=")[3]);
        try {
            System.out.println("israel");
            P4PSim.main(new String[]{correct_data_path.split("=")[1],correct_data_path.split("=")[3]});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return personService.checkFinal(correct_data_path.split("=")[3]);
    }


    @PostMapping("/p4psim") //peerID
    public String p4pSim(@Valid @NonNull @RequestBody String ds){
        String raw_data_path = ds.substring(ds.indexOf("\"",8)+1, ds.length()-2);
        System.out.println(raw_data_path);
        try {
              P4PSim.main(new String[]{raw_data_path});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Signature";
    }


    @PostMapping("/finalP4P") //peerID
    public void finalP4P(@Valid @NonNull @RequestBody byte[] ds){
        personService.finalP4P(ds);
    }

    @PostMapping("/requestrc") //peerID
    public void requestVifromSQMatrix(@Valid @NonNull @RequestBody P_VifromSQMatrix p_vifromSQMatrix){
        personService.requestVifromSQMatrix(p_vifromSQMatrix);
    }

    @PostMapping("/sumCountforUnit") //serverID
    public void requestSumandCountforUnit(@Valid @NonNull @RequestBody String requestedUnitRange){
        personService.requestSumandCountforUnit(requestedUnitRange);
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person){
        personService.addPerson(person);
    }

//    @GetMapping
//    public List<Person> getAllPeople(){
//        return personService.getAllPeople();
//    }

    // localhost:8081/api/v1/person/
//    @GetMapping(path = "{id}")
//    public  Person getPersonById(UUID id){
//        System.out.println(id.toString());
//        return personService.getPersonById(id)
//                .orElse(null);
// Optional: a container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value.
//        Return the value if present, otherwise return other.
//        return null;
//    }

    @DeleteMapping(path="{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path="{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull  @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);

    }
}
