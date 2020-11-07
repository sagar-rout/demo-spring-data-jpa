package com.example.demospringdatajpa;

import com.example.demospringdatajpa.model.Department;
import com.example.demospringdatajpa.model.Student;
import com.example.demospringdatajpa.repo.StudentRepository;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(TestDataSourceConfiguration.class)
public class StudentRepositoryIT {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void before() {
        SQLStatementCountValidator.reset();
    }

    @Test
    public void get_all_students() {

        // GIVEN
        final var age = 20;
        final var students = Set.of(new Student().setAge(age).setName("Sagar"));
        final var department = new Department().setName("Science").setStudents(students);

        testEntityManager.persistAndFlush(department);

        // WHEN
        final var studentsWithAge20 = studentRepository.findAllByAge(age);

        // THEN
        SQLStatementCountValidator.assertInsertCount(2);
        SQLStatementCountValidator.assertSelectCount(1);
        Assertions.assertThat(studentsWithAge20).hasSize(1);
    }


}