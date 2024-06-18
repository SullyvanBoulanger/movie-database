package fr.movie;

import fr.movie.ui.MenuInteraction;

/**
 * Menu to search in database
 */
public class Menu {
    public static void main(String[] args) {
        MenuInteraction menuInteraction = new MenuInteraction();
        menuInteraction.printWelcome();

        do {
            menuInteraction.printMenu();
            menuInteraction.askUser();
        } while (menuInteraction.applyUserChoice());
    }
}
