package fr.movie.mappers;

import static fr.movie.utils.ParseUtils.parseDouble;
import static fr.movie.utils.StringUtils.trim;

import java.time.LocalDate;

import fr.movie.dtos.ActorDto;
import fr.movie.entities.Actor;
import fr.movie.entities.Location;

/**
 * Represents a mapper to map a ActorDto to an Actor Entity
 */
public class ActorMapper extends PersonMapper<ActorDto, Actor> {

    /**
     * Constructor to provide other mappers
     * 
     * @param countryMapper  CountryMapper
     * @param locationMapper LocationMapper
     */
    public ActorMapper(CountryMapper countryMapper, LocationMapper locationMapper) {
        super(countryMapper, locationMapper);
    }

    @Override
    public Actor mapDtoToEntity(ActorDto dto) {
        String id = trim(dto.getId());
        String identity = trim(dto.getIdentite());
        String url = trim(dto.getUrl());
        LocalDate birthDate = mapBirthDateStringToDate(dto.getNaissance().getDateNaissance());
        Location birthLocation = mapBirthLocationStringToLocation(dto.getNaissance().getLieuNaissance());
        double height = parseDouble(trim(dto.getHeight()).split(" ")[0]);

        Actor actor = new Actor(id, identity, url, birthDate, birthLocation, height);
        return findInMemoryOrElsePut(id, actor);
    }

}
