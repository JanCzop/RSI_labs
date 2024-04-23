package com.example.lab04;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {
    private List<Person> personList;

    public PersonRepositoryImpl() {
        // Inicjalizacja listy
        personList = new ArrayList<>();
        // Dodaj kilka początkowych osób
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
        for (Person thePerson : personList) {
            if (thePerson.getId() == id) {
                return thePerson;
            }
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person updatePerson(Person person) throws PersonNotFoundEx {
        int index = -1;
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getId() == person.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            personList.set(index, person);
            return person;
        } else {
            throw new PersonNotFoundEx(person.getId());
        }
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundEx {
        for (Person thePerson : personList) {
            if (thePerson.getId() == id) {
                personList.remove(thePerson);
                return true;
            }
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person addPerson(Person person) throws BadRequestEx {
        for (Person p:personList) {
            if(p.getId() == person.getId()) throw new BadRequestEx("Person with this ID already exists");
        }
        personList.add(person);
        return person;
    }

    @Override
    public int countPersons() {
        return personList.size();
    }

}
