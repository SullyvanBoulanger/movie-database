package fr.movie.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "IDENTITE")
    private String identity;

    @Column(name = "URL")
    private String url;

    @Column(name = "DATE_NAISSANCE")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "ID_LIEU_NAISSANCE")
    private Location birthLocation;
}
