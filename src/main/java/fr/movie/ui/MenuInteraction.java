package fr.movie.ui;

import java.util.List;
import java.util.Scanner;

import fr.movie.entities.Actor;
import fr.movie.entities.Movie;
import fr.movie.repositories.MovieRepository;

/**
 * Represents menu interaction between user and computer
 */
public class MenuInteraction {
    /**
     * MovieRepository
     */
    private MovieRepository movieRepository = new MovieRepository();

    /**
     * Scanner
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * int representing user choice
     */
    private int userChoice = 0;

    /**
     * Print welcome message
     */
    public void printWelcome() {
        System.out.println("""
                Bienvenue à IMDB, votre base de donnée pour les films.
                Que désirez-vous ?
                """);
    }

    /**
     * Ask User to input an integer for his choice
     */
    public void askUser() {
        System.out.println("Choissisez une option (1 à 8) : ");
        userChoice = scanner.nextInt();
        scanner.nextLine();
    }

    /**
     * Apply user choice (between 1 & 8).
     * If 8 is chosen, the scanner will be close and return false.
     * 
     * @return False if user has chosen 8, else return true
     */
    public boolean applyUserChoice() {
        printSeparator();

        switch (userChoice) {
            case 1:
                printMoviesFromActorName();
                break;
            case 2:
                printCastingFromMovieName();
                break;
            case 3:
                printMoviesBetweenYears();
                break;
            case 4:
                printCommonMoviesBetweenActors();
                break;
            case 5:
                printCommonActorsBetweenMovies();
                break;
            case 6:
                printMoviesBetweenYearsWithAnActor();
                break;
            case 7:

                break;
            case 8:
                scanner.close();
                return false;
            default:
                break;

        }

        printSeparator();
        return true;
    }

    /**
     * Print menu options
     */
    public void printMenu() {
        System.out.println("1. Affichage de la filmographie d’un acteur donné\n");
        System.out.println("2. Affichage du casting d’un film donné\n");
        System.out.println("3. Affichage des films sortis entre 2 années données\n");
        System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés\n");
        System.out.println("5. Affichage des acteurs communs à 2 films donnés\n");
        System.out.println(
                "6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting\n");
        System.out.println("7. Trouver le plus court chemin entre 2 acteurs\n");
        System.out.println("8. Fin de l’application\n");
    }

    /**
     * Print a separating line to improve readibility
     */
    private void printSeparator() {
        System.out.println("----------");
    }

    /**
     * Ask user for a String with message before
     * 
     * @param message Message to print before the input lock
     * @return String input by user
     */
    private String askStringUser(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * Ask user for a integer with message before
     * 
     * @param message Message to print before the input lock
     * @return int input by user
     */
    private int askIntUser(String message) {
        System.out.println(message);
        int answer = scanner.nextInt();
        scanner.nextLine();
        return answer;
    }

    /**
     * Print movies from an actor name asked
     */
    private void printMoviesFromActorName() {
        String actorName = askStringUser("Acteur/actrice (Prénom Nom) : ");

        List<Movie> movies = movieRepository.findMoviesFromActorName(actorName);

        printSeparator();
        movies.forEach(movie -> System.out.println(movie.getName()));
    }

    /**
     * Print actors from movie's casting from a movie name asked
     */
    private void printCastingFromMovieName() {
        String movieName = askStringUser("Nom du film : ");

        List<Actor> casting = movieRepository.findCastingFromMovieName(movieName);

        printSeparator();
        casting.forEach(actor -> System.out.println(actor.getIdentity()));
    }

    /**
     * Print movies between two years asked
     */
    private void printMoviesBetweenYears() {
        int firstYear = askIntUser("1ère année (inclus): ");
        int secondYear = askIntUser("2ème année (inclus): ");

        List<Movie> movies = movieRepository.findMovieBetweenTwoYears(firstYear, secondYear);

        printSeparator();
        movies.forEach(movie -> System.out.println(movie.getName()));
    }

    /**
     * Print movies common to two actors name asked
     */
    private void printCommonMoviesBetweenActors() {
        String firstActorName = askStringUser("1er acteur/actrice (Prénom Nom) : ");
        String secondActorName = askStringUser("2ème acteur/actrice (Prénom Nom) : ");

        List<Movie> movies = movieRepository.findMoviesCommonToTwoActors(firstActorName, secondActorName);

        printSeparator();
        movies.forEach(movie -> System.out.println(movie.getName()));
    }

    /**
     * Print actors common to two movie name asked
     */
    private void printCommonActorsBetweenMovies() {
        String firstMovieName = askStringUser("1er film : ");
        String secondMovieName = askStringUser("2ème film : ");

        List<Actor> actors = movieRepository.findActorsCommonToTwoMovies(firstMovieName, secondMovieName);

        printSeparator();
        actors.forEach(actor -> System.out.println(actor.getIdentity()));
    }

    /**
     * Print movies between years with an actor all asked
     */
    private void printMoviesBetweenYearsWithAnActor() {
        int firstYear = askIntUser("1ère année (inclus): ");
        int secondYear = askIntUser("2ème année (inclus): ");

        String actorName = askStringUser("Acteur/actrice (Prénom Nom) : ");

        List<Movie> movies = movieRepository.findMoviesBetweenYearsWithAnActor(firstYear, secondYear, actorName);

        printSeparator();
        movies.forEach(movie -> System.out.println(movie.getName()));
    }
}
