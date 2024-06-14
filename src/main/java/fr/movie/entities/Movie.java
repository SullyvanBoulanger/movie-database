package fr.movie.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Movie
 */
@Entity
@Table(name = "FILM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    /**
     * Represents movie's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Represents movie's name
     */
    @Column(name = "NOM")
    private String name;

    /**
     * Represents movie's url
     */
    @Column(name = "URL")
    private String url;

    /**
     * Represents movie's rating
     */
    @Column(name = "NOTE")
    private double rating;

    /**
     * Represents movie's plot
     */
    @Column(name = "INTRIGUE", length = 512)
    private String plot;

    /**
     * Represents movie's release date
     */
    @Column(name = "ANNEE_SORTIE")
    private LocalDate releaseDate;

    /**
     * Represents movie's language
     */
    @ManyToOne
    @JoinColumn(name = "ID_LANGUE")
    private Language language;
    
    /**
     * Represents movie's directors
     */
    @ManyToMany
    @JoinTable(
        name = "FILMS_REALISATEURS",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_REALISATEUR")
    )
    private List<Director> directors;

    /**
     * Represents movie's principal actors
     */
    @ManyToMany
    @JoinTable(
        name = "FILMS_ACTEURS",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR")
    )
    private List<Actor> principalCasting;

    /**
     * Represents movie's locations
     */
    @ManyToMany
    @JoinTable(
        name = "FILMS_LIEUX",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_LIEU")
    )
    private List<Location> filmingLocations;
    
    /**
     * Represents movie's roles
     */
    @ManyToMany
    @JoinTable(
        name = "FILMS_ROLES",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
    )
    private List<Role> roles;

    /**
     * Represents movie's genres
     */
    @ManyToMany
    @JoinTable(
        name = "FILMS_GENRES",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_GENRE")
    )
    private List<Genre> genres;
}
