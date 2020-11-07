package com.example.demospringdatajpa;

import com.example.demospringdatajpa.domain.Department;
import com.example.demospringdatajpa.domain.Student;
import com.example.demospringdatajpa.model.Gender;
import com.example.demospringdatajpa.model.Nationality;
import com.example.demospringdatajpa.repo.StudentRepository;
import com.example.demospringdatajpa.repo.projection.StudentProjectionDto;
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
        final var department = create_test_data(age);

        testEntityManager.persistAndFlush(department);

        // WHEN
        final var studentsWithAge20 = studentRepository.findAllByAge(age);

        // THEN
        SQLStatementCountValidator.assertInsertCount(2);
        SQLStatementCountValidator.assertSelectCount(1);
        Assertions.assertThat(studentsWithAge20).hasSize(1);
    }

    @Test
    public void get_data_using_projections_read_only() {
        // GIVEN
        final var age = 20;
        final var department = create_test_data(age);

        testEntityManager.persistAndFlush(department);

        // WHEN
        final var studentsWithAge20 = studentRepository.findStudentsByAge(20);

        // THEN
        SQLStatementCountValidator.assertInsertCount(2);
        SQLStatementCountValidator.assertSelectCount(1);
        Assertions.assertThat(studentsWithAge20).extracting(StudentProjectionDto::getName).containsOnly("Sagar");
        Assertions.assertThat(studentsWithAge20).hasSize(1);
    }

    @Test
    public void get_auditing_data() {
        // GIVEN
        final var age = 20;
        final var department = create_test_data(age);

        testEntityManager.persistAndFlush(department);

        // WHEN
        final var students = studentRepository.findAll();

        students.forEach(System.out::println);

        // THEN
        SQLStatementCountValidator.assertInsertCount(2);
        SQLStatementCountValidator.assertSelectCount(1);
        Assertions.assertThat(students).extracting(Student::getDateCreated).doesNotContainNull();
        Assertions.assertThat(students).extracting(Student::getLastUpdated).doesNotContainNull();
        Assertions.assertThat(students).hasSize(1);
    }

    private Department create_test_data(int age) {
        final var department = new Department().setName("Science");
        final var student = new Student().setAge(age)
                .setName("Sagar")
                .setNationality(Nationality.INDIAN)
                .setGender(Gender.MALE);
        department.addStudent(student);
        return department;
    }
}