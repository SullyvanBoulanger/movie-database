package fr.movie.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing an Actor
 */
@Entity
@Table(name = "ACTEUR")
@Getter
@Setter
@NoArgsConstructor
public class Actor extends Person {
    /**
     * Represents actor's height
     */
    @Column(name = "TAILLE")
    private double height;

    /**
     * Represents actor's roles
     */
    @ManyToMany
    @JoinTable(
        name = "ACTEURS_ROLES",
        joinColumns = @JoinColumn(name = "ID_ACTEUR"),
        inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
    )
    private List<Role> roles;

    /**
     * Represents actor's movies
     */
    @ManyToMany
    @JoinTable(
        name = "FILMS_ACTEURS",
        joinColumns = @JoinColumn(name = "ID_ACTEUR"),
        inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private List<Movie> movies;

    public Actor(String id, String identity, String url, LocalDate birthDate, Location birthLocation, double height) {
        super(id, identity, url, birthDate, birthLocation);
        this.height = height;
    }
}
