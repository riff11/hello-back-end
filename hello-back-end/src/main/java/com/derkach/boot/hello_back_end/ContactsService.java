package com.derkach.boot.hello_back_end;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.derkach.boot.hello_back_end.contacts.Contacts;
import com.derkach.boot.hello_back_end.repository.ContactsRepository;

@Service
public class ContactsService {
	
	@Autowired
	private ContactsRepository contactsRepository;
	
	public Contacts getContacts(String regexp){
		System.out.println(regexp);
		Contacts contacts ;
		return new Contacts(contactsRepository.findAll().stream().filter(contact -> {return !contact.getName().matches(regexp); }).collect(Collectors.toList()));
	}

}
