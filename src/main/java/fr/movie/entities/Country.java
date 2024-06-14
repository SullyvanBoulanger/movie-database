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
 * Entity representing a Country
 */
@Entity
@Table(name = "PAYS")
public class Country {
    /**
     * Represents country's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Represents country's name
     */
    @Column(name = "NOM")
    private String name;

    /**
     * Represents country's url
     */
    @Column(name = "URL")
    private String url;

    /**
     * Represents country's locations
     */
    @OneToMany(mappedBy = "country")
    private List<Location> locations;
}
