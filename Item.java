import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Item class.
 * similar to the room class
 * each item has description and weight. 
 * @author nicholas Lindgren
 * @version 10/31/19
 */
public class Item
{
    private String description;
    private String name;
    private int weight;
    private HashMap<String, Object> location; 

    /**
     * Constructor for objects of class Item
     * use the moveItem function to set its initial location
     * @param description
     * @param name
     * @param weight
     */
    public Item(String description, String name, int weight)
    {
        this.description = description;
        this.name = name;
        this.weight = weight;
        this.location = new HashMap<String, Object>();
        
       
    }
    /**
     * Constructor for objects of the class Item
     * duplicates the item by passing in an existing item. 
     * probably don't need this but the method is there
     * @param Item i
     */
    public Item(Item i)
    {
        this.description = i.giveDescription();
        this.name = i.giveName();
        this.weight = i.giveWeight();
        this.location = new HashMap<String, Object>();
        this.location.put(this.name, i.returnLocation());
    }
    /**
     * giveLocation
     * String representation of location object
     * @return name -
     */
    public String giveLocation()
    {
        if(!location.isEmpty())
        {
            Object t = location.get(this.name);
            
            return t.toString();
        }
        return null; 
        
    }
    /**
     * moveItem
     * assigns the item to a location
     * locations can be inventory
     * @param location - room / player its found on
     */
    public void moveItem(Object local)
    {
        location.clear();
        location.put(this.name, local); //sets location    
    }
    /**
     * giveName
     * @return name
     */
    public String giveName()
    {
        return name;
    }
    /**
     * giveWeight
     * @return weight
     */
    public int giveWeight()
    {
        return weight;
    }
    /**
     * giveDescription
     * @return description
     */
    public String giveDescription()
    {
        return description;
    }
    /**
     * returnLocation
     * @return- a reference to the object in the hashmap to reassign. 
     */
    public Object returnLocation()
    {
        return location.get(this.name);
    }
       /**
     * printDescription
     * prints the description of the item
     
     */
    public void printDescription()
    {
       System.out.print(description);
    }
    /**
     * print name 
     * prints the name of the item
     */
    public void printName()
    {
        // put your code here
       System.out.print(name);
    }
    /**
     * prints weigh
     * prints the weight of the item
     * there is a leading space 
     */
    public void printWeight()
    {
        // put your code here
       System.out.print(""+weight); 
    }
    
}
