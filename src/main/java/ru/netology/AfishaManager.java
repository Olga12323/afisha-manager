import java.util.Arrays;

public class AfishaManager {
    private static final int DEFAULT_LIMIT = 5; // 👈 ПО УМОЛЧАНИЮ 5!

    private Movie[] movies;
    private int limit;

    // Конструктор по умолчанию - лимит = 5
    public AfishaManager() {
        this.limit = DEFAULT_LIMIT;
        this.movies = new Movie[0];
    }

    // Конструктор с кастомным лимитом
    public AfishaManager(int limit) {
        this.limit = limit;
        this.movies = new Movie[0];
    }

    // Добавление фильма
    public void addMovie(Movie movie) {
        Movie[] newMovies = new Movie[movies.length + 1];
        System.arraycopy(movies, 0, newMovies, 0, movies.length);
        newMovies[movies.length] = movie;
        movies = newMovies;
    }

    // Все фильмы в порядке добавления
    public Movie[] findAll() {
        return movies;
    }

    // Последние фильмы в обратном порядке (до limit штук)
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

    // Геттер для лимита
    public int getLimit() {
        return limit;
    }
}
