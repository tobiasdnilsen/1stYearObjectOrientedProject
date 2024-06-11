package Prosjektoppgave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class fileHandler implements fileHandlerInterface {

    @Override
    public ArrayList<Movie> getStandardMovies() throws FileNotFoundException {
        ArrayList<Movie> movies = new ArrayList<>();
        String filePath = new File("standardfilmer.txt").getAbsolutePath();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lineInfo = line.split(";");
            String movieTitle = lineInfo[0];
            String movieGenre = lineInfo[1];
            int moviePrice = Integer.parseInt(lineInfo[2]);
            int movieLength = Integer.parseInt(lineInfo[3]);
            int movieAgeLimit = Integer.parseInt(lineInfo[4]);
            movies.add(new Movie(movieTitle, movieGenre, moviePrice, movieLength, movieAgeLimit));
        }
        scanner.close();
        Collections.sort(movies);
        return movies;
    }

    @Override
    public void writeReceipt(UserClass user){
        // skriver ut informasjon om film til en tekstfil
        String filnavn = "receipt.txt";
        try {
            PrintWriter writer = new PrintWriter(filnavn);
            for (int i = 0; i < user.getRentedMovies().size(); i++){
                Movie movie = user.getRentedMovies().get(i);
                writer.println("Movie title: " + movie.getMovieTitle());
                writer.println("Rated age: " + movie.getAgeLimit());
                writer.println("Movie genre: " + movie.getMovieGenre());
                writer.println("Movie length: " + movie.getMovieLength());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
