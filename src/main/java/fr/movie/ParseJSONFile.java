package fr.movie;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.movie.dtos.MovieDto;
import fr.movie.entities.Movie;
import fr.movie.mappers.DtoToEntityMapper;
import fr.movie.services.MovieService;

/**
 * Script parsing a JSON File into entities
 */
public class ParseJSONFile {
    /**
     * Path to JSON File
     */
    private static String FILE_PATH = "src/main/resources/data/films.json";

    public static void main(String[] args) {
        File jsonFile = new File(FILE_PATH);
        ObjectMapper objectMapper = new ObjectMapper();
        DtoToEntityMapper dtoToEntityMapper = new DtoToEntityMapper();

        try {
            List<MovieDto> movieDto = objectMapper.readValue(jsonFile, new TypeReference<List<MovieDto>>(){});
            List<Movie> movies = movieDto.stream().map(dtoToEntityMapper::mapMovieDtoToMovieEntity).toList();

            MovieService movieService = new MovieService();
            // TODO : Refacto how I persist my elements because right now, entities can be doubled
            movieService.persistMovies(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
