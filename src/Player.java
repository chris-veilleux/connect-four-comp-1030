/** Application Purpose: This class contains the state and behaviour of Player objects
 *  Author: Chris Veilleux
 *  Date: November 17, 2021
 *  Time: 16:23
 */

public class Player
{
    private String playerName; //player's name
    private Boolean winner; //value representing if the player is the winner
    private char symbol; //player's unique symbol to be placed on the game board
    private int bet; //player's bet

    //constructor to create a new Player object
    public Player(String playerName, char symbol)
    {
        this.playerName = playerName;
        winner = false;
        this.symbol = symbol;
        bet = 0;
    }

    //overloaded constructor to create a new Player object
    public Player(String playerName, char symbol, int bet)
    {
        this.playerName = playerName;
        winner = false;
        this.symbol = symbol;
        this.bet = bet;
    }

    //setter for player's name
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }
    //getter for player's name
    public String getPlayerName()
    {
        return playerName;
    }
    //setter for winner value of Player objects
    public void setWinner(boolean winner)
    {
        this.winner = winner;
    }
    //getter for winner value of Player objects
    public boolean getWinner()
    {
        return winner;
    }
    //getter for a Player object's unique symbol
    public char getSymbol()
    {
        return symbol;
    }
    //getter for a Player object's bet value
    public int getBet()
    {
        return bet;
    }

    //static method to print a message for the winning user
    public static void printWinner(String winner, int playerOneBet, int playerTwoBet)
    {
        int prizeMoney = playerOneBet + playerTwoBet;
        System.out.println("*********************************************************");
        if (winner.equalsIgnoreCase("tie"))
        {
            System.out.println("Tie game! Nobody wins the $" + prizeMoney);
        }
        else
        {
            System.out.println("Congratulations " + winner + ", you have won: $" + prizeMoney);
        }
    }
}
