package gui;
import entity.*;
import repository.*;
import javax.swing.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LibrarianProfile extends JFrame  implements ActionListener{

    private  JPanel panel;
    private JLabel namelabel,passwordlabel,emaillabel,l1;
    private JTextField TF1,TF2,TF3;
    private JButton Homebutton,Booksbutton,button3,Profilebutton,Logoutbutton,updatebutton,aboutbutton;


    public LibrarianProfile(){
        super("Librarian Profile");
        this.setSize(1080,650);

        panel=new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) /2, (screenSize.height - getHeight())/2);
        Font infoFont=new Font("Century",Font.BOLD,30);

        namelabel=new JLabel("Username ");
        namelabel.setBounds(50,100,300,100);
        namelabel.setForeground(Color.BLACK);
        namelabel.setFont(infoFont);
        panel.add(namelabel);

        TF1=new JTextField();
        TF1.setBounds(200,140,200,30);
        TF1.setForeground(Color.BLACK.brighter());
        panel.add(TF1);

        passwordlabel=new JLabel("Password ");
        passwordlabel.setBounds(50,150,300,100);
        passwordlabel.setForeground(Color.BLACK);
        passwordlabel.setFont(infoFont);
        panel.add(passwordlabel);

        TF2=new JTextField();
        TF2.setBounds(200,190,200,30);
        TF2.setForeground(Color.BLACK.brighter());
        panel.add(TF2);

        emaillabel=new JLabel("Email ");
        emaillabel.setBounds(50,200,300,100);
        emaillabel.setForeground(Color.BLACK);
        emaillabel.setFont(infoFont);
        panel.add(emaillabel);

        TF3=new JTextField();
        TF3.setBounds(200,235,200,30);
        TF3.setForeground(Color.BLACK.brighter());
        panel.add(TF3);

        l1=new JLabel("Enter your own username\nEnter your new password and/or email.");
        l1.setBounds(50,60,700,100);
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Century",Font.PLAIN,20));
        panel.add(l1);

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

        updatebutton=new JButton("UPDATE");
        updatebutton.setBounds(10,350,200,40);
        updatebutton.setBackground(Color.CYAN);
        updatebutton.setOpaque(true);
        updatebutton.setForeground(Color.BLACK);
        updatebutton.setOpaque(true);
        updatebutton.setFont(infoFont);
        panel.add(updatebutton);

        Profilebutton.addActionListener(this);
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        updatebutton.addActionListener(this);
        this.add(panel);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updatebutton) {
            String username = TF1.getText();
            String newPassword = TF2.getText();
            String newEmail = TF3.getText();
            if (username.isEmpty() || newPassword.isEmpty() || newEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
            } else {
                try{
                    Librarian l1 = new Librarian(username, newPassword, newEmail);
                    LibrarianRepository lr1 = new LibrarianRepository();
                    lr1.updateLibrarian(l1);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Profile updated successfully");
            }
        }
        else if(e.getSource()==Profilebutton){
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
