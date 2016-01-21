package courseOrganizer;

import java.util.ArrayList;

/**
 * Object model of a student
 * @author Nicholas
 */
public class Student extends Person {
    
    /**
     * Constructor of a student. Uses constructor from Person superclass
     * @param name Name of student
     * @param id ID of student
     */
    public Student(String name, String id)
    {
        super(name, id);
    }
    
    /**
     * Serializes fields from the student for persistence
     * @return String of serialized fields 
     */
    public String serialize() 
    {
        String out = "s;";
        String del = ";";
        out = out.concat(name + del + id + del);
        
        if(attendingClasses.size() != 0)
        {
            for(Classroom classID: attendingClasses)
            {
             out = out.concat(classID.getID() + "~");
            }
        }
        else
        {
            out = out.concat("empty");
        }
        
        return out;
    }
}
