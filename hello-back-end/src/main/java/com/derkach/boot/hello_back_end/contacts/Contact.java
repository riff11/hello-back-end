package com.derkach.boot.hello_back_end.contacts;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
	@Id
	@GeneratedValue
	private  long id;
	
	@Column(columnDefinition = "text")
	private  String name;
	
	public Contact() {
	}

	public Contact(long id, String content) {
		this.id = id;
		this.name = content;
	}
	
	

	@Override
	public String toString() {
		return "Contacts [id=" + id + ", name=" + name + "]";
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
