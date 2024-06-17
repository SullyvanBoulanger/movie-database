package fr.movie.repositories;

import fr.movie.entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.AllArgsConstructor;

/**
 * Represents a repository containing requests to find Movies
 */
@AllArgsConstructor
public class MovieRepository {
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
    public MovieRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("movies");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Find a Movie by Id
     * @param id id to search for
     * @return Movie if provided id exists or null
     */
    public Movie findMovieById(String id) {
        return entityManager.find(Movie.class, id);
    }
}
