package com.example.lab04.Repo;

import com.example.lab04.Exceptions.PersonExistsEx;
import com.example.lab04.Exceptions.PersonNotFoundEx;
import com.example.lab04.Exceptions.StatusEx;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPersons();
    Person getPerson(int id) throws PersonNotFoundEx;
    Person updatePerson(int id, Person person) throws PersonNotFoundEx, PersonExistsEx;
    boolean deletePerson(int id) throws PersonNotFoundEx;
    Person addPerson(Person person) throws PersonExistsEx;
    int countPersons();
    Person hirePerson(int id) throws PersonNotFoundEx, StatusEx;
    Person firePerson(int id) throws PersonNotFoundEx, StatusEx;
}
