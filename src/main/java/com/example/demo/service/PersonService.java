package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.vmatrixhash.P_VifromSQMatrix;
import com.example.demo.model.Person;
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
    public String checkFinal(String uuid_str, String batch_time){return personDao.checkFinal(uuid_str, batch_time);}

    public int finalP4P(byte[] digitalSignature){ return personDao.finalP4P(digitalSignature);}
    public int requestVifromSQMatrix(P_VifromSQMatrix p_vifromSQMatrix) { return personDao.requestVifromSQMatrix(p_vifromSQMatrix);}
    public int requestSumandCountforUnit(String requestedUnitRange){ return personDao.requestSumandCountforUnit(requestedUnitRange);}
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