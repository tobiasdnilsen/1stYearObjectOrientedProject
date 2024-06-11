package Prosjektoppgave;
import java.util.ArrayList;

public class UserClass {
    
    private String username;
    private int age;
    private int balance;
    private ArrayList<Movie> rentedMovies;

    public UserClass(String StartUsername, int StartAge, int StartBalance){
        
        if (StartUsername.length() < 1){
            throw new IllegalArgumentException("You need characters in your username.");
        }
        this.username = StartUsername;

        if (StartAge < 0){
            throw new IllegalArgumentException("Age cant be a negative number.");
        }
        this.age = StartAge;

        if (StartBalance < 0){
            throw new IllegalArgumentException("You cannot have a negative number in your wallet.");
        }

        this.balance = StartBalance;
        rentedMovies = new ArrayList<>();
        
        }

    public String getUsername() {
        return this.username;
    }

   

    public int getAge() {
        return this.age;
    }

    public boolean overOr18(){
        if (this.age >= 18){
            return true;
        }

        else {
            return false;
        }
    }

    public int getBalance(){
        return this.balance;
    }


    public void addBalance(int sum){
        if (sum > 0){
            this.balance = (sum + this.balance);
        }
        else {
            throw new IllegalArgumentException("You cant add a negative number to your wallet.");
        }
    }

    public void removeBalance(int sum) throws IllegalArgumentException {
        if (sum > this.balance){
            throw new IllegalArgumentException("Error: Not enough balance");
        }

        if (sum < 0){
            throw new IllegalArgumentException("Cannot remove a negative integer");
        }

        this.balance -= sum;
    }

    public ArrayList<Movie> getRentedMovies(){
        return rentedMovies;
    }

    }
