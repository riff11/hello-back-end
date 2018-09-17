package com.derkach.boot.hello_back_end;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/hello/contacts")
    public Contacts greeting(@RequestParam( value="nameFilter", defaultValue="World") String name) {
        return new Contacts(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
