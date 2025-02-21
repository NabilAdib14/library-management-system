package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class LibrarianIssuedBooks extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label, booknamelable, authornamelable, bookquantitylable;
    private JTextField booktf, authortf, quantitytf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns = {"Issue ID", "Issue Date", "Book ID", "Student ID"};
    private Book[] books;
    private DatabaseConnection dbc;
    private JButton Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton, addbutton, updatebutton, deletebutton, clearbutton;
    private String loggedInUsername;
    
    public LibrarianIssuedBooks(String loggedInUsername) {
        super("Issued Books");
        this.setSize(1080, 650);
        this.loggedInUsername=loggedInUsername;
        		
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

        label = new JLabel("Issued Books");
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setBounds(445, 115, 800, 100);
        signbg.add(label);

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
        
        model.setRowCount(0);
        dbc = new DatabaseConnection();	
        String query = "SELECT * FROM issue_table";	
        try {
            dbc.openConnection();
            dbc.result= dbc.st.executeQuery(query);	            
            while (dbc.result.next()) {
                int issueID = dbc.result.getInt("I_ID");
                String issueDate = dbc.result.getString("I_DATE");
                int bookID = dbc.result.getInt("B_ID");	
                int studentid = dbc.result.getInt("S_ID");
                String[] rowData = {String.valueOf(issueID), issueDate, String.valueOf(bookID), String.valueOf(studentid)};
                model.addRow(rowData);
            }           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally {
        	 dbc.closeConnection();
        }

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
        
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Profilebutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);


        this.add(panel);  
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Homebutton) {
			LibrarianHomeFrame shf1 = new LibrarianHomeFrame(loggedInUsername);
			shf1.setVisible(true);
			dispose();
		}else if(e.getSource()==Booksbutton) {
			LibrarianBookFrame sbf1 = new LibrarianBookFrame(loggedInUsername);
			sbf1.setVisible(true);
			dispose();
		}else if(e.getSource()==Profilebutton) {
			LibrarianProfile sp1 = new LibrarianProfile(loggedInUsername); 
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

		}		
	}
    
}
