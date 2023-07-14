package moviedatabase.data;

import moviedatabase.model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MovieDatabase class represents a database of movies.
 * It provides functionality to load movies from a file, search movies by title or review score,
 * add new movies, and save the changes back to the file.
 */
public class MovieDatabase {
    private String fileName;
    private List<Movie> movies;

    /**
     * Constructs a MovieDatabase object with the specified file path.
     * It initializes the movies list and loads the movies from the file.
     *
     * @param fileName the file path to load movies from
     */
    public MovieDatabase(String fileName) {
        this.fileName = fileName;
        this.movies = new ArrayList<>();
        loadMovies();
    }

    /**
     * Loads movies from the file specified in the constructor.
     * Each line in the file represents a movie with the format: "title, reviewScore".
     * Movies are added to the movies list.
     * In case of any errors during loading, an error message is printed to the console.
     */
    private void loadMovies() {
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String title = parts[0].trim();
                    String reviewScore = parts[1].trim();
                    Movie movie = new Movie(title, reviewScore);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading movies: " + e.getMessage());
        }
    }

    /**
     * Searches movies in the database by title, based on a given keyword.
     * The search is case-insensitive, meaning it matches titles regardless of case.
     *
     * @param keyword the keyword to search for in movie titles
     * @return a list of movies whose titles contain the keyword
     */
    public List<Movie> searchByTitle(String keyword) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(movie);
            }
        }
        return result;
    }

    /**
     * Searches movies in the database by review score, based on a given minimum review score.
     *
     * @param minReviewScore the minimum review score to search for
     * @return a list of movies with a review score greater than or equal to the specified minimum
     */
    public List<Movie> searchByReviewScore(int minReviewScore) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            String reviewScoreString = movie.getReviewScore();
            String[] reviewScores = reviewScoreString.split("/");
            if (reviewScores.length == 2) {
                int reviewScore = Integer.parseInt(reviewScores[0].trim());
                if (reviewScore >= minReviewScore) {
                    result.add(movie);
                }
            }
        }
        return result;
    }


    /**
     * Adds a new movie to the database.
     * The movie is added to the movies list and then saved to the file.
     *
     * @param movie the movie to be added to the database
     */
    public void addMovie(Movie movie) {
        String reviewScore = movie.getReviewScore();
        String formattedReviewScore = formatReviewScore(reviewScore) + "/5";
        Movie newMovie = new Movie(movie.getTitle(), formattedReviewScore);
        movies.add(newMovie);
        saveMovies();
    }



    /**
     * Formats the review score to "X/Y" format with a slash ("/").
     *
     * @param reviewScore the review score to be formatted
     * @return the formatted review score
     */
    private String formatReviewScore(String reviewScore) {
        return reviewScore.replace(" ", "/");
    }

    /**
     * Saves the movies from the movies list back to the file specified in the constructor.
     * The existing contents of the file are overwritten.
     * In case of any errors during saving, an error message is printed to the console.
     */
    private void saveMovies() {
        File file = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Movie movie : movies) {
                writer.write(movieToString(movie));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the movie: " + e.getMessage());
        }
    }

    /**
     * Converts a Movie object to its string representation in the format: "title, reviewScore".
     *
     * @param movie the movie to convert to a string
     * @return the string representation of the movie
     */
    private String movieToString(Movie movie) {
        return movie.getTitle() + "," + movie.getReviewScore();
    }
}
