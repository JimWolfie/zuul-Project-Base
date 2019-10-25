
/**
 * The main function to get the game created and running when the user opens the program
 *
 * @author Nicholas Lindgren and Steve Cate
 * @version 10/24/19
 */
public class Main
{
    static Game newGame;
    /**
     * This will make a new game and run the play method of the game so it will start
     */
    public static void Main()
    {
        // This will make a fresh game to play from the start
        newGame = new Game();
        
        // This will run the game so that the user can start playing
        newGame.play();
        
        // This will close out of the game when it is over
        System.exit(0);

    }
}
