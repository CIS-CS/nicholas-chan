/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseOrganizer;

import ibio.IBIO;
import java.util.ArrayList;

/**
 * Student class
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
