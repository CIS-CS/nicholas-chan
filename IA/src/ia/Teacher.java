/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

/**
 * Teacher class
 * @author Nicholas
 */
public class Teacher extends Person{
    /**
     * Constructor of the teacher. Uses constructor of person superclass
     * @param name Name of teacher
     * @param id ID number of teacher
     */
    public Teacher(String name, String id)
    {
        super(name, id);
    }  
    
    /**
     * Serializes fields for persistence
     * @return Serialized fields
     */
    public String serialize()
    {
        String out = "t;";
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