package com.hw16.description;

import java.util.Objects;

public enum CLI {
    ADD("add", "Add new book to library"),
    READ("read", "Get book by title"),
    SHOW("show", "Show books by author"),
    EXIT("exit", "Close app"),
    HELP("help", "Show all commands");

    public String command;
    public String description;

    CLI(String command, String description) {
        this.command = command;
        this.description = description;
    }

    @Override
    public String toString() {
        if (Objects.equals(command, "add")) {
            return command + "   ->  " + description;
        }

        return command + "  ->  " + description;
    }
}