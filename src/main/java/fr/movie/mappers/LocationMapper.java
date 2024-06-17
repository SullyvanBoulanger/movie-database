package fr.movie.mappers;

import static fr.movie.utils.StringUtils.trim;

import fr.movie.dtos.CountryDto;
import fr.movie.dtos.LocationDto;
import fr.movie.entities.Country;
import fr.movie.entities.Location;
import lombok.AllArgsConstructor;

/**
 * Represents a mapper to map a LocationDto to a Location Entity
 */
@AllArgsConstructor
public class LocationMapper extends DtoToEntityMapper<LocationDto, Location> {

    /**
     * Country mapper
     */
    private CountryMapper countryMapper = new CountryMapper();

    @Override
    public Location mapDtoToEntity(LocationDto dto) {
        String city = trim(dto.getVille());
        String region = trim(dto.getEtatDept());

        Country country = countryMapper.mapDtoToEntity(new CountryDto(trim(dto.getPays()), ""));

        Location location = new Location(city, region, country);

        return findInMemoryOrElsePut(getCompositeKey(location), location);
    }

    /**
     * Generate a composite key from a Location for the Map in memory
     * 
     * @param location Location
     * @return String composite key
     */
    public String getCompositeKey(Location location) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getCity())
                .append("|")
                .append(location.getRegion())
                .append("|")
                .append(location.getCountry().getName());

        return stringBuilder.toString();
    }
}
