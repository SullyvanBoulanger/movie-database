package fr.movie.mappers;

import static fr.movie.utils.StringUtils.trim;
import static fr.movie.utils.ParseUtils.canParseToLocaldate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import fr.movie.dtos.CountryDto;
import fr.movie.entities.Country;
import fr.movie.entities.Location;
import fr.movie.entities.Person;
import lombok.AllArgsConstructor;

/**
 * Represents an abstract mapper to map a Dto to an Entity extending Person
 */
@AllArgsConstructor
public abstract class PersonMapper<T, U extends Person> extends DtoToEntityMapper<T, U> {

    /**
     * Country Mapper
     */
    private CountryMapper countryMapper;

    /**
     * Location Mapper
     */
    private LocationMapper locationMapper;

    /**
     * Map a String representing a date to a LocalDate
     * The string must be in format "MMMM d yyyy" with English Locale or "d MMMM
     * yyyy" with French Locale
     * Example 01 : "January 9 1943" or "September 20 1975"
     * Example 02 : "15 octobre 2000"
     * 
     * @param birthDateString the String representing a birth date
     * @return LocalDate corresponding to the date in String
     */
    protected LocalDate mapBirthDateStringToDate(String birthDateString) {
        if (birthDateString.equals(""))
            return null;

        DateTimeFormatter englishFormatter = DateTimeFormatter.ofPattern("MMMM d yyyy").withLocale(Locale.ENGLISH);

        DateTimeFormatter frenchFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy").withLocale(Locale.FRENCH);

        LocalDate parsedDate = null;

        if (canParseToLocaldate(trim(birthDateString), englishFormatter)) {
            parsedDate = LocalDate.parse(trim(birthDateString), englishFormatter);
        } else if (canParseToLocaldate(trim(birthDateString), frenchFormatter)) {
            parsedDate = LocalDate.parse(trim(birthDateString), frenchFormatter);
        }

        return parsedDate;
    }

    /**
     * Map a String representing a location to a Location Entity
     * The string can be in different format
     * Example 1 : "country"
     * Example 2 : "region, country"
     * Example 3 : "city, region, country"
     * 
     * @param string String representing a location
     * @return Location corresponding to the location in String
     */
    protected Location mapBirthLocationStringToLocation(String string) {
        if (string.equals(""))
            return null;

        String[] splittedString = string.split(",");

        String city = "";
        String region = "";
        String countryName = "";

        switch (splittedString.length) {
            case 1:
                countryName = trim(splittedString[0]);
                break;
            case 2:
                region = trim(splittedString[0]);
                countryName = trim(splittedString[1]);
                break;
            case 3:
                city = trim(splittedString[0]);
                region = trim(splittedString[1]);
                countryName = trim(splittedString[2]);
                break;
            default:
                countryName = trim(splittedString[splittedString.length - 1]);
                break;
        }

        CountryDto countryToPut = new CountryDto(countryName, "");
        Country country = countryMapper.mapDtoToEntity(countryToPut);
        Location location = new Location(city, region, country);

        return locationMapper.findInMemoryOrElsePut(locationMapper.getCompositeKey(location), location);
    }
}
