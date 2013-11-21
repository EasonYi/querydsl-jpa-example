package com.querydsl.jpa.example.repository;

import static com.querydsl.jpa.example.model.QUser.user;

import java.util.List;

import com.google.inject.persist.Transactional;
import com.querydsl.jpa.example.model.User;

@Transactional
public class UserRepository extends AbstractRepository<User> {
    @Override
    public User byId(Long id) {
        return find(User.class, id);
    }

    public User save(User user) {
        return persistOrMerge(user);
    }

    public List<User> all() {
        return from(user).list(user);
    }
}
