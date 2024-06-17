package fr.movie.mappers;

import static fr.movie.utils.StringUtils.trim;

import java.time.LocalDate;

import fr.movie.dtos.DirectorDto;
import fr.movie.entities.Director;
import fr.movie.entities.Location;

/**
 * Represents a mapper to map a DirectorDto to a Director Entity
 */
public class DirectorMapper extends PersonMapper<DirectorDto, Director> {

    public DirectorMapper(CountryMapper countryMapper, LocationMapper locationMapper) {
        super(countryMapper, locationMapper);
    }

    @Override
    public Director mapDtoToEntity(DirectorDto dto) {
        String id = trim(dto.getId());
        String identity = trim(dto.getIdentite());
        String url = trim(dto.getUrl());
        LocalDate birthDate = mapBirthDateStringToDate(dto.getNaissance().getDateNaissance());
        Location birthLocation = mapBirthLocationStringToLocation(dto.getNaissance().getLieuNaissance());

        Director director = new Director(id, identity, url, birthDate, birthLocation);
        return findInMemoryOrElsePut(id, director);
    }

}
