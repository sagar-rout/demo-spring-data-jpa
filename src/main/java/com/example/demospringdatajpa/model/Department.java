package com.example.demospringdatajpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Student> students = new HashSet<>();

    private String name;

    public UUID getId() {
        return id;
    }

    public Department setId(UUID id) {
        this.id = id;
        return this;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Department setStudents(Set<Student> students) {
        this.students = students;
        return this;
    }

    public String getName() {
        return name;
    }

    public Department setName(String name) {
        this.name = name;
        return this;
    }
}
