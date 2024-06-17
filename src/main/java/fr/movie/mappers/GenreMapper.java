package fr.movie.mappers;

import static fr.movie.utils.StringUtils.trim;

import fr.movie.entities.Genre;

/**
 * Represents a mapper to map a String to a Genre Entity
 */
public class GenreMapper extends DtoToEntityMapper<String, Genre> {

    @Override
    public Genre mapDtoToEntity(String dto) {
        String name = trim(dto);

        Genre genre = new Genre(name);

        return findInMemoryOrElsePut(name, genre);
    }
    
}
