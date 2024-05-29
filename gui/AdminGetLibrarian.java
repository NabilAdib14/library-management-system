package gui;
import entity.*;
import repository.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class AdminGetLibrarian extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label, namelabel, passwordlabel, emaillabel;

    private JTextField nametf, passwordtf, emailtf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns = {" Username", "Password", "Email"};
    private Librarian librarians[];

    private JButton Homebutton, Booksbutton, Logoutbutton, aboutbutton, addbutton, updatebutton, deletebutton, clearbutton;


    public AdminGetLibrarian(){
        super("Admin Librarian User Control");
        this.setSize(1080, 650);

        AdminRepository ar1 = new AdminRepository();
        try{
            librarians = ar1.getLibrarians();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error loading available librarians");
        }

        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        Font infoFont = new Font("Century", Font.BOLD, 30);
        Font infoFont2 = new Font("Century", Font.BOLD, 20);


        label = new JLabel("Librarian Information ");
        label.setFont(infoFont);
        label.setBounds(380, 65, 800, 100);
        panel.add(label);


        table = new JTable();

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setSelectionBackground(Color.GREEN);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        scroll = new JScrollPane(table);
        scroll.setBounds(200, 190, 600, 100);
        panel.add(scroll);
        for (int i = 0; i < librarians.length; i++) {
            Librarian l1= librarians[i];
            String[] rowData = {l1.getUsername(),l1.getPassword(),l1.getMail()};
            model.addRow(rowData);
        }
        namelabel = new JLabel("Username:");
        namelabel.setFont(infoFont2);
        namelabel.setBounds(10, 290, 120, 80);
        panel.add(namelabel);

        passwordlabel = new JLabel("Password:");
        passwordlabel.setFont(infoFont2);
        passwordlabel.setBounds(10, 340, 160, 80);
        panel.add(passwordlabel);

        emaillabel = new JLabel("Email:");
        emaillabel.setFont(infoFont2);
        emaillabel.setBounds(10, 390, 125, 80);
        panel.add(emaillabel);

        nametf = new JTextField();
        nametf.setBounds(135, 320, 120, 20);
        panel.add(nametf);

        passwordtf = new JTextField();
        passwordtf.setBounds(150, 371, 120, 20);
        panel.add(passwordtf);

        emailtf = new JTextField();
        emailtf.setBounds(110, 421, 120, 20);
        panel.add(emailtf);


        Homebutton = new JButton("Home");
        Homebutton.setBounds(10, 50, 90, 30);
        Homebutton.setBackground(Color.CYAN);
        Homebutton.setOpaque(true);
        Homebutton.setForeground(Color.BLACK);
        Homebutton.setOpaque(true);
        panel.add(Homebutton);

        Booksbutton = new JButton("Books");
        Booksbutton.setBounds(120, 50, 90, 30);
        Booksbutton.setBackground(Color.CYAN);
        Booksbutton.setOpaque(true);
        Booksbutton.setForeground(Color.BLACK);
        Booksbutton.setOpaque(true);
        panel.add(Booksbutton);



        Logoutbutton = new JButton("Logout");
        Logoutbutton.setBounds(230, 50, 90, 30);
        Logoutbutton.setBackground(Color.CYAN);
        Logoutbutton.setOpaque(true);
        Logoutbutton.setForeground(Color.BLACK);
        Logoutbutton.setOpaque(true);
        panel.add(Logoutbutton);


        aboutbutton = new JButton("About");
        aboutbutton.setBounds(340, 50, 90, 30);
        aboutbutton.setBackground(Color.CYAN);
        aboutbutton.setOpaque(true);
        aboutbutton.setForeground(Color.BLACK);
        aboutbutton.setOpaque(true);
        panel.add(aboutbutton);

        addbutton = new JButton("Add");
        addbutton.setBounds(10, 500, 90, 30);
        addbutton.setBackground(Color.CYAN);
        addbutton.setOpaque(true);
        addbutton.setForeground(Color.BLACK);
        addbutton.setOpaque(true);
        panel.add(addbutton);

        updatebutton = new JButton("Update");
        updatebutton.setBounds(120, 500, 90, 30);
        updatebutton.setBackground(Color.CYAN);
        updatebutton.setOpaque(true);
        updatebutton.setForeground(Color.BLACK);
        updatebutton.setOpaque(true);
        panel.add(updatebutton);

        deletebutton = new JButton("Delete");
        deletebutton.setBounds(230, 500, 90, 30);
        deletebutton.setBackground(Color.CYAN);
        deletebutton.setOpaque(true);
        deletebutton.setForeground(Color.BLACK);
        deletebutton.setOpaque(true);
        panel.add(deletebutton);

        clearbutton = new JButton("Clear");
        clearbutton.setBounds(340, 500, 90, 30);
        clearbutton.setBackground(Color.CYAN);
        clearbutton.setOpaque(true);
        clearbutton.setForeground(Color.BLACK);
        clearbutton.setOpaque(true);
        panel.add(clearbutton);

        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        addbutton.addActionListener(this);
        clearbutton.addActionListener(this);
        deletebutton.addActionListener(this);
        updatebutton.addActionListener(this);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                int numberofrow = table.getSelectedRow();

                String name = model.getValueAt(numberofrow, 0).toString();
                String password = model.getValueAt(numberofrow, 1).toString();
                String email = model.getValueAt(numberofrow, 2).toString();

                nametf.setText(name);
                passwordtf.setText(password);
                emailtf.setText(email);

            }
        });
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int numberofrow = table.getSelectedRow();
        if (e.getSource() == Homebutton) {
            AdminHome a1 = new AdminHome();
            a1.setVisible(true);
            dispose();
        } else if (e.getSource() == Booksbutton) {

            AdminBook b1 = new AdminBook();
            b1.setVisible(true);
            b1.setResizable(false);
            b1.setLocationRelativeTo(null);
            dispose();

        }else if (e.getSource() == aboutbutton) {

            JOptionPane.showMessageDialog(null, " Umar Farouk Ahmed-(23-53185-3) \n Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);

        } else if (e.getSource() == addbutton) {

            String username = nametf.getText();
            String password = passwordtf.getText();
            String email = emailtf.getText();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null,"All fields are required to add a librarian.");
            } else {
                String[] newRowData = new String[3];
                newRowData[0] = nametf.getText();
                newRowData[1] = passwordtf.getText();
                newRowData[2] = emailtf.getText();
                model.addRow(newRowData);
                Librarian l1 = new Librarian(username, password, email);
                AdminRepository ar1 = new AdminRepository();
                try{
                    ar1.addLibrarian(l1);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Librarian added successfully");
                nametf.setText("");
                passwordtf.setText("");
                emailtf.setText("");
            }
        } else if (e.getSource() == clearbutton) {

            nametf.setText("");
            passwordtf.setText("");
            emailtf.setText("");

        } else if (e.getSource() == deletebutton) {
            if (numberofrow >= 0) {
                String username = model.getValueAt(numberofrow, 0).toString();
                String password =  model.getValueAt(numberofrow, 1).toString();
                String email = model.getValueAt(numberofrow, 2).toString();
                Librarian l1 = new Librarian(username, password, email);
                AdminRepository ar1 = new AdminRepository();
                try{
                    ar1.removeLibrarian(l1);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                model.removeRow(numberofrow);
                JOptionPane.showMessageDialog(null, "Librarian removed successfully.");

            } else {
                JOptionPane.showMessageDialog(null, "No row has been selected");
            }

        } else if (e.getSource() == updatebutton) {

            if(numberofrow>=0){
                String name = nametf.getText();
                String password = passwordtf.getText();
                String email = emailtf.getText();
                if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"All fields are required to update a librarian.");
                }else{
                    String oldUsername = model.getValueAt(numberofrow, 0).toString();
                    String oldPassword = model.getValueAt(numberofrow, 1).toString();
                    String oldMail = model.getValueAt(numberofrow, 2).toString();
                    Librarian oldLibrarian=new Librarian(oldUsername, oldPassword, oldMail);
                    Librarian newLibrarian=new Librarian(name, password, email);
                    AdminRepository ar1 = new AdminRepository();
                    try{
                        ar1.updateLibrarian(oldLibrarian, newLibrarian);
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    model.setValueAt(name, numberofrow, 0);
                    model.setValueAt(password, numberofrow, 1);
                    model.setValueAt(email, numberofrow, 2);
                    nametf.setText("");
                    passwordtf.setText("");
                    emailtf.setText("");
                    JOptionPane.showMessageDialog(null, "Librarian Updated Successfully");
                }
            }else {
                JOptionPane.showMessageDialog(null,"Please select a row to update.");
            }
        }
        else if(e.getSource()==Logoutbutton){
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if(x==JOptionPane.YES_OPTION){
                WelcomeFrame w1=new WelcomeFrame();
                w1.setVisible(true);
                dispose();
            }
        }
    }
}