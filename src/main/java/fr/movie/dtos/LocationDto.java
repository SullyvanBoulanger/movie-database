package fr.movie.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto representing a Location for JSON Parse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    /**
     * Represents the location's city
     */
    private String ville;
    
    /**
     * Represents the location's region
     */
    private String etatDept;

    /**
     * Represents the location's country
     */
    private String pays;
}
