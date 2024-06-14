package fr.movie.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Location
 */
@Entity
@Table(name = "LIEU")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Location {
    /**
     * Represents location's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Represents location's city
     */
    @Column(name = "VILLE")
    @NonNull
    private String city;

    /**
     * Represents location's region
     */
    @Column(name = "REGION")
    @NonNull
    private String region;

    /**
     * Represents location's country
     */
    @ManyToOne
    @JoinColumn(name = "ID_PAYS")
    @NonNull
    private Country country;
}
