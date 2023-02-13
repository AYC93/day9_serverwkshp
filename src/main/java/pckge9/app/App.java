package pckge9.app;

import java.io.Console;
import java.util.Random;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        Random rand = new Random();
        
        // generate random number between 0 and 100
        Integer randomNumber = rand.nextInt(100);

        // store my guess
        Integer guess = 0;

        Console cons = System.console();
        
        // while loop if guessing forever, for loop if limited

        while(guess != randomNumber){
            
            String strGuess = cons.readLine("Please guess the random number.");
            guess = Integer.parseInt(strGuess);
            if (guess < randomNumber){
                message("^^^ Higher");
            }else if (guess > randomNumber) {
                message("vvvv Lower");
            } else{
                message("You got the right answer!");
            }
    }
}

    private static void message(String s) {
        System.out.println(s);
    }
}
