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

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "NOM_PERSONNAGE")
    private String characterName;

    @ManyToMany
    @JoinTable(
        name = "ACTEURS_ROLES",
        joinColumns = @JoinColumn(name = "ID_ROLE"),
        inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR")
    )
    private List<Actor> actors;
    
    @ManyToMany
    @JoinTable(
        name = "FILMS_ROLES",
        joinColumns = @JoinColumn(name = "ID_ROLE"),
        inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private List<Movie> movies;
}
