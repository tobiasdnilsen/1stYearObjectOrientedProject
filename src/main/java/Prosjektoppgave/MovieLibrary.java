package Prosjektoppgave;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieLibrary {
    private String name;
    private ArrayList<Movie> movieStore;
    private UserClass user = new UserClass("Petter", 22, 500);
    private fileHandlerInterface fileHandler = new fileHandler();

    public MovieLibrary(String name){
        if (name.length() < 1){
            throw new IllegalArgumentException("Error: Invalid Movie Library name");
        }
        this.name = name;
        movieStore = new ArrayList<>();
        try {
            ArrayList<Movie> movies = fileHandler.getStandardMovies();
            for (int i = 0; i < movies.size(); i++){
                movieStore.add(movies.get(i));
            }
        } catch (FileNotFoundException e){
            System.out.println("Error. File does not exist.");
        }
    }

    public ArrayList<Movie> getLibrary(){
        return this.movieStore;
    }

    public UserClass getUser(){
        return this.user;
    }

    public String getMovieLibraryName(){
        return this.name;
    }

    public void addToLibrary(Movie movie){
        this.movieStore.add(movie);
        user.addBalance(50);
        Collections.sort(this.movieStore);
    }

    public void rentMovie(Movie movie){
        user.getRentedMovies().add(movie);
        movieStore.remove(movie);
        user.removeBalance(movie.getPrice());
    }

    public fileHandlerInterface getFileHandler(){
        return fileHandler;
    }

    public String toString(){
        String streng = "";
        for (int i = 0; i < this.movieStore.size(); i ++){
            streng += this.movieStore.get(i) + "\n";
        }
        return streng;
    }

    public static void main(String[] args){
        MovieLibrary library = new MovieLibrary("Filmutleierne");
        Movie diehard = new Movie("Die Hard", "Action", 75, 120,15);
        Movie Interstellar = new Movie("Intersterllar","Sci-Fi",169,120,12);
        Movie shrek = new Movie("Shrek", "Family", 75, 110, 3);
        library.addToLibrary(diehard);
        library.rentMovie(diehard);
        library.rentMovie(shrek);
        //library.addToCart(diehard);
        //library.addToCart(Interstellar);
        //library.addToCart(shrek);
        System.out.println(library);
        System.out.println(library.user.getBalance());
    }
}