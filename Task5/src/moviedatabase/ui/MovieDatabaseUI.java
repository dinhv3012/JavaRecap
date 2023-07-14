package moviedatabase.ui;

import moviedatabase.data.MovieDatabase;
import moviedatabase.model.Movie;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A command line user interface for a movie database.
 */
public class MovieDatabaseUI {
	private Scanner _scanner;
	private MovieDatabase _movieDatabase;

	/**
	 * Constructs a MovieDatabaseUI with the specified MovieDatabase.
	 *
	 * @param movieDatabase the MovieDatabase to be used
	 */
	public MovieDatabaseUI(MovieDatabase movieDatabase) {
		_movieDatabase = movieDatabase;
		_scanner = new Scanner(System.in);
	}

	/**
	 * Starts the movie database user interface.
	 */
	public void startUI() {
		int input;
		boolean quit = false;

		System.out.println("** MOVIE DATABASE **");

		while (!quit) {
			input = getNumberInput(_scanner, 1, 4, getMainMenu());

			switch (input) {
				case 1:
					searchTitle();
					break;
				case 2:
					searchReviewScore();
					break;
				case 3:
					addMovie();
					break;
				case 4:
					quit = true;
			}
		}
		// Close the scanner to free resources
		_scanner.close();
	}

	/**
	 * Gets input from the user and translates it into a valid number.
	 *
	 * @param scanner the Scanner object used to get input
	 * @param min     the minimum valid number
	 * @param max     the maximum valid number
	 * @param message the message displayed to the user
	 * @return the chosen menu number
	 */
	private int getNumberInput(Scanner scanner, int min, int max, String message) {
		int input = -1;

		while (input < 0) {
			System.out.println(message);
			try {
				input = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException | NoSuchElementException e) {
				input = -1;
			}

			if (input < min || input > max) {
				System.out.println("Invalid value");
				input = -1;
			}
		}
		return input;
	}

	/**
	 * Gets a search string from the user, searches for movies by title in the movie database,
	 * and presents the search result.
	 */
	private void searchTitle() {
		System.out.print("Enter a keyword: ");
		String title = _scanner.nextLine().trim();

		List<Movie> result = _movieDatabase.searchByTitle(title);
		printMovies(result);
	}

	/**
	 * Gets a search string from the user, searches for movies by review score in the movie database,
	 * and presents the search result.
	 */
	private void searchReviewScore() {
		int review = getNumberInput(_scanner, 1, 5, "Enter the minimum review score (1 - 5): ");

		List<Movie> result = _movieDatabase.searchByReviewScore(review);
		printMovies(result);
	}

	/**
	 * Gets information from the user about a new movie and adds it to the database.
	 */
	private void addMovie() {
		System.out.print("Enter the title: ");
		String title = _scanner.nextLine().trim();
		System.out.print("Enter the review score (1 - 5): ");
		String reviewScore = _scanner.nextLine().trim();
		Movie movie = new Movie(title, reviewScore);
		_movieDatabase.addMovie(movie);
		System.out.println("Movie added successfully.");
	}

	/**
	 * Prints the movies in the specified list.
	 *
	 * @param movies the list of movies to print
	 */
	private void printMovies(List<Movie> movies) {
		if (movies.isEmpty()) {
			System.out.println("No movies found.");
		} else {
			System.out.println("-----------------------");
			for (Movie movie : movies) {
				System.out.println("Title: " + movie.getTitle() + " Review score: " + movie.getReviewScore());
			}
			System.out.println("-----------------------");
		}
	}

	/**
	 * Returns the main menu text.
	 *
	 * @return the main menu text
	 */
	private String getMainMenu() {
		return "-------------------\n" +
				"1. Search title\n" +
				"2. Search review score\n" +
				"3. Add movie\n" +
				"-------------------\n" +
				"4. Close program";
	}
}
