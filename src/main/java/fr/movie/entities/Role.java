package fr.movie.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entity representing a Role
 */
@Entity
@Table(name = "ROLE")
public class Role {
    /**
     * Represents role's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Represents role's character name
     */
    @Column(name = "NOM_PERSONNAGE")
    private String characterName;

    /**
     * Represents role's actors
     */
    @ManyToMany
    @JoinTable(
        name = "ACTEURS_ROLES",
        joinColumns = @JoinColumn(name = "ID_ROLE"),
        inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR")
    )
    private List<Actor> actors;
    
    /**
     * Represents role's movies
     */
    @ManyToMany
    @JoinTable(
        name = "FILMS_ROLES",
        joinColumns = @JoinColumn(name = "ID_ROLE"),
        inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private List<Movie> movies;
}
