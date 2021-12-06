/** Application Purpose: This class will 'jump start' the program by instantiating a game object and calling its method
 *  Author: Chris Veilleux
 *  Date: November 17, 2021
 *  Time: 16:14
 */

public class ConnectFour
{
    public static void main(String[] args)
    {
        //instantiate a new Game object
        Game g1 = new Game();
        //call the begin method from g1 object
        g1.begin();
    }
}