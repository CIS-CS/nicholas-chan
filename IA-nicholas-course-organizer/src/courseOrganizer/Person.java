 /*
 * Person.java
 * Created on September 16, 2015
 */
package courseOrganizer;

import java.util.ArrayList;

/**
 * Contains details about individuals
 * @author Nicholas
 * @version 0.0
 */
public class Person 
{
    //Field declarations
    protected String name = "";
    protected String id;
    protected ArrayList<Classroom> attendingClasses; 
    
    /**
     * Constructor of a person
     * @param inName Name of the person
     * @param inID ID of the person
     */
    public Person(String inName, String inID)
    {
        name = inName;

        id = inID;
        
        attendingClasses = new ArrayList<Classroom>();
    }
    
    /**
     * Returns the name of the person
     * @return Name of person
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Changes the name of the person
     * @param inName New name
     */
    public void changeName(String inName)
    {
        name = inName;
    }
    
    /**
     * Returns the ID of the person
     * @return ID number of person
     */
    public String getID()
    {
        return id;
    }
    
    /**
     * Returns the classes that the person attends
     * @return String of classes separated with comma's 
     */
    public String getClasses()
    {
        String output = "";
        
        if(attendingClasses.size() <= 1)
        {
            if(attendingClasses.size() == 0)
            {
                output = "";
            }
            else
            {
                output = attendingClasses.get(0).getName();
            }
        }
        else
        {
            for(Classroom classes: attendingClasses)
            {
                output = output + classes.getName() + ",";
            }
            output = output.substring(0, output.length()-1);
        }
           
        return output;
    }
    
    /**
     * Returns the classes that the person is attending
     * @return ArrayList of classes
     */
    public ArrayList<Classroom> getAttending()
    {
        return attendingClasses;
    }
    
    /**
     * Adds a class to the person's record of classes
     * @param inClass Class to be added
     */
    public void addClass(Classroom inClass)
    {
        attendingClasses.add(inClass);
    }
    
    /**
     * Removes a class from the person's record of classes
     * @param inClass Class to be removed
     */
    public void removeClass(Classroom inClass)
    {
        if(attendingClasses.remove(inClass)== false)
        {
            System.out.println("class not present");
        }
    } 
}
