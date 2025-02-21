package gui;
import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianProfile extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel namelabel, passwordlabel, emaillabel, label1, idlabel, phnlabel;
    private JTextField TF1, TF2, TF3, TF4;
    private JButton Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton, updatebutton;
    private String loggedInUsername;

    public LibrarianProfile(String loggedInUsername) {
        super("Librarian Profile");
        this.setSize(1080, 650);
        this.loggedInUsername=loggedInUsername;

        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        ImageIcon signbgImg = new ImageIcon("gui\\image\\LibrarianProfile.png"); 
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);

        JLabel signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight());  

        panel.add(signbg, Integer.valueOf(0)); 

        Font infoFont = new Font("SansSerif", Font.BOLD, 18);
        
        
        String id;
        LibrarianRepository l1 = new LibrarianRepository();
        Librarian lb = l1.getLibrarianByUsername(loggedInUsername);
        id = String.valueOf(lb.getID());
        
        idlabel = new JLabel("ID: "+id);
        idlabel.setBounds(90, 205, 300, 100);
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

        label1 = new JLabel("Enter updated credentials");
        label1.setBounds(90, 140, 700, 100);
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("SansSerif", Font.PLAIN, 20));
        signbg.add(label1);

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

        updatebutton = new JButton("UPDATE");
        updatebutton.setBounds(170, 500, 200, 40);
        updatebutton.setBackground(new Color(139, 69, 19));
        updatebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        updatebutton.setForeground(Color.WHITE);
        signbg.add(updatebutton);
        
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        Profilebutton.addActionListener(this);
        updatebutton.addActionListener(this);

        this.add(panel);
        
        TF1.setText(lb.getUsername());
		TF2.setText(lb.getPassword());
		TF3.setText(lb.getMail());
		TF4.setText(lb.getPhone());
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Homebutton) {
            LibrarianHomeFrame a1 = new LibrarianHomeFrame(loggedInUsername);
            a1.setVisible(true);
            dispose();
        } else if (e.getSource() == Booksbutton) {
            LibrarianBookFrame b1 = new LibrarianBookFrame(loggedInUsername);
            b1.setVisible(true);
            dispose();

        }else if (e.getSource() == aboutbutton) {

            JOptionPane.showMessageDialog(null, " Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);

        }else if(e.getSource()==Profilebutton) {
			LibrarianProfile sp1 = new LibrarianProfile(loggedInUsername);
			sp1.setVisible(true);
			dispose();
		}else if(e.getSource()==Logoutbutton){
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if(x==JOptionPane.YES_OPTION){
                WelcomeFrame w1=new WelcomeFrame();
                w1.setVisible(true);
                dispose();
            }
		}else if (e.getSource() == updatebutton) {
		    LibrarianRepository lr1 = new LibrarianRepository();
		    Librarian lb = lr1.getLibrarianByUsername(loggedInUsername);
		    String username = TF1.getText().trim();
		    String password = TF2.getText().trim();
		    String email = TF3.getText().trim();
		    String phone = TF4.getText().trim();

		    if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Please fill in all fields before updating.");
		        return;
		    }
		    Librarian updatedlb = new Librarian(lb.getID(), username, password, email, phone);
		    lr1.updateLibrarian(updatedlb);
		    TF1.setText("");
		    TF2.setText("");
		    TF3.setText("");
		    TF4.setText("");
		    Librarian lb1 = lr1.getLibrarianById(lb.getID());
		    LibrarianProfile s1 = new LibrarianProfile(lb1.getUsername());
			s1.setVisible(true);
			dispose();
		}		
	}
}
