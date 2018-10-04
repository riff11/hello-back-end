package com.derkach.boot.hello_back_end.contacts;

import java.util.List;
/**
 * list of contacts
 * @author alex
 *
 */
public class Contacts {
	
	private List<Contact> contacts;
	

	public Contacts(List<Contact> contacts) {
		super();
		this.contacts = contacts;
	}

	public Contacts() {
	}

	public List<Contact> getContacts() {
		return contacts;
	}	

}
