package repository;
import java.sql.*;
import javax.swing.JOptionPane;
import entity.*;

public class BookRepository {
	private DatabaseConnection dbc; 
    private PublisherRepository pr;
    public BookRepository() {
        pr = new PublisherRepository();
        dbc=new DatabaseConnection();
    }

    public int getNumberOfTypesOfBooks() {
        int number = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM book";
        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            if (dbc.result.next()) {
                number = dbc.result.getInt("TOTAL");
            }
        } catch(Exception ex){
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
		} finally {
            dbc.closeConnection(); 
        }
        return number;
    }
    
    public int getNumberOfBooks() {
        int counter = 0;
        String query = "SELECT SUM(B_QUANTITY) AS TOTAL FROM book";
        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            if (dbc.result.next()) {
                counter = dbc.result.getInt("TOTAL");
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return counter;
    }

    public Book[] getAvailableBooks() {
        int numberOfBooks = getNumberOfTypesOfBooks();
        Book[] books = new Book[numberOfBooks];
        String query = "SELECT * FROM book";
        try {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);
            int index = 0;
            while (dbc.result.next()) {
                Integer id = dbc.result.getInt("B_ID");
                String title = dbc.result.getString("B_TITLE");
                String author = dbc.result.getString("B_AUTHOR");
                int quantity = dbc.result.getInt("B_QUANTITY");
                Integer publisherId = dbc.result.getInt("P_ID");

                Publisher publisher = pr.getPublisherById(publisherId);

                Book book = new Book(id,title, author, quantity, publisher);
                books[index] = book;
                index++;
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return books;
    }

    public void addBook(Book b) {
		String query = "INSERT INTO book (B_TITLE,B_AUTHOR,B_QUANTITY,P_ID) VALUES "
				+ "('"+b.getTitle()+"','"+b.getAuthor()+"','"+b.getQuantity()+"','"+b.getPublisher().getID()+"');";
		try	{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Book added successfully");
		} catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
		} finally {
			dbc.closeConnection();
		}
    }

    public void removeBook(Book b) {
    	String checkQuery = "SELECT COUNT(*) FROM issue_table WHERE B_ID = " + b.getID() + ";";
        String query = "DELETE FROM book WHERE B_ID = '"+b.getID()+"';"; 
        try {
    	   dbc.openConnection();
    	   dbc.result =dbc.st.executeQuery(checkQuery);
    	   if (dbc.result.next() && dbc.result.getInt("COUNT(*)") == 0) {
               dbc.st.executeUpdate(query);
               JOptionPane.showMessageDialog(null, "Book removed successfully");
           } else {
               JOptionPane.showMessageDialog(null, "Cannot remove book. There are issued books.");
           }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }

	public void updateBook(Book b){
		String query = "UPDATE book SET B_TITLE = '"+b.getTitle()+"', "
									 + "B_AUTHOR = '"+b.getAuthor()+"', "
									 + "B_QUANTITY = '"+b.getQuantity()+"', "
									 + "P_ID = '"+b.getPublisher().getID()+"'  "
									 + "WHERE B_ID='"+b.getID()+"';"; 
		try{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Book updated successfully");
		} catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
		}finally {
			dbc.closeConnection();
		}
	}
	
	public boolean hasStudentBorrowedBook(int studentId, int bookId) {
	    String query = "SELECT COUNT(*) FROM issue_table WHERE S_ID = " + studentId + " AND B_ID = " + bookId + ";";
	    try {
	        dbc.openConnection();  
	        dbc.result = dbc.st.executeQuery(query);
	        if (dbc.result.next()) {
	            if(dbc.result.getInt("COUNT(*)") > 0) {
	            	return true;
	            };
	        }
	    }catch(Exception ex){
	    	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
	    }
	    finally {
	        dbc.closeConnection();
	    }
	    return false;
	}

	 public boolean borrowBook(int studentId, int bookId) {
	    String insertIssueQuery = "INSERT INTO issue_table (I_DATE, B_ID, S_ID) VALUES (NOW(), " + bookId + ", " + studentId + ");";
	    String updateBookQuery = "UPDATE book SET B_QUANTITY = B_QUANTITY - 1 WHERE B_ID = " + bookId + ";";
	    boolean isSuccess = false;
	    try {
	        dbc.openConnection();
	        int rowsInserted = dbc.st.executeUpdate(insertIssueQuery);
	        if (rowsInserted > 0) {
	            int rowsUpdated = dbc.st.executeUpdate(updateBookQuery);
	            if (rowsUpdated > 0) {
	                isSuccess = true; 
	            }
	        }
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
	    } finally {
	        dbc.closeConnection();
	    }
	    return isSuccess; 
	} 
	 
	public boolean returnBook(int studentId, int bookId) {
	    String deleteIssueQuery = "DELETE FROM issue_table WHERE S_ID = " + studentId + " AND B_ID = " + bookId + ";";
	    String updateBookQuery = "UPDATE book SET B_QUANTITY = B_QUANTITY + 1 WHERE B_ID = " + bookId + ";";
	    boolean isSuccess = false;

	    try {
	        dbc.openConnection();
	        int rowsDeleted = dbc.st.executeUpdate(deleteIssueQuery);
	        if (rowsDeleted > 0) {
	            int rowsUpdated = dbc.st.executeUpdate(updateBookQuery);
	            if (rowsUpdated > 0) {
	                isSuccess = true;
	            }
	        }
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
	    } finally {
	        dbc.closeConnection();
	    }
	    return isSuccess;
	}


	public Book getBookById(int bid) {
	    String query = "SELECT * FROM book WHERE B_ID = '" + bid+ "';";
	    Book book = null;
	    try {
	        dbc.openConnection();
	        dbc.result = dbc.st.executeQuery(query);
	        if (dbc.result.next()) {
	            int id = dbc.result.getInt("B_ID");
	            String title = dbc.result.getString("B_TITLE");
	            String author = dbc.result.getString("B_AUTHOR");
	            int quantity = dbc.result.getInt("B_QUANTITY");
	            int publisherid = dbc.result.getInt("P_ID");

	            Publisher publisher = pr.getPublisherById(publisherid);

	            book = new Book(id, title, author, quantity, publisher);
	        }
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
	    } finally {
	        dbc.closeConnection();
	    }
	    return book; 
	}


}
