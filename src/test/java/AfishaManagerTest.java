import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AfishaManagerTest {

    @Test
    public void shouldAddMovie() {
        AfishaManager manager = new AfishaManager();
        Movie movie = new Movie("Бладшот");

        manager.addMovie(movie);

        Movie[] all = manager.findAll();
        assertEquals(1, all.length);
        assertEquals(movie, all[0]);
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

        Movie[] all = manager.findAll();
        assertEquals(3, all.length);
        assertEquals(movie1, all[0]);
        assertEquals(movie2, all[1]);
        assertEquals(movie3, all[2]);
    }

    @Test
    public void shouldFindLastMoviesWithDefaultLimit() {
        AfishaManager manager = new AfishaManager();

        // Добавляем 12 фильмов
        for (int i = 1; i <= 12; i++) {
            manager.addMovie(new Movie("Фильм " + i));
        }

        Movie[] last = manager.findLast();

        // По умолчанию лимит 10, поэтому должно вернуться 10 последних
        assertEquals(10, last.length);
        assertEquals("Фильм 12", last[0].getTitle());
        assertEquals("Фильм 3", last[9].getTitle());
    }

    @Test
    public void shouldFindLastMoviesWithCustomLimit() {
        AfishaManager manager = new AfishaManager(5);

        // Добавляем 7 фильмов
        for (int i = 1; i <= 7; i++) {
            manager.addMovie(new Movie("Фильм " + i));
        }

        Movie[] last = manager.findLast();

        // Лимит 5, поэтому должно вернуться 5 последних
        assertEquals(5, last.length);
        assertEquals("Фильм 7", last[0].getTitle());
        assertEquals("Фильм 3", last[4].getTitle());
    }

    @Test
    public void shouldFindLastMoviesWhenLessThanLimit() {
        AfishaManager manager = new AfishaManager(10);

        // Добавляем только 3 фильма
        Movie movie1 = new Movie("Бладшот");
        Movie movie2 = new Movie("Вперёд");
        Movie movie3 = new Movie("Отель Белград");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        Movie[] last = manager.findLast();

        // Фильмов меньше лимита, поэтому должны вернуться все 3
        assertEquals(3, last.length);
        assertEquals(movie3, last[0]);
        assertEquals(movie2, last[1]);
        assertEquals(movie1, last[2]);
    }

    @Test
    public void shouldFindLastMoviesWhenEmpty() {
        AfishaManager manager = new AfishaManager();

        Movie[] last = manager.findLast();

        assertEquals(0, last.length);
    }

    @Test
    public void shouldReturnEmptyArrayWhenNoMovies() {
        AfishaManager manager = new AfishaManager();

        Movie[] all = manager.findAll();

        assertEquals(0, all.length);
    }

    @Test
    public void shouldHandleMoviesFromExample() {
        AfishaManager manager = new AfishaManager();

        manager.addMovie(new Movie("Бладшот"));
        manager.addMovie(new Movie("Вперёд"));
        manager.addMovie(new Movie("Отель Белград"));
        manager.addMovie(new Movie("Джентльмены"));
        manager.addMovie(new Movie("Человек-невидимка"));
        manager.addMovie(new Movie("Тролли. Мировой тур"));

        Movie[] all = manager.findAll();
        assertEquals(6, all.length);
        assertEquals("Бладшот", all[0].getTitle());
        assertEquals("Тролли. Мировой тур", all[5].getTitle());

        Movie[] last = manager.findLast();
        assertEquals(6, last.length); // Меньше лимита 10
        assertEquals("Тролли. Мировой тур", last[0].getTitle());
        assertEquals("Бладшот", last[5].getTitle());
    }
}
