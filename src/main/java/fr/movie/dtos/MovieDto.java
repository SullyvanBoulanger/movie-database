package fr.movie.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDto {
    private String id;

    private CountryDto pays;

    private String nom;

    private String url;

    private String rating;

    private String plot;

    private String langue;

    private LocationDto lieuTournage;

    private List<DirectorDto> realisateurs;

    private List<ActorDto> castingPrincipal;

    private String anneeSortie;

    private List<RoleDto> roles;

    private List<String> genres;
}
