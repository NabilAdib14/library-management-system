package gui;
import entity.*;
import repository.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import  java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StudentProfile extends JFrame  implements ActionListener{
    private  JPanel panel;
    private JLabel namelable,idlable,emaillable,l1;
    private JTextField TF1,TF2,TF3;
    private  JButton savebutton;

    private JButton Homebutton,Booksbutton,button3,Profilebutton,Logoutbutton,updatebutton,aboutbutton;


    public StudentProfile(){
        super("Student Profile");
        this.setSize(1080,650);

        panel=new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) /2, (screenSize.height - getHeight())/2);
        Font infoFont=new Font("Century",Font.BOLD,30);

        namelable=new JLabel("Username ");
        namelable.setBounds(50,100,300,100);
        namelable.setForeground(Color.BLACK);
        namelable.setFont(infoFont);
        panel.add(namelable);

        TF1=new JTextField();
        TF1.setBounds(200,140,200,30);
        TF1.setForeground(Color.BLACK.brighter());
        panel.add(TF1);

        idlable=new JLabel("Password ");
        idlable.setBounds(50,150,300,100);
        idlable.setForeground(Color.BLACK);
        idlable.setFont(infoFont);
        panel.add(idlable);

        TF2=new JTextField();
        TF2.setBounds(200,190,200,30);
        TF2.setForeground(Color.BLACK.brighter());
        panel.add(TF2);

        emaillable=new JLabel("Email ");
        emaillable.setBounds(50,200,300,100);
        emaillable.setForeground(Color.BLACK);
        emaillable.setFont(infoFont);
        panel.add(emaillable);

        TF3=new JTextField();
        TF3.setBounds(200,235,200,30);
        TF3.setForeground(Color.BLACK.brighter());
        panel.add(TF3);

        l1=new JLabel("Enter your own username. Enter your new password and/or email.");
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updatebutton) {
            String username = TF1.getText();
            String newPassword = TF2.getText();
            String newEmail = TF3.getText();
            if (username.isEmpty() || newPassword.isEmpty() || newEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
            } else {
                try {
                    Student s1 = new Student(username, newPassword, newEmail);
                    StudentRepository sr1 = new StudentRepository();
                    sr1.updateStudent(s1);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Profile updated successfully");
            }
        }
        else if(e.getSource()==Profilebutton){
            StudentProfile p1=new StudentProfile();
            p1.setVisible(true);
            dispose();
        }
        else if(e.getSource()==Homebutton){
            StudentFrame a1=new StudentFrame();
            a1.setVisible(true);
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
