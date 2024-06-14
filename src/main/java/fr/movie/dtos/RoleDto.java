package fr.movie.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto representing a Role for JSON Parse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    /**
     * Represents the role's character name
     */
    private String characterName;

    /**
     * Represents the role's actor
     */
    private ActorDto acteur;
}
