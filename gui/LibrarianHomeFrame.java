package gui;
import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class LibrarianHomeFrame extends JFrame implements ActionListener {

    private JLabel AvailableBooks, AvailableStudents;
    private JLabel usernameLabel, idLabel, emailLabel;
    private JPanel panel;
    private JButton Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton;
    private JLabel label1, label2, label3, label4;
    private String loggedInUsername;
    private AdminRepository adminRepository;

    public LibrarianHomeFrame(String loggedInUsername) {
    	super("Librarian");
        this.setSize(1080, 650);
        this.loggedInUsername = loggedInUsername; 
        
        LibrarianRepository lr = new LibrarianRepository();
        Librarian loggedInLibrarian = lr.getLibrarianByUsername(loggedInUsername); 


        panel = new JPanel();
        panel.setLayout(null); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        adminRepository = new AdminRepository();

        ImageIcon signbgImg = new ImageIcon("gui\\image\\LibrarianHome.png"); 
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);

        JLabel signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight());  

        label1 = new JLabel("Welcome, ");
        label1.setBounds(120, 200, 200, 30);
        label1.setFont(new Font("SansSerif", Font.BOLD, 30));
        signbg.add(label1);
        
        String username;
        if (loggedInLibrarian != null) {
            username = loggedInLibrarian.getUsername();
        } else {
            username = "Unknown";
        }
        usernameLabel = new JLabel(username);
        usernameLabel.setBounds(280, 200, 200, 30);
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        signbg.add(usernameLabel);

        label2 = new JLabel("ID: ");
        label2.setBounds(120, 300, 200, 30);
        label2.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(label2);
        
        String id;
        if(loggedInLibrarian!=null) {
        	id=String.valueOf(loggedInLibrarian.getID());
        }else {
        	id="Unknown";
        }
        idLabel = new JLabel(id);
        idLabel.setBounds(160, 300, 200, 30);
        idLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(idLabel);

        label3 = new JLabel("Email: ");
        label3.setBounds(320, 300, 200, 30);
        label3.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(label3);
        
        String email;
        if(loggedInLibrarian!=null) {
        	email=loggedInLibrarian.getMail();
        }else {
        	email="Unknown";
        }
        emailLabel = new JLabel(email);
        emailLabel.setBounds(410, 300, 400, 30);
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(emailLabel);
        
        
        BookRepository br1 = new BookRepository();
        label4 = new JLabel("Available Books: " + br1.getNumberOfBooks());
        label4.setBounds(120, 400, 250, 30);
        label4.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(label4);

        Homebutton = new JButton("Home");
        Homebutton.setBounds(275, 100, 90, 30);
        Homebutton.setBackground(new Color(139, 69, 19)); 
        Homebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Homebutton.setForeground(Color.WHITE);
        panel.add(Homebutton);

        Booksbutton = new JButton("Books");
        Booksbutton.setBounds(385, 100, 90, 30);
        Booksbutton.setBackground(new Color(139, 69, 19)); 
        Booksbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Booksbutton.setForeground(Color.WHITE);
        panel.add(Booksbutton);

        Profilebutton = new JButton("Profile");
        Profilebutton.setBounds(505, 100, 90, 30);
        Profilebutton.setBackground(new Color(139, 69, 19)); 
        Profilebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Profilebutton.setForeground(Color.WHITE);
        panel.add(Profilebutton);

        Logoutbutton = new JButton("Logout");
        Logoutbutton.setBounds(615, 100, 90, 30);
        Logoutbutton.setBackground(new Color(139, 69, 19)); 
        Logoutbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Logoutbutton.setForeground(Color.WHITE);
        panel.add(Logoutbutton);

        aboutbutton = new JButton("About");
        aboutbutton.setBounds(725, 100, 90, 30);
        aboutbutton.setBackground(new Color(139, 69, 19));
        aboutbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        aboutbutton.setForeground(Color.WHITE);
        panel.add(aboutbutton);
        
        panel.add(signbg);  
        this.add(panel);
        
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        Profilebutton.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Homebutton) {
            LibrarianHomeFrame a1 = new LibrarianHomeFrame(loggedInUsername);
            a1.setVisible(true);
            dispose();
        } else if (e.getSource() == Booksbutton) {
            LibrarianBookFrame b1 = new LibrarianBookFrame(loggedInUsername);
            b1.setVisible(true);
            dispose();
        }else if(e.getSource()==Profilebutton) {
			LibrarianProfile lp1 = new LibrarianProfile(loggedInUsername);
			lp1.setVisible(true);
			dispose();
		}else if (e.getSource() == Logoutbutton) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                WelcomeFrame w1 = new WelcomeFrame();
                w1.setVisible(true);
                dispose();
            }
        } else if (e.getSource() == aboutbutton) {
            JOptionPane.showMessageDialog(null, "Md. Nabil Adibur Rahman-(23-53187-3) \nMaisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
   
}




