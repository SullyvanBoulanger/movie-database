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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Genre (like a movie genre)
 */
@Entity
@Table(name = "GENRE")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Genre {
    /**
     * Represents genre's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Represents genre's name
     */
    @Column(name = "NOM")
    @NonNull
    private String name;

    /**
     * Represents genre's movies
     */
    @ManyToMany
    @JoinTable(
        name = "FILMS_GENRES",
        joinColumns = @JoinColumn(name = "ID_GENRE"),
        inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private List<Movie> movies;
}
