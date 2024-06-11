package Prosjektoppgave;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface fileHandlerInterface {
    public void writeReceipt(UserClass user);
    public ArrayList<Movie> getStandardMovies() throws FileNotFoundException;
}
