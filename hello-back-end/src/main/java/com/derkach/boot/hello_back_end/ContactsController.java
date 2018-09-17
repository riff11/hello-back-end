package com.derkach.boot.hello_back_end;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.derkach.boot.hello_back_end.repository.ContactsRepository;

@RestController
public class ContactsController {

//	private static final String template = "Hello, %s!";
	@Autowired
	private ContactsRepository contactsRepository;

	@RequestMapping("/hello/contacts")
	public List<Contacts> filter(@RequestParam(value = "nameFilter", defaultValue = "World") String regexp) {
		
		return contactsRepository.findAll().stream().filter(contact -> {return !contact.getName().matches(regexp); }).collect(Collectors.toList());
	}
}
