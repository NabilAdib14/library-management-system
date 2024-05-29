package gui;
import entity.*;
import repository.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.lang.*;
import java.awt.*;
import  java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentFrame extends JFrame implements ActionListener{

    private JLabel user,AvailableBooks,imagelable;
    private ImageIcon img;
    private JPanel panel;
    private JTextField userTF;
    private JButton Homebutton,Booksbutton,Profilebutton,Logoutbutton,aboutbutton;
    private final BookFrame BookFrame;

    public StudentFrame(){
        super("Student");
        this.setSize(1080,650);
        this.BookFrame = new BookFrame(); 

        panel=new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) /2, (screenSize.height - getHeight())/2);
        Font infoFont=new Font("Century",Font.BOLD,30);



        AvailableBooks=new JLabel("Available Books : "+ String.valueOf(BookFrame.getTotalBooks()));
        AvailableBooks.setBounds(10,180,400,100);
        AvailableBooks.setForeground(Color.BLACK);
        AvailableBooks.setFont(infoFont);
        panel.add(AvailableBooks);

        Homebutton=new JButton("Home");
        Homebutton.setBounds(10,50,90,30);
        Homebutton.setBackground(Color.CYAN);
        Homebutton.setOpaque(true);
        Homebutton.setForeground(Color.BLACK);
        Homebutton.setOpaque(true);
        panel.add(Homebutton);

        Booksbutton=new JButton("Books");
        Booksbutton.setBounds(120,50,90,30);
        Booksbutton.setBackground(Color.CYAN);
        Booksbutton.setOpaque(true);
        Booksbutton.setForeground(Color.BLACK);
        Booksbutton.setOpaque(true);
        panel.add(Booksbutton);

        Profilebutton=new JButton("Profile");
        Profilebutton.setBounds(240,50,90,30);
        Profilebutton.setBackground(Color.CYAN);
        Profilebutton.setOpaque(true);
        Profilebutton.setForeground(Color.BLACK);
        Profilebutton.setOpaque(true);
        panel.add(Profilebutton);

        Logoutbutton=new JButton("Logout");
        Logoutbutton.setBounds(350,50,90,30);
        Logoutbutton.setBackground(Color.CYAN);
        Logoutbutton.setOpaque(true);
        Logoutbutton.setForeground(Color.BLACK);
        Logoutbutton.setOpaque(true);
        panel.add(Logoutbutton);

        aboutbutton=new JButton("About");
        aboutbutton.setBounds(460,50,90,30);
        aboutbutton.setBackground(Color.CYAN);
        aboutbutton.setOpaque(true);
        aboutbutton.setForeground(Color.BLACK);
        aboutbutton.setOpaque(true);
        panel.add(aboutbutton);





        Profilebutton.addActionListener(this);
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        this.add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Profilebutton){
            StudentProfile p1=new StudentProfile();
            p1.setVisible(true);
            dispose();
        } 
        else if(e.getSource()==Homebutton){
            StudentFrame s1 = new StudentFrame();
            s1.setVisible(true);
            dispose();
        }
        else if(e.getSource()==Booksbutton){
            StudentBooks b1=new StudentBooks();
            b1.setVisible(true);

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
            JOptionPane.showMessageDialog(null," Umar Farouk Ahmed-(23-53185-3) \n Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)","Contributors",JOptionPane.PLAIN_MESSAGE);
        }

    }

}
