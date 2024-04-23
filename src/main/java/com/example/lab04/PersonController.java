package com.example.lab04;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private PersonRepository dataRepo = new PersonRepositoryImpl();

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return dataRepo.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable int id) throws PersonNotFoundEx {
        return dataRepo.getPerson(id);
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        return dataRepo.addPerson(person);
    }

    @PutMapping("/persons/{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody Person person) throws PersonNotFoundEx {
        person.setId(id);
        return dataRepo.updatePerson(person);
    }

    @DeleteMapping("/persons/{id}")
    public boolean deletePerson(@PathVariable int id) throws PersonNotFoundEx {
        return dataRepo.deletePerson(id);
    }
}

