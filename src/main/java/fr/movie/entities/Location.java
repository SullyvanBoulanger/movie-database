package fr.movie.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIEU")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "VILLE")
    private String city;

    @Column(name = "REGION")
    private String region;

    @ManyToOne
    @JoinColumn(name = "ID_PAYS")
    private Country country;
}
