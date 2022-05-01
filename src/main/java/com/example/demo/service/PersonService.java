package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import com.example.demo.model.RCVisTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao){
        this.personDao = personDao;
    }
    public int addPerson(Person person){
        return  personDao.insertPerson(person);
    }

    public RCVisTuple requestVifromSQMatrix(int row, int col){ return personDao.requestVifromSQMatrix(row, col);}
    public long[] requestSumandCountforUnit(int x1, int x2){ return personDao.requestSumandCountforUnit(x1, x2);}
    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }

}