package com.example.lab04.Repo;

import com.example.lab04.Exceptions.PersonExistsEx;
import com.example.lab04.Exceptions.PersonNotFoundEx;
import com.example.lab04.Exceptions.StatusEx;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonController {
    private final PersonRepositoryImpl data = new PersonRepositoryImpl();

    @GetMapping("/persons")
    public CollectionModel<EntityModel<Person>> getAllPersons() {
        try {
            System.out.println("...called GET ALL"); //dla śledzenia działania
            List<EntityModel<Person>> persons = data.getAllPersons()
                    .stream()
                    .map(person -> {
                        List<Link> links = new ArrayList<>();
                        links.add(linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel());

                        if (person.getStatus() == Person.PersonStatus.HIRED) {
                            links.add(linkTo(methodOn(PersonController.class).firePerson(person.getId())).withRel("firePerson"));
                        } else if (person.getStatus() == Person.PersonStatus.NOT_HIRED) {
                            links.add(linkTo(methodOn(PersonController.class).hirePerson(person.getId())).withRel("hirePerson"));
                            links.add(linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("deletePerson"));
                        }
                        else {
                            links.add(linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("deletePerson"));
                        }
                        links.add(linkTo(methodOn(PersonController.class).getAllPersons()).withRel("list all"));

                        return EntityModel.of(person, links);
                    })
                    .collect(Collectors.toList());

            return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).getAllPersons()).withSelfRel());
        } catch (PersonNotFoundEx e) {
            System.out.println("...GET ALL Exception");
            throw e;
        }
    }

    @GetMapping("/persons/{id}")
    public EntityModel<?> getPerson(@PathVariable int id) {
        try {
            System.out.println("...called GET"); //dla śledzenia działania
            Person person = data.getPerson(id);
            if (person.getStatus().equals(Person.PersonStatus.HIRED)) {
                return EntityModel.of(person,
                        linkTo(methodOn(PersonController.class)
                                .getPerson(person.getId())).withSelfRel(),
                        linkTo(methodOn(PersonController.class)
                                .firePerson(person.getId())).withRel("firePerson"),
                        linkTo(methodOn(PersonController.class)
                                .getAllPersons()).withRel("list all") );
            }
            if (person.getStatus().equals(Person.PersonStatus.NOT_HIRED)) {
                return EntityModel.of(person,
                        linkTo(methodOn(PersonController.class)
                                .getPerson(person.getId())).withSelfRel(),
                        linkTo(methodOn(PersonController.class)
                                .getPerson(person.getId())).withRel("hirePerson"),
                        linkTo(methodOn(PersonController.class)
                                .getPerson(person.getId())).withRel("deletePerson"),
                        linkTo(methodOn(PersonController.class)
                                .getAllPersons()).withRel("list all"));
            }
            else {
                return EntityModel.of(person,
                        linkTo(methodOn(PersonController.class)
                                .getPerson(person.getId())).withSelfRel(),
                        linkTo(methodOn(PersonController.class)
                                .deletePerson(person.getId())).withRel("deletePerson"),
                        linkTo(methodOn(PersonController.class)
                                .getAllPersons()).withRel("list all"));
            }
        } catch (PersonNotFoundEx e) {
            System.out.println("...GET Exception");
            throw e;
        }
    }

    @PostMapping("/persons")
    public EntityModel<Person> addPerson(@RequestBody Person person) throws PersonExistsEx {
        try {
            System.out.println("...called ADD"); //dla śledzenia działania
            data.addPerson(person);
            return EntityModel.of(person,
                    linkTo(methodOn(PersonController.class)
                            .getPerson(person.getId())).withSelfRel(),
                    linkTo(methodOn(PersonController.class)
                            .deletePerson(person.getId())).withRel("deletePerson"),
                    linkTo(methodOn(PersonController.class)
                            .getAllPersons()).withRel("list all"));

        } catch (PersonExistsEx e) {
            System.out.println("...ADD Exception");
            throw e;
        }
    }

    @PutMapping("/persons/{id}")
    public EntityModel<?> updatePerson(@PathVariable int id, @RequestBody Person person) {
        try {
            System.out.println("...called PUT"); //dla śledzenia działania
            Person updatedPerson = data.updatePerson(id, person);
            return EntityModel.of(updatedPerson,
                    linkTo(methodOn(PersonController.class)
                            .getPerson(id)).withSelfRel(),
                    linkTo(methodOn(PersonController.class)
                            .deletePerson(id)).withRel("deletePerson"),
                    linkTo(methodOn(PersonController.class)
                            .getAllPersons()).withRel("list all") );

        } catch (PersonNotFoundEx e) {
            System.out.println("...PUT Exception");
            throw e;
        }
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable int id) {
        try {
            System.out.println("...called DELETE"); //dla śledzenia działania
            data.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (PersonNotFoundEx e) {
            System.out.println("...DELETE Exception");
            throw e;
        }
    }

    @PatchMapping("/persons/{id}/hire")
    public EntityModel<?> hirePerson(@PathVariable int id) {
        try {
            System.out.println("...called hirePerson");
            data.hirePerson(id);
            return getPerson(id);
        } catch (StatusEx e) {
            System.out.println("...hirePerson Exception");
            throw e;
        }
    }

    @PatchMapping("/persons/{id}/fire")
    public EntityModel<?> firePerson(@PathVariable int id) {
        try {
            System.out.println("...called firePerson");
            data.firePerson(id);
            return getPerson(id);
        } catch (StatusEx e) {
            System.out.println("...firePerson Exception");
            throw e;
        }
    }

}
