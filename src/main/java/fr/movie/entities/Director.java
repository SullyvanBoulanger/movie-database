package fr.movie.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "REALISATEUR")
public class Director extends Person {
    @ManyToMany
    @JoinTable(
        name = "FILMS_REALISATEURS",
        joinColumns = @JoinColumn(name = "ID_REALISATEUR"),
        inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private List<Movie> movies;
}
