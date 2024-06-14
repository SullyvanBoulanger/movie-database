package fr.movie.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto representing a Movie for JSON Parse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDto {
    /**
     * Represents the movie's id
     */
    private String id;

    /**
     * Represents the movie's country
     */
    private CountryDto pays;

    /**
     * Represents the movie's name
     */
    private String nom;

    /**
     * Represents the movie's url
     */
    private String url;

    /**
     * Represents the movie's rating
     */
    private String rating;

    /**
     * Represents the movie's plot
     */
    private String plot;

    /**
     * Represents the movie's language
     */
    private String langue;

    /**
     * Represents the movie's filming locations
     */
    private LocationDto lieuTournage;

    /**
     * Represents the movie's directors
     */
    private List<DirectorDto> realisateurs;

    /**
     * Represents the movie's principal actors
     */
    private List<ActorDto> castingPrincipal;

    /**
     * Represents the movie's release date
     */
    private String anneeSortie;

    /**
     * Represents the movie's roles
     */
    private List<RoleDto> roles;

    /**
     * Represents the movie's genres
     */
    private List<String> genres;
}
