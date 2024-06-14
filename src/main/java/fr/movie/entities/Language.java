package fr.movie.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entity representing a Language
 */
@Entity
@Table(name = "LANGUE")
public class Language {
    /**
     * Represents language's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Represents language's name
     */
    @Column(name = "NOM")
    private String name;

    /**
     * Represents language's movies
     */
    @OneToMany(mappedBy = "language")
    private List<Movie> movies;
}
