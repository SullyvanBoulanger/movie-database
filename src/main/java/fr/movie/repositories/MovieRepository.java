package fr.movie.repositories;

import java.util.List;

import fr.movie.entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
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
     * 
     * @param id id to search for
     * @return Movie if provided id exists or null
     */
    public Movie findMovieById(String id) {
        return entityManager.find(Movie.class, id);
    }

    /**
     * Find movies from an actor name
     * 
     * @param actorName String : Actor Name
     * @return List of movies
     */
    public List<Movie> findMoviesFromActorName(String actorName) {
        TypedQuery<Movie> query = entityManager.createQuery(
                "SELECT movies FROM Actor actor JOIN actor.movies movies WHERE actor.identity LIKE :name", Movie.class);
        query.setParameter("name", actorName);

        return query.getResultList();
    }
}
