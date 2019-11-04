import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author Nicholas Lindgren and Steve Cate
 * @version 10/24/19
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<Room> retraceSteps; // used for the back command to retrace steps
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        retraceSteps = new ArrayList<Room>();
        retraceSteps.add(currentRoom); // this is the last place you can go back to 
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // all the rooms that are needed for the game
        Room road1, road2, road3, road4, road5, dadsHouse, sadLake, momsHouse, friendA, friendB, icecreamShop,
            abandonedHouse, mysteryX, work, plotOfBMovie, lectureHall, collegeLab, collegeForest, mtCool, secretLab;
      
        // create the rooms
        // main roadway
        road1 = new Room("outside at the end of the road");
        road2 = new Room("outside on the road");
        road3 = new Room("outside on the road");
        road4 = new Room("outside on the road");
        road5 = new Room("outside at the end of the road");
        // the rest of the rooms houses, shops, college...
        dadsHouse = new Room("in your dads house");
        sadLake = new Room("outside next to the sad lake");
        momsHouse = new Room("in your moms house");
        friendA = new Room("in your friend A's house");
        friendB = new Room("in your friend B's house");
        icecreamShop = new Room("in the icecream shop");
        abandonedHouse = new Room("in a abandoned house, its creepy in here");
        mysteryX = new Room("in mysteryX");
        work = new Room("at your work");
        plotOfBMovie = new Room("plot of b movie");
        lectureHall = new Room("in the lecture hall at the college");
        collegeLab = new Room("in the college lab");
        collegeForest = new Room("in the forest behind the college");
        mtCool = new Room("on top of Mt Cool that makes you cool");
        secretLab = new Room("in a hidden secret lab");
        
        // initialise room exits
        // for each room list exits in order of north, east, south, west.
        // set up the roadway
        road1.setExit("north", momsHouse);
        road1.setExit("south", dadsHouse);
        road1.setExit("west", road2);
        
        road2.setExit("north", friendA);
        road2.setExit("east", road1);
        road2.setExit("south", icecreamShop);
        road2.setExit("west", road3);
        
        road3.setExit("north", abandonedHouse);
        road3.setExit("east", road2);
        road3.setExit("south", friendB);
        road3.setExit("west", road4);
        
        road4.setExit("north", mysteryX);
        road4.setExit("east", road3);
        road4.setExit("south", work);
        road4.setExit("west", road3);
        
        road5.setExit("north", lectureHall);
        road5.setExit("east", road4);
        road5.setExit("south", plotOfBMovie);
        
        // set up all the rooms, houses...
        dadsHouse.setExit("north", road1);
        dadsHouse.setExit("south", sadLake);
        
        sadLake.setExit("north", dadsHouse);
        
        momsHouse.setExit("south", road1);
        
        friendA.setExit("south", road2);
        
        friendB.setExit("north", road3);
        
        icecreamShop.setExit("north", road2);
        
        abandonedHouse.setExit("south", road3);
        
        mysteryX.setExit("south", road4);
        
        work.setExit("north", road4);
        
        plotOfBMovie.setExit("north", road5);
        
        lectureHall.setExit("north", collegeLab);
        lectureHall.setExit("south", road5);
        
        collegeLab.setExit("east", collegeForest);
        collegeLab.setExit("south", lectureHall);
        
        collegeForest.setExit("east", mtCool);
        collegeForest.setExit("west", collegeLab);
        
        mtCool.setExit("north", secretLab);
        mtCool.setExit("west", collegeForest);
        
        secretLab.setExit("south", mtCool);

        currentRoom = dadsHouse;  // start game at dads house
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                System.out.println(currentRoom.getLongDescription());
                break;
                
            case DANCE:
                System.out.println("Calm down this isn't Fortnite.");
                break;
                
            case BACK:
                back(command);
                break;
                
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            retraceSteps.add(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * "Back" was entered so go back to the last room the player was in before the current room
     * also remove the last room from retracesteps so you can keep going back
     */
    private void back (Command command)
    {
        if(command.hasSecondWord())
        {
            System.out.println("If you want to go back to the room before this one simply type 'Back' and nothing else");
            return;
        }
        
        if(retraceSteps.size() == 1)
        {
            System.out.println("You cant go back any father this is where you started.");
        }
        else
        {
            int i = retraceSteps.size();
            currentRoom = retraceSteps.get(i-1);
            System.out.println(currentRoom.getLongDescription());
            retraceSteps.remove(i-1);
        }
    }
}
