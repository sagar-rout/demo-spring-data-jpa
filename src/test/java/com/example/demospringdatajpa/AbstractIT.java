package com.example.demospringdatajpa;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ContextConfiguration(initializers = { AbstractIT.Initializer.class })
@AutoConfigureTestDatabase(replace = Replace.NONE )
public abstract class AbstractIT {

    @SuppressWarnings("deprecation")
	public static PostgreSQLContainer<?> postgreSqlContainer = new PostgreSQLContainer<>();
	
	static {
		postgreSqlContainer.start();
	}

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			TestPropertyValues
					.of("spring.datasource.url=" + postgreSqlContainer.getJdbcUrl(),
							"spring.datasource.username=" + postgreSqlContainer.getUsername(),
							"spring.datasource.password=" + postgreSqlContainer.getPassword())
					.applyTo(applicationContext.getEnvironment());

		}

	}
}
