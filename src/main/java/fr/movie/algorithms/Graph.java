package fr.movie.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import fr.movie.entities.Actor;

/**
 * Represents a graph of actors
 */
public class Graph {
    /**
     * Represents the queue
     */
    private Queue<List<Actor>> queue = new LinkedList<>();

    /**
     * Represents already visited/marked actor names
     * String is used instead of Actor object to light comparisons
     */
    private Set<String> visited = new HashSet<>(); // To avoid visiting the same actor multiple times

    /**
     * List of all paths
     */
    private List<Path> paths = new ArrayList<>();

    public Graph(Actor startingActor) {
        this.queue.offer(List.of(startingActor));

        this.visited.add(startingActor.getIdentity());
    }

    public List<Path> bfs(Actor searched) {
        while (!queue.isEmpty()) {
            List<Actor> currentPath = queue.poll();
            Actor lastActor = currentPath.getLast();

            if (lastActor.equals(searched)) {
                paths.add(new Path(currentPath, 1 + currentPath.size()));
            }

            lastActor.getMovies().stream()
                    .flatMap(movie -> movie.getRoles().stream())
                    .flatMap(role -> role.getActors().stream())
                    .filter(actor -> !visited.contains(actor.getIdentity()))
                    .forEach(actor -> {
                            List<Actor> newPath = new ArrayList<>(currentPath);
                            newPath.add(actor);
                            queue.offer(newPath);
                            visited.add(actor.getIdentity());
                    });

            // for (Movie movie : lastActor.getMovies()) {
            //     for (Role role : movie.getRoles()) {
            //         for (Actor nextActor : role.getActors()) {

            //             if (!visited.contains(nextActor.getIdentity())) {
            //                 List<Actor> newPath = new ArrayList<>(currentPath);
            //                 newPath.add(nextActor);
            //                 queue.offer(newPath);
            //                 visited.add(nextActor.getIdentity());
            //             }

            //         }
            //     }
            // }
        }

        return paths;
    }
}
