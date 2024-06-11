package project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import Prosjektoppgave.Movie;
import Prosjektoppgave.MovieLibrary;
import Prosjektoppgave.UserClass;
import java.nio.file.Path;

public class MovieStoreTest {

    MovieLibrary library = new MovieLibrary("Movie library");
    Movie movie = new Movie("Avengers", "Action", 50, 120, 15);

    @Test
    public void testBalance(){
        // testing that the user balance updates correctly when adding and removing balance
        assertEquals(500, library.getUser().getBalance());
        library.getUser().addBalance(200);
        assertEquals(700, library.getUser().getBalance());
        library.getUser().removeBalance(100);
        assertEquals(600, library.getUser().getBalance());
    }

    @Test
    public void testIllegalMovieLibrary(){
        // test that movie library name can not be empty
        assertThrows(IllegalArgumentException.class, () -> new MovieLibrary(""));
    }

    @Test
    public void testAddMovie(){
        // testing that user balance gets updated when adding a movie to movie library
        assertEquals(500, library.getUser().getBalance());
        library.addToLibrary(movie);
        assertEquals(550, library.getUser().getBalance());
        // testing that the movie gets added correctly
        assertTrue(library.getLibrary().contains(movie));
    }

    @Test
    public void testRentMovie(){
        assertEquals(500, library.getUser().getBalance());
        library.rentMovie(movie);
        // testing that the rented movie(s) is removed from movie library and moved to the users rented movies
        assertFalse(library.getLibrary().contains(movie));
        assertTrue(library.getUser().getRentedMovies().contains(movie));
        // testing that renting a movie affects the users balance correctly
        assertEquals(450, library.getUser().getBalance());
    }

    @Test
    public void testWriteReceipt(){
        library.rentMovie(movie);
        library.getFileHandler().writeReceipt(library.getUser());
        Path path = Paths.get("receipt.txt").toAbsolutePath(); // locates the file and its path
        String pathnameString = path.toString();
        File file = new File(pathnameString);
        // testing that the file exists
        assertTrue(file.exists());
        // testing that the file is not empty
        assertNotEquals(0, file.length());
    }

    @Test
    public void testIllegalMovie(){
        assertThrows(IllegalArgumentException.class, () -> library.getUser().removeBalance(600));
        // test that exception is thrown when price is less than 0
        assertThrows(IllegalArgumentException.class, () -> new Movie("Godfather", "Action", -1, 120, 18));
        // test that exception is thrown when movie length is less than 10
        assertThrows(IllegalArgumentException.class, () -> new Movie("Godfather", "Action", 50, 5, 18));
        // test that exception is thrown when age limit is less than 0
        assertThrows(IllegalArgumentException.class, () -> new Movie("Godfather", "Action", 50, 120, -1));
        // test that exception is thrown when age limit is more than 20
        assertThrows(IllegalArgumentException.class, () -> new Movie("Godfather", "Action", 50, 120, 21));
        assertFalse(movie.getMovieTitle().isEmpty());
        assertFalse(movie.getMovieGenre().isEmpty());
        // Test that movie title cannot be an empty string
        assertThrows(IllegalArgumentException.class, () -> new Movie("", "Action", 50, 120, 21));
        // Test that movie genre cannot be an empty string
        assertThrows(IllegalArgumentException.class, () -> new Movie("Title", "", 50, 120, 21));
    }

    @Test
    public void testIllegalUserClass(){
        // test that exception is thrown when age is less than 0
        assertThrows(IllegalArgumentException.class, () -> new UserClass("Username", -1, 500));
        // test that exception is thrown when start balance is less than 0
        assertThrows(IllegalArgumentException.class, () -> new UserClass("Username", 18, -100));
        UserClass user = new UserClass("Username", 18, 500);
        // test that user name cannot be an empty string
        assertThrows(IllegalArgumentException.class, () -> new UserClass("", 18, 400));
        // test that the correct username is returned
        assertEquals("Username", user.getUsername());
        // test that over 18 check works correctly
        assertTrue(user.overOr18());
        // test that the correct age is returned
        assertEquals(18, user.getAge());
        // test that the correct balance is returned
        assertEquals(500, user.getBalance());
        // test that exception is thrown when trying to add balance less than 0
        assertThrows(IllegalArgumentException.class, () -> user.addBalance(-10));
         // test that exception is thrown when trying to remove a sum that exceeds account balance
        assertThrows(IllegalArgumentException.class, () -> user.removeBalance(501));
        // test that exception is thrown when trying to remove a negative sum
        assertThrows(IllegalArgumentException.class, () -> user.removeBalance(-1));

    }
}
