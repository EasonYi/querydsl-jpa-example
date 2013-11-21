package com.querydsl.jpa.example.data;

import java.util.List;

import com.querydsl.jpa.example.Identifiable;

public class TweetData implements Identifiable {
    private final Long id;

    private final UserData poster;

    private final String content;

    private final List<UserData> mentions;

    private final LocationData location;

    public TweetData(Long id, UserData poster, String content,
            List<UserData> mentions, LocationData location) {
        this.id = id;
        this.poster = poster;
        this.content = content;
        this.mentions = mentions;
        this.location = location;
    }

    @Override
    public Long getId() {
        return id;
    }

    public UserData getPoster() {
        return poster;
    }

    public List<UserData> getMentions() {
        return mentions;
    }

    public String getContent() {
        return content;
    }

    public LocationData getLocation() {
        return location;
    }
}
