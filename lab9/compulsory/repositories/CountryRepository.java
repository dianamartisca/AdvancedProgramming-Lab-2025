package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.EntityManagerFactorySingleton;
import org.example.entities.Country;

import java.util.List;

public class CountryRepository {
    private final EntityManager em;

    public CountryRepository() {
        this.em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    public void create(Country country) {
        em.getTransaction().begin();
        em.persist(country);
        em.getTransaction().commit();
    }

    public Country findById(int id) {
        return em.find(Country.class, id);
    }

    public List<Country> findByName(String namePattern) {
        TypedQuery<Country> query = em.createNamedQuery("Country.findByName", Country.class);
        query.setParameter("namePattern", "%" + namePattern + "%");
        return query.getResultList();
    }
}