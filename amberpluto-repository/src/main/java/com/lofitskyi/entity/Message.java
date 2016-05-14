package com.lofitskyi.entity;

public class Message {
    private String name;
    private String from;
    private String text;

    public Message() {
    }

    public Message(String name, String from, String text) {
        this.name = name;
        this.from = from;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
