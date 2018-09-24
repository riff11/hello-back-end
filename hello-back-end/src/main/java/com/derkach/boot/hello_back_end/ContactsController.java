package com.derkach.boot.hello_back_end;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.derkach.boot.hello_back_end.contacts.Contacts;

@RestController
public class ContactsController {

//	private static final String template = "Hello, %s!";
	@Autowired
	private ContactsService contactsService; 

	
	//^ - %5E
	//$ - %24
	//^.*[a].*$  - nameFilter=%5E.%2A%5Ba%5D.%2A$
	@RequestMapping("/hello/contacts")
	public Contacts filter(@RequestParam(value = "nameFilter") String regexp) {
		System.out.println(regexp);
		return contactsService.getContacts(regexp);
	}
}
