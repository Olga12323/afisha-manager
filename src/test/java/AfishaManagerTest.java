import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        AfishaManager manager = new AfishaManager(); // лимит = 5 по умолчанию

        // Добавляем 7 фильмов
        for (int i = 1; i <= 7; i++) {
            manager.addMovie(new Movie("Фильм " + i));
        }

        Movie[] last = manager.findLast();

        // Должно вернуть 5 фильмов (лимит по умолчанию)
        assertEquals(manager.getLimit(), last.length);
        assertEquals(5, last.length);

        // Проверяем порядок (обратный)
        assertEquals("Фильм 7", last[0].getTitle());
        assertEquals("Фильм 6", last[1].getTitle());
        assertEquals("Фильм 5", last[2].getTitle());
        assertEquals("Фильм 4", last[3].getTitle());
        assertEquals("Фильм 3", last[4].getTitle());
    }

    @Test
    public void shouldFindLastMoviesWithCustomLimit() {
        int customLimit = 3;
        AfishaManager manager = new AfishaManager(customLimit);

        // Добавляем 7 фильмов
        for (int i = 1; i <= 7; i++) {
            manager.addMovie(new Movie("Фильм " + i));
        }

        Movie[] last = manager.findLast();

        // Должно вернуть customLimit = 3 фильма
        assertEquals(customLimit, last.length);
        assertEquals("Фильм 7", last[0].getTitle());
        assertEquals("Фильм 6", last[1].getTitle());
        assertEquals("Фильм 5", last[2].getTitle());
    }

    @Test
    public void shouldFindLastMoviesWhenLessThanLimit() {
        AfishaManager manager = new AfishaManager(5);

        Movie movie1 = new Movie("Бладшот");
        Movie movie2 = new Movie("Вперёд");
        Movie movie3 = new Movie("Отель Белград");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        Movie[] last = manager.findLast();

        // Фильмов меньше лимита - возвращаем все 3
        assertEquals(3, last.length);
        Movie[] expected = {movie3, movie2, movie1};
        assertArrayEquals(expected, last);
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

        // Проверяем findAll
        Movie[] expectedAll = {bloodshot, forward, belgrade, gentlemen, invisible, trolls};
        assertArrayEquals(expectedAll, manager.findAll());

        // Проверяем findLast - должно вернуть 5 последних
        Movie[] expectedLast = {trolls, invisible, gentlemen, belgrade, forward};
        assertArrayEquals(expectedLast, manager.findLast());
    }

    @Test
    public void shouldWorkWithDifferentLimits() {
        // Проверяем лимит 3
        AfishaManager manager1 = new AfishaManager(3);
        for (int i = 1; i <= 5; i++) {
            manager1.addMovie(new Movie("Фильм " + i));
        }
        assertEquals(3, manager1.findLast().length);

        // Проверяем лимит 7
        AfishaManager manager2 = new AfishaManager(7);
        for (int i = 1; i <= 5; i++) {
            manager2.addMovie(new Movie("Фильм " + i));
        }
        assertEquals(5, manager2.findLast().length); // Фильмов меньше лимита

        // Проверяем лимит 0 (крайний случай)
        AfishaManager manager3 = new AfishaManager(0);
        for (int i = 1; i <= 5; i++) {
            manager3.addMovie(new Movie("Фильм " + i));
        }
        assertEquals(0, manager3.findLast().length);
    }
}