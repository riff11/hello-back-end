package com.derkach.boot.hello_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.derkach.boot.hello_back_end.contacts.Contact;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Long> {
}
