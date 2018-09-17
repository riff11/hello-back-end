package com.derkach.boot.hello_back_end;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contacts {
	@Id
	@GeneratedValue(generator = "answer_generator")
	@SequenceGenerator(name = "answer_generator", sequenceName = "answer_sequence")
	private final long id;
	
	@Column(columnDefinition = "text")
	private final String name;

	public Contacts(long id, String content) {
		this.id = id;
		this.name = content;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
