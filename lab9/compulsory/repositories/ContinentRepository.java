package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.EntityManagerFactorySingleton;
import org.example.entities.Continent;

import java.util.List;

public class ContinentRepository {
    private final EntityManager em;

    public ContinentRepository() {
        this.em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    public void create(Continent continent) {
        em.getTransaction().begin();
        em.persist(continent);
        em.getTransaction().commit();
    }

    public Continent findById(int id) {
        return em.find(Continent.class, id);
    }

    public List<Continent> findByName(String namePattern) {
        TypedQuery<Continent> query = em.createNamedQuery("Continent.findByName", Continent.class);
        query.setParameter("namePattern", "%" + namePattern + "%");
        return query.getResultList();
    }
}