package com.example.excercise.domain.model.user;

public enum UserType {
    OWNER("OWNER"),
    CLIENT("CLIENT");

    String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
