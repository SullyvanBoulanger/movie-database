package fr.movie.services;

import java.util.List;

import fr.movie.entities.Movie;
import fr.movie.repositories.MovieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

/**
 * Service to manipulate databse for Movie
 */
public class MovieService {

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
    public MovieService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("movies");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Persist in database a list of movies
     * 
     * @param movies List of movies
     */
    @Transactional
    public void persistMovies(List<Movie> movies) {
        MovieRepository movieRepository = new MovieRepository(entityManagerFactory, entityManager);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        movies.forEach(movie -> {
            Movie existingMovie = movieRepository.findMovieById(movie.getId());

            if (existingMovie == null) {
                entityManager.persist(movie);
            }
        });

        transaction.commit();
    }
}
