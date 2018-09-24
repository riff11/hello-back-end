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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.derkach.boot.hello_back_end.contacts.Contact;
import com.derkach.boot.hello_back_end.repository.ContactsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactsServiceTest {

	@MockBean(name = "contactsRepository")
	private ContactsRepository contactsRepository;

	@Autowired
	private ContactsService contactsService;

	private List<Contact> cList = new ArrayList(500);

	private JSONParser parser = new JSONParser();

	private void fillList(String path) throws IOException, ParseException {
		InputStream inputStream = ContactsServiceTest.class.getResourceAsStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		Object obj = parser.parse(inputStreamReader);
//		System.out.println(obj.getClass().getName());

		JSONArray jsonArray = (JSONArray) obj;
		cList.clear();
		for (Object object : jsonArray) {
			cList.add(new Contact((Long) ((JSONObject) object).get("id"), (String) ((JSONObject) object).get("name")));
		}

		for (Contact object : cList) {
			System.out.println(object);
		}		
	}

	@Test
	public final void testGetContacts() throws Exception {
		fillList("/testContacts");	
		when(this.contactsRepository.findAll()).thenReturn(cList);
		long i = contactsRepository.findAll().size();
		System.out.println(i);
		assertTrue(contactsService.filter("^C.*$").getContacts().size() == i-9); 
		
		assertTrue(contactsService.filter("^Mohammad.*$").getContacts().size() == i-1); 
		
		assertTrue(contactsService.filter("^.*[gif].*$").getContacts().size() == i-52); 		
	}

}
