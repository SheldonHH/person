package com.example.demo.api;

import com.example.demo.model.vmatrixhash.P_VifromSQMatrix;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import uploadingfiles.StorageService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    // localhost:8080/api/v1/person/
    @GetMapping(path = "{id}")
    public  Person getPersonById(UUID id){
        return personService.getPersonById(id)
                .orElse(null);
// Optional: a container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value.
//        Return the value if present, otherwise return other.
    }

    @DeleteMapping(path="{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path="{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull  @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);

    }
}
