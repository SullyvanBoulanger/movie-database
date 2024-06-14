package fr.movie.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACTEUR")
public class Actor extends Person {
    @Column(name = "TAILLE")
    private double height;

    @ManyToMany
    @JoinTable(
        name = "ACTEURS_ROLES",
        joinColumns = @JoinColumn(name = "ID_ACTEUR"),
        inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
    )
    private List<Role> roles;

    @ManyToMany
    @JoinTable(
        name = "FILMS_ACTEURS",
        joinColumns = @JoinColumn(name = "ID_ACTEUR"),
        inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private List<Movie> movies;
}
