package com.querydsl.jpa.example.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;

import com.querydsl.jpa.example.guice.AbstractPersistenceTest;
import com.querydsl.jpa.example.model.User;

public class UserRepositoryTest extends AbstractPersistenceTest {
    @Inject
    private UserRepository repository;

    @Test
    public void save_and_get_by_id() {
        String username = "jackie";
        User user = new User(username);
        repository.save(user);
        assertEquals(username, repository.byId(user.getId()).getUsername());
    }

    @Test
    public void get_all() {
        repository.save(new User("jimmy"));
        assertTrue(repository.all().size() == 1);
    }
}
