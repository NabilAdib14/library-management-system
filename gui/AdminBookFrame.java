package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdminBookFrame extends JFrame implements ActionListener{

    private JPanel panel;
    private JLabel label, booknamelable, authornamelable, bookquantitylable;
    private JTextField booktf, authortf, quantitytf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns ={"Book ID", "Title", "Author", "Quantity", "Publisher"};
    private Book[] books;
    private String loggedInUsername;
    private DatabaseConnection dbc; 


    private JButton Homebutton, Booksbutton, Logoutbutton, aboutbutton;

    public AdminBookFrame() {
        super("Admin Book page");
        this.setSize(1080, 650);
        dbc = new DatabaseConnection();
        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        Font infoFont = new Font("Century", Font.BOLD, 30);
        Font infoFont2 = new Font("Century", Font.BOLD, 20);

        ImageIcon signbgImg = new ImageIcon("gui\\image\\AdminBookFrame.png"); 
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);
        JLabel signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight()); 
        panel.add(signbg);

        label = new JLabel("Book Information");
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setBounds(410, 115, 800, 100);
        signbg.add(label);

        table = new JTable();

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setSelectionBackground(Color.GREEN);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        scroll = new JScrollPane(table); 
        scroll.setBounds(140, 190, 800, 170);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100); 
        columnModel.getColumn(1).setPreferredWidth(300); 
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(300);
        signbg.add(scroll);
        
        model.setRowCount(0);
        try {
            dbc.openConnection();
            String query = "SELECT B.B_ID, B.B_TITLE, B.B_AUTHOR, B.B_QUANTITY, P.P_NAME FROM book B, publisher P WHERE B.P_ID = P.P_ID;";

            ResultSet rs = dbc.st.executeQuery(query);
            while (rs.next()) {
                String id = Integer.toString(rs.getInt("B_ID"));
                String title = rs.getString("B_TITLE");
                String author = rs.getString("B_AUTHOR");
                String quantity = Integer.toString(rs.getInt("B_QUANTITY"));
                String publisherUsername = rs.getString("P_NAME");
                String[] rowData = {id, title, author, quantity, publisherUsername};
                model.addRow(rowData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            dbc.closeConnection();
        }

       
        Homebutton = new JButton("Home");
        Homebutton.setBounds(320, 100, 90, 30);
        Homebutton.setBackground(new Color(139, 69, 19)); 
        Homebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Homebutton.setForeground(Color.WHITE);
        signbg.add(Homebutton);

        Booksbutton = new JButton("Books");
        Booksbutton.setBounds(430, 100, 90, 30);
        Booksbutton.setBackground(new Color(139, 69, 19)); 
        Booksbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Booksbutton.setForeground(Color.WHITE);
        signbg.add(Booksbutton);

        Logoutbutton = new JButton("Logout");
        Logoutbutton.setBounds(540, 100, 90, 30);
        Logoutbutton.setBackground(new Color(139, 69, 19)); 
        Logoutbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Logoutbutton.setForeground(Color.WHITE);
        signbg.add(Logoutbutton);

        aboutbutton = new JButton("About");
        aboutbutton.setBounds(650, 100, 90, 30);
        aboutbutton.setBackground(new Color(139, 69, 19)); 
        aboutbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        aboutbutton.setForeground(Color.WHITE);
        signbg.add(aboutbutton);
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);

        this.add(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
