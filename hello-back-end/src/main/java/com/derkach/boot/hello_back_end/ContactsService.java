package com.derkach.boot.hello_back_end;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.derkach.boot.hello_back_end.contacts.Contact;
import com.derkach.boot.hello_back_end.contacts.Contacts;
import com.derkach.boot.hello_back_end.repository.ContactsRepository;

@Service
@CacheConfig(cacheNames = { "contacts", "filter" })
public class ContactsService {

	@Autowired
	private ContactsRepository contactsRepository;
	
	

	public ContactsService(ContactsRepository contactsRepository) {
		this.contactsRepository = contactsRepository;
	}
	
	

	public ContactsService() {
	}



	/**
	 * geting filtered list
	 * @param regexp
	 *            for filtering
	 * @return filtered list
	 */
	@Cacheable("filter")
	public Contacts filter(String regexp) {
		return new Contacts(getContacts().stream().filter(contact -> {
			return !contact.getName().matches(regexp);
		}).collect(Collectors.toList()));
	}

	/**
	 * geting full list
	 * 
	 * @return
	 */
	@Cacheable("contacts")
	public List<Contact> getContacts() {
		return contactsRepository.findAll();
	}

}
