import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author Nicholas Lindgren and Steve Cate
 * @version 10/24/19
 */

public class Room 
{
    private String name;
    private String description;
    private HashMap<String, Room> exits;// stores exits of this room.
    private ArrayList<Item> itemList;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's name
     */
    public Room(String description, String name) 
    {
        this.description = description;
        this.name = name;
        exits = new HashMap<>();
        itemList = new ArrayList<Item>();
    }
    /**
     * Creates a shallow copy of the hash map for reference reasons.
     * not implemented but there for completeness sake
     * @param name - string of obj you want to reference
     * @return clone - pointer to the object's hashmap
     
    public HashMap clonedRoom ()
    {
        
        return (HashMap)exits.clone();
        
    }*/
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor); //defines neighbors 
        exits.putIfAbsent("self", this); //defines itself as its own neighbor
        //self defined for completion reasons 
    }
    /**
     * addNewItem
     * constructs an item and adds it to the list.
     * also sets the item's hashmap 
     * @param description
     * @param name
     * @param weight
     */
    public void addNewItem(String description, String name, int weight)
    {
        Item i = new Item(description, name, weight); //make new item
        i.moveItem(this); //sets the hashmap in the item class to the calling object
        itemList.add(i); //add to list
        
    }
    /**
     * addItemFromLocal
     * finds 
     * @todo add in code
     * @param index - index of the item of this object's arraylist. 
     * @param destination- arraylist of the object you want to add the item to
     */
    public void addItemFromLocal (int index, ArrayList<Item> destination)
    {
        
    }
    /**
     * printItemIndex
     * prints a string with this object's arraylist and index
     * @todo add in code
     * 
     */
    public void printItemIndex()
    {
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    public boolean isName (String name)
    {
        if(this.name.equals(name))
        {
            return true;
        }
        return false;
    }
    /**
     * toString
     * @overrides toString
     */
    public String toString ()
    {
        return name;
    }
}

