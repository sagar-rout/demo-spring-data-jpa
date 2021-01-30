package com.example.demospringdatajpa.domain;

import com.example.demospringdatajpa.model.Gender;
import com.example.demospringdatajpa.model.Nationality;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private UUID id;

	private String name;
	private int age;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private Nationality nationality;

	@ManyToOne
	private Department department;

	@CreationTimestamp
	private ZonedDateTime dateCreated;

	@UpdateTimestamp
	private ZonedDateTime lastUpdated;

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

	public Gender getGender() {
		return gender;
	}

	public Student setGender(Gender gender) {
		this.gender = gender;
		return this;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public Student setNationality(Nationality nationality) {
		this.nationality = nationality;
		return this;
	}

	public Department getDepartment() {
		return department;
	}

	public Student setDepartment(Department department) {
		this.department = department;
		return this;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public Student setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
		return this;
	}

	public ZonedDateTime getLastUpdated() {
		return lastUpdated;
	}

	public Student setLastUpdated(ZonedDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
		return this;
	}

	@Override
	public String toString() {
		return "Student{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", gender=" + gender
				+ ", nationality=" + nationality + ", dateCreated=" + dateCreated + ", lastUpdated=" + lastUpdated
				+ '}';
	}
}