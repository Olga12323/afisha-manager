import java.util.Arrays;

public class AfishaManager {
    private Movie[] movies;
    private int limit;

    public AfishaManager() {
        this.limit = 10; // лимит по умолчанию
        this.movies = new Movie[0];
    }

    public AfishaManager(int limit) {
        this.limit = limit;
        this.movies = new Movie[0];
    }

    public void addMovie(Movie movie) {
        Movie[] newMovies = new Movie[movies.length + 1];
        System.arraycopy(movies, 0, newMovies, 0, movies.length);
        newMovies[movies.length] = movie;
        movies = newMovies;
    }

    public Movie[] findAll() {
        return movies;
    }

    public Movie[] findLast() {
        int resultLength = Math.min(movies.length, limit);
        Movie[] result = new Movie[resultLength];

        for (int i = 0; i < resultLength; i++) {
            result[i] = movies[movies.length - 1 - i];
        }
        return result;
    }

    // ✅ ГЕТТЕР для limit - чтобы тесты не использовали магические числа!
    public int getLimit() {
        return limit;
    }
}