package fr.movie.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto representing a Birth for JSON Parse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BirthDto {
    /**
     * Represents the birth date
     */
    private String dateNaissance;

    /**
     * Represents the birth location
     */
    private String lieuNaissance;
}
