package gui;
import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class StudentProfile extends JFrame implements ActionListener{
    private JPanel panel;
    private JLabel namelabel, passwordlabel, emaillabel, l1, idlabel, phnlabel;
    private JTextField TF1, TF2, TF3, TF4;
    private JButton updatebutton, Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton;
    private String loggedInUsername;

    public StudentProfile(String loggedInUsername) {
        super("Student Profile");
        this.setSize(1080, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        this.loggedInUsername=loggedInUsername;
        panel = new JPanel(null);
        
        ImageIcon signbgImg = new ImageIcon("gui\\image\\LibrarianProfile.png");
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);
        JLabel signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight());
        
        Font infoFont = new Font("SansSerif", Font.BOLD, 18);
        
        String id;
        StudentRepository s1 = new StudentRepository();
        Student st = s1.getStudentByUsername(loggedInUsername);
        id = String.valueOf(st.getID());
        idlabel = new JLabel("ID: "+id);
        idlabel.setBounds(90, 200, 300, 100);
        idlabel.setForeground(Color.BLACK);
        idlabel.setFont(infoFont);
        signbg.add(idlabel);
        
        namelabel = new JLabel("Username: ");
        namelabel.setBounds(90, 250, 300, 100);
        namelabel.setForeground(Color.BLACK);
        namelabel.setFont(infoFont);
        signbg.add(namelabel);

        TF1 = new JTextField();
        TF1.setBounds(250, 290, 210, 30);
        TF1.setForeground(Color.BLACK.brighter());
        signbg.add(TF1);

        passwordlabel = new JLabel("Password: ");
        passwordlabel.setBounds(90, 300, 300, 100);
        passwordlabel.setForeground(Color.BLACK);
        passwordlabel.setFont(infoFont);
        signbg.add(passwordlabel);

        TF2 = new JTextField();
        TF2.setBounds(250, 340, 210, 30);
        TF2.setForeground(Color.BLACK.brighter());
        signbg.add(TF2);

        emaillabel = new JLabel("Email: ");
        emaillabel.setBounds(90, 350, 300, 100);
        emaillabel.setForeground(Color.BLACK);
        emaillabel.setFont(infoFont);
        signbg.add(emaillabel);

        TF3 = new JTextField();
        TF3.setBounds(250, 390, 210, 30);
        TF3.setForeground(Color.BLACK.brighter());
        signbg.add(TF3);

        TF4 = new JTextField();
        TF4.setBounds(250, 440, 210, 30);
        TF4.setForeground(Color.BLACK.brighter());
        signbg.add(TF4);

        phnlabel = new JLabel("Phone: ");
        phnlabel.setBounds(90, 400, 300, 100);
        phnlabel.setForeground(Color.BLACK);
        phnlabel.setFont(infoFont);
        signbg.add(phnlabel);

        l1 = new JLabel("Provide your updated credentials.");
        l1.setBounds(90, 140, 700, 100);
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("SansSerif", Font.PLAIN, 20));
        signbg.add(l1);

        Homebutton = createStyledButton("Home", 275, 100);
        Booksbutton = createStyledButton("Books", 385, 100);
        Profilebutton = createStyledButton("Profile", 505, 100);
        Logoutbutton = createStyledButton("Logout", 615, 100);
        aboutbutton = createStyledButton("About", 725, 100);
        updatebutton = createStyledButton("UPDATE", 170, 500, 200, 40);

        signbg.add(Homebutton);
        signbg.add(Booksbutton);
        signbg.add(Profilebutton);
        signbg.add(Logoutbutton);
        signbg.add(aboutbutton);
        signbg.add(updatebutton);
        
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        Profilebutton.addActionListener(this);
        updatebutton.addActionListener(this);
        
        panel.add(signbg);
        this.add(panel);
        
        TF1.setText(st.getUsername());
		TF2.setText(st.getPassword());
		TF3.setText(st.getMail());
		TF4.setText(st.getPhone());
    }
    
    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 90, 30);
        button.setBackground(new Color(139, 69, 19));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        return button;
    }
    
    private JButton createStyledButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(139, 69, 19));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        return button;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Homebutton) {
            StudentHomeFrame a1 = new StudentHomeFrame(loggedInUsername);
            a1.setVisible(true);
            dispose();
        } else if (e.getSource() == Booksbutton) {
            StudentBookFrame b1 = new StudentBookFrame(loggedInUsername);
            b1.setVisible(true);
            dispose();
        }else if (e.getSource() == aboutbutton) {

            JOptionPane.showMessageDialog(null, " Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);

        }else if(e.getSource()==Profilebutton) {
			StudentProfile sp1 = new StudentProfile(loggedInUsername);
			sp1.setVisible(true);
			dispose();
		}else if(e.getSource()==Logoutbutton){
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if(x==JOptionPane.YES_OPTION){
                WelcomeFrame w1=new WelcomeFrame();
                w1.setVisible(true);
                dispose();
            }
        } else if (e.getSource() == updatebutton) {
		    StudentRepository sr1 = new StudentRepository();
		    Student st = sr1.getStudentByUsername(loggedInUsername);
		    String username = TF1.getText().trim();
		    String password = TF2.getText().trim();
		    String email = TF3.getText().trim();
		    String phone = TF4.getText().trim();

		    if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Please fill in all fields before updating.");
		        return;
		    }

		    Student updatedStudent = new Student(st.getID(), username, password, email, phone);
		    sr1.updateStudent(updatedStudent);
		    TF1.setText("");
		    TF2.setText("");
		    TF3.setText("");
		    TF4.setText("");
		    
		    Student st1 = sr1.getStudentById(st.getID());
		    StudentProfile s1 = new StudentProfile(st1.getUsername());
			s1.setVisible(true);
			dispose();
		}

	}
    
 
}
