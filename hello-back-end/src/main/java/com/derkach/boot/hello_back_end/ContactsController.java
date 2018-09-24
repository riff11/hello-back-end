package com.derkach.boot.hello_back_end;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.derkach.boot.hello_back_end.contacts.Contacts;

@RestController
public class ContactsController {

	@Autowired
	private ContactsService contactsService; 

	/**
	 * 
	 * @param regexp for filtering
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET, path = "/hello/contacts")
	public Contacts filter(@RequestParam(value = "nameFilter") String regexp) throws Exception {
		return contactsService.filter(regexp);
	}
}
