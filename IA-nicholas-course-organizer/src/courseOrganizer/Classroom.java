/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseOrganizer;

import java.util.ArrayList;

/**
 * Classroom class representing a group of students, teachers, and computers
 * @author Nicholas
 */
public class Classroom {
    //Field declarations
    private String name;
    private String id;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Computer> computers;
    
    
    /**
     * Constructor of the Classroom
     * @param inName Name of the classroom
     * @param inID ID number of the classroom
     */
    public Classroom(String inName, String inID)
    {
        name = inName;  //Name of the classroom
        id = inID;      //ID of the classroom 
        
        students = new ArrayList<Student>();    //Arraylist of all students
        teachers = new ArrayList<Teacher>();    //Arraylist of all teachers
        computers = new ArrayList<Computer>();  //Arraylist of all computers
    }
    
    /**
     * Returns the name of the class
     * @return Name of the class
     */
    public String getName()
    {
        return name; 
    }
    
    /**
     * Changes the name of the class to a given name
     * @param name New name of class
     */
    public void changeName(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the ID number of the class
     * @return ID of class
     */
    public String getID()
    {
        return id;
    }
    
    /**
     * Adds a student to the class
     * @param inStudent Student to be added to class
     */
    public void addStudent(Student inStudent)
    {
        //Check if student is already in class
        boolean containsInput = false;
        for(Student student: students)
        {
            if( inStudent == student )
            {
                containsInput = true;
            }
        }
        //If not in class, add to class
        if(containsInput == false)
        {
            students.add(inStudent);
            inStudent.addClass(this);
        }
    }
    
    /**
     * Adds a teacher to the class
     * @param inTeacher Teacher to be added to class
     */
    public void addTeacher(Teacher inTeacher)
    {
        //Checks if teacher is already in class
        boolean containsTeacher = false;
        for(Teacher teacher: teachers)
        {
            if(inTeacher == teacher)
            {
                containsTeacher = true;
            }
        }
        //If not in class, add to class
        if(containsTeacher == false)
        {
            teachers.add(inTeacher);
            inTeacher.addClass(this);
        }
        
    }
    
    /**
     * Adds a computer to the class
     * @param inComputer Computer to be added to class
     */
    public void addComputer(Computer inComputer)
    {
        //Check if computer is already in class
        boolean containsComputer = false;
        for(Computer computer: computers)
        {
            if(inComputer == computer)
            {
                containsComputer = true;
            }
        }
        //If not in class, add to class
        if(containsComputer == false)
        {
            computers.add(inComputer);
            inComputer.addClass(this);
        }
    }
    
    /**
     * Removes a student from the class if present
     * @param inStudent Student to be removed
     */
    public void removeStudent(Student inStudent)
    {
        boolean containsInput = false;
        for(Student student: students)
        {
            if( inStudent == student )
            {
                containsInput = true;
            }
        }
        
        if(containsInput == true)
        {
            students.remove(inStudent);
            inStudent.removeClass(this);
        }
    }
    
    /**
     * Removes a teacher from the class if present
     * @param inTeacher Teacher to be removed
     */
    public void removeTeacher(Teacher inTeacher)
    {
        boolean containsInput = false;
        for(Teacher teacher: teachers)
        {
            if( inTeacher == teacher )
            {
                containsInput = true;
            }
        }
        
        if(containsInput == true)
        {
            teachers.remove(inTeacher);
            inTeacher.removeClass(this);
        }
    }
    
    /**
     * Removes a computer from the class if present
     * @param inComputer Computer to be removed
     */
    public void removeComputer(Computer inComputer)
    {
        boolean containsInput = false;
        for(Computer computer: computers)
        {
            if( inComputer == computer )
            {
                containsInput = true;
            }
        }
        
        if(containsInput == true)
        {
            computers.remove(inComputer);
            inComputer.removeClass(this);
        }
    }
    
    /**
     * Returns ArrayList of all students
     * @return ArrayList of students
     */
    public ArrayList<Student> getStudents()
    {
        return students;
    }
    
    /**
     * Returns number of students in class
     * @return Number of students
     */
    public int getNumOfStudents()
    {
        return students.size();

    }
    
    /**
     * Returns number of teachers in class
     * @return Number of teachers
     */
    public int getNumOfTeachers()
    {
        return teachers.size();
    }
    
    /**
     * Returns number of computers in class
     * @return Number of computers
     */
    public int getNumOfComputers()
    {
        return computers.size();
    }
    
    /**
     * Returns ArrayList of all teachers
     * @return ArrayList of teachers
     */
    public ArrayList<Teacher> getTeachers()
    {
        return teachers;
    }
    
    /**
     * Returns ArrayList of all computers
     * @return ArrayList of computers
     */
    public ArrayList<Computer> getComputers()
    {
        return computers; 
    }
    
        /**
     * Serializes fields for persistence
     * @return Serialized fields
     */
    public String serialize()
    {
        String out = "g;";
        String del = ";";
        out = out.concat(name + del + id + del);
        
        if(students.size() == 0)
        {
            out = out.concat("empty");
        }
        else
        {
            for(Student studentID: students)
            {
                out = out.concat(studentID.getID() + "~");
            }
        }
        out = out.concat(del);
        
        if(teachers.size() != 0)
        {
            for(Teacher teacherID: teachers)
            {
                out = out.concat(teacherID.getID() + "~");
            }
        }
        else
        {
            out = out.concat("empty");
        }
        out = out.concat(del);
        
        if(computers.size() != 0)
        {
            for(Computer computerID: computers)
            {
                out = out.concat(computerID.getID() + "~");
            }
        }
        else
        {
            out = out.concat("empty");
        }
        out = out.concat(del);
       
        return out;
    }
}
