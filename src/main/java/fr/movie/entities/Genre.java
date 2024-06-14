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
@Table(name = "GENRE")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "NOM")
    private String name;

    @ManyToMany
    @JoinTable(
        name = "FILMS_GENRES",
        joinColumns = @JoinColumn(name = "ID_GENRE"),
        inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private List<Movie> movies;
}
