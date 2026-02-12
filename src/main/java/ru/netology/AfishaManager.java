public class AfishaManager {
    private Movie[] movies = new Movie[0];
    private int limit;

    public AfishaManager() {
        this.limit = 5;
    }

    public AfishaManager(int limit) {
        this.limit = limit;
    }

    public void addMovie(Movie movie) {
        Movie[] newMovies = new Movie[movies.length + 1];
        for (int i = 0; i < movies.length; i++) {
            newMovies[i] = movies[i];
        }
        newMovies[newMovies.length - 1] = movie;
        movies = newMovies;
    }

    public Movie[] findAll() {
        return movies;
    }

    public Movie[] findLast() {
        int resultLength;
        if (movies.length < limit) {
            resultLength = movies.length;
        } else {
            resultLength = limit;
        }

        Movie[] result = new Movie[resultLength];
        for (int i = 0; i < resultLength; i++) {
            result[i] = movies[movies.length - 1 - i];
        }
        return result;
    }

    public int getLimit() {
        return limit;
    }
}