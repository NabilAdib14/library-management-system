package repository;
import entity.*;
import java.sql.*;
import java.io.*;
import javax.swing.JOptionPane;

public class StudentRepository {
	private DatabaseConnection dbc;
    public StudentRepository(){
    	dbc=new DatabaseConnection();
    };
    
    public void addStudent(Student s) {
        try {
            dbc.openConnection();
            Statement stmt = dbc.con.createStatement();
            String query = "INSERT INTO student (S_NAME, S_PWD, S_EMAIL, S_PHN) VALUES "
                         + "('" + s.getUsername() + "', '" + s.getPassword() + "', '" + s.getMail() + "', '" + s.getPhone() + "');";
            stmt.executeUpdate(query);
            Integer studentID = getGeneratedID(s);
            if (studentID != -1) {
            	String query1 = "INSERT INTO manager_student (S_ID, M_ID) VALUES (" + studentID + ", 111);";
                stmt.executeUpdate(query1);
                JOptionPane.showMessageDialog(null, "Student added successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
            stmt.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }

    
    public void updateStudent(Student s) {
        String query = "UPDATE student SET S_NAME = '" + s.getUsername() + 
                       "', S_PWD = '" + s.getPassword() + 
                       "', S_EMAIL = '" + s.getMail() + 
                       "', S_PHN = '" + s.getPhone() + 
                       "' WHERE S_ID = " + s.getID() + ";";

        try {
            dbc.openConnection();
            dbc.st.executeUpdate(query); 
            JOptionPane.showMessageDialog(null,"Information Updated Successfully");
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
       
    }
    
    public int getNumberOfStudents() {
        int count = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM student;";
        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            if (dbc.result.next()) {
                count = dbc.result.getInt("TOTAL");
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return count;
    }
    
    public Student[] getStudents() {
        int numberOfStudents = getNumberOfStudents();
        Student[] students = new Student[numberOfStudents];
        String query = "SELECT * FROM student";
        
        try {
        	dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            int index = 0;
            while (dbc.result.next() && index < numberOfStudents) {
                Integer id = dbc.result.getInt("S_ID");
                String username = dbc.result.getString("S_NAME");
                String password = dbc.result.getString("S_PWD");
                String mail = dbc.result.getString("S_EMAIL");
                String phone = dbc.result.getString("S_PHN");
                students[index] = new Student(id, username, password, mail, phone);
                index++;
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return students;
    }
    
    public Integer getGeneratedID(Student s) {
        Integer generatedId = -1;
        String query = "SELECT S_ID FROM student WHERE S_NAME='" + s.getUsername() + "';"; 
        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            if (dbc.result.next()) { 
                generatedId = dbc.result.getInt("S_ID");
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }

        return generatedId;
    }
    
    public Student getStudentByUsername(String username) {
        String query = "SELECT * FROM student WHERE S_NAME = '" + username + "';";
        Student student = null;
        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);            
            if (dbc.result.next()) {
                int id = dbc.result.getInt("S_ID");
                String password = dbc.result.getString("S_PWD");
                String email = dbc.result.getString("S_EMAIL");
                String phone = dbc.result.getString("S_PHN"); 
                student = new Student(id, username, password, email, phone);
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        
        return student; 
    }
    
    public Student getStudentById(Integer sid) {
        String query = "SELECT * FROM student WHERE S_ID = " + sid + ";";
        Student student = null;
        
        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            if (dbc.result.next()) {
            	String username = dbc.result.getString("S_NAME");
                String password = dbc.result.getString("S_PWD");
                String email = dbc.result.getString("S_EMAIL");
                String phone = dbc.result.getString("S_PHN");
                student = new Student(sid, username, password, email, phone);
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        
        return student; 
    }


    
}
