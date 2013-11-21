package com.querydsl.jpa.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;

import com.querydsl.jpa.example.data.UserData;
import com.querydsl.jpa.example.guice.AbstractPersistenceTest;

public class DefaultServiceTest extends AbstractPersistenceTest {
    @Inject
    private DefaultService service;

    @Test
    public void create_user() {
        String username = "johnny";
        UserData userData = service.save(new UserData(username));
        assertEquals(username, service.userById(userData.getId()).getUsername());
    }

    @Test
    public void tweet_with_existing_user() {
        UserData userData = service.save(new UserData("jimmy"));
        assertNotNull(service.tweet(userData, "When the levee breaks!"));
    }

    @Test
    public void find_tweets_by_poster() {
        String username = "jimmy";
        UserData userData = service.save(new UserData(username));
        service.tweet(userData, "When the levee breaks!");
        service.tweet(userData, "Money for nothing!");
        service.tweet(userData, "Burning down the house!");
        service.tweet(service.save(new UserData("melissa")), "Burn, burn, burn!");
        assertEquals(3, service.tweetsByPoster(username).size());

    }

}
