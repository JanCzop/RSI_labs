package com.example.lab04.Repo;

import com.example.lab04.Exceptions.BadRequestEx;
import com.example.lab04.Exceptions.PersonNotFoundEx;
import com.example.lab04.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository{

    private List<Person> personList;

    public PersonRepositoryImpl() {
        personList = new ArrayList<>();
        personList.add(new Person(1, "John", 30));
        personList.add(new Person(2, "Alice", 25));
        personList.add(new Person(3, "Bob", 40));
    }
    @Override
    public List<Person> getAllPersons() {
        return personList;
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundEx {
        for (Person p:personList) {
            if(p.getId() == id) return p;
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person updatePerson(int id, Person person) throws PersonNotFoundEx, BadRequestEx {
        boolean exists = false;
        int searched_id = 0;
        for (int i = 0; i < personList.size(); i++) {
            if(personList.get(i).getId() == person.getId()) throw new BadRequestEx("Cannot update: " + person.getId() + " - there is somebody with this id");
            if(personList.get(i).getId() == id) {
                exists = true;
                searched_id = i;
            }
        }
        if(!exists) throw new PersonNotFoundEx(id);
        else {
            personList.set(searched_id,person);
            return person;
        }
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundEx {
        for(Person p:personList){
            if(p.getId() == id){
                personList.remove(p);
                return true;
            }
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person addPerson(Person person) throws BadRequestEx {
        for(Person p :personList){
            if(p.getId() == person.getId()) throw new BadRequestEx("Cannot add: " + person.getId() + " - there is somebody with this id");
        }
        personList.add(person);
        return person;
    }

    @Override
    public int countPersons() {
        return personList.size();
    }
}
