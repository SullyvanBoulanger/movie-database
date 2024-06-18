package fr.movie.algorithms;

import java.util.List;

import fr.movie.entities.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a path for Breadth-First Search algorithm
 */
@AllArgsConstructor
public class Path {
    /**
     * The sequence of actors in the path
     */
    private List<Actor> actors;

    /**
     * Total steps or distance of the path
     */
    @Getter
    private int length;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        actors.forEach(actor -> stringBuilder.append(actor.getIdentity()).append(" -> "));
        stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length() - 1);

        return stringBuilder.toString();
    }
}
