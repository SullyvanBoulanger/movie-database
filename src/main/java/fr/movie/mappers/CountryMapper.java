package fr.movie.mappers;

import static fr.movie.utils.StringUtils.trim;

import fr.movie.dtos.CountryDto;
import fr.movie.entities.Country;

/**
 * Represents a mapper to map a CountryDto to a Country Entity
 */
public class CountryMapper extends DtoToEntityMapper<CountryDto, Country> {

    @Override
    public Country mapDtoToEntity(CountryDto dto) {
        String name = trim(dto.getNom());
        String url = trim(dto.getUrl());

        Country country = new Country(name, url);

        return findInMemoryOrElsePut(name, country);
    }

}
