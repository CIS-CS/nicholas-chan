/*
 * Persistor.java
 * Created on September 16, 2015
 * 
 * Student Structure:
 * class type * name * id * class count * classes... *
 * 
 * Teacher Structure:
 * class type * name * id * class count * classes... * preference count *
 * preferences... 
 */

package ia;

import java.io.FileWriter; //comments
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileNotFoundException;

import ibio.IBIO; //indicate not mine put in diagram

import java.util.ArrayList;

/**
 * Writes and reads a file. 
 * @author Nicholas Chan
 * @version 0.0
 */

public class Persistor 
{
    private String filename = "C:\\Users\\Nicholas\\persistorTest.txt";
    
    private FileWriter fw = null; //init in constructor
    private PrintWriter outFile = null;
    private FileReader fr = null;
    private BufferedReader inFile = null;
    
    private Organizer organizer;
    
    /**
     * Constructor of the Persistor
     * @param inOrg Reference to the Organizer
     */
    public Persistor(Organizer inOrg)
    {
        organizer = inOrg;
    }
    
    /**
     * Saves data into a file
     * @param savLoc 
     */
    public void save(String savLoc)
    {
        FileWriter fw = null;
        PrintWriter outFile = null;
        
        ArrayList<Classroom> classes = organizer.getClasses();
        ArrayList<Student> students = organizer.getStudents();
        ArrayList<Teacher> teachers = organizer.getTeachers();
        ArrayList<Computer> computers = organizer.getComputers();
        try
        {
            fw = new FileWriter(savLoc, true);
            
            outFile = new PrintWriter(fw);
            outFile.println(organizer.serialize());
            for(Classroom saveClass: classes)
            {
                outFile.println(saveClass.serialize());
            }
            for(Student saveStudent: students)
            {
                outFile.println(saveStudent.serialize());
            }
            for(Teacher saveTeacher: teachers)
            {
                outFile.println(saveTeacher.serialize());
            }
            for(Computer saveComp: computers)
            {
                outFile.println(saveComp.serialize());
            }
            outFile.close();
        }
        catch(IOException e)
        {
            IBIO.output("Error writing file: " + e);
            System.exit(0);
        }
    }
    
    /**
     * Loads data from save file
     * @param loadLoc Path of file to load 
     */
    public void load(String loadLoc)
    {
        organizer.clear();
        Stack classStudentStack = new Stack();
        Stack classTeacherStack = new Stack();
        Stack classComputerStack = new Stack();
        String[] lineData; 
        
        FileReader fr = null; 
        BufferedReader inFile = null; 
        
        try {
            fr = new FileReader(loadLoc); 
            inFile = new BufferedReader(fr);
            
            String record = null;
            record = inFile.readLine();  
            while (record != null)
            {
                lineData = record.split(";");
                //System.out.println(lineData);
                processData(lineData, classStudentStack, classTeacherStack,
                        classComputerStack); //try catch block/writiup limitations
                record = inFile.readLine();    // Read the next record until EOF 
            } 
            //System.out.println(organizer.students.get(0).getName());
            // Close the file
            setClassParameters(classStudentStack, classTeacherStack,
                        classComputerStack);
            
            inFile.close();
            
        }
        catch (FileNotFoundException e) {
            // Opening the file may cause this error.
            IBIO.output("Error opening file for reading: " + e);
            System.exit(0);            
        }
        catch (IOException e) {
            // Reading or closing may cause this error.
            IBIO.output("Error reading or closing file: " + e);
            System.exit(0);            
        } 
    }
    
    private void setClassParameters(Stack classStudentStack, 
            Stack classTeacherStack, 
            Stack classComputerStack)
    {
        while(classStudentStack.isEmpty() == false)
        {
            String lineData = classStudentStack.pop();
            String[] data = lineData.split(";");
            
            String originalClassID = data[0];
            
            String[] arrayListData = data[1].split("~");
            
            if(arrayListData[0].equals("empty") == false)
            {
                Classroom newClassroom = organizer.getClassroom(originalClassID);
                for(String id: arrayListData)
                {
                    Student newStudent = organizer.getStudent(id);
                    newClassroom.addStudent(newStudent);
                }
            }
        }
        
        while(classTeacherStack.isEmpty() == false)
        {
            String lineData = classTeacherStack.pop();
            System.out.println(lineData);
            String[] data = lineData.split(";");
            
            String originalClassID = data[0];
            
            String[] arrayListData = data[1].split("~");
            
            if(arrayListData[0].equals("empty") == false)
            {
                Classroom newClassroom = organizer.getClassroom(originalClassID);
                for(String id: arrayListData)
                {
                    Teacher newTeacher = organizer.getTeacher(id);
                    newClassroom.addTeacher(newTeacher);
                }
            }
        }
        
        while(classComputerStack.isEmpty() == false)
        {
            String lineData = classComputerStack.pop();
            System.out.println(lineData);
            String[] data = lineData.split(";");
            
            String originalClassID = data[0];
            
            String[] arrayListData = data[1].split("~");
            
            if(arrayListData[0].equals("empty") == false)
            {
                Classroom newClassroom = organizer.getClassroom(originalClassID);
                for(String id: arrayListData)
                {
                    Computer newComputer = organizer.getComputer(id);
                    newClassroom.addComputer(newComputer);
                }
            }
        }
    }
    
    private void processData(String[] inputData, Stack classStudentStack, 
            Stack classTeacherStack, 
            Stack classComputerStack)
    {
        String init = inputData[0];
        switch(init)
        {
            case "o": 
                int[] totals = new int[4];
                totals[0] = Integer.parseInt(inputData[1]);
                totals[1] = Integer.parseInt(inputData[2]);
                totals[2] = Integer.parseInt(inputData[3]);
                totals[3] = Integer.parseInt(inputData[4]);
                organizer.changeTotals(totals);
                break;
            case "g":
                Classroom classroom = new Classroom(inputData[1], inputData[2]);
                organizer.addClassroom(classroom);
                //System.out.println(inputData[5]);
                classStudentStack.push(inputData[2]+";"+inputData[3]);
                classTeacherStack.push(inputData[2]+";"+inputData[4]);
                classComputerStack.push(inputData[2]+";"+inputData[5]);
                break;
            case "s":
                Student student = new Student(inputData[1], inputData[2]);
                organizer.addStudent(student);
                break;
            case "t":
                Teacher teacher = new Teacher(inputData[1], inputData[2]);
                organizer.addTeacher(teacher);
                break;
            
            case "c":
                boolean condition = false;
                if(inputData[3].equals("w"))
                {
                    condition = true;
                }
                else
                {
                    condition = false;
                }
                Computer computer = new Computer(inputData[1], inputData[4], inputData[2], condition);
                organizer.addComputer(computer);
                break;
        }
    }
    
    /**
     * Changes the file save/load path
     * @param newPath The new path
     */
    public void changePath(String newPath)
    {
        filename = newPath;
    }
    
    /**
     * Returns the file save/load location
     * @return The save/load file location
     */
    public String getPath()
    {
        return filename;
    }
    
}
