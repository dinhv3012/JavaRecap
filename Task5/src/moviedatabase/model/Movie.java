package moviedatabase.model;

/**
 * The Movie class represents a movie with its title and review score.
 */
public class Movie {
    private String title;
    private String reviewScore;

    /**
     * Constructs a Movie object with the specified title and review score.
     *
     * @param title       the title of the movie
     * @param reviewScore the review score of the movie
     */
    public Movie(String title, String reviewScore) {
        this.title = title;
        this.reviewScore = reviewScore;
    }

    /**
     * Returns the title of the movie.
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the review score of the movie.
     *
     * @return the review score of the movie
     */
    public String getReviewScore() {
        return reviewScore;
    }
}
