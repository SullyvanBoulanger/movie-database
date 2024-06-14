package fr.movie.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDto {
    private String id;

    private String identite;
    
    private String url;

    private BirthDto naissance;
}
