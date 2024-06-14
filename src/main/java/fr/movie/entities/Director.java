package fr.movie.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Director
 */
@Entity
@Table(name = "REALISATEUR")
@Getter
@Setter
@NoArgsConstructor
public class Director extends Person {
    /**
     * Represents director's movies
     */
    @ManyToMany
    @JoinTable(
        name = "FILMS_REALISATEURS",
        joinColumns = @JoinColumn(name = "ID_REALISATEUR"),
        inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private List<Movie> movies;

    public Director(String id, String identity, String url, LocalDate birthDate, Location birthLocation) {
        super(id, identity, url, birthDate, birthLocation);
    }
}
