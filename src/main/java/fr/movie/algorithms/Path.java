package fr.movie.algorithms;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a path for Breadth-First Search algorithm
 */
@AllArgsConstructor
public class Path<T> {
    /**
     * The sequence of elements in the path
     */
    private List<T> elements;

    /**
     * Total steps or distance of the path
     */
    @Getter
    private int length;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        elements.forEach(element -> stringBuilder.append(element.toString()).append(" -> "));
        stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length() - 1);

        return stringBuilder.toString();
    }
}
