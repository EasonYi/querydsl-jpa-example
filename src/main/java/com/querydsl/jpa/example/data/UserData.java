package com.querydsl.jpa.example.data;

import com.querydsl.jpa.example.Identifiable;

public class UserData implements Identifiable {
    private final Long id;
    private final String username;

    public UserData(String username) {
        id = null;
        this.username = username;
    }

    public UserData(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public Long getId() {
        return id;
    }
}
