package com.querydsl.jpa.example.guice;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.runner.RunWith;

import com.google.inject.persist.Transactional;

@RunWith(GuiceTestRunner.class)
public abstract class AbstractPersistenceTest {
    @Inject
    private Provider<EntityManager> em;

    @After
    @Transactional
    public void after() {
        EntityManager entityManager = em.get();
        entityManager.getEntityManagerFactory().getCache().evictAll();
        Session session = entityManager.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection)
                    throws SQLException {
                List<String> tables = new ArrayList<String>();
                DatabaseMetaData md = connection.getMetaData();
                ResultSet rs = md.getTables(null, null, null, null);
                try {
                    while (rs.next()) {
                        tables.add(rs.getString("TABLE_NAME"));
                    }
                } finally {
                    rs.close();
                }

                java.sql.Statement stmt = connection
                        .createStatement();
                try {
                    stmt.execute("set foreign_key_checks=0");
                    for (String table : tables) {
                        stmt.execute("truncate " + table);
                    }
                    stmt.execute("set foreign_key_checks=1");
                } finally {
                    stmt.close();
                }

            }
        });
    }
}
