
/**
 * Player class is a blueprint of the playergame piece in zuul
 * can be human or ai controlled
 * 
 *
 * @author Nicholas Lindgren
 * @version 11/1/19
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private Room currentRoom;
    private Inventory itemList;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name, Room currentRoom)
    {
        this.name = name;
        this.currentRoom = currentRoom;
        this.itemList = new Inventory(this);
        this.itemList.setLoad(200);
    }

    /**
     * toString 
     * returns name
     * @overrides tostring
     */
    public String toString ()
    {
        // put your code here
        return name;
    }
    public Room currentRoom()
    {
        return currentRoom;
    }
    public void currentRoomNew(Room nextRoom)
    {
        this.currentRoom = nextRoom;
    }
    public Inventory itemList()
    {
        return this.itemList;
    }
}
