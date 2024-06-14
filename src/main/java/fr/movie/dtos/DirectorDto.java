package fr.movie.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto representing a Director for JSON Parse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDto {
    /**
     * Represents the director's id
     */
    private String id;

    /**
     * Represents the director's identity
     */
    private String identite;
    
    /**
     * Represents the director's url
     */
    private String url;

    /**
     * Represents the director's birth
     */
    private BirthDto naissance;
}
