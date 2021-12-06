/** Application Purpose: This class contains the state and behaviour of Game objects
 *  Author: Chris Veilleux
 *  Date: November 17, 2021
 *  Time: 16:18
 */

//import the Scanner and InputMismatchException classes from the java.util package
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game
{
    public void begin()
    {
        //array to contain both Player objects
        Player[] players = new Player[2];
        //array to contain symbols that a player can use
        char[] symbols = {'X', 'O'};

        //gather user input for players one and two names and symbols with a Scanner object
        Scanner userInput = new Scanner(System.in);

        //instantiate a Player object for both players
        for (int i = 0; i < 2; i++)
        {
            boolean success = false;
            do
            {
                //ask the user for a name and optionally, a bet
                System.out.println("Enter the name of a player (required), and your bet (optional), separated by a space: ");
                String fullInput = userInput.nextLine();

                //split the user input into their name and their bet, if provided
                String[] splitInput = fullInput.split(" ");

                //based on what the user inputted, decide which Player constructor to use, or call the static method to print an error if they entered invalid input
                switch (splitInput.length) {
                    case 1:
                        players[i] = new Player(fullInput, symbols[i]);
                        success = true;
                        break;
                    case 2:
                        try
                        {
                            String firstPlayerName = splitInput[0];
                            //note use of a wrapper class:
                            Integer firstPlayerBet = new Integer(splitInput[1]);
                            players[i] = new Player(firstPlayerName, symbols[i], firstPlayerBet.intValue());
                            success = true;
                        } catch (NumberFormatException e) {
                            printAppropriateErrorMessage(4);
                        }
                        break;
                    default:
                        printAppropriateErrorMessage(5);
                }
            } while (!success);
        }

        //if the two player names match, get the user to enter a new name for the second user
        while ((players[0].getPlayerName()).equalsIgnoreCase(players[1].getPlayerName()))
        {
            printAppropriateErrorMessage(6);
            String newPlayerName = userInput.nextLine();
            players[1].setPlayerName(newPlayerName);
        }

        //instantiate a new GameBoard object
        GameBoard gb1 = new GameBoard();

        //activeColumn will hold the column the player wishes to add a chip to
        int activeColumn;
        //result will be used to determine if the player has entered a valid row
        int result;
        //continueGame will become false when a player has won
        boolean continueGame = true;

        //while loop will run until a player has won or a tie occurs
        while (continueGame)
        {
            //alternate code within for loop for each of the players
            for (int i = 0; i < 2; i++)
            {
                //print game board
                gb1.printGameBoard();

                //gather valid input from the player about where they wish to place a chip
                do
                {
                    System.out.println(players[i].getPlayerName() + ": In which column would you like to place your chip? (1-7) ");
                    //exception handling for when the user enters anything but numerical values
                    try
                    {
                        activeColumn = userInput.nextInt() - 1;
                        result = gb1.placeNewChip(players[i].getSymbol(), activeColumn);
                    }
                    catch (InputMismatchException e)
                    {
                        userInput.nextLine();
                        result = 1;
                        printAppropriateErrorMessage(result);
                    }
                } while (result != 0); //if 1 is returned, the move was invalid. if 0 is returned, the chip was successfully placed

                //check if the player being referenced on this iteration has won
                continueGame = !gb1.hasPlayerWon(players[i].getSymbol());
                if (!continueGame)
                {
                    players[i].setWinner(true);
                    break;
                }

                //check if the board is full, and therefore a tie game
                continueGame = !gb1.isTieGame();
                if (!continueGame)
                    break;
            }
        }

        //determine who has won the game
        String winner;
        if (players[0].getWinner())
            winner = players[0].getPlayerName();
        else if (players[1].getWinner())
            winner = players[1].getPlayerName();
        else
            winner = "tie";

        //print the final game board
        System.out.println("*********************** GAME OVER ***********************");
        gb1.printGameBoard();

        //congratulate the winner by calling the static method printWinner in the Player class
        Player.printWinner(winner,players[0].getBet(),players[1].getBet());

        //close the Scanner object
        userInput.close();
    }

    //static method will print an error message that is specific to the type of exception thrown
    public static void printAppropriateErrorMessage(int errorType)
    {
        switch (errorType)
        {
            case 1:
                System.out.println("Enter only numerical values");
                break;
            case 2:
                System.out.println("Sorry, that column does not exist.");
                break;
            case 3:
                System.out.println("Sorry, that column is full already.");
                break;
            case 4:
                System.out.println("Enter only digits for your bet please.");
                break;
            case 5:
                System.out.println("Only provide your name, or your name and a numerical bet please.");
                break;
            case 6:
                System.out.println("Player names cannot match. Please enter a new name for player two: ");
        }
    }
}
