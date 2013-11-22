package com.querydsl.example.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Column(unique = true)
    private String username;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Tweet> tweets = new ArrayList<Tweet>();

    public User() {
    }

    public User(Long id, String username) {
        setId(id);
        this.username = username;
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
