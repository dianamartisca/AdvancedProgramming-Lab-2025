package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab9PU");

    private EntityManagerFactorySingleton() {}

    public static EntityManagerFactory getInstance() {
        return emf;
    }
}