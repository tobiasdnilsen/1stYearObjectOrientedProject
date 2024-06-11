package Prosjektoppgave;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class MovieStoreController {

    
    @FXML
    public Button btnAddMovies;
    public Button btnAddBalance;
    public Button btnRentMovie;
    public Label labelBalance, LabelNoMovieText, labelNoBalance, labelNoRent;
    public TextField txtDeposit, txtAddToLib;
    public ListView<Movie> txtMoviesAvailable;
    public ListView<Movie> txtRentedMovies;
    private MovieLibrary library;
    private UserClass user;
    private ArrayList<Movie> rentedMovies;

    @FXML
    public void initialize(){
        library = new MovieLibrary("Stay Keeg filmutleie");
        user = library.getUser();
        txtMoviesAvailable.getItems().addAll(library.getLibrary());
        labelBalance.setText(Integer.toString(user.getBalance()) + " NOK");
        rentedMovies = new ArrayList<>();
    }

    @FXML 
    public void addMovie() throws IllegalAccessException{
        try {
            System.out.println(this.txtAddToLib.getText()); 
            Movie movie = createMovie();
            library.addToLibrary(movie);
            txtMoviesAvailable.getItems().add(movie);
            labelBalance.setText(Integer.toString(user.getBalance()) + " NOK");
            this.LabelNoMovieText.getText();
            this.LabelNoMovieText.setText("");
            }
        catch (Exception e){
            this.LabelNoMovieText.setText("Invalid syntax.");
            throw new IllegalAccessException();   
        }
        
        }
        

    @FXML
    public void addBalance() throws IllegalAccessException{

        try {
            int sum = Integer.parseInt(this.txtDeposit.getText());
            user.addBalance(sum);
            int newSum = user.getBalance();
            labelBalance.setText(newSum + " NOK");
            this.labelNoBalance.setText("");
        }
        catch (Exception e) {
            labelNoBalance.setText("Deposit must be positive digits.");
            throw new IllegalAccessException();
        }
        
    }

    public void rentMovie()throws IllegalAccessException{
        try {
            Movie movie = txtMoviesAvailable.getSelectionModel().getSelectedItem();
            
            if (movie.equals(null)){
                this.labelNoRent.setText("No movie selected.");
                throw new IllegalAccessException();
            }
            if (movie.getPrice() <= user.getBalance()){
                txtRentedMovies.getItems().add(movie);
                txtMoviesAvailable.getItems().remove(movie);
                library.rentMovie(movie);
                int newSum = user.getBalance();
                labelBalance.setText(newSum + " NOK");
                this.labelNoRent.setText(""); 
            }
            else{
                this.labelNoRent.setText("Add more funds.");
            }
            }

        catch (Exception e){
            this.labelNoRent.setText("No movie selected.");
            throw new IllegalAccessException();
        }
        
    }

    @FXML
    public void writeReceipt(){
        library.getFileHandler().writeReceipt(library.getUser());
    }

    private Movie createMovie(){
        String textString = this.txtAddToLib.getText();
        String[] lineInfo = textString.split(", ");
        String title = lineInfo[0];
        String genre = lineInfo[1];
        int length = Integer.parseInt(lineInfo[2]);
        int ageLimit = Integer.parseInt(lineInfo[3]);
        Movie movie = new Movie(title, genre, 50, length, ageLimit);
        return movie;
    }


   
}
