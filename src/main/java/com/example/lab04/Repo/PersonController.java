package com.example.lab04.Repo;

import com.example.lab04.Exceptions.PersonNotFoundEx;
import com.example.lab04.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonRepositoryImpl data = new PersonRepositoryImpl();

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return data.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable int id) throws PersonNotFoundEx {
        return data.getPerson(id);
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        return data.addPerson(person);
    }

    @PutMapping("/persons/{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody Person person) throws PersonNotFoundEx {
        return data.updatePerson(id,person);
    }

    @DeleteMapping("/persons/{id}")
    public boolean deletePerson(@PathVariable int id) throws PersonNotFoundEx {
        return data.deletePerson(id);
    }
}
