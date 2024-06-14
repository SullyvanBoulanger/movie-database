package fr.movie.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto representing an Actor for JSON Parse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto extends DirectorDto {
    /**
     * Represents the actor's height
     */
    private String height;
}
