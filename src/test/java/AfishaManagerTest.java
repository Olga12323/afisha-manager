import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AfishaManagerTest {

    @Test
    public void shouldAddMovie() {
        AfishaManager manager = new AfishaManager();
        Movie movie = new Movie("Бладшот");

        manager.addMovie(movie);

        Movie[] expected = {movie};
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    public void shouldFindAllMoviesInOrder() {
        AfishaManager manager = new AfishaManager();
        Movie movie1 = new Movie("Бладшот");
        Movie movie2 = new Movie("Вперёд");
        Movie movie3 = new Movie("Отель Белград");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        Movie[] expected = {movie1, movie2, movie3};
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    public void shouldFindLastMoviesWithDefaultLimit() {
        AfishaManager manager = new AfishaManager(); // лимит = 10 по умолчанию

        for (int i = 1; i <= 12; i++) {
            manager.addMovie(new Movie("Фильм " + i));
        }

        Movie[] last = manager.findLast();

        // Проверяем размер - должен быть равен лимиту менеджера
        assertEquals(manager.getLimit(), last.length);

        // Проверяем порядок (обратный)
        assertEquals("Фильм 12", last[0].getTitle());
        assertEquals("Фильм 11", last[1].getTitle());
        assertEquals("Фильм 10", last[2].getTitle());
        assertEquals("Фильм 3", last[9].getTitle());
    }

    @Test
    public void shouldFindLastMoviesWithCustomLimit() {
        int customLimit = 5;
        AfishaManager manager = new AfishaManager(customLimit);

        for (int i = 1; i <= 7; i++) {
            manager.addMovie(new Movie("Фильм " + i));
        }

        Movie[] last = manager.findLast();

        assertEquals(customLimit, last.length);
        assertEquals("Фильм 7", last[0].getTitle());
        assertEquals("Фильм 3", last[4].getTitle());
    }

    @Test
    public void shouldFindLastMoviesWhenLessThanLimit() {
        AfishaManager manager = new AfishaManager(10);

        Movie movie1 = new Movie("Бладшот");
        Movie movie2 = new Movie("Вперёд");
        Movie movie3 = new Movie("Отель Белград");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        Movie[] expected = {movie3, movie2, movie1};
        assertArrayEquals(expected, manager.findLast());
    }

    @Test
    public void shouldFindLastMoviesWhenEmpty() {
        AfishaManager manager = new AfishaManager();

        Movie[] last = manager.findLast();

        assertEquals(0, last.length);
        assertArrayEquals(new Movie[0], last);
    }

    @Test
    public void shouldReturnEmptyArrayWhenNoMovies() {
        AfishaManager manager = new AfishaManager();

        assertArrayEquals(new Movie[0], manager.findAll());
    }

    @Test
    public void shouldHandleMoviesFromExample() {
        AfishaManager manager = new AfishaManager();

        Movie bloodshot = new Movie("Бладшот");
        Movie forward = new Movie("Вперёд");
        Movie belgrade = new Movie("Отель Белград");
        Movie gentlemen = new Movie("Джентльмены");
        Movie invisible = new Movie("Человек-невидимка");
        Movie trolls = new Movie("Тролли. Мировой тур");

        manager.addMovie(bloodshot);
        manager.addMovie(forward);
        manager.addMovie(belgrade);
        manager.addMovie(gentlemen);
        manager.addMovie(invisible);
        manager.addMovie(trolls);

        Movie[] expectedAll = {bloodshot, forward, belgrade, gentlemen, invisible, trolls};
        assertArrayEquals(expectedAll, manager.findAll());

        Movie[] expectedLast = {trolls, invisible, gentlemen, belgrade, forward, bloodshot};
        assertArrayEquals(expectedLast, manager.findLast());
    }
}