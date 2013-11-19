package com.querydsl.jpa.example.repository;

import static com.querydsl.jpa.example.model.QPerson.person;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mysema.query.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.example.guice.GuiceTestRunner;
import com.querydsl.jpa.example.model.Person;

@RunWith(GuiceTestRunner.class)
public class PersonRepositoryTest {
    @Inject
    private PersonRepository repository;

    @Inject
    private EntityManager em;

    @Before
    public void setUp() {
        repository.save(new Person("jimmy"));
    }

    @After
    public void tearDown() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        new JPADeleteClause(em, person).execute();
        tx.commit();
    }

    @Test
    public void save() {
        String username = "jackie";
        Person person = new Person(username);
        repository.save(person);
        assertEquals(username, repository.byId(person.getId()).getUsername());
    }

    @Test
    public void byId() {
        String username = "annie";
        Person person = new Person(username);
        repository.save(person);
        assertEquals(username, repository.byId(person.getId()).getUsername());
    }

    @Test
    public void getAll() {
        assertTrue(repository.all().size() > 0);
    }
}
