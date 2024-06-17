package fr.movie.repositories;

import java.util.List;

import fr.movie.entities.Actor;
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

    /**
     * Find principal casting from a movie name
     * 
     * @param movieName String representing movie name
     * @return List of Actor
     */
    public List<Actor> findCastingFromMovieName(String movieName) {
        TypedQuery<Actor> query = entityManager.createQuery(
                "SELECT principalCasting FROM Movie movie JOIN movie.principalCasting principalCasting WHERE movie.name LIKE :name",
                Actor.class);
        query.setParameter("name", movieName);

        return query.getResultList();
    }

    /**
     * Find movies released between two years
     * 
     * @param firstYear  Int representing first year (included)
     * @param secondYear Int representing second year (included)
     * @return List of Movie
     */
    public List<Movie> findMovieBetweenTwoYears(int firstYear, int secondYear) {
        TypedQuery<Movie> query = entityManager.createQuery(
                "SELECT movie FROM Movie movie WHERE movie.releaseYear BETWEEN :firstYear AND :secondYear",
                Movie.class);
        query.setParameter("firstYear", firstYear);
        query.setParameter("secondYear", secondYear);

        return query.getResultList();
    }
}
