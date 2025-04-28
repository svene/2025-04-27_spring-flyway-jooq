package org.svenehrke.demo.springflywayjooq;

import com.testcontainers.demo.jooq.tables.Person;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.svenehrke.demo.springflywayjooq.domain.PersonDTO;
import org.testcontainers.containers.PostgreSQLContainer;

import org.jooq.impl.DSL;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jooq.Records.mapping;

@JdbcTest
@ContextConfiguration(classes = {TestcontainersConfiguration.class}) // Or @Import(TestcontainersConfiguration.class)
@RequiredArgsConstructor
class DbTest {

	@Autowired
	private PostgreSQLContainer<?> postgresContainer;

	@Autowired
	DataSource dataSource;

	@Test
	void datasource_available() {
		assertThat(dataSource).isNotNull();
		assertThat(dataSource).isNotNull();
	}

	@Test
	void postgrescontainer_available() {
		assertThat(postgresContainer).isNotNull();
	}
	@Test
	void jooq_list() throws SQLException {
		DSLContext jooq = DSL.using(dataSource.getConnection(), SQLDialect.POSTGRES);
		List<PersonDTO> personDTOS = jooq.select(Person.PERSON.ID, Person.PERSON.NAME)
			.from(Person.PERSON)
			.fetch(mapping(PersonDTO::new))
			;
		assertThat(personDTOS.size()).isEqualTo(4);
	}
}
