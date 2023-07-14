package moviedatabase;

import moviedatabase.data.MovieDatabase;
import moviedatabase.ui.MovieDatabaseUI;

import java.io.File;

/**
 * Entry point for a movie database as part of an assignment
 * in the course Introduction to Programming with Java.
 */
public class Main {
    /**
     * Program entry point. Starts the movie database UI.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        // Construct and start the UI
        MovieDatabase movieDatabase = new MovieDatabase("Movies.txt");
        MovieDatabaseUI movieDatabaseUI = new MovieDatabaseUI(movieDatabase);
        movieDatabaseUI.startUI();
    }
}
