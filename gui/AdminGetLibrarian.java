package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdminGetLibrarian extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label, namelabel, passwordlabel, emaillabel, phnlabel;
    private JTextField nametf, passwordtf, emailtf, phntf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns = {"ID", "Username", "Password", "Email","Phone"};
    private JButton Homebutton, Booksbutton, Logoutbutton, aboutbutton, addbutton, updatebutton, deletebutton, clearbutton;
    private Librarian librarians[];
    private String loggedInUsername;
    
    public AdminGetLibrarian() {
        super("Admin Librarian User Control");
        this.setSize(1080, 650);
        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        

        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 16);
        Color buttonColor = new Color(139, 69, 19); 

        label = new JLabel("Librarian Information ");
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setBounds(410, 65, 800, 100);
        panel.add(label);

        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setSelectionBackground(Color.GREEN);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80); 
        columnModel.getColumn(1).setPreferredWidth(200); 
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(220);
        columnModel.getColumn(4).setPreferredWidth(200);

        scroll = new JScrollPane(table);
        scroll.setBounds(245, 140, 600, 170);
        panel.add(scroll);
        
        populateTable();

        namelabel = new JLabel("Username: ");
        namelabel.setBounds(340, 300, 300, 100);
        namelabel.setForeground(Color.BLACK);
        namelabel.setFont(buttonFont);
        panel.add(namelabel);

        nametf = new JTextField();
        nametf.setBounds(500, 340, 210, 30);
        nametf.setForeground(Color.BLACK.brighter());
        panel.add(nametf);

        passwordlabel = new JLabel("Password: ");
        passwordlabel.setBounds(340, 350, 300, 100);
        passwordlabel.setForeground(Color.BLACK);
        passwordlabel.setFont(buttonFont);
        panel.add(passwordlabel);

        passwordtf = new JTextField();
        passwordtf.setBounds(500, 390, 210, 30);
        passwordtf.setForeground(Color.BLACK.brighter());
        panel.add(passwordtf);

        emaillabel = new JLabel("Email: ");
        emaillabel.setBounds(340, 400, 210, 100);
        emaillabel.setForeground(Color.BLACK);
        emaillabel.setFont(buttonFont);
        panel.add(emaillabel);

        emailtf = new JTextField();
        emailtf.setBounds(500, 440, 210, 30);
        emailtf.setForeground(Color.BLACK.brighter());
        panel.add(emailtf);
        
        phnlabel = new JLabel("Phone: ");
        phnlabel.setBounds(340, 450, 210, 100);
        phnlabel.setForeground(Color.BLACK);
        phnlabel.setFont(buttonFont);
        panel.add(phnlabel);

        phntf = new JTextField();
        phntf.setBounds(500, 490, 210, 30);
        phntf.setForeground(Color.BLACK.brighter());
        panel.add(phntf);

        Homebutton = createStyledButton("Home", 320, 55, buttonFont, buttonColor);
        Booksbutton = createStyledButton("Books", 430, 55, buttonFont, buttonColor);
        Logoutbutton = createStyledButton("Logout", 540, 55, buttonFont, buttonColor);
        aboutbutton = createStyledButton("About", 650, 55, buttonFont, buttonColor);
        addbutton = createStyledButton("Add", 340, 551, buttonFont, buttonColor);
        updatebutton = createStyledButton("Update", 445, 551, buttonFont, buttonColor);
        deletebutton = createStyledButton("Delete", 550, 551, buttonFont, buttonColor);
        clearbutton = createStyledButton("Clear", 650, 551, buttonFont, buttonColor);

        panel.add(Homebutton);
        panel.add(Booksbutton);
        panel.add(Logoutbutton);
        panel.add(aboutbutton);
        panel.add(addbutton);
        panel.add(updatebutton);
        panel.add(deletebutton);
        panel.add(clearbutton);

        ImageIcon signbgImg = new ImageIcon("gui\\image\\Admingetlibrarian.png");
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);
        JLabel signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight());
        panel.add(signbg);
        

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
                Integer id = Integer.parseInt((String) model.getValueAt(numberofrow, 0));
                String name = model.getValueAt(numberofrow, 1).toString();
                String password = model.getValueAt(numberofrow, 2).toString();
                String email = model.getValueAt(numberofrow, 3).toString();
                String phone = model.getValueAt(numberofrow, 4).toString();

                nametf.setText(name);
                passwordtf.setText(password);
                emailtf.setText(email);
                phntf.setText(phone);

            }
        });
        this.add(panel);
    }

    private JButton createStyledButton(String text, int x, int y, Font font, Color color) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 90, 30);
        button.setBackground(color);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        return button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int numberofrow = table.getSelectedRow();
        if (e.getSource() == Homebutton) {
            AdminHomeFrame a1 = new AdminHomeFrame();
            a1.setVisible(true);
            dispose();
        } else if (e.getSource() == Booksbutton) {
            AdminBookFrame b1 = new AdminBookFrame();
            b1.setVisible(true);
            dispose();

        }else if (e.getSource() == aboutbutton) {

            JOptionPane.showMessageDialog(null, " Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);

        }else if (e.getSource() == addbutton) {

            String username = nametf.getText();
            String password = passwordtf.getText();
            String email = emailtf.getText();
            String phone = phntf.getText()
;
            if (username.isEmpty() || password.isEmpty() || email.isEmpty() ||  phone.isEmpty()) {
                JOptionPane.showMessageDialog(null,"All fields are required to add a librarian.");
            } else {
                Librarian l1 = new Librarian(username, password, email,phone);
                AdminRepository ar1 = new AdminRepository();
                try{
                    ar1.addLibrarian(l1);
                    model.setRowCount(0);
                    populateTable(); 
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage()); 
                }
                nametf.setText("");
                passwordtf.setText("");
                emailtf.setText("");
                phntf.setText("");

            }
        } else if (e.getSource() == clearbutton) {

            nametf.setText("");
            passwordtf.setText("");
            emailtf.setText("");
            phntf.setText("");

        }else if (e.getSource() == deletebutton) {
            if (numberofrow >= 0) {
            	Integer id = Integer.parseInt((String) model.getValueAt(numberofrow, 0)) ;
                String username = model.getValueAt(numberofrow, 1).toString();
                String password = model.getValueAt(numberofrow, 2).toString();
                String email = model.getValueAt(numberofrow, 3).toString();
                String phone = model.getValueAt(numberofrow, 4).toString();
                
                Librarian l1 = new Librarian(id,username, password, email, phone);
                AdminRepository ar1 = new AdminRepository();
                try {
                    ar1.removeLibrarian(l1);
                    model.setRowCount(0);
                    populateTable();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No row has been selected");
            }
            nametf.setText("");
            passwordtf.setText("");
            emailtf.setText("");
            phntf.setText("");
        } else if (e.getSource() == updatebutton) {
            if (numberofrow >= 0) {
            	Integer id = Integer.parseInt((String) model.getValueAt(numberofrow, 0)) ;
                String name = nametf.getText();
                String password = passwordtf.getText();
                String email = emailtf.getText();
                String phn = phntf.getText();
                if (name.isEmpty() || password.isEmpty() || email.isEmpty() || phn.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required to update a librarian.");
                } else {
                    Librarian Librarian = new Librarian(id, name, password, email, phn);
                    AdminRepository ar1 = new AdminRepository();
                    try {
                        ar1.updateLibrarian(Librarian);                  
                        model.setRowCount(0);
                        populateTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    nametf.setText("");
                    passwordtf.setText("");
                    emailtf.setText("");
                    phntf.setText("");
                }
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
    private void populateTable() {
        AdminRepository ar = new AdminRepository();
        try {
            librarians = ar.getLibrarians();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        for (int i = 0; i < librarians.length; i++) {
                Librarian librarian = librarians[i];
                String[] rowData = {Integer.toString(librarian.getID()), librarian.getUsername(), librarian.getPassword(), librarian.getMail(), librarian.getPhone() };
                model.addRow(rowData);
        }
    }
}