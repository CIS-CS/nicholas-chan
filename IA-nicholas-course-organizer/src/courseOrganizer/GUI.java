/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseOrganizer;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * Graphical User Interface of the program. Manages user input
 * @author Nicholas
 */
public class GUI extends javax.swing.JFrame {
    //Organizer and persistor declarations
    Organizer org;
    Persistor persist;
    
    //Table model and sorter declarations
    DefaultTableModel studentTableModel; 
    DefaultTableModel teacherTableModel;
    DefaultTableModel computerTableModel;
    DefaultTableModel classesTableModel;
    DefaultTableModel classStudentModel;
    DefaultTableModel classTeacherModel;
    DefaultTableModel classComputerModel;

    TableRowSorter<DefaultTableModel> studentSorter;
    TableRowSorter<DefaultTableModel> teacherSorter;
    TableRowSorter<DefaultTableModel> computerSorter;
    TableRowSorter<DefaultTableModel> classesSorter;
    TableRowSorter<DefaultTableModel> classStudentSorter;
    TableRowSorter<DefaultTableModel> classTeacherSorter;
    TableRowSorter<DefaultTableModel> classComputerSorter;
    
    DataValidate dv;

    File originFile = null; 
    
    /**
     * Creates new form GUI, creates an organizer and persistor, and 
     * initializes all tables
     * 
     * @param org The input organizer.
     * @param persistor The input persistor.
     */
    public GUI(Organizer org, Persistor persistor) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("/icon.png")));
        this.org = org;
        this.persist = persistor;
        
        dv = new DataValidate();
        
        main(null);
        initComponents();
   
        setVisible(true);
        
        initTables();
        
        
    }

    private void initTables()
    {
        initStudentTable();
        initTeacherTable();
        initComputerTable();
        initClassesTable();        
    }
    private int getStudentTableSelected()
    {
        return studentTable.getSelectedRow();
    }
    
    private int getTeacherTableSelected()
    {
        return teacherTable.getSelectedRow();
    }
    
    private int getComputerTableSelected()
    {
        return computerTable.getSelectedRow();
    }
    
    private int getClassesTableSelected()
    {
        return classesTable.getSelectedRow();
    }
    
    private int[] getClassStudentTableSelected()
    {
        if(classStudentTable.getSelectedRow() == -1)
        {
            return new int[] {-1};
        }
        else
        {
            return classStudentTable.getSelectedRows();
        } 
    }
    
    private int[] getClassTeacherTableSelected()
    {
        if(classTeacherTable.getSelectedRow() == -1)
        {
            return new int[] {-1};
        }
        else
        {
            return classTeacherTable.getSelectedRows();
        }
    }
    
    private int[] getClassComputerTableSelected()
    {
        if(classComputerTable.getSelectedRow() == -1)
        {
            return new int[] {-1};
        }
        else
        {
            return classComputerTable.getSelectedRows();
        }
    }
    
    private void initTeacherTable()
    {
        teacherTableModel = (DefaultTableModel) teacherTable.getModel();
        teacherTable.setAutoCreateRowSorter(true);
        teacherTableModel.setRowCount(0);
        loadTeacherTable();
        teacherSorter = new TableRowSorter<DefaultTableModel>
            (teacherTableModel);
        teacherTable.setRowSorter(teacherSorter);
    }
    
    private void initComputerTable()
    {
        computerTableModel = (DefaultTableModel) computerTable.getModel();
        computerTable.setAutoCreateRowSorter(true);
        computerTableModel.setRowCount(0);
        loadComputerTable();
        computerSorter = new TableRowSorter<>(computerTableModel);
        computerTable.setRowSorter(computerSorter);
    }
    
    private void initStudentTable()
    {
        studentTableModel = (DefaultTableModel) studentTable.getModel();
        studentTable.setAutoCreateRowSorter(true);
        studentTableModel.setRowCount(0);
        loadStudentTable(); 
        studentSorter = new TableRowSorter<>(studentTableModel);
        studentTable.setRowSorter(studentSorter);
    }
    
    private void initClassesTable()
    {
        classesTableModel = (DefaultTableModel) classesTable.getModel();
        classesTable.setAutoCreateRowSorter(true);
        classesTableModel.setRowCount(0);
        loadClassesTable();
        classesSorter = new TableRowSorter<>(classesTableModel);
        classesTable.setRowSorter(classesSorter);
        System.out.println("Initialized classes table");
    }
    
    private void initClassStudentTable(Classroom inClass)
    {
        classStudentModel = (DefaultTableModel) classStudentTable.getModel();
        classStudentTable.setAutoCreateRowSorter(true);
        classStudentModel.setRowCount(0);
        loadClassStudentTable(inClass);
        classStudentSorter = new TableRowSorter<>(classStudentModel);
        classStudentTable.setRowSorter(classStudentSorter);
    }
    
    private void initClassTeacherTable(Classroom inClass)
    {
        classTeacherModel = (DefaultTableModel) classTeacherTable.getModel();
        classTeacherTable.setAutoCreateRowSorter(true);
        classTeacherModel.setRowCount(0);
        loadClassTeacherTable(inClass);
        classTeacherSorter = new TableRowSorter<>(classTeacherModel);
        classTeacherTable.setRowSorter(classTeacherSorter);
    }
    
    private void initClassComputerTable(Classroom inClass)
    {
        classComputerModel = (DefaultTableModel) classComputerTable.getModel();
        classComputerTable.setAutoCreateRowSorter(true);
        classComputerModel.setRowCount(0);
        loadClassComputerTable(inClass);
        classComputerSorter = new TableRowSorter<>(classComputerModel);
        classComputerTable.setRowSorter(classComputerSorter);
    }
    
    private void addStudentRow(Student student)
    {
        studentTableModel.addRow(new String[]{student.getID(), student.getName()
                , student.getClasses()});
    }
    
    private void addTeacherRow(Teacher teacher)
    {
        teacherTableModel.addRow(new String[]{teacher.getID(), teacher.getName()
                , teacher.getClasses()});
        
    }
    
    private void addComputerRow(Computer computer)
    {
        String condition;
        
        if(computer.getCondition() == true)
        {
            condition = "Working";
        }
        else
        {
            condition = "Not Working";
        }
        
        computerTableModel.addRow(new String[]
        {computer.getID(), computer.getBrand()
                , computer.getModel(), condition});
    }
    
    private void addClassesRow(Classroom classroom)
    {
        String studentCount = new Integer(classroom
                .getNumOfStudents()).toString();
        String teacherCount = new Integer(classroom
                .getNumOfTeachers()).toString();
        String computerCount = new Integer(classroom
                .getNumOfComputers()).toString();
        
        classesTableModel.addRow(new String[]{classroom.getID(), 
            classroom.getName(), studentCount, teacherCount, computerCount});
    }
    
    private void addClassStudentRow(Student student, Classroom classroom)
    {
        String id = student.getID();
        String name = student.getName();
        boolean enrolled = false;
        String enrollStatus = "";
        
        for(Classroom inClass: student.getAttending())
        {
            if(classroom == inClass)
            {
                enrolled = true;
            }
        }
        
        if(enrolled == true)
        {
            enrollStatus = "Enrolled";
        }
        else
        {
            enrollStatus = "Not Enrolled";
        }
        
        classStudentModel.addRow(new String[]{id, name, enrollStatus});
    }
    
    private void addClassTeacherRow(Teacher teacher, Classroom classroom)
    {
        String id = teacher.getID();
        String name = teacher.getName();
        boolean enrolled = false;
        String enrollStatus = "";
        
        for(Classroom inClass: teacher.getAttending())
        {
            if(classroom == inClass)
            {
                enrolled = true;
            }
        }
        
        if(enrolled == true)
        {
            enrollStatus = "Enrolled";
        }
        else
        {
            enrollStatus = "Not Enrolled";
        }
        
        classTeacherModel.addRow(new String[]{id, name, enrollStatus});
    }
 
    private void addClassComputerRow(Computer computer, Classroom classroom)
    {
        String id = computer.getID();
        String name = computer.getBrand() + " " + computer.getModel();
        boolean enrolled = false;
        String enrollStatus = "";
        
        for(Classroom inClass: computer.getAttending())
        {
            if(classroom == inClass)
            {
                enrolled = true;
            }
        }
        
        if(enrolled == true)
        {
            enrollStatus = "Enrolled";
        }
        else
        {
            enrollStatus = "Not Enrolled";
        }
        
        classComputerModel.addRow(new String[]{id, name, enrollStatus});
    }
        
    private void removeStudent(int rowNum)
    { 
       if(rowNum != -1)
        {
            String studentID = studentTable.getValueAt(rowNum,0).toString();
            org.removeStudent(studentID);
            studentTableModel.removeRow(rowNum);
            System.out.println("One student removed, remaining students: " + 
                    org.getStudentList());
        }
    }
    
    private void removeTeacher(int rowNum)
    {
        if(rowNum != -1)
        {
            String teacherID = teacherTable.getValueAt(rowNum,0).toString();
            org.removeTeacher(teacherID);
            teacherTableModel.removeRow(rowNum);
            System.out.println("One teacher removed, remaining teachers: " + 
                    org.getTeacherList());
        }
    }
    
    private void removeComputer(int rowNum)
    {
        if(rowNum != -1)
        {
            String computerID = computerTable.getValueAt(rowNum,0).toString();
            org.removeComputer(computerID);
            computerTableModel.removeRow(rowNum);
            System.out.println("One computer removed, remaining computers: " + 
                    org.getComputerList());
        }
    }
    
    private void removeClass(int rowNum)
    {
        if(rowNum != -1)
        {
            String classID = classesTable.getValueAt(rowNum,0).toString();
            org.removeClassroom(classID);
            classesTableModel.removeRow(rowNum);
        }
    }
    
    private void loadStudentTable()
    {
        ArrayList<Student> students = org.getStudents();
        if(students.isEmpty()==false)
        {
            for(Student inStudent: students)
            {
                addStudentRow(inStudent);
            }   
        }
    }
    
    private void loadTeacherTable()
    {
        ArrayList<Teacher> teachers = org.getTeachers();
        for(Teacher inTeacher: teachers)
        {
            addTeacherRow(inTeacher);
        }
    }
    
    private void loadComputerTable()
    {
        ArrayList<Computer> computers = org.getComputers();
        for(Computer inComputer: computers)
        {
            addComputerRow(inComputer);
        }
    }
    
    private void loadClassesTable()
    {
        ArrayList<Classroom> classes = org.getClasses();
        for(Classroom inClass: classes)
        {
            addClassesRow(inClass);
            System.out.println("Added class called " + inClass.getName());
        }
    }
    
    private void loadClassStudentTable(Classroom inClass)
    {
        ArrayList<Student> students = org.getStudents();
        for(Student inStudent: students)
        {
            addClassStudentRow(inStudent, inClass);
        }      
    }
    
    private void loadClassTeacherTable(Classroom inClass)
    {
        ArrayList<Teacher> teachers = org.getTeachers();
        for(Teacher inTeacher: teachers)
        {
            addClassTeacherRow(inTeacher, inClass);
        }
    }
    
    private void loadClassComputerTable(Classroom inClass)
    {
        ArrayList<Computer> computers = org.getComputers();
        for(Computer inComputer: computers)
        {
            addClassComputerRow(inComputer, inClass);
        }
    }

        private void saveAs()
    {
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter
                ("Reboot Class Record (.reboot)", "reboot"));
        fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);
 
        int returnVal = fileChooser.showSaveDialog(this);

        if(returnVal == fileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            if(file.getName().contains("."))
            {
                optionPane.showMessageDialog
                        (null, "Filename cannot contain: . ");
            }
            else
            {
                try
                {
                    persist.save(file.getAbsolutePath() + ".reboot");
                }
                catch(IOException e)
                {
                    optionPane.showMessageDialog(null
                            , "Cannot save file: " + e);
                }               
            }
        }
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        optionPane = new javax.swing.JOptionPane();
        addComputerDialog = new javax.swing.JPanel();
        addComputerBrandField = new javax.swing.JTextField();
        addComputerModelField = new javax.swing.JTextField();
        addComputerBrandLabel = new javax.swing.JLabel();
        addComputerModelLabel = new javax.swing.JLabel();
        addComputerWorkingButton = new javax.swing.JRadioButton();
        addComputerNotWorkingButton = new javax.swing.JRadioButton();
        addComputerButtonGroup = new javax.swing.ButtonGroup();
        addStudentDialog = new javax.swing.JPanel();
        addStudentLabel = new javax.swing.JLabel();
        addStudentNameField = new javax.swing.JTextField();
        addTeacherDialog = new javax.swing.JPanel();
        addTeacherLabel = new javax.swing.JLabel();
        addTeacherNameField = new javax.swing.JTextField();
        editStudentDialog = new javax.swing.JPanel();
        editStudentOriginalLabel = new javax.swing.JLabel();
        editStudentNewLabel = new javax.swing.JLabel();
        editStudentNewField = new javax.swing.JTextField();
        editStudentOriginalField = new javax.swing.JTextField();
        editTeacherDialog = new javax.swing.JPanel();
        editTeacherOriginalLabel = new javax.swing.JLabel();
        editTeacherNewLabel = new javax.swing.JLabel();
        editTeacherNewField = new javax.swing.JTextField();
        editTeacherOriginalField = new javax.swing.JTextField();
        editComputerDialog = new javax.swing.JPanel();
        editComputerBrandOriginalLabel = new javax.swing.JLabel();
        editComputerBrandNewLabel1 = new javax.swing.JLabel();
        editCompBrandNewField = new javax.swing.JTextField();
        editCompBrandOriginalField = new javax.swing.JTextField();
        editCompSeperator = new javax.swing.JSeparator();
        editCompModelNewField = new javax.swing.JTextField();
        editComputerModelNewLabel = new javax.swing.JLabel();
        editComputerModelOriginalLabel = new javax.swing.JLabel();
        editCompModelOriginalField = new javax.swing.JTextField();
        editCompSeperator2 = new javax.swing.JSeparator();
        editCompWorkingButton = new javax.swing.JRadioButton();
        editCompNotWorkingButton = new javax.swing.JRadioButton();
        editComputerButtonGroup = new javax.swing.ButtonGroup();
        editClassesDialog = new javax.swing.JPanel();
        editClassesOriginalLabel = new javax.swing.JLabel();
        editClassesNewLabel = new javax.swing.JLabel();
        editClassesNewField = new javax.swing.JTextField();
        editClassesOriginalField = new javax.swing.JTextField();
        addClassesDialog = new javax.swing.JPanel();
        addClassesLabel = new javax.swing.JLabel();
        addClassesNameField = new javax.swing.JTextField();
        editClassStudent = new javax.swing.JPanel();
        classStudentPane = new javax.swing.JScrollPane();
        classStudentTable = new javax.swing.JTable();
        EnrollStudentButton = new javax.swing.JButton();
        EnrollStudentButton1 = new javax.swing.JButton();
        classStudentSearchField = new javax.swing.JTextField();
        classStudentSearchButton = new javax.swing.JButton();
        editClassTeacher = new javax.swing.JPanel();
        classTeacherPane = new javax.swing.JScrollPane();
        classTeacherTable = new javax.swing.JTable();
        EnrollTeacherButton = new javax.swing.JButton();
        RemoveTeacherButton = new javax.swing.JButton();
        classTeacherSearchField = new javax.swing.JTextField();
        classTeacherSearchButton = new javax.swing.JButton();
        editClassComputer = new javax.swing.JPanel();
        classComputerPane = new javax.swing.JScrollPane();
        classComputerTable = new javax.swing.JTable();
        EnrollComputerButton = new javax.swing.JButton();
        RemoveComputerButton = new javax.swing.JButton();
        classComputerSearchField = new javax.swing.JTextField();
        classComputerSearchButton = new javax.swing.JButton();
        detailsPanel = new javax.swing.JPanel();
        studentsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentList = new javax.swing.JList();
        teachersLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        teacherList = new javax.swing.JList();
        computersLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        computerList = new javax.swing.JList();
        studentDetailsPanel = new javax.swing.JPanel();
        studentsLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        detailsStudentList = new javax.swing.JList();
        teacherDetailsPanel = new javax.swing.JPanel();
        studentsLabel3 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        detailsTeacherList = new javax.swing.JList();
        tabbedPane = new javax.swing.JTabbedPane();
        studentPanel = new javax.swing.JPanel();
        studentTableScrollPane = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        studentSearchField = new javax.swing.JTextField();
        studentSearchButton = new javax.swing.JButton();
        addStudentButton = new javax.swing.JButton();
        removeStudentButton = new javax.swing.JButton();
        viewDetailsButtonStudent = new javax.swing.JButton();
        editStudentButton = new javax.swing.JButton();
        teacherPanel = new javax.swing.JPanel();
        viewDetailsButtonTeacher = new javax.swing.JButton();
        teacherTableScrollPane = new javax.swing.JScrollPane();
        teacherTable = new javax.swing.JTable();
        teacherSearchField = new javax.swing.JTextField();
        teacherSearchButton = new javax.swing.JButton();
        addTeacherButton = new javax.swing.JButton();
        removeTeacherButton = new javax.swing.JButton();
        editTeacherButton = new javax.swing.JButton();
        computerPanel = new javax.swing.JPanel();
        computerTableScrollPane = new javax.swing.JScrollPane();
        computerTable = new javax.swing.JTable();
        computerSearchField = new javax.swing.JTextField();
        computerSearchButton = new javax.swing.JButton();
        addComputerButton = new javax.swing.JButton();
        removeComputerButton = new javax.swing.JButton();
        editComputerButton = new javax.swing.JButton();
        classesPanel = new javax.swing.JPanel();
        classesTableScrollPane = new javax.swing.JScrollPane();
        classesTable = new javax.swing.JTable();
        classesSearchField = new javax.swing.JTextField();
        classesSearchButton = new javax.swing.JButton();
        addClassButton = new javax.swing.JButton();
        removeClassButton = new javax.swing.JButton();
        editClassButton = new javax.swing.JButton();
        classEditStudentButton = new javax.swing.JButton();
        classEditTeacherButton = new javax.swing.JButton();
        classEditComputerButton = new javax.swing.JButton();
        viewDetailsButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        separator = new javax.swing.JPopupMenu.Separator();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        fileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);

        addComputerBrandLabel.setText("Brand:");

        addComputerModelLabel.setText("Model:");

        addComputerButtonGroup.add(addComputerWorkingButton);
        addComputerWorkingButton.setText("Working");

        addComputerButtonGroup.add(addComputerNotWorkingButton);
        addComputerNotWorkingButton.setText("Not Working");

        javax.swing.GroupLayout addComputerDialogLayout = new javax.swing.GroupLayout(addComputerDialog);
        addComputerDialog.setLayout(addComputerDialogLayout);
        addComputerDialogLayout.setHorizontalGroup(
            addComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addComputerDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addComputerDialogLayout.createSequentialGroup()
                        .addComponent(addComputerModelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addComputerModelField, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addComputerDialogLayout.createSequentialGroup()
                        .addComponent(addComputerBrandLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addComputerBrandField)
                        .addGap(15, 15, 15))
                    .addGroup(addComputerDialogLayout.createSequentialGroup()
                        .addGroup(addComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addComputerNotWorkingButton)
                            .addComponent(addComputerWorkingButton))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        addComputerDialogLayout.setVerticalGroup(
            addComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addComputerDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addComputerBrandLabel)
                    .addComponent(addComputerBrandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(addComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addComputerModelLabel)
                    .addComponent(addComputerModelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addComputerWorkingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addComputerNotWorkingButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addStudentLabel.setText("Student Name:");

        javax.swing.GroupLayout addStudentDialogLayout = new javax.swing.GroupLayout(addStudentDialog);
        addStudentDialog.setLayout(addStudentDialogLayout);
        addStudentDialogLayout.setHorizontalGroup(
            addStudentDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addStudentDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addStudentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addStudentNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );
        addStudentDialogLayout.setVerticalGroup(
            addStudentDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addStudentDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addStudentDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStudentLabel)
                    .addComponent(addStudentNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addTeacherLabel.setText("Teacher Name:");

        javax.swing.GroupLayout addTeacherDialogLayout = new javax.swing.GroupLayout(addTeacherDialog);
        addTeacherDialog.setLayout(addTeacherDialogLayout);
        addTeacherDialogLayout.setHorizontalGroup(
            addTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTeacherDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addTeacherLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addTeacherNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );
        addTeacherDialogLayout.setVerticalGroup(
            addTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTeacherDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTeacherLabel)
                    .addComponent(addTeacherNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editStudentOriginalLabel.setText("Original Name:");

        editStudentNewLabel.setText("New Name:");

        editStudentOriginalField.setEditable(false);

        javax.swing.GroupLayout editStudentDialogLayout = new javax.swing.GroupLayout(editStudentDialog);
        editStudentDialog.setLayout(editStudentDialogLayout);
        editStudentDialogLayout.setHorizontalGroup(
            editStudentDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editStudentDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editStudentDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editStudentNewLabel)
                    .addComponent(editStudentOriginalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editStudentDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editStudentOriginalField)
                    .addComponent(editStudentNewField, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editStudentDialogLayout.setVerticalGroup(
            editStudentDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editStudentDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editStudentDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editStudentOriginalLabel)
                    .addComponent(editStudentOriginalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editStudentDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editStudentNewLabel)
                    .addComponent(editStudentNewField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editTeacherOriginalLabel.setText("Original Name:");

        editTeacherNewLabel.setText("New Name:");

        editTeacherOriginalField.setEditable(false);

        javax.swing.GroupLayout editTeacherDialogLayout = new javax.swing.GroupLayout(editTeacherDialog);
        editTeacherDialog.setLayout(editTeacherDialogLayout);
        editTeacherDialogLayout.setHorizontalGroup(
            editTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTeacherDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editTeacherNewLabel)
                    .addComponent(editTeacherOriginalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editTeacherOriginalField)
                    .addComponent(editTeacherNewField, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editTeacherDialogLayout.setVerticalGroup(
            editTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTeacherDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editTeacherOriginalLabel)
                    .addComponent(editTeacherOriginalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editTeacherNewLabel)
                    .addComponent(editTeacherNewField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editComputerBrandOriginalLabel.setText("Original Brand:");

        editComputerBrandNewLabel1.setText("New Brand:");

        editCompBrandOriginalField.setEditable(false);

        editComputerModelNewLabel.setText("New Model:");

        editComputerModelOriginalLabel.setText("Original Model:");

        editCompModelOriginalField.setEditable(false);

        editComputerButtonGroup.add(editCompWorkingButton);
        editCompWorkingButton.setText("Working");

        editComputerButtonGroup.add(editCompNotWorkingButton);
        editCompNotWorkingButton.setText("Not Working");

        javax.swing.GroupLayout editComputerDialogLayout = new javax.swing.GroupLayout(editComputerDialog);
        editComputerDialog.setLayout(editComputerDialogLayout);
        editComputerDialogLayout.setHorizontalGroup(
            editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editComputerDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editCompSeperator, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editCompSeperator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(editComputerDialogLayout.createSequentialGroup()
                        .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editCompNotWorkingButton)
                            .addComponent(editCompWorkingButton)
                            .addGroup(editComputerDialogLayout.createSequentialGroup()
                                .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(editComputerBrandNewLabel1)
                                    .addComponent(editComputerBrandOriginalLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editCompBrandOriginalField)
                                    .addComponent(editCompBrandNewField, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
                            .addGroup(editComputerDialogLayout.createSequentialGroup()
                                .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(editComputerModelNewLabel)
                                    .addComponent(editComputerModelOriginalLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editCompModelOriginalField)
                                    .addComponent(editCompModelNewField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        editComputerDialogLayout.setVerticalGroup(
            editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editComputerDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editComputerBrandOriginalLabel)
                    .addComponent(editCompBrandOriginalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editComputerBrandNewLabel1)
                    .addComponent(editCompBrandNewField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editCompSeperator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editComputerModelOriginalLabel)
                    .addComponent(editCompModelOriginalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editComputerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editComputerModelNewLabel)
                    .addComponent(editCompModelNewField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editCompSeperator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editCompWorkingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editCompNotWorkingButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editClassesOriginalLabel.setText("Original Name:");

        editClassesNewLabel.setText("New Name:");

        editClassesOriginalField.setEditable(false);

        javax.swing.GroupLayout editClassesDialogLayout = new javax.swing.GroupLayout(editClassesDialog);
        editClassesDialog.setLayout(editClassesDialogLayout);
        editClassesDialogLayout.setHorizontalGroup(
            editClassesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editClassesDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editClassesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editClassesNewLabel)
                    .addComponent(editClassesOriginalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editClassesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editClassesOriginalField)
                    .addComponent(editClassesNewField, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editClassesDialogLayout.setVerticalGroup(
            editClassesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editClassesDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editClassesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editClassesOriginalLabel)
                    .addComponent(editClassesOriginalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editClassesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editClassesNewLabel)
                    .addComponent(editClassesNewField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addClassesLabel.setText("Class Name:");

        javax.swing.GroupLayout addClassesDialogLayout = new javax.swing.GroupLayout(addClassesDialog);
        addClassesDialog.setLayout(addClassesDialogLayout);
        addClassesDialogLayout.setHorizontalGroup(
            addClassesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addClassesDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addClassesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addClassesNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );
        addClassesDialogLayout.setVerticalGroup(
            addClassesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addClassesDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addClassesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addClassesLabel)
                    .addComponent(addClassesNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        classStudentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Student ID", "Student Name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        classStudentTable.getTableHeader().setReorderingAllowed(false);
        classStudentPane.setViewportView(classStudentTable);
        if (classStudentTable.getColumnModel().getColumnCount() > 0) {
            classStudentTable.getColumnModel().getColumn(0).setResizable(false);
            classStudentTable.getColumnModel().getColumn(1).setResizable(false);
            classStudentTable.getColumnModel().getColumn(2).setResizable(false);
        }

        EnrollStudentButton.setText("Enroll");
        EnrollStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnrollStudentButtonActionPerformed(evt);
            }
        });

        EnrollStudentButton1.setText("Remove");
        EnrollStudentButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnrollStudentButton1ActionPerformed(evt);
            }
        });

        classStudentSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classStudentSearchFieldActionPerformed(evt);
            }
        });
        classStudentSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                classStudentSearchFieldKeyTyped(evt);
            }
        });

        classStudentSearchButton.setText("Search");
        classStudentSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classStudentSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editClassStudentLayout = new javax.swing.GroupLayout(editClassStudent);
        editClassStudent.setLayout(editClassStudentLayout);
        editClassStudentLayout.setHorizontalGroup(
            editClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editClassStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classStudentPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(editClassStudentLayout.createSequentialGroup()
                        .addComponent(EnrollStudentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EnrollStudentButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editClassStudentLayout.createSequentialGroup()
                        .addComponent(classStudentSearchField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classStudentSearchButton)))
                .addContainerGap())
        );
        editClassStudentLayout.setVerticalGroup(
            editClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editClassStudentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classStudentSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classStudentSearchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classStudentPane, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnrollStudentButton)
                    .addComponent(EnrollStudentButton1))
                .addGap(17, 17, 17))
        );

        classTeacherTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Teacher ID", "Teacher Name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        classTeacherTable.getTableHeader().setReorderingAllowed(false);
        classTeacherPane.setViewportView(classTeacherTable);
        if (classTeacherTable.getColumnModel().getColumnCount() > 0) {
            classTeacherTable.getColumnModel().getColumn(0).setResizable(false);
            classTeacherTable.getColumnModel().getColumn(1).setResizable(false);
            classTeacherTable.getColumnModel().getColumn(2).setResizable(false);
        }

        EnrollTeacherButton.setText("Assign");
        EnrollTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnrollTeacherButtonActionPerformed(evt);
            }
        });

        RemoveTeacherButton.setText("Remove");
        RemoveTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveTeacherButtonActionPerformed(evt);
            }
        });

        classTeacherSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classTeacherSearchFieldActionPerformed(evt);
            }
        });
        classTeacherSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                classTeacherSearchFieldKeyTyped(evt);
            }
        });

        classTeacherSearchButton.setText("Search");
        classTeacherSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classTeacherSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editClassTeacherLayout = new javax.swing.GroupLayout(editClassTeacher);
        editClassTeacher.setLayout(editClassTeacherLayout);
        editClassTeacherLayout.setHorizontalGroup(
            editClassTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editClassTeacherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editClassTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classTeacherPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(editClassTeacherLayout.createSequentialGroup()
                        .addComponent(EnrollTeacherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RemoveTeacherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editClassTeacherLayout.createSequentialGroup()
                        .addComponent(classTeacherSearchField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classTeacherSearchButton)))
                .addContainerGap())
        );
        editClassTeacherLayout.setVerticalGroup(
            editClassTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editClassTeacherLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editClassTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classTeacherSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classTeacherSearchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classTeacherPane, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editClassTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnrollTeacherButton)
                    .addComponent(RemoveTeacherButton))
                .addGap(17, 17, 17))
        );

        classComputerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Teacher ID", "Teacher Name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        classComputerTable.getTableHeader().setReorderingAllowed(false);
        classComputerPane.setViewportView(classComputerTable);
        if (classComputerTable.getColumnModel().getColumnCount() > 0) {
            classComputerTable.getColumnModel().getColumn(0).setResizable(false);
            classComputerTable.getColumnModel().getColumn(1).setResizable(false);
            classComputerTable.getColumnModel().getColumn(2).setResizable(false);
        }

        EnrollComputerButton.setText("Assign");
        EnrollComputerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnrollComputerButtonActionPerformed(evt);
            }
        });

        RemoveComputerButton.setText("Remove");
        RemoveComputerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveComputerButtonActionPerformed(evt);
            }
        });

        classComputerSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classComputerSearchFieldActionPerformed(evt);
            }
        });
        classComputerSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                classComputerSearchFieldKeyTyped(evt);
            }
        });

        classComputerSearchButton.setText("Search");
        classComputerSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classComputerSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editClassComputerLayout = new javax.swing.GroupLayout(editClassComputer);
        editClassComputer.setLayout(editClassComputerLayout);
        editClassComputerLayout.setHorizontalGroup(
            editClassComputerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editClassComputerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editClassComputerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classComputerPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(editClassComputerLayout.createSequentialGroup()
                        .addComponent(EnrollComputerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RemoveComputerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editClassComputerLayout.createSequentialGroup()
                        .addComponent(classComputerSearchField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classComputerSearchButton)))
                .addContainerGap())
        );
        editClassComputerLayout.setVerticalGroup(
            editClassComputerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editClassComputerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editClassComputerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classComputerSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classComputerSearchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classComputerPane, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editClassComputerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnrollComputerButton)
                    .addComponent(RemoveComputerButton))
                .addGap(17, 17, 17))
        );

        studentsLabel.setText("Students");

        studentList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(studentList);

        teachersLabel.setText("Teachers");

        teacherList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(teacherList);

        computersLabel.setText("Computers");

        computerList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(computerList);

        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teachersLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(computersLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(detailsPanelLayout.createSequentialGroup()
                        .addComponent(computersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(detailsPanelLayout.createSequentialGroup()
                        .addComponent(teachersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(detailsPanelLayout.createSequentialGroup()
                        .addComponent(studentsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        studentsLabel1.setText("Enrolled Classes");

        detailsStudentList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(detailsStudentList);

        javax.swing.GroupLayout studentDetailsPanelLayout = new javax.swing.GroupLayout(studentDetailsPanel);
        studentDetailsPanel.setLayout(studentDetailsPanelLayout);
        studentDetailsPanelLayout.setHorizontalGroup(
            studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentsLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        studentDetailsPanelLayout.setVerticalGroup(
            studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentsLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        studentsLabel3.setText("Assigned Classes");

        detailsTeacherList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane10.setViewportView(detailsTeacherList);

        javax.swing.GroupLayout teacherDetailsPanelLayout = new javax.swing.GroupLayout(teacherDetailsPanel);
        teacherDetailsPanel.setLayout(teacherDetailsPanelLayout);
        teacherDetailsPanelLayout.setHorizontalGroup(
            teacherDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(teacherDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentsLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        teacherDetailsPanelLayout.setVerticalGroup(
            teacherDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentsLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Reboot Class Organizer");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        studentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Enrolled Classes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentTable.getTableHeader().setReorderingAllowed(false);
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentTableMouseClicked(evt);
            }
        });
        studentTableScrollPane.setViewportView(studentTable);
        if (studentTable.getColumnModel().getColumnCount() > 0) {
            studentTable.getColumnModel().getColumn(0).setResizable(false);
            studentTable.getColumnModel().getColumn(1).setResizable(false);
            studentTable.getColumnModel().getColumn(2).setResizable(false);
        }

        studentPanel.add(studentTableScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 16, 503, 570));

        studentSearchField.setToolTipText("Search");
        studentSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentSearchFieldKeyPressed(evt);
            }
        });
        studentPanel.add(studentSearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(527, 16, 259, -1));

        studentSearchButton.setText("Search");
        studentSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentSearchButtonActionPerformed(evt);
            }
        });
        studentPanel.add(studentSearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(527, 43, -1, -1));

        addStudentButton.setText("Add Student");
        addStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentButtonActionPerformed(evt);
            }
        });
        studentPanel.add(addStudentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 165, -1));

        removeStudentButton.setText("Remove Student");
        removeStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeStudentButtonActionPerformed(evt);
            }
        });
        studentPanel.add(removeStudentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 165, -1));

        viewDetailsButtonStudent.setText("View Details");
        viewDetailsButtonStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsButtonStudentActionPerformed(evt);
            }
        });
        studentPanel.add(viewDetailsButtonStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 165, -1));

        editStudentButton.setText("Edit Student Name");
        editStudentButton.setPreferredSize(new java.awt.Dimension(127, 25));
        editStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editStudentButtonActionPerformed(evt);
            }
        });
        studentPanel.add(editStudentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 165, -1));

        tabbedPane.addTab("Students", studentPanel);

        viewDetailsButtonTeacher.setText("View Details");
        viewDetailsButtonTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsButtonTeacherActionPerformed(evt);
            }
        });

        teacherTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher ID", "Teacher Name", "Assigned Classes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teacherTable.getTableHeader().setReorderingAllowed(false);
        teacherTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherTableMouseClicked(evt);
            }
        });
        teacherTableScrollPane.setViewportView(teacherTable);
        if (teacherTable.getColumnModel().getColumnCount() > 0) {
            teacherTable.getColumnModel().getColumn(0).setResizable(false);
            teacherTable.getColumnModel().getColumn(1).setResizable(false);
            teacherTable.getColumnModel().getColumn(2).setResizable(false);
        }

        teacherSearchField.setToolTipText("Search");
        teacherSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                teacherSearchFieldKeyPressed(evt);
            }
        });

        teacherSearchButton.setText("Search");
        teacherSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherSearchButtonActionPerformed(evt);
            }
        });

        addTeacherButton.setText("Add Teacher");
        addTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTeacherButtonActionPerformed(evt);
            }
        });

        removeTeacherButton.setText("Remove Teacher");
        removeTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTeacherButtonActionPerformed(evt);
            }
        });

        editTeacherButton.setText("Edit Teacher Name");
        editTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTeacherButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout teacherPanelLayout = new javax.swing.GroupLayout(teacherPanel);
        teacherPanel.setLayout(teacherPanelLayout);
        teacherPanelLayout.setHorizontalGroup(
            teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(teacherTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(teacherSearchField)
                    .addGroup(teacherPanelLayout.createSequentialGroup()
                        .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(teacherSearchButton)
                            .addComponent(viewDetailsButtonTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editTeacherButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeTeacherButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addTeacherButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 92, Short.MAX_VALUE)))
                .addContainerGap())
        );
        teacherPanelLayout.setVerticalGroup(
            teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(teacherPanelLayout.createSequentialGroup()
                        .addComponent(teacherSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(teacherSearchButton)
                        .addGap(18, 18, 18)
                        .addComponent(addTeacherButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeTeacherButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTeacherButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewDetailsButtonTeacher)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(teacherTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Teachers", teacherPanel);

        computerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Model ", "Condition"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        computerTable.getTableHeader().setReorderingAllowed(false);
        computerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                computerTableMouseClicked(evt);
            }
        });
        computerTableScrollPane.setViewportView(computerTable);
        if (computerTable.getColumnModel().getColumnCount() > 0) {
            computerTable.getColumnModel().getColumn(0).setResizable(false);
            computerTable.getColumnModel().getColumn(1).setResizable(false);
            computerTable.getColumnModel().getColumn(2).setResizable(false);
            computerTable.getColumnModel().getColumn(3).setResizable(false);
        }

        computerSearchField.setToolTipText("Search");
        computerSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                computerSearchFieldKeyPressed(evt);
            }
        });

        computerSearchButton.setText("Search");
        computerSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computerSearchButtonActionPerformed(evt);
            }
        });

        addComputerButton.setText("Add Computer");
        addComputerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addComputerButtonActionPerformed(evt);
            }
        });

        removeComputerButton.setText("Remove Computer");
        removeComputerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeComputerButtonActionPerformed(evt);
            }
        });

        editComputerButton.setText("Edit Computer ");
        editComputerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editComputerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout computerPanelLayout = new javax.swing.GroupLayout(computerPanel);
        computerPanel.setLayout(computerPanelLayout);
        computerPanelLayout.setHorizontalGroup(
            computerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(computerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(computerTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(computerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(computerSearchField)
                    .addGroup(computerPanelLayout.createSequentialGroup()
                        .addGroup(computerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeComputerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editComputerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addComputerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(computerSearchButton))
                        .addGap(0, 94, Short.MAX_VALUE)))
                .addContainerGap())
        );
        computerPanelLayout.setVerticalGroup(
            computerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(computerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(computerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(computerPanelLayout.createSequentialGroup()
                        .addComponent(computerSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(computerSearchButton)
                        .addGap(18, 18, 18)
                        .addComponent(addComputerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeComputerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editComputerButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(computerTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Computers", computerPanel);

        classesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Students", "Teachers", "Computers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        classesTable.getTableHeader().setReorderingAllowed(false);
        classesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                classesTableMouseClicked(evt);
            }
        });
        classesTableScrollPane.setViewportView(classesTable);
        if (classesTable.getColumnModel().getColumnCount() > 0) {
            classesTable.getColumnModel().getColumn(0).setResizable(false);
            classesTable.getColumnModel().getColumn(1).setResizable(false);
            classesTable.getColumnModel().getColumn(2).setResizable(false);
            classesTable.getColumnModel().getColumn(3).setResizable(false);
        }

        classesSearchField.setToolTipText("Search");
        classesSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                classesSearchFieldKeyPressed(evt);
            }
        });

        classesSearchButton.setText("Search");
        classesSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classesSearchButtonActionPerformed(evt);
            }
        });

        addClassButton.setText("Add Class");
        addClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClassButtonActionPerformed(evt);
            }
        });

        removeClassButton.setText("Remove Class");
        removeClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeClassButtonActionPerformed(evt);
            }
        });

        editClassButton.setText("Edit Class Name");
        editClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editClassButtonActionPerformed(evt);
            }
        });

        classEditStudentButton.setText("Edit Students");
        classEditStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classEditStudentButtonActionPerformed(evt);
            }
        });

        classEditTeacherButton.setText("Edit Teachers");
        classEditTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classEditTeacherButtonActionPerformed(evt);
            }
        });

        classEditComputerButton.setText("Edit Computers");
        classEditComputerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classEditComputerButtonActionPerformed(evt);
            }
        });

        viewDetailsButton.setText("View Details");
        viewDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout classesPanelLayout = new javax.swing.GroupLayout(classesPanel);
        classesPanel.setLayout(classesPanelLayout);
        classesPanelLayout.setHorizontalGroup(
            classesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classesTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(classesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classesSearchField)
                    .addGroup(classesPanelLayout.createSequentialGroup()
                        .addGroup(classesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeClassButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editClassButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addClassButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(classesSearchButton)
                            .addComponent(classEditStudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(classEditTeacherButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(classEditComputerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewDetailsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 112, Short.MAX_VALUE)))
                .addContainerGap())
        );
        classesPanelLayout.setVerticalGroup(
            classesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(classesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(classesPanelLayout.createSequentialGroup()
                        .addComponent(classesSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(classesSearchButton)
                        .addGap(18, 18, 18)
                        .addComponent(addClassButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeClassButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editClassButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classEditStudentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classEditTeacherButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classEditComputerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewDetailsButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(classesTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Classes", classesPanel);

        fileMenu.setText("File");

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);
        fileMenu.add(separator);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setText("Save As...");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");
        editMenu.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                editMenuMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        editMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMenuActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Clear All");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem1);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void studentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentTableMouseClicked
        //System.out.println("yes");
    }//GEN-LAST:event_studentTableMouseClicked

    private void removeStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeStudentButtonActionPerformed
        if(getStudentTableSelected()== -1)
        {
            optionPane.showMessageDialog(null, "No student selected"
                    + ", could not remove.");      
        }
        else
        {
            removeStudent(getStudentTableSelected());
        }
    }//GEN-LAST:event_removeStudentButtonActionPerformed
  
    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        saveAs();
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Reboot "
                + "Class Record (.reboot)", "reboot"));
        fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);
        
        int returnVal = fileChooser.showOpenDialog(this);
        
        if(returnVal == fileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            originFile = file;
            
            System.out.println(file.getName());
            if(dv.validateOpen(file.getName()))
            {
                try
                {
                    persist.load(file.getAbsolutePath());
                }   
                catch(FileNotFoundException e)
                {
                    optionPane.showMessageDialog(null, e.getMessage());
                }
                catch(IOException e)
                {
                    optionPane.showMessageDialog(null, 
                            "Cannot open file: " + e);
                }
            }
            else
            {
                optionPane.showMessageDialog(null, "Invalid file. "
                        + "Only .reboot files allowed");
            }
        }
        
        initStudentTable();
        initTeacherTable();
        initComputerTable();
        initClassesTable();
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void addStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentButtonActionPerformed
        int result = optionPane.showConfirmDialog(null, addStudentDialog, 
                "Add Student", optionPane.OK_CANCEL_OPTION, 
                optionPane.PLAIN_MESSAGE);
        
        if(result == optionPane.OK_OPTION)
        {
            if(addStudentNameField.getText().equals("") ==  false)
            {
                if(dv.validatePersonName(addStudentNameField.getText()))
                {
                    optionPane.showMessageDialog(null, 
                            dv.personNameError);
                }
                else
                {
                    org.addStudent(addStudentNameField.getText());
                } 
            }
            else
            {
                optionPane.showMessageDialog(null, 
                        "Please provide a student name");
            }
        }
        initStudentTable();
        addStudentNameField.setText("");       
    }//GEN-LAST:event_addStudentButtonActionPerformed

    private void studentSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentSearchButtonActionPerformed
        studentSorter.setRowFilter(RowFilter.regexFilter(studentSearchField
                .getText()));
    }//GEN-LAST:event_studentSearchButtonActionPerformed

    private void studentSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentSearchFieldKeyPressed
        studentSorter.setRowFilter(RowFilter.regexFilter(studentSearchField
                .getText()));
    }//GEN-LAST:event_studentSearchFieldKeyPressed

    private void teacherTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherTableMouseClicked

    private void teacherSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacherSearchFieldKeyPressed
        teacherSorter.setRowFilter(RowFilter.regexFilter(teacherSearchField
                .getText()));
    }//GEN-LAST:event_teacherSearchFieldKeyPressed

    private void teacherSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherSearchButtonActionPerformed
        teacherSorter.setRowFilter(RowFilter.regexFilter(teacherSearchField
                .getText()));
    }//GEN-LAST:event_teacherSearchButtonActionPerformed

    private void addTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTeacherButtonActionPerformed
        int result = optionPane.showConfirmDialog(null, addTeacherDialog, 
                "Add Teacher", optionPane.OK_CANCEL_OPTION
                , optionPane.PLAIN_MESSAGE);
        
        if(result == optionPane.OK_OPTION)
        {
            if(addTeacherNameField.getText().equals("") ==  false)
            {
                if(dv.validatePersonName(addTeacherNameField.getText()))
                {
                    optionPane.showMessageDialog(null, 
                            dv.personNameError);
                }
                else
                {
                    org.addTeacher(addTeacherNameField.getText());
                } 
            }
            else
            {
                optionPane.showMessageDialog(null
                        , "Please provide a teacher name");
            }
        }
        initTeacherTable();
        addTeacherNameField.setText(""); 
    }//GEN-LAST:event_addTeacherButtonActionPerformed

    private void removeTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTeacherButtonActionPerformed
        if(getTeacherTableSelected()== -1)
        {
            optionPane.showMessageDialog(null
                    , "No teacher selected, could not remove.");
        }
        removeTeacher(getTeacherTableSelected());
    }//GEN-LAST:event_removeTeacherButtonActionPerformed

    private void computerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_computerTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_computerTableMouseClicked

    private void computerSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_computerSearchFieldKeyPressed
        computerSorter.setRowFilter(RowFilter.
                regexFilter(computerSearchField.getText()));
    }//GEN-LAST:event_computerSearchFieldKeyPressed

    private void computerSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computerSearchButtonActionPerformed
        computerSorter.setRowFilter(RowFilter.
                regexFilter(computerSearchField.getText()));
    }//GEN-LAST:event_computerSearchButtonActionPerformed

    private void addComputerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addComputerButtonActionPerformed
        int result = optionPane.showConfirmDialog(null, addComputerDialog
                , "Add Computer", optionPane.OK_CANCEL_OPTION
                , optionPane.PLAIN_MESSAGE);
        boolean condition = false;
        boolean dataComplete = false;
        
        if(result == optionPane.OK_OPTION)
        {
            if(addComputerWorkingButton.isSelected()== true || 
                    addComputerNotWorkingButton.isSelected()== true)
                {
                    if(addComputerWorkingButton.isSelected())
                    {
                        condition = true;
                    }
                    else
                    {
                        condition = false;
                    }
                    if(addComputerBrandField.getText().equals("") == false)
                    {
                        if(addComputerModelField.getText().equals("") == false)
                        {
                            dataComplete = true;
                        }
                    }

                }

            if(dataComplete == true)
            {
                if(dv.validateGeneralName(addComputerBrandField.getText()) 
                        || dv.validateGeneralName(addComputerModelField
                                .getText()))
                {
                    org.addComputer(addComputerBrandField.getText()
                            , addComputerModelField.getText(), condition);    
                }
                else
                {
                    optionPane.showMessageDialog(null, dv.generalNameError);
                }
            }
            else
            {
                optionPane.showMessageDialog(
                        null,"Please fill out the form entirely");
            }
        }
        initComputerTable();
        addComputerBrandField.setText("");
        addComputerModelField.setText("");
        addComputerButtonGroup.clearSelection();
    }//GEN-LAST:event_addComputerButtonActionPerformed

    private void removeComputerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeComputerButtonActionPerformed
        if(getComputerTableSelected()== -1)
        {
            optionPane.showMessageDialog(null, 
                    "No computer selected, could not remove."); 
        }
        removeComputer(getComputerTableSelected());
    }//GEN-LAST:event_removeComputerButtonActionPerformed

    private void editStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editStudentButtonActionPerformed
        if(getStudentTableSelected()== -1)
        {
            optionPane.showMessageDialog(null, 
                    "No student selected, could not edit.");
        }
        else
        {
            editStudentOriginalField.setText(studentTable.getValueAt(
                    getStudentTableSelected(), 1).toString());
            int result = optionPane.showConfirmDialog(null, editStudentDialog, 
                    "Edit Student", optionPane.OK_CANCEL_OPTION, 
                    optionPane.PLAIN_MESSAGE);
            if(result == optionPane.OK_OPTION)
            {
                if(editStudentNewField.getText().equals("") == false)
                {
                    if(dv.validatePersonName(editStudentNewField.
                            getText()) == false)
                    {
                        org.getStudent(studentTable.getValueAt
                            (getStudentTableSelected(),0).toString()).
                            changeName(editStudentNewField.getText());
                        initStudentTable();
                    }
                    else
                    {
                        optionPane.showMessageDialog(null, dv.personNameError);
                    }
                }
                else
                {
                    optionPane.showMessageDialog(null, 
                            "Please complete the form");
                }
            }              
        } 
        
        editStudentOriginalField.setText("");
        editStudentNewField.setText("");
    }//GEN-LAST:event_editStudentButtonActionPerformed

    private void editTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTeacherButtonActionPerformed
        if(getTeacherTableSelected()== -1)
        {
            optionPane.showMessageDialog(null, "No teacher selected, "
                    + "could not edit.");
        }
        else
        {
            editTeacherOriginalField.setText(teacherTable.getValueAt(
                    getTeacherTableSelected(), 1).toString());
            int result = optionPane.showConfirmDialog(null, editTeacherDialog, 
                    "Edit Teacher", optionPane.OK_CANCEL_OPTION, 
                    optionPane.PLAIN_MESSAGE);
            if(result == optionPane.OK_OPTION)
            {
                if(editTeacherNewField.getText().equals("") == false)
                {
                    if(dv.validatePersonName(
                            editTeacherNewField.getText()) == false)
                    {
                        org.getTeacher(teacherTable.getValueAt
                            (getTeacherTableSelected(),0).toString())
                            .changeName(editTeacherNewField.getText());
                        initTeacherTable();
                    }
                    else
                    {
                        optionPane.showMessageDialog(null, dv.personNameError);                        
                    }
                }
                else
                {
                    optionPane.showMessageDialog(null, 
                            "Please complete the form");
                }
            }              
        } 
        
        editTeacherOriginalField.setText("");
        editTeacherNewField.setText("");       
    }//GEN-LAST:event_editTeacherButtonActionPerformed

    private void editComputerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editComputerButtonActionPerformed
        boolean changeBrand = false;
        boolean changeModel = false;
        boolean changeCondition = false;
        
        if(getComputerTableSelected()== -1)
        {
            optionPane.showMessageDialog(null, 
                    "No computer selected, could not edit.");
        }
        else
        {
            Computer editComp = org.getComputer(computerTable.getValueAt(
                    getComputerTableSelected(), 0).toString());
            
            editCompBrandOriginalField.setText(editComp.getBrand());
            editCompModelOriginalField.setText(editComp.getModel());
            if(editComp.getCondition())
            {
                editCompWorkingButton.setSelected(true);
            }
            else
            {
                editCompNotWorkingButton.setSelected(true);
            }
            
            int result = optionPane.showConfirmDialog(null, editComputerDialog, 
                    "Edit Computer", optionPane.OK_CANCEL_OPTION,
                    optionPane.PLAIN_MESSAGE);
        
        
        
            if(result == optionPane.OK_OPTION)
            {
                
                
                if(editCompBrandNewField.getText().equals("") == false)
                {
                    if(dv.validateGeneralName(editCompBrandNewField.getText()))
                    {
                        editComp.changeBrand(editCompBrandNewField.getText());
                    }
                    else
                    {
                        optionPane.showMessageDialog(null, dv.generalNameError);
                    }
                    changeBrand = true;
                }
                if(editCompModelNewField.getText().equals("") == false)
                {
                    if(dv.validateGeneralName(editCompModelNewField.getText()))
                    {
                        editComp.changeModel(editCompModelNewField.getText());
                    }
                    else
                    {
                        optionPane.showMessageDialog(null, dv.generalNameError);
                    }
                    changeModel = true;
                }
                
                if(editCompWorkingButton.isSelected() == true)
                {
                    editComp.changeCondition(true);
                    if(editComp.getCondition() == true)
                    {
                        changeCondition = true;
                    }
                }
                else
                {
                    editComp.changeCondition(false);
                    if(editComp.getCondition() == false)
                    {
                        changeCondition = true;
                    }
                } 
                initComputerTable();
                
                if(changeBrand == false && changeModel == false && 
                        changeCondition == false)
                {
                    optionPane.showMessageDialog(null, 
                            "Please complete the form");
                }
            }             
        } 
        


        editCompBrandOriginalField.setText("");
        editCompBrandNewField.setText(""); 
        editCompModelOriginalField.setText("");
        editCompModelNewField.setText(""); 
        editComputerButtonGroup.clearSelection();
        
    }//GEN-LAST:event_editComputerButtonActionPerformed

    private void classesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_classesTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_classesTableMouseClicked

    private void classesSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_classesSearchFieldKeyPressed
        classesSorter.setRowFilter(RowFilter.regexFilter(
                classesSearchField.getText()));
    }//GEN-LAST:event_classesSearchFieldKeyPressed

    private void classesSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classesSearchButtonActionPerformed
        classesSorter.setRowFilter(RowFilter.regexFilter(
                classesSearchField.getText()));
    }//GEN-LAST:event_classesSearchButtonActionPerformed

    private void addClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClassButtonActionPerformed
        int result = optionPane.showConfirmDialog(null, addClassesDialog, 
                "Add Class", optionPane.OK_CANCEL_OPTION, 
                optionPane.PLAIN_MESSAGE);
        
        if(result == optionPane.OK_OPTION)
        {
            if(addClassesNameField.getText().equals("") ==  false)
            {
                if(dv.validateGeneralName(addClassesNameField.getText()))
                {
                    org.addClassroom(addClassesNameField.getText());
                }
                else
                {
                    optionPane.showMessageDialog(null, dv.generalNameError);
                }
            }
            else
            {
                optionPane.showMessageDialog(null, "Please fill out the form");
            }
        }
        initClassesTable();
        addClassesNameField.setText("");         
    }//GEN-LAST:event_addClassButtonActionPerformed

    private void removeClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeClassButtonActionPerformed
        if(getClassesTableSelected()== -1)
        {
            optionPane.showMessageDialog(null,
                    "No classes selected, could not remove.");      
        }
        else
        {
            removeClass(getClassesTableSelected());
        }
    }//GEN-LAST:event_removeClassButtonActionPerformed

    private void editClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editClassButtonActionPerformed
        if(getClassesTableSelected()== -1)
        {
            optionPane.showMessageDialog(null,
                    "No classes selected, could not edit.");
        }
        else
        {
            editClassesOriginalField.setText(classesTable.getValueAt(
                    getClassesTableSelected(), 1).toString());
            int result = optionPane.showConfirmDialog(null, editClassesDialog, 
                    "Edit Class", optionPane.OK_CANCEL_OPTION, 
                    optionPane.PLAIN_MESSAGE);
            if(result == optionPane.OK_OPTION)
            {
                if(editClassesNewField.getText().equals("") == false)
                {
                    if(dv.validateGeneralName(editClassesNewField.getText()))
                    {
                        org.getClassroom(classesTable.getValueAt(
                                getClassesTableSelected(),0).toString()).
                                changeName(editClassesNewField.getText());
                        initClassesTable();
                    }
                    else
                    {
                        optionPane.showMessageDialog(null, dv.generalNameError);
                    } 
                }
                else
                {
                    optionPane.showMessageDialog(null,
                            "Please complete the form");
                }
            }              
        } 
        
        editClassesOriginalField.setText("");
        editClassesNewField.setText("");
    }//GEN-LAST:event_editClassButtonActionPerformed

    private void classEditStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classEditStudentButtonActionPerformed
        if(getClassesTableSelected() == -1)
        {
            optionPane.showMessageDialog(null, "No classes selected,"
                    + " could not edit.");
        }
        else
        {
            Classroom selectedClass = org.getClassroom(classesTable.
                    getValueAt(getClassesTableSelected(),0).toString());
            initClassStudentTable(selectedClass);
            optionPane.showConfirmDialog(null, editClassStudent, 
                "Edit Student List", optionPane.OK_CANCEL_OPTION, 
                optionPane.PLAIN_MESSAGE);   
            initClassesTable();
        }    
    }//GEN-LAST:event_classEditStudentButtonActionPerformed

    private void EnrollStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnrollStudentButtonActionPerformed
        Classroom selectedClass = org.getClassroom(classesTable.getValueAt(
                getClassesTableSelected(),0).toString());
        int[] selectedRows = getClassStudentTableSelected();

        ArrayList<Student> selectedStudents = new ArrayList<Student>();
        
        if(selectedRows[0] != -1)
        {
            for(int i: selectedRows)
            {
                selectedStudents.add(org.getStudent(classStudentTable.
                        getValueAt(i, 0).toString()));
            }
            for(Student student: selectedStudents)
            {
                selectedClass.addStudent(student);
            }
            initClassStudentTable(selectedClass);
            initStudentTable();
        }
        else
        {
            optionPane.showMessageDialog(null, "Please select a student to enroll");
        }
    }//GEN-LAST:event_EnrollStudentButtonActionPerformed

    private void EnrollStudentButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnrollStudentButton1ActionPerformed
        Classroom selectedClass = org.getClassroom(classesTable.
                getValueAt(getClassesTableSelected(),0).toString());
        int[] selectedRows = getClassStudentTableSelected();

        ArrayList<Student> selectedStudents = new ArrayList<Student>();
        
        if(selectedRows[0] != -1)
        {
            for(int i: selectedRows)
            {
                selectedStudents.add(org.getStudent(classStudentTable.
                        getValueAt(i, 0).toString()));
            }
            for(Student student: selectedStudents)
            {
                selectedClass.removeStudent(student);
            }
            initClassStudentTable(selectedClass);
            initStudentTable();
        }
        else
        {
            optionPane.showMessageDialog(null, 
                    "Please select a student to remove");
        }
    }//GEN-LAST:event_EnrollStudentButton1ActionPerformed

    private void classStudentSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classStudentSearchFieldActionPerformed
        classStudentSorter.setRowFilter(RowFilter.regexFilter(
                classStudentSearchField.getText()));
    }//GEN-LAST:event_classStudentSearchFieldActionPerformed

    private void classStudentSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classStudentSearchButtonActionPerformed
        classStudentSorter.setRowFilter(RowFilter.regexFilter(
                classStudentSearchField.getText()));
    }//GEN-LAST:event_classStudentSearchButtonActionPerformed

    private void classStudentSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_classStudentSearchFieldKeyTyped
        classStudentSorter.setRowFilter(RowFilter.regexFilter(
                classStudentSearchField.getText()));
    }//GEN-LAST:event_classStudentSearchFieldKeyTyped

    private void EnrollTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnrollTeacherButtonActionPerformed
        Classroom selectedClass = org.getClassroom(classesTable.
                getValueAt(getClassesTableSelected(),0).toString());
        int[] selectedRows = getClassTeacherTableSelected();

        ArrayList<Teacher> selectedTeachers = new ArrayList<Teacher>();
        if(selectedRows[0]!=-1)
        {
            for(int i: selectedRows)
            {
                selectedTeachers.add(org.getTeacher(classTeacherTable.
                        getValueAt(i, 0).toString()));
            }
            for(Teacher teacher: selectedTeachers)
            {
                selectedClass.addTeacher(teacher);
            }
            initClassTeacherTable(selectedClass);
            initTeacherTable(); 
        }
        else
        {
            optionPane.showMessageDialog(null, 
                    "Please select a teacher to enroll");
        }

    }//GEN-LAST:event_EnrollTeacherButtonActionPerformed

    private void RemoveTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveTeacherButtonActionPerformed
        Classroom selectedClass = org.getClassroom(classesTable.
                getValueAt(getClassesTableSelected(),0).toString());
        int[] selectedRows = getClassTeacherTableSelected();

        ArrayList<Teacher> selectedTeachers = new ArrayList<Teacher>();
        if(selectedRows[0] != -1)
        {
            for(int i: selectedRows)
            {
                selectedTeachers.add(org.getTeacher(classTeacherTable.
                        getValueAt(i, 0).toString()));
            }
            for(Teacher teacher: selectedTeachers)
            {
                selectedClass.removeTeacher(teacher);
            }
            initClassTeacherTable(selectedClass);
            initTeacherTable(); 
        }
        else
        {
            optionPane.showMessageDialog(null, 
                    "Please select a teacher to remove");
        }

    }//GEN-LAST:event_RemoveTeacherButtonActionPerformed

    private void classTeacherSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classTeacherSearchFieldActionPerformed
        classTeacherSorter.setRowFilter(RowFilter.regexFilter(
                classTeacherSearchField.getText()));
    }//GEN-LAST:event_classTeacherSearchFieldActionPerformed

    private void classTeacherSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_classTeacherSearchFieldKeyTyped
        classTeacherSorter.setRowFilter(RowFilter.regexFilter(
                classTeacherSearchField.getText()));
    }//GEN-LAST:event_classTeacherSearchFieldKeyTyped

    private void classTeacherSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classTeacherSearchButtonActionPerformed
        classTeacherSorter.setRowFilter(RowFilter.regexFilter(
                classTeacherSearchField.getText()));
    }//GEN-LAST:event_classTeacherSearchButtonActionPerformed

    private void classEditTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classEditTeacherButtonActionPerformed
        if(getClassesTableSelected() == -1)
        {
            optionPane.showMessageDialog(null, 
                    "No classes selected, could not edit.");
        }
        else
        {
            Classroom selectedClass = org.getClassroom(classesTable.getValueAt(
                    getClassesTableSelected(),0).toString());
            initClassTeacherTable(selectedClass);
            
            optionPane.showConfirmDialog(null, editClassTeacher, 
                "Edit Teacher List", optionPane.OK_CANCEL_OPTION, 
                optionPane.PLAIN_MESSAGE);      
            initClassesTable();
        } 
    }//GEN-LAST:event_classEditTeacherButtonActionPerformed

    private void EnrollComputerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnrollComputerButtonActionPerformed
        Classroom selectedClass = org.getClassroom(classesTable.getValueAt(
                getClassesTableSelected(),0).toString());
        int[] selectedRows = getClassComputerTableSelected();

        ArrayList<Computer> selectedComputers = new ArrayList<Computer>();
        if(selectedRows[0]!=-1)
        {
            for(int i: selectedRows)
            {
                selectedComputers.add(org.getComputer(classComputerTable.
                        getValueAt(i, 0).toString()));
            }
            for(Computer computer: selectedComputers)
            {
                selectedClass.addComputer(computer);
            }
            initClassComputerTable(selectedClass);
            initComputerTable();
        }
        else
        {
            optionPane.showMessageDialog(null,
                    "Please select a computer to assign");
        }
         
    }//GEN-LAST:event_EnrollComputerButtonActionPerformed

    private void RemoveComputerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveComputerButtonActionPerformed
        Classroom selectedClass = org.getClassroom(classesTable.getValueAt(
                getClassesTableSelected(),0).toString());
        int[] selectedRows = getClassComputerTableSelected();

        ArrayList<Computer> selectedComputers = new ArrayList<Computer>();
        if(selectedRows[0]!=-1)
        {
            for(int i: selectedRows)
            {
                selectedComputers.add(org.getComputer(classComputerTable.
                        getValueAt(i, 0).toString()));
            }
            for(Computer computer: selectedComputers)
            {
                selectedClass.removeComputer(computer);
            }
            initClassComputerTable(selectedClass);
            initComputerTable();
        }
        else
        {
            optionPane.showMessageDialog(null, 
                    "Please select a student to remove");
        }
         
    }//GEN-LAST:event_RemoveComputerButtonActionPerformed

    private void classComputerSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classComputerSearchFieldActionPerformed
        classComputerSorter.setRowFilter(RowFilter.regexFilter(
                classComputerSearchField.getText()));
    }//GEN-LAST:event_classComputerSearchFieldActionPerformed

    private void classComputerSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_classComputerSearchFieldKeyTyped
        classComputerSorter.setRowFilter(RowFilter.regexFilter(
                classComputerSearchField.getText()));
    }//GEN-LAST:event_classComputerSearchFieldKeyTyped

    private void classComputerSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classComputerSearchButtonActionPerformed
        classComputerSorter.setRowFilter(RowFilter.regexFilter(
                classComputerSearchField.getText()));
    }//GEN-LAST:event_classComputerSearchButtonActionPerformed

    private void classEditComputerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classEditComputerButtonActionPerformed
        if(getClassesTableSelected() == -1)
        {
            optionPane.showMessageDialog(null, 
                    "No classes selected, could not edit.");
        }
        else
        {
            Classroom selectedClass = org.getClassroom(
                    classesTable.getValueAt(getClassesTableSelected(),0).
                            toString());
            initClassComputerTable(selectedClass);
            
            optionPane.showConfirmDialog(null, editClassComputer, 
                "Edit Computer List", optionPane.OK_CANCEL_OPTION, 
                optionPane.PLAIN_MESSAGE);   
            initClassesTable();
        } 
    }//GEN-LAST:event_classEditComputerButtonActionPerformed

    private void viewDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsButtonActionPerformed
        if(getClassesTableSelected() == -1)
        {
            optionPane.showMessageDialog(null, "No classes selected, "
                    + "cannot view.");
        }
        else
        {
            Classroom selectedClass = org.getClassroom(classesTable.getValueAt(
                    getClassesTableSelected(),0).toString());
            ArrayList<Student> selectedStudents = selectedClass.getStudents();
            ArrayList<Teacher> selectedTeachers = selectedClass.getTeachers();
            ArrayList<Computer> selectedComputers = 
                    selectedClass.getComputers();
            
            DefaultListModel studentListModel = new DefaultListModel();
            DefaultListModel teacherListModel = new DefaultListModel();
            DefaultListModel computerListModel = new DefaultListModel();
            for(Student student: selectedStudents)
            {
                studentListModel.addElement(student.getName());
            }
            for(Teacher teacher: selectedTeachers)
            {
                teacherListModel.addElement(teacher.getName());
            }
            for(Computer computer: selectedComputers)
            {
                computerListModel.addElement(computer.getName());
            }
            
            studentList.setModel(studentListModel);
            teacherList.setModel(teacherListModel);
            computerList.setModel(computerListModel);
            
            optionPane.showConfirmDialog(null, detailsPanel, 
                "Class Details: " + selectedClass.getName(), 
                optionPane.OK_CANCEL_OPTION, optionPane.PLAIN_MESSAGE);      
        } 
    }//GEN-LAST:event_viewDetailsButtonActionPerformed

    private void viewDetailsButtonStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsButtonStudentActionPerformed
        if(getStudentTableSelected()==-1)
        {
            optionPane.showMessageDialog(null, 
                    "No students selected, cannot view.");
        }
        else
        {
            Student selectedStudent = org.getStudent(studentTable.
                    getValueAt(getStudentTableSelected(),0).toString());
            ArrayList<Classroom> selectedClasses = 
                    selectedStudent.getAttending();
            DefaultListModel studentDetailsModel = new DefaultListModel();
            for(Classroom inClass: selectedClasses)
            {
                studentDetailsModel.addElement(inClass.getName());
            }
            detailsStudentList.setModel(studentDetailsModel);
            optionPane.showConfirmDialog(null, studentDetailsPanel, 
                "Student Details: " + selectedStudent.getName(), 
                optionPane.OK_CANCEL_OPTION, optionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_viewDetailsButtonStudentActionPerformed

    private void viewDetailsButtonTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsButtonTeacherActionPerformed
        if(getTeacherTableSelected()==-1)
        {
            optionPane.showMessageDialog(null,
                    "No teachers selected, cannot view.");
        }
        else
        {
            Teacher selectedTeacher = org.getTeacher(teacherTable.
                    getValueAt(getTeacherTableSelected(),0).toString());
            ArrayList<Classroom> selectedClasses = 
                    selectedTeacher.getAttending();
            DefaultListModel teacherDetailsModel = new DefaultListModel();
            for(Classroom inClass: selectedClasses)
            {
                teacherDetailsModel.addElement(inClass.getName());
            }
            detailsTeacherList.setModel(teacherDetailsModel);
            optionPane.showConfirmDialog(null, teacherDetailsPanel, 
                "Teacher Details: " + selectedTeacher.getName(), 
                optionPane.OK_CANCEL_OPTION, optionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_viewDetailsButtonTeacherActionPerformed

    private void editMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMenuActionPerformed

    }//GEN-LAST:event_editMenuActionPerformed

    private void editMenuMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_editMenuMenuKeyPressed
       
    }//GEN-LAST:event_editMenuMenuKeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int reply = optionPane.showConfirmDialog(null, "Are you sure?", 
                    "Clear all entries", optionPane.YES_NO_OPTION);
        if(reply == optionPane.YES_OPTION)
        {
            org.clear();
            initTables();
        }    
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( optionPane.showConfirmDialog(new JFrame(), 
           "Are you sure you want to close?", 
           "Exitting Window", optionPane.YES_NO_OPTION) == 
                optionPane.YES_OPTION) { 

              System.exit(WindowConstants.DISPOSE_ON_CLOSE); 
          } 
          else { 

          }
    }//GEN-LAST:event_formWindowClosing

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        if(originFile == null)
        {
            saveAs();
        }
        else
        {
            try
            {
                persist.save(originFile.getAbsolutePath() + ".reboot");
            }
            catch(IOException e)
            {
                optionPane.showMessageDialog(null, "Cannot save file: " + e);
            }         
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EnrollComputerButton;
    private javax.swing.JButton EnrollStudentButton;
    private javax.swing.JButton EnrollStudentButton1;
    private javax.swing.JButton EnrollTeacherButton;
    private javax.swing.JButton RemoveComputerButton;
    private javax.swing.JButton RemoveTeacherButton;
    private javax.swing.JButton addClassButton;
    private javax.swing.JPanel addClassesDialog;
    private javax.swing.JLabel addClassesLabel;
    private javax.swing.JTextField addClassesNameField;
    private javax.swing.JTextField addComputerBrandField;
    private javax.swing.JLabel addComputerBrandLabel;
    private javax.swing.JButton addComputerButton;
    private javax.swing.ButtonGroup addComputerButtonGroup;
    private javax.swing.JPanel addComputerDialog;
    private javax.swing.JTextField addComputerModelField;
    private javax.swing.JLabel addComputerModelLabel;
    private javax.swing.JRadioButton addComputerNotWorkingButton;
    private javax.swing.JRadioButton addComputerWorkingButton;
    private javax.swing.JButton addStudentButton;
    private javax.swing.JPanel addStudentDialog;
    private javax.swing.JLabel addStudentLabel;
    private javax.swing.JTextField addStudentNameField;
    private javax.swing.JButton addTeacherButton;
    private javax.swing.JPanel addTeacherDialog;
    private javax.swing.JLabel addTeacherLabel;
    private javax.swing.JTextField addTeacherNameField;
    private javax.swing.JScrollPane classComputerPane;
    private javax.swing.JButton classComputerSearchButton;
    private javax.swing.JTextField classComputerSearchField;
    private javax.swing.JTable classComputerTable;
    private javax.swing.JButton classEditComputerButton;
    private javax.swing.JButton classEditStudentButton;
    private javax.swing.JButton classEditTeacherButton;
    private javax.swing.JScrollPane classStudentPane;
    private javax.swing.JButton classStudentSearchButton;
    private javax.swing.JTextField classStudentSearchField;
    private javax.swing.JTable classStudentTable;
    private javax.swing.JScrollPane classTeacherPane;
    private javax.swing.JButton classTeacherSearchButton;
    private javax.swing.JTextField classTeacherSearchField;
    private javax.swing.JTable classTeacherTable;
    private javax.swing.JPanel classesPanel;
    private javax.swing.JButton classesSearchButton;
    private javax.swing.JTextField classesSearchField;
    public javax.swing.JTable classesTable;
    private javax.swing.JScrollPane classesTableScrollPane;
    private javax.swing.JList computerList;
    private javax.swing.JPanel computerPanel;
    private javax.swing.JButton computerSearchButton;
    private javax.swing.JTextField computerSearchField;
    public javax.swing.JTable computerTable;
    private javax.swing.JScrollPane computerTableScrollPane;
    private javax.swing.JLabel computersLabel;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JList detailsStudentList;
    private javax.swing.JList detailsTeacherList;
    private javax.swing.JButton editClassButton;
    private javax.swing.JPanel editClassComputer;
    private javax.swing.JPanel editClassStudent;
    private javax.swing.JPanel editClassTeacher;
    private javax.swing.JPanel editClassesDialog;
    private javax.swing.JTextField editClassesNewField;
    private javax.swing.JLabel editClassesNewLabel;
    private javax.swing.JTextField editClassesOriginalField;
    private javax.swing.JLabel editClassesOriginalLabel;
    private javax.swing.JTextField editCompBrandNewField;
    private javax.swing.JTextField editCompBrandOriginalField;
    private javax.swing.JTextField editCompModelNewField;
    private javax.swing.JTextField editCompModelOriginalField;
    private javax.swing.JRadioButton editCompNotWorkingButton;
    private javax.swing.JSeparator editCompSeperator;
    private javax.swing.JSeparator editCompSeperator2;
    private javax.swing.JRadioButton editCompWorkingButton;
    private javax.swing.JLabel editComputerBrandNewLabel1;
    private javax.swing.JLabel editComputerBrandOriginalLabel;
    private javax.swing.JButton editComputerButton;
    private javax.swing.ButtonGroup editComputerButtonGroup;
    private javax.swing.JPanel editComputerDialog;
    private javax.swing.JLabel editComputerModelNewLabel;
    private javax.swing.JLabel editComputerModelOriginalLabel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JButton editStudentButton;
    private javax.swing.JPanel editStudentDialog;
    private javax.swing.JTextField editStudentNewField;
    private javax.swing.JLabel editStudentNewLabel;
    private javax.swing.JTextField editStudentOriginalField;
    private javax.swing.JLabel editStudentOriginalLabel;
    private javax.swing.JButton editTeacherButton;
    private javax.swing.JPanel editTeacherDialog;
    private javax.swing.JTextField editTeacherNewField;
    private javax.swing.JLabel editTeacherNewLabel;
    private javax.swing.JTextField editTeacherOriginalField;
    private javax.swing.JLabel editTeacherOriginalLabel;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JOptionPane optionPane;
    private javax.swing.JButton removeClassButton;
    private javax.swing.JButton removeComputerButton;
    private javax.swing.JButton removeStudentButton;
    private javax.swing.JButton removeTeacherButton;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JPopupMenu.Separator separator;
    private javax.swing.JPanel studentDetailsPanel;
    private javax.swing.JList studentList;
    private javax.swing.JPanel studentPanel;
    private javax.swing.JButton studentSearchButton;
    private javax.swing.JTextField studentSearchField;
    public javax.swing.JTable studentTable;
    private javax.swing.JScrollPane studentTableScrollPane;
    private javax.swing.JLabel studentsLabel;
    private javax.swing.JLabel studentsLabel1;
    private javax.swing.JLabel studentsLabel3;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel teacherDetailsPanel;
    private javax.swing.JList teacherList;
    private javax.swing.JPanel teacherPanel;
    private javax.swing.JButton teacherSearchButton;
    private javax.swing.JTextField teacherSearchField;
    public javax.swing.JTable teacherTable;
    private javax.swing.JScrollPane teacherTableScrollPane;
    private javax.swing.JLabel teachersLabel;
    private javax.swing.JButton viewDetailsButton;
    private javax.swing.JButton viewDetailsButtonStudent;
    private javax.swing.JButton viewDetailsButtonTeacher;
    // End of variables declaration//GEN-END:variables
}
