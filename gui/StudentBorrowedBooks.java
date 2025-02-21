package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;

import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StudentBorrowedBooks extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label, booknamelable, authornamelable, bookquantitylable, idlabel, booknamelabel, authornamelabel;
    private JTextField idtf, booktf, authortf, quantitytf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns = {"Issue Date", "Book ID", "Title","Author ","Publisher ID"};
    private String loggedInUsername;
    private JButton Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton, returnbutton, clearbutton;
    private Book[] books;
    private DatabaseConnection dbc;
    
    public StudentBorrowedBooks(String loggedInUsername) {
    	super("Borrowed Books");
        this.setSize(1080, 650);
        this.loggedInUsername = loggedInUsername;

        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        
        ImageIcon signbgImg = new ImageIcon("gui\\image\\bg.png"); 
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);

        JLabel signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight()); 
        panel.add(signbg);

        label = new JLabel("Borrowed Books");
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setBounds(445, 115, 800, 100);
        signbg.add(label);
        
        idlabel = new JLabel("Book ID:");
        idlabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        idlabel.setBounds(130, 370, 100, 80);
        signbg.add(idlabel);

        booknamelabel = new JLabel("Book Title:");
        booknamelabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        booknamelabel.setBounds(130, 420, 160, 80);
        signbg.add(booknamelabel);

        authornamelabel = new JLabel("Author Name:");
        authornamelabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        authornamelabel.setBounds(130, 470, 160, 80);
        signbg.add(authornamelabel);


        idtf = new JTextField();
        idtf.setBounds(270, 400, 235, 20);
        signbg.add(idtf);

        booktf = new JTextField();
        booktf.setBounds(270, 450, 235, 20); 
        signbg.add(booktf);

        authortf = new JTextField();
        authortf.setBounds(270, 500, 235, 20);
        signbg.add(authortf);
        

        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setSelectionBackground(Color.GREEN);
        table.setBackground(Color.decode("#fff6ed"));
        table.setRowHeight(30);
        scroll = new JScrollPane(table);
        scroll.setBounds(245, 190, 600, 170);
        signbg.add(scroll);
        
        populateTable();
        
        Homebutton = new JButton("Home");
        Homebutton.setBounds(275, 100, 90, 30);
        Homebutton.setBackground(new Color(139, 69, 19)); 
        Homebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Homebutton.setForeground(Color.WHITE);
        signbg.add(Homebutton);

        Booksbutton = new JButton("Books");
        Booksbutton.setBounds(385, 100, 90, 30);
        Booksbutton.setBackground(new Color(139, 69, 19)); 
        Booksbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Booksbutton.setForeground(Color.WHITE);
        signbg.add(Booksbutton);

        Profilebutton = new JButton("Profile");
        Profilebutton.setBounds(505, 100, 90, 30);
        Profilebutton.setBackground(new Color(139, 69, 19)); 
        Profilebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Profilebutton.setForeground(Color.WHITE);
        signbg.add(Profilebutton);

        Logoutbutton = new JButton("Logout");
        Logoutbutton.setBounds(615, 100, 90, 30);
        Logoutbutton.setBackground(new Color(139, 69, 19)); 
        Logoutbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Logoutbutton.setForeground(Color.WHITE);
        signbg.add(Logoutbutton);

        aboutbutton = new JButton("About");
        aboutbutton.setBounds(725, 100, 90, 30);
        aboutbutton.setBackground(new Color(139, 69, 19)); 
        aboutbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        aboutbutton.setForeground(Color.WHITE);
        signbg.add(aboutbutton);

        returnbutton = createActionButton("Return", 320, 551, new Color(139, 69, 19));
        signbg.add(returnbutton);
        
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Profilebutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        returnbutton.addActionListener(this);
        
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                int numberofrow = table.getSelectedRow();
                Integer id = Integer.parseInt( model.getValueAt(numberofrow, 1).toString());
                String title = model.getValueAt(numberofrow, 2).toString();
                String author = model.getValueAt(numberofrow, 3).toString();
                

                idtf.setText(String.valueOf(id));
                booktf.setText(title);
                authortf.setText(author);

            }
        });
        this.add(panel);  
    }
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 90, 30);
        button.setBackground(new Color(139, 69, 19)); 
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        return button;
    }

    private JButton createActionButton(String text, int x, int y, Color bgColor) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 90, 30);
        button.setBackground(bgColor);
        button.setOpaque(true);
        button.setForeground(Color.white);
        return button;
    }

    private void populateTable() {
        StudentRepository sr = new StudentRepository();
        Student student = sr.getStudentByUsername(loggedInUsername);       
        if (student == null) {
            JOptionPane.showMessageDialog(this, "Error: Unable to find student details.");           
        }
        else{
	        int studentID = student.getID();	     
	        DatabaseConnection dbc = new DatabaseConnection();	
	        String query = "SELECT I.I_DATE, B.B_ID, B.B_TITLE, B.B_AUTHOR, B.P_ID FROM book B, issue_table I WHERE B.B_ID=I.B_ID AND I.S_ID =" + studentID;	
	        try {
	            dbc.openConnection();
	            dbc.result = dbc.st.executeQuery(query);	            
	            while (dbc.result.next()) {
	                String issueDate = dbc.result.getString("I_DATE");
	                int bookID = dbc.result.getInt("B_ID");	
	                String title = dbc.result.getString("B_TITLE");
	                String author = dbc.result.getString("B_AUTHOR");
	                int pid = dbc.result.getInt("P_ID");
	                String[] rowData = {issueDate, String.valueOf(bookID), title, author, String.valueOf(pid)};
	                model.addRow(rowData);
	            }	       
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, ex.getMessage());
	        } finally {
	        	dbc.closeConnection();
	        }
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Homebutton) {
			StudentHomeFrame shf1 = new StudentHomeFrame(loggedInUsername);
			shf1.setVisible(true);
			dispose();
		}else if(e.getSource()==Booksbutton) {
			StudentBookFrame sbf1 = new StudentBookFrame(loggedInUsername);
			sbf1.setVisible(true);
			dispose();
		}else if(e.getSource()==Profilebutton) {
			StudentProfile sp1 = new StudentProfile(loggedInUsername);
			sp1.setVisible(true);
			dispose();
		}else if(e.getSource()==Logoutbutton) {
			 int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
	            if(x==JOptionPane.YES_OPTION){
	                WelcomeFrame w1=new WelcomeFrame();
	                w1.setVisible(true);
	                dispose();
	            }
		}else if(e.getSource()==aboutbutton) {
            JOptionPane.showMessageDialog(null, " Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);

		} else if (e.getSource() == returnbutton) {	
			int selectedRow = table.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(this, "Please select a book to return.");
	            return;
	        }
	        int bookId = Integer.parseInt(model.getValueAt(selectedRow, 1).toString());
	        StudentRepository sr = new StudentRepository();
	        Student student = sr.getStudentByUsername(loggedInUsername);
	        int studentId = student.getID();
	        BookRepository br = new BookRepository();
	        if (br.returnBook(studentId, bookId)==true) {
	            JOptionPane.showMessageDialog(this, "Book returned successfully!");
	            idtf.setText("");
	            booktf.setText("");
	            authortf.setText("");
	            model.setRowCount(0);
	            populateTable();
	        } else {
	            JOptionPane.showMessageDialog(this, "Failed to return the book. Please try again.");
	        }
		}
	}
}
