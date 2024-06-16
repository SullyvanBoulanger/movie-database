package fr.movie.mappers;

import static fr.movie.utils.ParseUtils.canParseToLocaldate;
import static fr.movie.utils.ParseUtils.parseDouble;
import static fr.movie.utils.ParseUtils.parseInt;
import static fr.movie.utils.StringUtils.trim;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import fr.movie.dtos.ActorDto;
import fr.movie.dtos.CountryDto;
import fr.movie.dtos.DirectorDto;
import fr.movie.dtos.LocationDto;
import fr.movie.dtos.MovieDto;
import fr.movie.dtos.RoleDto;
import fr.movie.entities.Actor;
import fr.movie.entities.Country;
import fr.movie.entities.Director;
import fr.movie.entities.Genre;
import fr.movie.entities.Language;
import fr.movie.entities.Location;
import fr.movie.entities.Movie;
import fr.movie.entities.Role;

/**
 * Class mapping DTO to Entity
 */
public class DtoToEntityMapper {
    private Map<String, Language> languagesInMemory = new HashMap<>();
    private Map<String, Location> locationsInMemory = new HashMap<>();
    private Map<String, Country> countriesInMemory = new HashMap<>();
    private Map<String, Director> directorsInMemory = new HashMap<>();
    private Map<String, Actor> actorsInMemory = new HashMap<>();
    private Map<String, Role> rolesInMemory = new HashMap<>();
    private Map<String, Genre> genresInMemory = new HashMap<>();

    /**
     * Map a MovieDto from JSON parse to an Movie Entity
     * 
     * @param movieDto the parsed MovieDto
     * @return the mapped Movie Entity
     */
    public Movie mapMovieDtoToMovieEntity(MovieDto movieDto) {
        String id = trim(movieDto.getId());
        String name = trim(movieDto.getNom());
        String url = trim(movieDto.getUrl());
        double rating = parseDouble(trim(movieDto.getRating()));
        String plot = trim(movieDto.getPlot());
        LocalDate releaseDate = LocalDate.of(parseInt(trim(movieDto.getAnneeSortie())), 1, 1);

        Language languageToPut = null;
        String languageName = trim(movieDto.getLangue());

        if (movieDto.getLangue() != null) {
            languageToPut = new Language(languageName);
        }

        Language language = findInMemoryOrElsePut(
                languagesInMemory,
                languageName,
                languageToPut);

        List<Location> filmingLocations = new ArrayList<>();
        if (movieDto.getLieuTournage() != null) {
            filmingLocations.add(mapLocationDtoToEntity(movieDto.getLieuTournage()));
        }

        List<Director> directors = movieDto.getRealisateurs().stream().map(this::mapDirectorDtoToEntity).toList();
        List<Actor> actors = movieDto.getCastingPrincipal().stream().map(this::mapActorDtoToEntity).toList();
        List<Role> roles = movieDto.getRoles().stream().map(this::mapRoleDtoToEntity).toList();
        List<Genre> genres = movieDto.getGenres().stream().map(this::mapGenreStringToEntity).toList();

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

        return movie;
    }

    /**
     * Map a LocationDto from JSON parse to an Location Entity
     * 
     * @param locationDto the parsed LocationDto
     * @return the mapped Location Entity
     */
    public Location mapLocationDtoToEntity(LocationDto locationDto) {
        String city = trim(locationDto.getVille());
        String region = trim(locationDto.getEtatDept());

        Country countryToPut = new Country(trim(locationDto.getPays()), "");
        Country country = findInMemoryOrElsePut(
                countriesInMemory,
                countryToPut.getName(),
                countryToPut);

        Location location = new Location(city, region, country);

        return findInMemoryOrElsePut(locationsInMemory, getCompositeKey(location), location);
    }

    /**
     * Map a CountryDto from JSON parse to an Country Entity
     * 
     * @param countryDto the parsed CountryDto
     * @return the mapped Country Entity
     */
    public Country mapCountryDtoToEntity(CountryDto countryDto) {
        String name = trim(countryDto.getNom());
        String url = trim(countryDto.getUrl());

        Country country = new Country(name, url);

        return findInMemoryOrElsePut(countriesInMemory, name, country);
    }

    /**
     * Map a DirectorDto from JSON parse to an Director Entity
     * 
     * @param directorDto the parsed DirectorDto
     * @return the mapped Director Entity
     */
    public Director mapDirectorDtoToEntity(DirectorDto directorDto) {
        String id = trim(directorDto.getId());
        String identity = trim(directorDto.getIdentite());
        String url = trim(directorDto.getUrl());
        LocalDate birthDate = mapBirthDateStringToDate(directorDto.getNaissance().getDateNaissance());
        Location birthLocation = mapBirthLocationStringToLocation(directorDto.getNaissance().getLieuNaissance());

        Director director = new Director(id, identity, url, birthDate, birthLocation);
        return findInMemoryOrElsePut(directorsInMemory, id, director);
    }

    /**
     * Map a ActorDto from JSON parse to an Actor Entity
     * 
     * @param actorDto the parsed ActorDto
     * @return the mapped Actor Entity
     */
    public Actor mapActorDtoToEntity(ActorDto actorDto) {
        String id = trim(actorDto.getId());
        String identity = trim(actorDto.getIdentite());
        String url = trim(actorDto.getUrl());
        LocalDate birthDate = mapBirthDateStringToDate(actorDto.getNaissance().getDateNaissance());
        Location birthLocation = mapBirthLocationStringToLocation(actorDto.getNaissance().getLieuNaissance());
        double height = parseDouble(trim(actorDto.getHeight()).split(" ")[0]);

        Actor actor = new Actor(id, identity, url, birthDate, birthLocation, height);
        return findInMemoryOrElsePut(actorsInMemory, id, actor);
    }

    /**
     * Map a RoleDto from JSON parse to an Role Entity
     * 
     * @param roleDto the parsed RoleDto
     * @return the mapped Role Entity
     */
    public Role mapRoleDtoToEntity(RoleDto roleDto) {
        String characterName = trim(roleDto.getCharacterName());
        List<Actor> actors = new ArrayList<>();
        Actor actor = mapActorDtoToEntity(roleDto.getActeur());
        actors.add(actor);

        Role role = new Role(characterName, actors);
        return findInMemoryOrElsePut(rolesInMemory, characterName, role);
    }

    /**
     * Map a GenreDto from JSON parse to an Genre Entity
     * 
     * @param genreDto the parsed GenreDto
     * @return the mapped Genre Entity
     */
    public Genre mapGenreStringToEntity(String genreString) {
        String name = trim(genreString);

        Genre genre = new Genre(name);

        return findInMemoryOrElsePut(genresInMemory, name, genre);
    }

    /**
     * Map a String representing a date to a LocalDate
     * The string must be in format "MMM dd yyyy"
     * Example : "January 9 1943"
     * 
     * @param birthDateString the String representing a birth date
     * @return LocalDate corresponding to the date in String
     */
    public LocalDate mapBirthDateStringToDate(String birthDateString) {
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
    private Location mapBirthLocationStringToLocation(String string) {
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

        Country countryToPut = new Country(countryName, "");
        Location location = new Location(city, region,
                findInMemoryOrElsePut(countriesInMemory, countryName, countryToPut));

        return findInMemoryOrElsePut(locationsInMemory, getCompositeKey(location), location);
    }

    /**
     * Generate a composite key from a Location for the Map in memory
     * 
     * @param location Location
     * @return String composite key
     */
    private String getCompositeKey(Location location) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getCity())
                .append("|")
                .append(location.getRegion())
                .append("|")
                .append(location.getCountry().getName());

        return stringBuilder.toString();
    }

    /**
     * Find an object T from provided map and if not exist, put the provided object
     * at the key
     * 
     * @param <T>          Type of the value
     * @param inMemoryMap  In memory map of the Type requested
     * @param key          String key
     * @param objectoToPut Object to put if the map does not contain key
     * @return Object of Value Type finded or else, the object put
     */
    private <T> T findInMemoryOrElsePut(Map<String, T> inMemoryMap, String key, T objectoToPut) {
        if (!inMemoryMap.containsKey(key)) {
            inMemoryMap.put(key, objectoToPut);

            return objectoToPut;
        }

        return inMemoryMap.get(key);
    }
}
