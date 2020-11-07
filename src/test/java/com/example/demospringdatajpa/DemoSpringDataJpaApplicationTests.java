package com.example.demospringdatajpa;

import com.example.demospringdatajpa.repo.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoSpringDataJpaApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void contextLoads() {
		Assertions.assertThat(studentRepository).isNotNull();
	}

}
