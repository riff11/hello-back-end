package com.derkach.boot.hello_back_end;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.derkach.boot.hello_back_end.contacts.Contact;
import com.derkach.boot.hello_back_end.repository.ContactsRepository;

public class ContactsServiceTest {

	private ContactsRepository contactsRepository;

	private ContactsService contactsService;

	private List<Contact> cList = new ArrayList(500);

	private JSONParser parser = new JSONParser();

	private void fillList(String path) throws IOException, ParseException {
		InputStream inputStream = ContactsServiceTest.class.getResourceAsStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		Object obj = parser.parse(inputStreamReader);

		JSONArray jsonArray = (JSONArray) obj;
		cList.clear();
		for (Object object : jsonArray) {
			cList.add(new Contact((Long) ((JSONObject) object).get("id"), (String) ((JSONObject) object).get("name")));
		}

//		for (Contact object : cList) {
//			System.out.println(object);
//		}
	}

	@Before
	public void setUp() throws Exception {
		fillList("/testContacts");
		contactsRepository = Mockito.mock(ContactsRepository.class);
		contactsService = new ContactsService(contactsRepository);
		when(this.contactsRepository.findAll()).thenReturn(cList);
	}

	@Test
	public final void testGetContacts() throws Exception {

		long i = contactsRepository.findAll().size();
		System.out.println(i);
		assertTrue(contactsService.filter("^C.*$").getContacts().size() == i - 9);

		assertTrue(contactsService.filter("^Mohammad.*$").getContacts().size() == i - 1);

		assertTrue(contactsService.filter("^.*[gif].*$").getContacts().size() == i - 52);
	}

}
