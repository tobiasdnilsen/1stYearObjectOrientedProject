package Prosjektoppgave;

import java.util.ArrayList;
import java.util.Collections;

public class Movie implements Comparable<Movie> {
    private String movieTitle;
    private String movieGenre;
    private int price;
    private int movieLength; // in minutes
    private int ageLimit;

    public Movie(String movieTitle, String movieGenre, int price, int movieLength, int ageLimit){
        if (movieTitle.length() < 1 || movieTitle.length() > 100){
            throw new IllegalArgumentException("Error: Invalid movie title");
        }

        if (movieGenre.length() < 1 || movieGenre.length() > 50){
            throw new IllegalArgumentException("Error: Invalid movie genre");
        }

        if (price < 0){
            throw new IllegalArgumentException("Error: Invalid movie price");
        }

        if (movieLength < 10){
            throw new IllegalArgumentException("Error: Invalid movie length");
        }
        if (ageLimit < 0 || ageLimit > 20){
            throw new IllegalArgumentException("Age limit not valdíd.");
        }

        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.price = price;
        this.movieLength = movieLength;
        this.ageLimit = ageLimit;
    }

    public String getMovieTitle(){
        return this.movieTitle;
    }

    public String getMovieGenre(){
        return movieGenre;
    } 

    public int getPrice(){
        return this.price;
    }

    public int getMovieLength(){
        return this.movieLength;
    }

    public int getAgeLimit(){
        return this.ageLimit;
    }

    @Override
    public int compareTo(Movie movie) {
        return this.movieTitle.compareTo(movie.movieTitle);
    }

    public String toString(){
        return "Movie title: " + getMovieTitle() + "\nMovie genre: " + getMovieGenre() + "\nMovie length: " + getMovieLength() + " minutes \nPrice: " + getPrice()+" NOK";
    }

    public static void main(String[] args){
        Movie diehard = new Movie("Die Hard", "Action", 75, 120,15);
        Movie Interstellar = new Movie("Intersterllar","Sci-Fi",169,120,12);
        System.out.println(diehard);
        System.out.println(Interstellar);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(diehard);
        movies.add(Interstellar);
        Collections.sort(movies);
        System.out.println(movies);
    }
}