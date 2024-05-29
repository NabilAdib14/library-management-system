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
import java.awt.*;
import  java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BookFrame extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label, booknamelable, authornamelable, bookquantitylable;
    private JTextField booktf, authortf, quantitytf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns = {"Book Name", "Author Name", "Book Quantity"};
    private Book[] books;

    private JButton Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton, addbutton, updatebutton, deletebutton, clearbutton;

    public BookFrame() {
        super("Librarian Book Store");
        this.setSize(1080, 650);

        BookRepository br1 = new BookRepository();
        try{
            books=br1.getAvailableBooks();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error loading available books");
        }

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
        booknamelable = new JLabel("Book Name:");
        booknamelable.setFont(infoFont2);
        booknamelable.setBounds(10, 290, 120, 80);
        panel.add(booknamelable);

        authornamelable = new JLabel("Author Name:");
        authornamelable.setFont(infoFont2);
        authornamelable.setBounds(10, 340, 160, 80);
        panel.add(authornamelable);

        bookquantitylable = new JLabel("Quantity:");
        bookquantitylable.setFont(infoFont2);
        bookquantitylable.setBounds(10, 390, 125, 80);
        panel.add(bookquantitylable);

        booktf = new JTextField();
        booktf.setBounds(135, 320, 120, 20);
        panel.add(booktf);

        authortf = new JTextField();
        authortf.setBounds(150, 371, 120, 20);
        panel.add(authortf);

        quantitytf = new JTextField();
        quantitytf.setBounds(110, 421, 120, 20);
        panel.add(quantitytf);


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

        Profilebutton = new JButton("Profile");
        Profilebutton.setBounds(240, 50, 90, 30);
        Profilebutton.setBackground(Color.CYAN);
        Profilebutton.setOpaque(true);
        Profilebutton.setForeground(Color.BLACK);
        Profilebutton.setOpaque(true);
        panel.add(Profilebutton);

        Logoutbutton = new JButton("Logout");
        Logoutbutton.setBounds(350, 50, 90, 30);
        Logoutbutton.setBackground(Color.CYAN);
        Logoutbutton.setOpaque(true);
        Logoutbutton.setForeground(Color.BLACK);
        Logoutbutton.setOpaque(true);
        panel.add(Logoutbutton);


        aboutbutton = new JButton("About");
        aboutbutton.setBounds(460, 50, 90, 30);
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

        Profilebutton.addActionListener(this);
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

                String bookname = model.getValueAt(numberofrow, 0).toString();
                String authorname = model.getValueAt(numberofrow, 1).toString();
                String quantity = model.getValueAt(numberofrow, 2).toString();

                booktf.setText(bookname);
                authortf.setText(authorname);
                quantitytf.setText(quantity);

            }
        });
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
        int numberofrow = table.getSelectedRow();
        if (e.getSource() == Profilebutton) {
            LibrarianProfile p1 = new LibrarianProfile();
            p1.setVisible(true);
            dispose();
        } else if (e.getSource() == Homebutton) {
            LibrarianFrame a1 = new LibrarianFrame();
            a1.setVisible(true);
            dispose();
        } else if (e.getSource() == Booksbutton) {

            BookFrame b1 = new BookFrame();
            b1.setVisible(true);
            b1.setResizable(true);
            b1.setLocationRelativeTo(null);
            dispose();

        }else if (e.getSource() == aboutbutton) {

            JOptionPane.showMessageDialog(null, " Umar Farouk Ahmed-(23-53185-3) \n Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);
        
        } else if (e.getSource() == addbutton) {

            String title = booktf.getText();
            String author = authortf.getText();
            String quantity = quantitytf.getText();

            if (title.isEmpty() || author.isEmpty() || quantity.isEmpty()) {
                JOptionPane.showMessageDialog(null,"All fields are required to add a book.");
            } else {
                String[] newRowData = new String[3];
                newRowData[0] = booktf.getText();
                newRowData[1] = authortf.getText();
                newRowData[2] = quantitytf.getText();
                model.addRow(newRowData);
                Book b1 = new Book(title, author, Integer.parseInt(quantity));
                BookRepository br1 = new BookRepository();
                try{
                    br1.addBook(b1);
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Book added successfully");
                booktf.setText("");
                authortf.setText("");
                quantitytf.setText("");

            }
        } else if (e.getSource() == clearbutton) {

            booktf.setText("");
            authortf.setText("");
            quantitytf.setText("");

        } else if (e.getSource() == deletebutton) {
            if (numberofrow >=0) {
                String title = model.getValueAt(numberofrow, 0).toString();
                String author = model.getValueAt(numberofrow, 1).toString();
                int quantity = Integer.parseInt(model.getValueAt(numberofrow, 2).toString());
                Book b1 = new Book(title, author, quantity);
                BookRepository br = new BookRepository();
                try{
                    br.removeBook(b1);                
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                model.removeRow(numberofrow);
        
                JOptionPane.showMessageDialog(null, "Book removed successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
        }else if (e.getSource() == updatebutton) {
            if (numberofrow >=0) {
                String title = booktf.getText();
                String author = authortf.getText();
                String quantity = quantitytf.getText();
        
                if (title.isEmpty() || author.isEmpty() || quantity.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required to update a book.");
                } else {
                    String oldTitle = model.getValueAt(numberofrow, 0).toString();
                    String oldAuthor = model.getValueAt(numberofrow, 1).toString();
                    int oldQuantity = Integer.parseInt(model.getValueAt(numberofrow, 2).toString());
                    Book oldBook = new Book(oldTitle, oldAuthor, oldQuantity);
                    Book newBook = new Book(title, author, Integer.parseInt(quantity));
                    BookRepository br = new BookRepository();
                    try{
                        br.updateBook(oldBook, newBook);
                    }catch(IOException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    model.setValueAt(title, numberofrow, 0);
                    model.setValueAt(author, numberofrow, 1);
                    model.setValueAt(quantity, numberofrow, 2);
                    booktf.setText("");
                    authortf.setText("");
                    quantitytf.setText("");       
                    JOptionPane.showMessageDialog(null, "Book updated successfully.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to update.");
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