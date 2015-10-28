/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseOrganizer;

import java.util.ArrayList;

/**
 *
 * @author Nicholas
 */
public class Computer {
    //Field declarations
    private String brand = "";
    private String type = "";
    private String model = "";
    private String id = "";
    private boolean condition = true;
    private ArrayList<Classroom> attendingClasses;
    
    /**
     * Constructor of the computer object
     * @param brand Brand of the computer
     * @param id ID number of the computer
     * @param model Model of the computer
     * @param condition Condition of computer, where true denotes working
     */
    public Computer(String brand, String id, String model, boolean condition)
    {
        this.brand = brand;
        this.id = id;
        this.model = model;
        this.condition = condition;
        attendingClasses = new ArrayList<Classroom>();
    }
    
    /**
     * Returns the classes that the computer is assigned to
     * @return ArrayList of Classroom's
     */
    public ArrayList<Classroom> getAttending()
    {
        return attendingClasses;
    }
    
    /**
     * Adds a class to the computer's record of classes
     * @param inClass Classroom to add to the record
     */
    public void addClass(Classroom inClass)
    {
        attendingClasses.add(inClass);
    }
    
    /**
     * Removes a class from the computer's record of classes
     * @param inClass Classroom to remove from the record
     */
    public void removeClass(Classroom inClass)
    {
        if(attendingClasses.remove(inClass)== false)
        {
            System.out.println("class not present");
        }
    }
    
    /**
     * Returns the name of the computer. A combination of its brand and model
     * @return Name of the computer
     */
    public String getName()
    {
        return brand + " " + model;
    }
    
    /**
     * Returns the brand of the computer
     * @return Brand of the computer
     */
    public String getBrand()
    {
        return brand;
    }
    
    /**
     * Returns the ID number of the computer
     * @return ID number of the computer
     */
    public String getID()
    {
        return id;
    }
    
    /**
     * Returns the model of the computer
     * @return Model of the computer
     */
    public String getModel()
    {
        return model;
    }
    
    /**
     * Returns the condition of the computer
     * @return Condition of the computer
     */
    public boolean getCondition()
    {
        return condition;
    }
    
    /**
     * Changes the model of the computer
     * @param inModel New model
     */
    public void changeModel(String inModel)
    {
        model = inModel;
    }
    
    /**
     * Changes the brand of the computer
     * @param inBrand New brand
     */
    public void changeBrand(String inBrand)
    {
        brand = inBrand;
    }
    
    /**
     * Changes the condition of the computer
     * @param inCondition New condition
     */
    public void changeCondition(boolean inCondition)
    {
        condition = inCondition;
    }
    
    /**
     * Serializes fields for persistence
     * @return Serialized fields
     */
    public String serialize()
    {
        String out = "c;"; //0
        String del = ";";
        String con = "";
        
        if(condition == true)
        {
            con = "w"; 
        }
        else
        {
            con = "n"; 
        }
        out = out.concat(brand /*1*/ + del + model /*2*/ + del + con /*3*/ + del + id /*4*/ + del);
        
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
