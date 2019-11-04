
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
    private int maxLoad;
    private int rLoad;
    private boolean consumable;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory(Object location)
    {
        this.itemList = new ArrayList<Item>();
        this.location = new HashMap <String, Object>();
        this.name = location.toString();
        this.maxLoad = -1;
        this.rLoad = 0;
        this.consumable = false;
    }
    /**
     * setLoad
     * sets the load of the player added to argument 
     * sets the max load of this invenetory
     * @param p = some max load 
     */
    public void setLoad(int p)
    {
       maxLoad =p;
       rLoad = maxLoad;
    }
    /**
     * setLoad 
     * sets the load of room object
     */
    public void setLoad()
    {
        maxLoad = -1;
        rLoad = -1;
    }

    /**
     * addItemFromLocal
     * finds the item in the list, adds it if its there and not too heavy or would exceed load
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
                if(this.loadSafe(i) == true)
                {
                    
                    destination.addTo(new Item(i));
                    this.itemList.remove(i);
                    System.out.println("success");
                    return;
                }
                
            }
        }
        System.out.println("failure to find");
    }
    /**
     * consume 
     * implements magic cookie thing. 
     */
    public void consume (String name)
    {
        boolean found = false;
        Iterator it = itemList.iterator();
        while(found == false && it.hasNext())
        {
            Item i = (Item)it.next();
            if(i.giveName().equals(name)&& this.consumable==true)
            {
                if(this.loadSafe(i) == true)
                {
                    this.maxLoad += i.giveWeight();
                    this.itemList.remove(i);
                    System.out.println("success");
                    return;
                }
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
    /**
     * addTo
     * @param item - item to add into the arrayList
     */
    public void addTo(Item i)
    {
        if(this.loadSafe(i)==true)
        {
              this.rLoad += loadCorrect(i.giveWeight());
              this.itemList.add(i);
              System.out.print("item added");
              return;
        }
            System.out.print("loadsafe == false");
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
     * locations are player objects or room objects
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
    /**
     * load safe
     * @param item - checks if room or player
     * @return - true if safe
     */
    public boolean loadSafe(Item i)
    {
        if(!(this.maxLoad == -1))
        {
            if(i.giveWeight()> maxLoad)
            {
                    System.out.println("too heavy. can't pick up");
                    return false;
            }
            if( i.giveWeight() > rLoad )
            {
                     System.out.println("not enough remaining weight");
                     return false;
            }
            
            return true;
        }
     return true;
    }
    /**
     * loadCorrect
     * @param int i - item's weight
     * @return int- value to adjust for room or player's rLoad
     */
    public int loadCorrect(int i)
    {
        if (this.maxLoad == -1)
        {
            return 0;
        }
        return i;
    }
    /**
     * isConsumable 
     * sets consumable to true if item is magic cookie (should be enum)
     */
    private void isConsumable()
    {
        if(this.name.equals("magic_cookie"))
        {
            this.consumable =true;
            
        }
    }
}
