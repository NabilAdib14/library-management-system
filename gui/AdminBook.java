package gui;
import entity.*;
import repository.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import  java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AdminBook extends JFrame implements ActionListener{

    private JPanel panel;
    private JLabel label, booknamelable, authornamelable, bookquantitylable;
    private JTextField booktf, authortf, quantitytf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns = {"Book Name", "Author Name", "Book Quantity"};
    private Book[] books;

    private JButton Homebutton, Booksbutton, Logoutbutton, aboutbutton;


    public AdminBook(){
        super("Admin Book page");
        BookRepository br1 = new BookRepository();
        try{
            books=br1.getAvailableBooks();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error loading available books");
        }

        this.setSize(1080, 650);

        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        Font infoFont = new Font("Century", Font.BOLD, 30);
        Font infoFont2 = new Font("Century", Font.BOLD, 20);


        label = new JLabel("Book Information");
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
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            String[] rowData = {book.getTitle(), book.getAuthor(), Integer.toString(book.getQuantity())};
            model.addRow(rowData);
        }



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


        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);


        this.add(panel);
    }

    public  int getTotalBooks(){
        int total = 0;
        for(int i=0; i<model.getRowCount(); i++){
            total += Integer.parseInt(model.getValueAt(i, 2).toString());
        }
        return total;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
