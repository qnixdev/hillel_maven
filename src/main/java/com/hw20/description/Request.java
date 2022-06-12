package com.hw20.description;

public enum Request {
    WELCOME("Welcome in book library!"),
    NOT_FOUND("Command not found!\n"),
    NEXT("Enter next command:"),
    SAVE("New book successfully save!"),
    BYE("Bye");

    public String title;

    Request(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}