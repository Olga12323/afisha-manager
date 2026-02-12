import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AfishaManagerTest {

    @Test
    public void shouldAddMovie() {
        AfishaManager manager = new AfishaManager();
        Movie movie = new Movie("Бладшот");

        manager.addMovie(movie);

        Movie[] expected = {movie};
        Movie[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAll() {
        AfishaManager manager = new AfishaManager();
        Movie movie1 = new Movie("Бладшот");
        Movie movie2 = new Movie("Вперёд");
        Movie movie3 = new Movie("Отель Белград");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        Movie[] expected = {movie1, movie2, movie3};
        Movie[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindLastWithDefaultLimit() {
        AfishaManager manager = new AfishaManager();

        manager.addMovie(new Movie("Бладшот"));
        manager.addMovie(new Movie("Вперёд"));
        manager.addMovie(new Movie("Отель Белград"));
        manager.addMovie(new Movie("Джентльмены"));
        manager.addMovie(new Movie("Человек-невидимка"));
        manager.addMovie(new Movie("Тролли. Мировой тур"));

        Movie[] last = manager.findLast();

        assertEquals(5, last.length);

        Movie[] expected = {
                new Movie("Тролли. Мировой тур"),
                new Movie("Человек-невидимка"),
                new Movie("Джентльмены"),
                new Movie("Отель Белград"),
                new Movie("Вперёд")
        };
        assertArrayEquals(expected, last);
    }

    @Test
    public void shouldFindLastWithCustomLimit() {
        AfishaManager manager = new AfishaManager(3);

        manager.addMovie(new Movie("Бладшот"));
        manager.addMovie(new Movie("Вперёд"));
        manager.addMovie(new Movie("Отель Белград"));
        manager.addMovie(new Movie("Джентльмены"));
        manager.addMovie(new Movie("Человек-невидимка"));

        Movie[] last = manager.findLast();

        assertEquals(3, last.length);

        Movie[] expected = {
                new Movie("Человек-невидимка"),
                new Movie("Джентльмены"),
                new Movie("Отель Белград")
        };
        assertArrayEquals(expected, last);
    }

    @Test
    public void shouldFindLastWhenLessThanLimit() {
        AfishaManager manager = new AfishaManager(5);

        manager.addMovie(new Movie("Бладшот"));
        manager.addMovie(new Movie("Вперёд"));

        Movie[] last = manager.findLast();

        assertEquals(2, last.length);

        Movie[] expected = {
                new Movie("Вперёд"),
                new Movie("Бладшот")
        };
        assertArrayEquals(expected, last);
    }

    @Test
    public void shouldFindLastWhenEmpty() {
        AfishaManager manager = new AfishaManager();

        Movie[] last = manager.findLast();

        assertEquals(0, last.length);
        assertArrayEquals(new Movie[0], last);
    }
}