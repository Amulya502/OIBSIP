package sample;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GuessTheNumber{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 10;
        int maxRounds = 10;
        int round = 0;

        JOptionPane j = new JOptionPane();

        System.out.println("Welcome to the Guess the Number game!");
        whilelab:
        while (round < maxRounds) {
            round++;
            int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
          
           
            System.out.println("I'm thinking of a number between " + lowerBound + " and " + upperBound);
            int attempts = 0;
            int score = maxAttempts; // Initialize score to maximum
            System.out.println("Menu:");
            System.out.println("1. Guess the number");
            System.out.println("2. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            while (attempts < maxAttempts) {
                

                switch (choice) {
                    case 1:
                        System.out.print("Enter your guess: ");
                        int userGuess = scanner.nextInt();
                        attempts++;

                        if (userGuess == randomNumber) {
                            System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                            j.showMessageDialog(null,
                                    "Congratulations! You guessed the number in " + attempts + " attempts.");
                            score = maxAttempts - attempts; // Calculate score based on remaining attempts
                            j.showMessageDialog(null, "Your score: " + score * 10);
                            break whilelab;
                        } else if (userGuess < randomNumber) {
                            System.out.println("Try a higher number.");
                        } else {
                            System.out.println("Try a lower number.");
                        }

                        int attemptsLeft = maxAttempts - attempts;
                        if (attemptsLeft > 0) {
                            System.out.println("Attempts left: " + attemptsLeft);
                        } else {
                            System.out.println("Out of attempts. The number was " + randomNumber);
                        }
                        break;
                    case 2:
                        System.out.println("Thank you for playing!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please select 1 or 2.");
                }
            }

            System.out.println("Round " + round + " completed.");
            if (round < maxRounds) {
                System.out.print("Do you want to play another round? (1 for Yes, 2 for No): ");
                int playAgainChoice = scanner.nextInt();
                if (playAgainChoice != 1) {
                    System.out.println("Thank you for playing!");
                    scanner.close();
                    System.exit(0);
                }
            } else {
                System.out.println("You have completed " + maxRounds + " rounds. Thank you for playing!");
            }
        }
    }
}

