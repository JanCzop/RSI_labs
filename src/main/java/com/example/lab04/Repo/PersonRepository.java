package com.example.lab04.Repo;

import com.example.lab04.Exceptions.BadRequestEx;
import com.example.lab04.Exceptions.PersonNotFoundEx;
import com.example.lab04.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPersons();
    Person getPerson(int id) throws PersonNotFoundEx;
    Person updatePerson(int id, Person person) throws PersonNotFoundEx, BadRequestEx;
    boolean deletePerson(int id) throws PersonNotFoundEx;
    Person addPerson(Person person) throws BadRequestEx;
    int countPersons();
}
