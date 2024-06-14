package fr.movie.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto representing a Country for JSON Parse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {
    /**
     * Represents the country's name
     */
    private String nom;

    /**
     * Represents the country's url
     */
    private String url;
}
