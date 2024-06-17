package fr.movie.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.AllArgsConstructor;

/**
 * Represents a repository containing requests to find Actor
 */
@AllArgsConstructor
    public class ActorRepository {
    /**
     * EntityManagerFactory
     */
    private EntityManagerFactory entityManagerFactory;

    /**
     * EntityManager
     */
    private EntityManager entityManager;

    /**
     * Empty constructor initializing the attributes to connect to database
     */
    public ActorRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("movies");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
}
