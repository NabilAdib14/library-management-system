package gui;
import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class AdminHomeFrame extends JFrame implements ActionListener{
    private JLabel AvailableBooks, getinfo;
    private JPanel panel;
    private JButton Homebutton, Booksbutton, Logoutbutton, aboutbutton, librarianbutton, studentbutton;
    private String loggedInUsername;

    public AdminHomeFrame() {
        super("ADMIN");
        this.setSize(1080, 650);

        panel = new JPanel();
        panel.setLayout(null); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        Font infoFont = new Font("SansSerif", Font.BOLD, 30);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 16);
        Color buttonColor = new Color(139, 69, 19); 

        BookRepository br1 = new BookRepository();
        AvailableBooks = new JLabel("Available Books : " + br1.getNumberOfBooks());
        AvailableBooks.setBounds(120, 180, 400, 100);
        AvailableBooks.setForeground(Color.BLACK);
        AvailableBooks.setFont(infoFont);
        panel.add(AvailableBooks);

        getinfo = new JLabel("Get Info: ");
        getinfo.setBounds(140, 345, 400, 100);
        getinfo.setForeground(Color.BLACK);
        getinfo.setFont(infoFont);
        getinfo.setFont(new Font("SansSerif", Font.BOLD, 30));
        panel.add(getinfo);

        librarianbutton = new JButton("Librarian");
        librarianbutton.setBounds(290, 375, 120, 40);
        librarianbutton.setBackground(buttonColor);
        librarianbutton.setFont(buttonFont);
        librarianbutton.setForeground(Color.WHITE);
        panel.add(librarianbutton);

        studentbutton = new JButton("Student");
        studentbutton.setBounds(430, 375, 120, 40);
        studentbutton.setBackground(buttonColor);
        studentbutton.setFont(buttonFont);
        studentbutton.setForeground(Color.WHITE);
        panel.add(studentbutton);

        Homebutton = new JButton("Home");
        Homebutton.setBounds(275, 100, 90, 30);
        Homebutton.setBackground(buttonColor);
        Homebutton.setFont(buttonFont);
        Homebutton.setForeground(Color.WHITE);
        panel.add(Homebutton);

        Booksbutton = new JButton("Books");
        Booksbutton.setBounds(385, 100, 90, 30);
        Booksbutton.setBackground(buttonColor);
        Booksbutton.setFont(buttonFont);
        Booksbutton.setForeground(Color.WHITE);
        panel.add(Booksbutton);

        Logoutbutton = new JButton("Logout");
        Logoutbutton.setBounds(505, 100, 90, 30);
        Logoutbutton.setBackground(buttonColor);
        Logoutbutton.setFont(buttonFont);
        Logoutbutton.setForeground(Color.WHITE);
        panel.add(Logoutbutton);

        aboutbutton = new JButton("About");
        aboutbutton.setBounds(615, 100, 90, 30);
        aboutbutton.setBackground(buttonColor);
        aboutbutton.setFont(buttonFont);
        aboutbutton.setForeground(Color.WHITE);
        panel.add(aboutbutton);

        ImageIcon signbgImg = new ImageIcon("gui\\image\\LibrarianHome.png");
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);
        JLabel signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight());
        panel.add(signbg);


        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        librarianbutton.addActionListener(this);
        studentbutton.addActionListener(this);
        
        this.add(panel);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Homebutton){
           AdminHomeFrame a1=new AdminHomeFrame();
           a1.setVisible(true);
           dispose();
       }
       else if(e.getSource()==Booksbutton){
           AdminBookFrame b1=new AdminBookFrame();
           b1.setVisible(true);
           dispose();
       }
       else if(e.getSource()==librarianbutton){
           AdminGetLibrarian a1=new AdminGetLibrarian();
           a1.setVisible(true);
           dispose();
        }
       else if(e.getSource()==studentbutton){
           AdminGetStudent s1=new AdminGetStudent();
           s1.setVisible(true);
           dispose();

        }
       else if(e.getSource()==Logoutbutton){
           int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
           if (choice == JOptionPane.YES_OPTION) {
               WelcomeFrame w1=new WelcomeFrame();
               w1.setVisible(true);
               dispose();
           }
       }
       else if(e.getSource()==aboutbutton){
           JOptionPane.showMessageDialog(null,"Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)","Contributors",JOptionPane.PLAIN_MESSAGE);
       }

   }

}
