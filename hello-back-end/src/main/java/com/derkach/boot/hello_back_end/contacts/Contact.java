package com.derkach.boot.hello_back_end.contacts;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
	
	private static final long serialVersionUID = 8827488035495682118L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  long id;
	
	@Column(columnDefinition = "text")
	private  String name;
	
	public Contact() {
	}

	public Contact(long id, String content) {
		this.id = id;
		this.name = content;
	}
	
	public Contact( String content) {
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
