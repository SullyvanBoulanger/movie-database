package fr.movie.ui;

import java.util.List;
import java.util.Scanner;

import fr.movie.entities.Movie;
import fr.movie.repositories.MovieRepository;

public class MenuInteraction {
    private MovieRepository movieRepository = new MovieRepository();

    private Scanner scanner = new Scanner(System.in);

    private int userChoice = 0;

    public void printWelcome() {
        System.out.println("""
                Bienvenue à IMDB, votre base de donnée pour les films.
                Que désirez-vous ?
                """);
    }

    public void askUser() {
        System.out.println("Choissisez une option (1 à 8) : ");
        userChoice = scanner.nextInt();
        scanner.nextLine();
    }

    public boolean applyUserChoice() {
        printSeparator();

        switch (userChoice) {
            case 1:
                printMoviesFromActorName();
                break;
            case 2:

                break;

            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

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

    private String askUser(String message){
        System.out.println(message);
        String answer = scanner.nextLine();
        return answer;
    }

    private void printMoviesFromActorName(){
        String actorName = askUser("Prénom Nom de l'acteur : ");

        List<Movie> movies = movieRepository.findMoviesFromActorName(actorName);

        printSeparator();
        movies.forEach(movie -> System.out.println(movie.getName()));
    }

    private void printSeparator(){
        System.out.println("----------");
    }
}
