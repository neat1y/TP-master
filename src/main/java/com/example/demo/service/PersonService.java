package com.example.demo.service;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public Optional<Person> findbyName(String name){
        return personRepository.findByUsername(name);
    }

    public List<Person> all() {
        return personRepository.findAll();
    }

    public String findById(Integer id) {
        return personRepository.findById(id).get().getUsername();
    }
    @Transactional
    public void update(int id, Person person) {
        Person pers=personRepository.getById(id);
        pers.setUsername(pers.getUsername());
        pers.setConfines(person.getConfines());
    }
}
