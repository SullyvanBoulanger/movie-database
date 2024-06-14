package fr.movie.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Language
 */
@Entity
@Table(name = "LANGUE")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    private String name;

    /**
     * Represents language's movies
     */
    @OneToMany(mappedBy = "language")
    private List<Movie> movies;
}
