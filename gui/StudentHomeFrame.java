package gui;
import entity.*;
import repository.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentHomeFrame extends JFrame implements ActionListener{
    private JLabel label1, label2, label3, label4;
    private JLabel usernameLabel, idLabel, emailLabel;
    private JPanel panel;
    private JButton Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton;
    private AdminRepository ar;
    private String loggedInUsername;

    public StudentHomeFrame(String loggedInUsername) {
        super("Student");
        this.setSize(1080, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        this.loggedInUsername=loggedInUsername;
        
        StudentRepository sr = new StudentRepository();
        Student loggedInStudent = sr.getStudentByUsername(loggedInUsername);
        
        panel = new JPanel(null);

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
        if (loggedInStudent != null) {
            username = loggedInStudent.getUsername();
        } else {
            username = "Unknown";
        }
        usernameLabel = new JLabel(username);
        usernameLabel.setBounds(280, 200, 200, 30); 
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 29));
        signbg.add(usernameLabel);

        label2 = new JLabel("ID: ");
        label2.setBounds(120, 300, 60, 30); 
        label2.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(label2);
        
        String id;
        if(loggedInStudent!=null) {
        	id=String.valueOf(loggedInStudent.getID());
        }else {
        	id="Unknown";
        }
        idLabel = new JLabel(id);
        idLabel.setBounds(160, 300, 100, 30); 
        idLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(idLabel);

        label3 = new JLabel("Email: ");
        label3.setBounds(300, 300, 100, 30); 
        label3.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(label3);

        String email;
        if(loggedInStudent!=null) {
        	email=loggedInStudent.getMail();
        }else {
        	email="Unknown";
        }
        emailLabel = new JLabel(email);
        emailLabel.setBounds(380, 300, 400, 30);
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(emailLabel);

        BookRepository br1 = new BookRepository();
        label4 = new JLabel("Available Books: " + br1.getNumberOfBooks());
        label4.setBounds(120, 400, 400, 30); 
        label4.setFont(new Font("SansSerif", Font.BOLD, 24));
        signbg.add(label4);

        Homebutton = createStyledButton("Home", 275, 100);
        Booksbutton = createStyledButton("Books", 385, 100);
        Profilebutton = createStyledButton("Profile", 505, 100);
        Logoutbutton = createStyledButton("Logout", 615, 100);
        aboutbutton = createStyledButton("About", 725, 100);

        signbg.add(Homebutton);
        signbg.add(Booksbutton);
        signbg.add(Profilebutton);
        signbg.add(Logoutbutton);
        signbg.add(aboutbutton);

        panel.add(signbg);
        this.add(panel);
        
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        Profilebutton.addActionListener(this);
        aboutbutton.addActionListener(this);
    }

    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 90, 30);
        button.setBackground(new Color(139, 69, 19));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        return button;
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

		}
	}
    
}
