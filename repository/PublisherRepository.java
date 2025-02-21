package repository;
import java.sql.*;

import javax.swing.JOptionPane;

import entity.*;
import java.io.*;

public class PublisherRepository {
	 private DatabaseConnection dbc;
	 public PublisherRepository() {
	        dbc = new DatabaseConnection();
	 }
	 
     public void addPublisher(Publisher p){
		String query = "INSERT INTO publisher (P_NAME,P_EMAIL,P_PHN) VALUES "
				+ "('"+p.getUsername()+"','"+p.getMail()+"','"+p.getPhone()+"');";
		try{
			dbc.openConnection();
			dbc.st.execute(query);
		} catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
		} finally {
			dbc.closeConnection();
		}
	 }

     public void removePublisher(Publisher p) {
        String query = "DELETE FROM publisher WHERE P_ID ="+p.getID()+";"; 
        try {
        	dbc.openConnection();
        	dbc.st.execute(query);
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
     }   
	 public Publisher getPublisherByUsername(String username) {
        Publisher publisher = null;
        String query =  "SELECT * FROM publisher WHERE P_NAME = '" + username + "';";
        try{
            dbc.result=dbc.st.executeQuery(query);
            if(dbc.result.next()) {
            	int id =dbc.result.getInt("P_ID");
            	String name =dbc.result.getString("P_NAME");
            	String mail = dbc.result.getString("P_EMAIL");
            	String phn =dbc.result.getString("P_PHN");
            	publisher = new Publisher(id,name,mail,phn);
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return publisher;
      }

	 public Publisher getPublisherById(Integer pid) {
        Publisher publisher = null;
        String query =  "SELECT * FROM publisher WHERE P_ID =" + pid + ";";
        try{
        	dbc.openConnection();
        	dbc.result=dbc.st.executeQuery(query);
        	if(dbc.result.next()) {
        		int id =dbc.result.getInt("P_ID");
            	String name =dbc.result.getString("P_NAME");
            	String mail = dbc.result.getString("P_EMAIL");
            	String phn =dbc.result.getString("P_PHN");
            	publisher = new Publisher(id,name,mail,phn);
        	}
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return publisher;
	}
    
	 public int getNumberOfPublishers() {
        int count = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM publisher";
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

	 public Publisher[] getPublishers() {
        int numberOfPublishers = getNumberOfPublishers();
        Publisher[] publishers = new Publisher[numberOfPublishers];
        String query = "SELECT * FROM publisher";       
        try {
        	dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            int index = 0;
            while (dbc.result.next() && index < numberOfPublishers) {
                Integer id = dbc.result.getInt("P_ID");
                String username = dbc.result.getString("P_NAME");
                String mail = dbc.result.getString("P_EMAIL");
                String phone = dbc.result.getString("P_PHN");
                publishers[index] = new Publisher(id, username, mail, phone);
                index++;
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return publishers; 
    }


}
