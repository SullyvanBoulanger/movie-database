package fr.movie.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

@Entity
@Table(name = "FILM")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "NOM")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "NOTE")
    private double rating;

    @Column(name = "INTRIGUE")
    private double plot;

    @Column(name = "ANNEE_SORTIE")
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "ID_LANGUE")
    private Language language;
    
    @ManyToMany
    @JoinTable(
        name = "FILMS_REALISATEURS",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_REALISATEUR")
    )
    private List<Director> directors;

    @ManyToMany
    @JoinTable(
        name = "FILMS_ACTEURS",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR")
    )
    private List<Actor> principalCasting;

    @ManyToMany
    @JoinTable(
        name = "FILMS_LIEUX",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_LIEU")
    )
    private List<Location> filmingLocations;
    
    @ManyToMany
    @JoinTable(
        name = "FILMS_ROLES",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
    )
    private List<Role> roles;

    @ManyToMany
    @JoinTable(
        name = "FILMS_GENRES",
        joinColumns = @JoinColumn(name = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_GENRE")
    )
    private Set<Genre> genres;
}
