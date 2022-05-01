package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/requestrc") //peerID
    public void requestVifromSQMatrix(@Valid @NonNull @RequestBody UUID peerId, int row, int col){
        personService.requestVifromSQMatrix(row, col);
    }

    @PostMapping("/sumCountforUnit") //serverID
    public void requestSumandCountforUnit(@Valid @NonNull @RequestBody UUID serverID, int x1, int x2){
        personService.requestSumandCountforUnit(x1, x2);
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
