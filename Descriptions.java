
/**
 * 
 * this class holds all the text desciptions as strings in an array. 
 *
 * @author Nicholas Lindgren
 * @version 10/21/19
 */
import java.util.ArrayList;

public class Descriptions
{
    private static ArrayList<String> list;

    /**
     * Constructor for objects of class Descriptions
     */
    public Descriptions()
    {
        this.list = new ArrayList<String>(100);
    }

    /**
     * prints description at a given position of the index
     * @todo structure the index to be typed 
     * @param index - position of string you want for a given description
     * 
     */
    public static void printDescription(int index)
    {
        // put your code here
       System.out.println(" " +list.get(index));
    }
}
