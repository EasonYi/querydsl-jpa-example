package com.querydsl.jpa.example.repository;

import static com.querydsl.jpa.example.model.QPerson.person;

import java.util.List;

import javax.persistence.Transient;

import com.google.inject.persist.Transactional;
import com.querydsl.jpa.example.model.Person;

public class PersonRepository extends AbstractRepository<Person> {
    @Override
    public Person byId(Long id) {
        return find(Person.class, id);
    }

    @Transactional
    public Person save(Person person) {
        return persistOrMerge(person);
    }

    public List<Person> all() {
        return from(person).list(person);
    }
}
