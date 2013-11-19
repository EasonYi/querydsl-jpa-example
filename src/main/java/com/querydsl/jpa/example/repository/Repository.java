package com.querydsl.jpa.example.repository;

import java.io.Serializable;

public interface Repository<Entity, Id extends Serializable> {
    /**
     * Get the persisted instance with the given id
     *
     * @param id
     * @return
     */
    Entity byId(Id id);

}