/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseOrganizer;

import java.util.ArrayList;

/**
 * Organizes the Object Model. Interfaced with object model and GUI
 * @author Nicholas
 */
public class Organizer {
    //Arraylist declarations
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private ArrayList<Classroom> classes = new ArrayList<Classroom>();
    private ArrayList<Computer> computers = new ArrayList<Computer>();
    
    //Field declarations
    private int totalStudents;
    private int totalTeachers;
    private int totalClasses;
    private int totalComputers;
    /**
     * Constructor of the Organizer
     */
    public Organizer()
    {
        
    }
    
    public void clear()
    {
        students = new ArrayList<Student>();
        teachers = new ArrayList<Teacher>();
        classes = new ArrayList<Classroom>();
        computers = new ArrayList<Computer>();
    }
    
    /**
     * Creates a new student and adds them to the collection of Students
     * @param name Name of the new student
     */
    public void addStudent(String name)
    {
        Student newStudent = new Student(name, "S"+ String.format("%05d",totalStudents));
        totalStudents++;
        students.add(newStudent); 
    }
    
    public void addStudent(Student inStudent)
    {
        students.add(inStudent);
    }
    /**
     * Removes a student with a given ID
     * @param id ID of the student to be removed
     */
    public void removeStudent(String id)
    {
        Student studentRem = null;
        for(Student student: students)
        {
            if(student.getID() == id)
            {
                studentRem = student;
            }
        }
        students.remove(studentRem);
    }
    
    /**
     * Returns a student with a given ID. 
     * @param id ID of the student
     * @return Student corresponding to input ID. Returns null if not found
     */
    public Student getStudent(String id)
    {
        Student studentGet = null;
        for(Student student: students)
        {
            //System.out.println(student.getID());
            if(student.getID().equals(id))
            {
                studentGet = student;
            }
        }
        
        return studentGet;
    }
    
    /**
     * Returns a teacher with a given ID
     * @param id ID of the teacher
     * @return Teacher corresponding to input ID. Returns null if not found
     */
    public Teacher getTeacher(String id)
    {
        Teacher teacherGet = null;
        for(Teacher teacher: teachers)
        {
            if(teacher.getID().equals(id))
            {
                teacherGet = teacher;
            }
        }
        return teacherGet;
    }
    
    /**
     * Returns a classroom with a given ID
     * @param id ID of the classroom
     * @return Classroom corresponding to input ID. Returns null if not found
     */
    public Classroom getClassroom(String id)
    {
        Classroom classGet = null;
        for(Classroom classroom: classes)
        {
            //System.out.println(classroom.getID());
            if(classroom.getID().equals(id) == true)
            {
                classGet = classroom;
            }
        }
        return classGet;
    }
    
    /**
     * Returns a computer with a given ID
     * @param id ID of the computer
     * @return Computer corresponding to input ID. Returns null if not found
     */
    public Computer getComputer(String id)
    {
        Computer computerGet = null;
        for(Computer computer: computers)
        {
            if(computer.getID().equals(id))
            {
                computerGet = computer;
            }
        }
        return computerGet;
    }
    
    /**
     * Creates a new teacher and adds them to the collection of Teachers
     * @param name Name of the new teacher
     */
    public void addTeacher(String name)
    {
        Teacher newTeacher = new Teacher(name, "T" + String.format("%05d",totalTeachers));
        totalTeachers++;
        teachers.add(newTeacher);
    }
    
    public void addTeacher(Teacher teacher)
    {
        teachers.add(teacher);
    }
    
    /**
     * Removes a teacher with a given ID
     * @param id ID of the teacher to be removed
     */
    public void removeTeacher(String id)
    {
        Teacher teacherRem = null;
        for(Teacher teacher: teachers)
        {
            if(teacher.getID() == id)
            {
                teacherRem = teacher;
            }
        }
        teachers.remove(teacherRem);
    }
    
    /**
     * Creates a new computer and adds it to the collection of Computers
     * @param name Name of the new computer
     */
    public void addComputer(String brand, String model, boolean condition)
    {
        Computer newComputer = new Computer
        (brand, "C" + String.format("%05d",totalComputers), model, condition);
        totalComputers++;
        computers.add(newComputer); 
        System.out.println("Added new computer: " + brand + " " + model);
    }
    
    public void addComputer(Computer computer)
    {
        computers.add(computer);
    }
    /**
     * Removes a computer with a given ID
     * @param id ID of the computer to be removed
     */
    public void removeComputer(String id)
    {
        Computer computerRem = null;
        for(Computer computer: computers)
        {
            if(computer.getID() == id)
            {
                computerRem = computer;
            }
        }
        computers.remove(computerRem);        
    }
    
    /**
     * Creates a new classroom and adds it to the collection of Classrooms
     * @param name 
     */
    public void addClassroom(String name)
    {
        Classroom newClassroom = new Classroom(name, "G" + String.format("%05d",totalClasses));
        totalClasses++;
        classes.add(newClassroom); 
    }
    
    public void addClassroom(Classroom classroom)
    {
        classes.add(classroom);
    }
    
    /**
     * Removes a classroom with a given ID
     * @param id ID of the class to be removed
     */
    public void removeClassroom(String id)
    {
        Classroom classRem = null;
        for(Classroom classroom: classes)
        {
            if(classroom.getID() == id)
            {
                classRem = classroom;
            }
        }
        classes.remove(classRem);        
    }
    
    /**
     * Returns the total number of students
     * @return ArrayList of references to Student objects
     */
    public ArrayList<Student> getStudents()
    {
        return students;
    }
    
    /**
     * Returns a list of all students
     * @return String of all students separated with comma's
     */
    public String getStudentList()
    {
        String output = "";
        for(Student student: students)
        {
            output = output + student.getName() + ", ";
        }
        return output;
    }
    
    /**
     * Returns a list of all teachers
     * @return String of all teachers separated with comma's
     */
    public String getTeacherList()
    {
        String output = "";
        for(Teacher teacher: teachers)
        {
            output = output + teacher.getName() + ", ";
        }
        return output;
    }
    
    /**
     * Returns a list of all computers
     * @return String of all computers separated with comma's
     */
    public String getComputerList()
    {
        String output = "";
        for(Computer computer: computers)
        {
            output = output + computer.getBrand() + " " + computer.getModel() + ", ";
        }
        return output;
    }
    
    /**
     * Returns the collection of teachers
     * @return ArrayList of references to Teacher objects
     */
    public ArrayList<Teacher> getTeachers()
    {
        return teachers;
    }
    
    /**
     * Returns the collection of classrooms
     * @return ArrayList of references to Classroom objects
     */
    public ArrayList<Classroom> getClasses()
    {
        return classes;
    }
    
    /**
     * Returns the collection of computers
     * @return ArrayList of references to Computer objects
     */
    public ArrayList<Computer> getComputers()
    {
        return computers;
    }
    
    /**
     * Returns the total number of teachers
     * @return Number of teachers
     */
    public int getNumberOfTeachers()
    {
        return teachers.size();
    }
    
    /**
     * Returns the total number of students
     * @return Number of students
     */
    public int getNumberOfStudents()
    {
        return students.size();
    }
    
    /**
     * Returns the total number of computers
     * @return Number of computers
     */
    public int getNumberOfComputers()
    {
        return computers.size();
    }
    
    /**
     * Returns the total number of classes
     * @return Number of classes
     */
    public int getNumberOfClasses()
    {
        return classes.size();
    }
    
    public int[] getTotals()
    {
        return new int[] {totalStudents, totalTeachers, totalComputers, totalClasses};
    }
    
    /**
     * Changes the total number of students, teachers, classrooms and computers
     * Used at program start
     * @param totals Array of integers that contain the total number of all fields
     */
    public void changeTotals(int[] totals)
    {
        totalStudents = totals[0];
        totalTeachers = totals[1];
        totalComputers = totals[2];
        totalClasses = totals[3];

    }
    
    public String serialize()
    {
        return "o;" + Integer.toString(totalStudents) + ";" + 
                Integer.toString(totalTeachers) + ";" + 
                Integer.toString(totalComputers) + ";" + 
                Integer.toString(totalClasses);
    }
}
