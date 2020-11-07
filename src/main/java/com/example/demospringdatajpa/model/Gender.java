package com.example.demospringdatajpa.model;

public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE");

    private String text;

    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
