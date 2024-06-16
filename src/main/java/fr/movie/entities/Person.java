package fr.movie.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Abstract superclass reprensenting a Person
 * Used by Actor and Director classes
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class Person {
    /**
     * Represents person's id
     */
    @Id
    private String id;

    /**
     * Represents person's identity
     */
    @Column(name = "IDENTITE")
    @NonNull
    private String identity;

    /**
     * Represents person's url
     */
    @Column(name = "URL")
    @NonNull
    private String url;

    /**
     * Represents person's birth date
     */
    @Column(name = "DATE_NAISSANCE")
    private LocalDate birthDate;

    /**
     * Represents person's birth location
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_LIEU_NAISSANCE")
    private Location birthLocation;
}
