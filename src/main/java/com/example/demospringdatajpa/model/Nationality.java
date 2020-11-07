package com.example.demospringdatajpa.model;

public enum Nationality {

    INDIAN("INDIAN"),
    DEUTSCHLAND("DEUTSCHLAND");

    private String text;

    Nationality(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
