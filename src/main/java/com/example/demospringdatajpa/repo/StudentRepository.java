package com.example.demospringdatajpa.repo;

import com.example.demospringdatajpa.domain.Student;
import com.example.demospringdatajpa.repo.projection.StudentProjectionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findAllByAge(int age);

    List<StudentProjectionDto> findStudentsByAge(int age);
}
