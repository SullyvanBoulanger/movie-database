package fr.movie.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
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
 * Entity representing a Country
 */
@Entity
@Table(name = "PAYS")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    private String name;

    /**
     * Represents country's url
     */
    @Column(name = "URL")
    @NonNull
    private String url;

    /**
     * Represents country's locations
     */
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Location> locations;
}
