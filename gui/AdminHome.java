package gui;
import entity.*;
import repository.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.lang.*;
import java.awt.*;
import  java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AdminHome extends JFrame implements ActionListener{

    private JLabel AvailableBooks,getinfo;
    private ImageIcon img;
    private JPanel panel;
    private JTextField userTF;
    private JButton Homebutton,Booksbutton,Logoutbutton,aboutbutton,librarianbutton,studentbutton;
    private final BookFrame BookFrame;
    public AdminHome(){
        super("ADMIN");
        this.setSize(1080,650);
        this.BookFrame = new BookFrame();



        panel=new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) /2, (screenSize.height - getHeight())/2);
        Font infoFont=new Font("Century",Font.BOLD,30);

        BookRepository br1 = new BookRepository();
        AvailableBooks=new JLabel("Available Books : "+ br1.getNumberofBooks());
        AvailableBooks.setBounds(10,180,400,100);
        AvailableBooks.setForeground(Color.BLACK);
        AvailableBooks.setFont(infoFont);
        panel.add(AvailableBooks);

        getinfo =new JLabel("Get Info: ");
        getinfo.setBounds(10,240,400,100);
        getinfo.setForeground(Color.BLACK);
        getinfo.setFont(infoFont);
        panel.add(getinfo);

        librarianbutton=new JButton("Librarian");
        librarianbutton.setBounds(160,275,90,30);
        librarianbutton.setBackground(Color.CYAN);
        librarianbutton.setOpaque(true);
        librarianbutton.setForeground(Color.BLACK);
        librarianbutton.setOpaque(true);
        panel.add(librarianbutton);

        studentbutton=new JButton("Student");
        studentbutton.setBounds(270,275,90,30);
        studentbutton.setBackground(Color.CYAN);
        studentbutton.setOpaque(true);
        studentbutton.setForeground(Color.BLACK);
        studentbutton.setOpaque(true);
        panel.add(studentbutton);
        

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



        Logoutbutton=new JButton("Logout");
        Logoutbutton.setBounds(230,50,90,30);
        Logoutbutton.setBackground(Color.CYAN);
        Logoutbutton.setOpaque(true);
        Logoutbutton.setForeground(Color.BLACK);
        Logoutbutton.setOpaque(true);
        panel.add(Logoutbutton);

        aboutbutton=new JButton("About");
        aboutbutton.setBounds(340,50,90,30);
        aboutbutton.setBackground(Color.CYAN);
        aboutbutton.setOpaque(true);
        aboutbutton.setForeground(Color.BLACK);
        aboutbutton.setOpaque(true);
        panel.add(aboutbutton);



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
            AdminHome a1=new AdminHome();
            a1.setVisible(true);
            dispose();
        }
        else if(e.getSource()==Booksbutton){
            AdminBook b1=new AdminBook();
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
            JOptionPane.showMessageDialog(null," Umar Farouk Ahmed-(23-53185-3) \n Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)","Contributors",JOptionPane.PLAIN_MESSAGE);
        }

    }


}
