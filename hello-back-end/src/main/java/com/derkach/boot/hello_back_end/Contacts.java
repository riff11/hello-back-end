package com.derkach.boot.hello_back_end;

public class Contacts {

    private final long id;
    private final String content;

    public Contacts(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
