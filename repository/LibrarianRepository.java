package repository;
import java.sql.*;

import javax.swing.JOptionPane;

import entity.*;
import java.sql.*;


public class LibrarianRepository {
	private DatabaseConnection dbc;
    public LibrarianRepository(){
    	dbc=new DatabaseConnection();
    };

    public void addLibrarian(Librarian l) {
        try {
            dbc.openConnection();
            Statement stmt = dbc.con.createStatement();
            String query = "INSERT INTO librarian (L_NAME, L_PWD, L_EMAIL, L_PHN) VALUES "
                         + "('" + l.getUsername() + "', '" + l.getPassword() + "', '" + l.getMail() + "', '" + l.getPhone() + "');";
            stmt.executeUpdate(query);

            Integer librarianID = getGeneratedID(l);
            if (librarianID != -1) {
                stmt.executeUpdate("INSERT INTO manager_librarian (L_ID, M_ID) VALUES (" + librarianID + ", 111);");
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

        
	public void updateLibrarian(Librarian l){
		String query = "UPDATE librarian SET L_NAME = '" + l.getUsername() + 
                "', L_PWD = '" + l.getPassword() + 
                "', L_EMAIL = '" + l.getMail() + 
                "', L_PHN = '" + l.getPhone() + 
                "' WHERE L_ID = " + l.getID() + ";"; 
		try{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"Information Updated Successfully");
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
			}
		finally {
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
        String query = "SELECT * FROM librarian";
        dbc.openConnection();
        try {
            dbc.result = dbc.st.executeQuery(query);
            int index = 0;
            while (dbc.result.next() && index < numberOfLibrarians) {
            	Integer id = dbc.result.getInt("L_ID");
                String username = dbc.result.getString("L_NAME");
                String password = dbc.result.getString("L_PWD");
                String mail = dbc.result.getString("L_EMAIL");
                String phn=dbc.result.getString("L_PHN");
                librarians[index] = new Librarian(id,username, password, mail, phn);
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
    	
    public Integer getGeneratedID(Librarian l) {
        Integer generatedId = -1;
        String query = "SELECT L_ID FROM librarian WHERE L_NAME='" + l.getUsername() + "';";

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
    
    public Librarian getLibrarianByUsername(String username) {
        String query = "SELECT * FROM librarian WHERE L_NAME = '" + username + "';";
        Librarian librarian = null;
        
        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            if (dbc.result.next()) {
                int id = dbc.result.getInt("L_ID");
                String password = dbc.result.getString("L_PWD");
                String email = dbc.result.getString("L_EMAIL");
                String phone = dbc.result.getString("L_PHN");
                librarian = new Librarian(id, username, password, email, phone);
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        
        return librarian; 
    }
    
    public Librarian getLibrarianById(Integer lid) {
        String query = "SELECT * FROM librarian WHERE L_ID = '" + lid + "';";
        Librarian librarian = null;
        
        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            
            if (dbc.result.next()) {
                String username = dbc.result.getString("L_NAME");
                String password = dbc.result.getString("L_PWD");
                String email = dbc.result.getString("L_EMAIL");
                String phone = dbc.result.getString("L_PHN");
                librarian = new Librarian(lid, username, password, email, phone);
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        
        return librarian; 
    }

    
}
