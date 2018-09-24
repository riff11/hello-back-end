package com.derkach.boot.hello_back_end.contacts;

import java.util.List;

public class Contacts {
	
	private List<Contact> contacts;
	

	public Contacts(List<Contact> contacts) {
		super();
		this.contacts = contacts;
	}


	public List<Contact> getContacts() {
		return contacts;
	}	

}
