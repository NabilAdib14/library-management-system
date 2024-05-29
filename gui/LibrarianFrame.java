package gui;
import entity.*;
import repository.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;
import java.awt.*;
import  java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianFrame extends JFrame implements ActionListener {

    private JLabel user,AvailableBooks,AvailableStudents,imagelable;
    private ImageIcon img;
    private JPanel panel;
    private JTextField userTF;
    private JButton Homebutton,Booksbutton,Profilebutton,Logoutbutton,aboutbutton;
    private final BookFrame BookFrame;

    public LibrarianFrame(){
        super("Librarian");
        this.setSize(1080,650);
        this.BookFrame = new BookFrame(); 

        BookRepository br1 = new BookRepository();
        StudentRepository sr1 = new StudentRepository();

        panel=new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) /2, (screenSize.height - getHeight())/2);
        Font infoFont=new Font("Century",Font.BOLD,30);

//        img=new ImageIcon(getClass().getResource("image/book.png"));
//        imagelable=new JLabel(img);
//        imagelable.setBounds(300,0,1080,650);
//        panel.add(imagelable);

        AvailableBooks=new JLabel("Available Books : "+ br1.getNumberofBooks());
        AvailableBooks.setBounds(10,180,400,100);
        AvailableBooks.setForeground(Color.BLACK);
        AvailableBooks.setFont(infoFont);
        panel.add(AvailableBooks);

        AvailableStudents=new JLabel("Available Students : "+ sr1.getNumberofStudents());
        AvailableStudents.setBounds(10,260,400,100);
        AvailableStudents.setForeground(Color.BLACK);
        AvailableStudents.setFont(infoFont);
        panel.add(AvailableStudents);

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

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Profilebutton){
            LibrarianProfile p1=new LibrarianProfile();
                p1.setVisible(true);
                dispose();
        }
        else if(e.getSource()==Homebutton){
            LibrarianFrame a1=new LibrarianFrame();
                a1.setVisible(true);
               dispose();
        }
        else if(e.getSource()==Booksbutton){
            BookFrame b1=new BookFrame();
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
