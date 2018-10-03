package com.derkach.boot.hello_back_end;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.derkach.boot.hello_back_end.contacts.Contact;
import com.derkach.boot.hello_back_end.repository.ContactsRepository;

@RunWith(SpringRunner.class)
//@SqlGroup({
//    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
//    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
//
//          })
@DataJpaTest
public class SpringDataTest {

//	@Autowired
//	private TestEntityManager entityManager;

	@Autowired
	private ContactsRepository contactsRepository;
	
	


	@Test
	public final void findAll() throws IOException, ParseException {
		assertEquals(contactsRepository.findAll().size(), 520000);
	}
	
	
	@Test
	public final void deleteAll() throws IOException, ParseException {
		contactsRepository.deleteAll();
		assertEquals(contactsRepository.findAll().size(), 0);
	}
	
	@Test
	public final void store() {
		Contact contact = contactsRepository.save(new Contact("Smith"));
		assertThat(contact).hasFieldOrPropertyWithValue("name", "Smith");
	}
}
