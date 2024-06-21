package fr.movie.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Represents a graph of elements
 * 
 * T represents element type
 */
public class Graph<T> {
    /**
     * Represents the queue
     */
    private Queue<List<T>> queue = new LinkedList<>();

    /**
     * Represents already visited/marked actor names
     * String is used instead of Actor object to light comparisons
     */
    private Set<String> visited = new HashSet<>();

    /**
     * List of all paths
     */
    private List<Path<T>> paths = new ArrayList<>();

    /**
     * Mapping Function to fill queue
     */
    private Function<T, Stream<T>> mappingFunction = null;

    /**
     * Constructor requesting a starting element for the graph and a mapping
     * Function
     * 
     * @param startingElement Starting Element
     * @param mappingFunction Mapping Function
     */
    public Graph(T startingElement, Function<T, Stream<T>> mappingFunction) {
        this.queue.offer(List.of(startingElement));
        this.visited.add(startingElement.toString());
        this.mappingFunction = mappingFunction;
    }

    /**
     * Execute a Breadth First Search (known as BFS) and return a list of Path
     * leading to searched element
     * 
     * @param searchedElement Searched Element
     * @return List of Path leading to searched element
     */
    public List<Path<T>> bfs(T searchedElement) {
        while (!queue.isEmpty()) {
            List<T> currentPath = queue.poll();
            T lastElement = currentPath.getLast();

            if (lastElement.equals(searchedElement)) {
                paths.add(new Path<>(currentPath, 1 + currentPath.size()));
            }

            mappingFunction.apply(lastElement)
                    .filter(element -> !visited.contains(element.toString()))
                    .forEach(element -> {
                        List<T> newPath = new ArrayList<>(currentPath);
                        newPath.add(element);
                        queue.offer(newPath);
                        visited.add(element.toString());
                    });
        }

        return paths;
    }
}
