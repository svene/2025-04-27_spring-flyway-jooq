package org.svenehrke.demo.springflywayjooq;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@ContextConfiguration(classes = {TestcontainersConfiguration.class})
@RequiredArgsConstructor
class DbTest {

	@Autowired
	private PostgreSQLContainer<?> postgresContainer;

	@Autowired
	DataSource dataSource;

	@Test
	void datasource_available() {
		assertThat(dataSource).isNotNull();
	}

	@Test
	void postgrescontainer_available() {
		assertThat(postgresContainer).isNotNull();
	}
}
