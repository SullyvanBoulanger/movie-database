package fr.movie.mappers;

import static fr.movie.utils.ParseUtils.parseDouble;
import static fr.movie.utils.ParseUtils.parseInt;
import static fr.movie.utils.StringUtils.trim;

import java.util.ArrayList;
import java.util.List;

import fr.movie.dtos.MovieDto;
import fr.movie.entities.Actor;
import fr.movie.entities.Director;
import fr.movie.entities.Genre;
import fr.movie.entities.Language;
import fr.movie.entities.Location;
import fr.movie.entities.Movie;
import fr.movie.entities.Role;

/**
 * Represents a mapper to map a MovieDto to a Movie Entity
 */
public class MovieMapper extends DtoToEntityMapper<MovieDto, Movie> {

    /**
     * Language mapper
     */
    private LanguageMapper languageMapper = new LanguageMapper();

    /**
     * Country mapper
     */
    private CountryMapper countryMapper = new CountryMapper();

    /**
     * Location mapper
     */
    private LocationMapper locationMapper = new LocationMapper(countryMapper);

    /**
     * Director mapper
     */
    private DirectorMapper directorMapper = new DirectorMapper(countryMapper, locationMapper);

    /**
     * Actor mapper
     */
    private ActorMapper actorMapper = new ActorMapper(countryMapper, locationMapper);

    /**
     * Role mapper
     */
    private RoleMapper roleMapper = new RoleMapper(actorMapper);

    /**
     * Genre mapper
     */
    private GenreMapper genreMapper = new GenreMapper();

    @Override
    public Movie mapDtoToEntity(MovieDto movieDto) {
        String id = trim(movieDto.getId());
        String name = trim(movieDto.getNom());
        String url = trim(movieDto.getUrl());
        double rating = parseDouble(trim(movieDto.getRating()));
        String plot = trim(movieDto.getPlot());

        if (movieDto.getPays() != null)
            countryMapper.putCountryInMemory(countryMapper.mapDtoToEntity(movieDto.getPays()));

        int releaseDate = parseInt(trim(movieDto.getAnneeSortie()));
        Language language = languageMapper.mapDtoToEntity(movieDto.getLangue());

        List<Location> filmingLocations = new ArrayList<>();
        if (movieDto.getLieuTournage() != null) {
            filmingLocations.add(locationMapper.mapDtoToEntity(movieDto.getLieuTournage()));
        }

        List<Director> directors = movieDto.getRealisateurs().stream().map(directorMapper::mapDtoToEntity).toList();
        List<Actor> actors = movieDto.getCastingPrincipal().stream().map(actorMapper::mapDtoToEntity).toList();
        List<Role> roles = movieDto.getRoles().stream().map(roleMapper::mapDtoToEntity).toList();
        List<Genre> genres = movieDto.getGenres().stream().map(genreMapper::mapDtoToEntity).toList();

        Movie movie = new Movie(
                id,
                name,
                url,
                rating,
                plot,
                releaseDate,
                language,
                directors,
                actors,
                filmingLocations,
                roles,
                genres);

        return findInMemoryOrElsePut(id, movie);
    }
}
