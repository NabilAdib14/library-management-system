package repository;
import entity.*;
import javax.swing.JOptionPane;
import java.sql.*;

public class AdminRepository{
	private DatabaseConnection dbc;
    public AdminRepository(){
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

    
    public void removeStudent(Student s) {
        String checkQuery = "SELECT COUNT(*) FROM issue_table WHERE S_ID = " + s.getID() + ";";
        String query1 = "DELETE FROM manager_student WHERE S_ID = " + s.getID() + ";";
        String query2 = "DELETE FROM student WHERE S_ID = " + s.getID() + ";";

        try {
            dbc.openConnection();
            ResultSet rs = dbc.st.executeQuery(checkQuery);
            if (rs.next() && rs.getInt("COUNT(*)") == 0) {
                dbc.st.executeUpdate(query1);
                dbc.st.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Student removed successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Cannot remove student. There are issued books.");
            }

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
                             "' WHERE S_ID = " + s.getID()+";" ;

        try {
            dbc.openConnection();
            dbc.st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Student updated successfully.");

        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }
    
	
	public int getNumberOfStudents() {
        int count = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM student";
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
        String query = "SELECT S.S_ID, S.S_NAME, S.S_PWD, S.S_EMAIL, S.S_PHN FROM student s, manager_student M "
        		+ "WHERE M.S_ID = S.S_ID AND M.M_ID=111";
        
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
    
    
    public void addLibrarian(Librarian lb) {
        try {
            dbc.openConnection();
            Statement stmt = dbc.con.createStatement();
            String query = "INSERT INTO librarian (L_NAME, L_PWD, L_EMAIL, L_PHN) VALUES "
                    + "('" + lb.getUsername() + "', '" + lb.getPassword() + "', '" + lb.getMail() + "', '" + lb.getPhone() + "');";
            stmt.executeUpdate(query);
            Integer librarianID = getGeneratedID(lb);
            if (librarianID != -1) {
            	String query1="INSERT INTO manager_librarian (L_ID, M_ID) VALUES (" + librarianID + ", 111);";
                stmt.executeUpdate(query1);
                JOptionPane.showMessageDialog(null, "Librarian added successfully");
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
    
    public void removeLibrarian(Librarian lb) {
        String query = "DELETE FROM librarian WHERE L_ID = "+lb.getID()+";"; 
        String query1 = "DELETE FROM manager_librarian WHERE l_ID ="+ lb.getID()+";";
        try {
        	   dbc.openConnection();
        	   dbc.st.executeUpdate(query1);
        	   dbc.st.executeUpdate(query);
               JOptionPane.showMessageDialog(null, "Librarian removed successfully.");
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }
    
    public void updateLibrarian(Librarian lb) {
        String updateQuery = "UPDATE librarian SET L_NAME = '" + lb.getUsername() + 
                             "', L_PWD = '" + lb.getPassword() + 
                             "', L_EMAIL = '" + lb.getMail() + 
                             "', L_PHN = '" + lb.getPhone() + 
                             "' WHERE L_ID = " + lb.getID() + ";";
        try {
            dbc.openConnection();
            dbc.st.executeUpdate(updateQuery);
            JOptionPane.showMessageDialog(null,"Librarian updated successfully.");
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }
    
	public int getNumberOfLibrarians() {
        int count = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM librarian";
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
	
    public Librarian[] getLibrarians() {
        int numberOfLibrarians = getNumberOfLibrarians();
        Librarian[] librarians = new Librarian[numberOfLibrarians];
        String query = "SELECT M.L_ID, L.L_NAME, L.L_PWD, L.L_EMAIL, L.L_PHN FROM librarian L, manager_librarian M "
        		+ "WHERE M.L_ID = L.L_ID AND M.M_ID=111";
        
        try {
        	dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            int index = 0;
            while (dbc.result.next() && index < numberOfLibrarians) {
                Integer id = dbc.result.getInt("L_ID");
                String username = dbc.result.getString("L_NAME");
                String password = dbc.result.getString("L_PWD");
                String mail = dbc.result.getString("L_EMAIL");
                String phone = dbc.result.getString("L_PHN");
                librarians[index] = new Librarian(id, username, password, mail, phone);
                index++;
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return librarians;
    }
    
    public Integer getGeneratedID(Librarian lb) {
        Integer generatedId = -1;
        String query = "SELECT L_ID FROM librarian WHERE L_NAME='" + lb.getUsername() + "';";

        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query); 

            if (dbc.result.next()) { 
                generatedId = dbc.result.getInt("L_ID");
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection(); 
        }

        return generatedId; 
    }
}
