
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
/**
 * Inventory objects for player and room classes
 *
 * @author NicholasLindgren
 * @version 11/3/19
 */
public class Inventory
{
    private ArrayList<Item> itemList;
    private HashMap<String, Object> location;
    private String name;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory(Object location)
    {
        this.itemList = new ArrayList<Item>();
        this.location = new HashMap <String, Object>();
        this.name = location.toString();
    }

    /**
     * addItemFromLocal
     * finds the item in the list, adds it if its there.
     * @param orgin -name of item to find
     * @param destination- Inventory of the object you want to add the item to
     */
    public void addItemFromLocal(String name, Inventory destination)
    {
        boolean found = false;
        Iterator it = itemList.iterator();
        while(found == false && it.hasNext())
        {
            Item i = (Item)it.next();
            if(i.giveName().equals(name))
            {
                found =true;
                destination.addTo(new Item(i));
                this.itemList.remove(i);
                System.out.println("success");
            }
        }
        
        
    }
    /**
     * printItemIndex
     * prints the index and item's name
     */
    public void printItemIndex()
    {
        System.out.println("Items found here: ");
        for(Item i : itemList)
        {
           System.out.print(""+i);
           i.printName();           
        }
    }
    
    public void addTo(Item i)
    {
        this.itemList.add(i);
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
     * locations are Inventories
     * @param location - room / player its found on
     */
    public void moveItem(Object local)
    {
        location.clear();
        location.put(this.name, local); //sets location    
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
     * toString
     * @overrides toString
     */
    public String toString ()
    {
        return name;
    }
}
