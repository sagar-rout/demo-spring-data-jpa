package com.example.demospringdatajpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private int age;

    @ManyToOne
    private Department department;

    public UUID getId() {
        return id;
    }

    public Student setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }
}