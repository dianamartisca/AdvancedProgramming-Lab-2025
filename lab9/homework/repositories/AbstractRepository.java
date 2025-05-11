package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.EntityManagerFactorySingleton;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

public abstract class AbstractRepository<T, ID> {
    private static final Logger logger = Logger.getLogger(AbstractRepository.class.getName());

    static {
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("application.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    private final Class<T> entityClass;
    protected final EntityManager em;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    public void create(T entity) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
            logger.info("Entity created: " + entity);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Error creating entity: " + entity, e);
        }
    }

    public T findById(ID id) {
        try {
            long startTime = System.currentTimeMillis();
            T result = em.find(entityClass, id);
            long endTime = System.currentTimeMillis();
            logger.info("findById executed in " + (endTime - startTime) + "ms for ID: " + id);
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding entity by ID: " + id, e);
            return null;
        }
    }

    public List<T> findByName(String namePattern) {
        try {
            long startTime = System.currentTimeMillis();
            TypedQuery<T> query = em.createNamedQuery(entityClass.getSimpleName() + ".findByName", entityClass);
            query.setParameter("namePattern", "%" + namePattern + "%");
            List<T> results = query.getResultList();
            long endTime = System.currentTimeMillis();
            logger.info("findByName executed in " + (endTime - startTime) + "ms for name pattern: " + namePattern);
            return results;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding entities by name pattern: " + namePattern, e);
            return null;
        }
    }
}