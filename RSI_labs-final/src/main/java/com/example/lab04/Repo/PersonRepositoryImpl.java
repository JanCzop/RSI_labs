package com.example.lab04.Repo;

import com.example.lab04.Exceptions.PersonExistsEx;
import com.example.lab04.Exceptions.PersonNotFoundEx;
import com.example.lab04.Exceptions.StatusEx;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository{

    private List<Person> personList;

    public PersonRepositoryImpl() {
        personList = new ArrayList<>();
        personList.add(new Person(1, "John", 30, Person.PersonStatus.NOT_HIRED));
        personList.add(new Person(2, "Alice", 25, Person.PersonStatus.HIRED));
        personList.add(new Person(3, "Bob", 40, Person.PersonStatus.NOT_HIRED));
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
    public Person updatePerson(int id, Person person) throws PersonNotFoundEx, PersonExistsEx {
        boolean exists = false;
        int searched_id = 0;
        for (int i = 0; i < personList.size(); i++) {
            if(personList.get(i).getId() == person.getId() && id!=person.getId())
                throw new PersonExistsEx("Cannot update: " + person.getId() + " - there is somebody with this id");
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
    public Person addPerson(Person person) throws PersonExistsEx {
        for(Person p :personList){
            if(p.getId() == person.getId()) throw new PersonExistsEx("Cannot add: " + person.getId() + " - there is somebody with this id");
        }
        personList.add(person);
        return person;
    }

    @Override
    public int countPersons() {
        return personList.size();
    }

    @Override
    public Person hirePerson(int id) throws PersonNotFoundEx, StatusEx{
        for(Person p:personList){
            if(p.getId() == id){
                if (p.getStatus() == Person.PersonStatus.NOT_HIRED) {
                    p.setStatus(Person.PersonStatus.HIRED);
                    return p;
                }
                else throw new StatusEx(p.getStatus());
            }
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person firePerson(int id) throws PersonNotFoundEx, StatusEx{
        for(Person p:personList){
            if(p.getId() == id){
                if (p.getStatus() == Person.PersonStatus.HIRED) {
                    p.setStatus(Person.PersonStatus.NOT_HIRED);
                    return p;
                }
                else throw new StatusEx(p.getStatus());
            }
        }
        throw new PersonNotFoundEx(id);
    }
}
