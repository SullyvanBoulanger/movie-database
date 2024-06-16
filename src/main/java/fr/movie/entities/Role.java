package fr.movie.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Role
 */
@Entity
@Table(name = "ROLE")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    private String characterName;

    /**
     * Represents role's actors
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "ACTEURS_ROLES",
        joinColumns = @JoinColumn(name = "ID_ROLE"),
        inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR")
    )
    @NonNull
    private List<Actor> actors;
    
    /**
     * Represents role's movies
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "FILMS_ROLES",
        joinColumns = @JoinColumn(name = "ID_ROLE"),
        inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private List<Movie> movies;
}
