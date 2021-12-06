/** Application Purpose: This class contains the state and behaviour of GameBoard objects
 *  Author: Chris Veilleux
 *  Date: November 17, 2021
 *  Time: 16:32
 */

//import the valueOf method from the String class of the java.lang package
import static java.lang.String.valueOf;

public class GameBoard
{
    //state of GameBoard objects: multi-dimensional array containing the locations of the chips on the game board
    private char[][] chipsOnBoard;

    //constructor that creates a new game board of 6 rows and 7 columns (standard Connect 4 size)
    public GameBoard()
    {
        chipsOnBoard = new char[6][7];
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                chipsOnBoard[i][j] = ' ';
            }
        }
    }

    //method that will print the game board to the console when called
    public void printGameBoard(){
        System.out.println("---------------------------------------------------------");
        for(int i = 0; i < 6; i++)
        {
            System.out.print("|   ");
            for (int j = 0; j < 7; j++)
            {
                System.out.print(chipsOnBoard[i][j] + "   |   ");
            }
            System.out.println();
            System.out.println("---------------------------------------------------------");
        }
        System.out.println("    1       2       3       4       5       6       7");
    }

    //method to place a new chip on the game board
    public int placeNewChip(char activePlayerSymbol, int column)
    {
        for (int i = 5; i > -1; i--)
        {
            //exception handling for when the user tries to add a chip to a column outside the bounds
            try
            {
                if (chipsOnBoard[i][column] == ' ')
                {
                    chipsOnBoard[i][column] = activePlayerSymbol;
                    return 0;
                }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                Game.printAppropriateErrorMessage(2);
                return 2;
            }
        }
        Game.printAppropriateErrorMessage(3);
        return 3;
    }

    //method for determining if the game has been won yet
    public boolean hasPlayerWon(char playerSymbol)
    {
        String row;
        StringBuilder column = new StringBuilder();
        String diagonal;

        //create a String of four of the player's symbols
        StringBuilder fourPlayerSymbols = new StringBuilder();
        fourPlayerSymbols.append(String.valueOf(playerSymbol).repeat(4));

        //each row in the 2d array represents one diagonal on the game board
        char[][] diagonals = {
                {chipsOnBoard[2][0], chipsOnBoard[3][1], chipsOnBoard[4][2], chipsOnBoard[5][3]},
                {chipsOnBoard[1][0], chipsOnBoard[2][1], chipsOnBoard[3][2], chipsOnBoard[4][3], chipsOnBoard[5][4]},
                {chipsOnBoard[0][0], chipsOnBoard[1][1], chipsOnBoard[2][2], chipsOnBoard[3][3], chipsOnBoard[4][4], chipsOnBoard[5][5]},
                {chipsOnBoard[0][1], chipsOnBoard[1][2], chipsOnBoard[2][3], chipsOnBoard[3][4], chipsOnBoard[4][5], chipsOnBoard[5][6]},
                {chipsOnBoard[0][2], chipsOnBoard[1][3], chipsOnBoard[2][4], chipsOnBoard[3][5], chipsOnBoard[4][6]},
                {chipsOnBoard[0][3], chipsOnBoard[1][4], chipsOnBoard[2][5], chipsOnBoard[3][6]},
                {chipsOnBoard[0][3], chipsOnBoard[1][2], chipsOnBoard[2][1], chipsOnBoard[3][0]},
                {chipsOnBoard[0][4], chipsOnBoard[1][3], chipsOnBoard[2][2], chipsOnBoard[3][1], chipsOnBoard[4][0]},
                {chipsOnBoard[0][5], chipsOnBoard[1][4], chipsOnBoard[2][3], chipsOnBoard[3][2], chipsOnBoard[4][1], chipsOnBoard[5][0]},
                {chipsOnBoard[0][6], chipsOnBoard[1][5], chipsOnBoard[2][4], chipsOnBoard[3][3], chipsOnBoard[4][2], chipsOnBoard[5][1]},
                {chipsOnBoard[1][6], chipsOnBoard[2][5], chipsOnBoard[3][4], chipsOnBoard[4][3], chipsOnBoard[5][2]},
                {chipsOnBoard[2][6], chipsOnBoard[3][5], chipsOnBoard[4][4], chipsOnBoard[5][3]}
        };

        //check if a player has four adjacent chips in a row
        for (int i = 0; i < 6; i++)
        {
            //create a String from all the values in one row
            row = valueOf(chipsOnBoard[i]);

            //check if the row contains four adjacent identical symbols
            if (row.contains(fourPlayerSymbols.toString()))
            {
                //player has indeed got four symbols in a row
                return true;
            }
        }

        //check if a player has four adjacent chips in a column
        for (int i = 0; i < 7; i++)
        {
            //create a String from all the values in one column
            for (int j = 0; j < 6; j++)
            {
                column.append(chipsOnBoard[j][i]);
            }

            //check if the column contains four adjacent identical symbols
            if (column.toString().contains(fourPlayerSymbols.toString()))
            {
                //player has indeed got four symbols in a column
                return true;
            }

            //delete the contents of the column so the next column can get checked
            column.delete(0,6);
        }

        //check if a player has four adjacent chips in a diagonal
        for (int i = 0; i < 12; i++)
        {
            //create a String from all the values in one diagonal
            diagonal = valueOf(diagonals[i]);

            //check if the diagonal contains four adjacent identical symbols
            if (diagonal.contains(fourPlayerSymbols.toString()))
            {
                //player has indeed got four symbols in a diagonal
                return true;
            }
        }

        //no player has four adjacent chips on the game board
        return false;
    }

    //method to check if the game board is full, therefore a tie game
    public boolean isTieGame()
    {
        //create a string comprised of the 7 items in the first row of a GameBoard
        if (!valueOf(chipsOnBoard[0]).contains(" "))
        {
            return true;
        }
        else
            return false;
    }
}
