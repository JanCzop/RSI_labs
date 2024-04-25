package com.example.lab04.Repo;

public class Person {
    private int id;
    private String name;
    private int age;

    private PersonStatus status;

    public enum PersonStatus{
        NOT_HIRED,
        HIRED
    }


    public Person(int id, String name, int age, PersonStatus status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public PersonStatus getStatus() {
        return status;
    }

    public void setStatus(PersonStatus status) {
        this.status = status;
    }
}
