package gui;
import entity.*;

import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LibrarianBookFrame extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label, titlelabel, authorlabel, quantitylabel, publisherlabel;
    private JTextField booktf, authortf, quantitytf, publishertf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns = {"Book ID", "Title", "Author", "Quantity", "Publisher", "Publisher ID"};
    private Book[] books;
    private AdminRepository ar;
    private String loggedInUsername;

    private JButton Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton, addbutton, updatebutton, deletebutton, clearbutton, IssuedBooksButton;

    public LibrarianBookFrame(String loggedInUsername) {
        super("Library Book Collection");
        this.loggedInUsername = loggedInUsername;

        this.setSize(1080, 650);       
 
        panel = new JPanel();
        panel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        ImageIcon signbgImg = new ImageIcon("gui\\image\\BookFrame.png"); 
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);

        JLabel signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight());  
        panel.add(signbg);

        label = new JLabel("Book Information");
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setBounds(420, 115, 800, 100);
        signbg.add(label);

        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setSelectionBackground(Color.GREEN);
        table.setBackground(Color.decode("#fff6ed"));
        table.setRowHeight(30);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80); 
        columnModel.getColumn(1).setPreferredWidth(200); 
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(200);
        columnModel.getColumn(5).setPreferredWidth(100);

        scroll = new JScrollPane(table);
        scroll.setBounds(200, 190, 700, 170);        
        signbg.add(scroll);
        
        populateBookTable();

        titlelabel = new JLabel("Book Name:");
        titlelabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titlelabel.setBounds(130, 355, 120, 80);
        signbg.add(titlelabel);

        authorlabel = new JLabel("Author Name:");
        authorlabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        authorlabel.setBounds(130, 395, 160, 80);
        signbg.add(authorlabel);

        quantitylabel = new JLabel("Quantity:");
        quantitylabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        quantitylabel.setBounds(130, 444, 125, 80);
        signbg.add(quantitylabel);
        
        publisherlabel = new JLabel("Publisher ID:");
        publisherlabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        publisherlabel.setBounds(130, 485, 125, 80);
        signbg.add(publisherlabel);

        booktf = new JTextField();
        booktf.setBounds(265, 384, 250, 25);
        signbg.add(booktf);

        authortf = new JTextField();
        authortf.setBounds(265, 426, 250, 25);
        signbg.add(authortf);

        quantitytf = new JTextField();
        quantitytf.setBounds(265, 473, 250, 25);
        signbg.add(quantitytf);
        
        publishertf = new JTextField();
        publishertf.setBounds(263, 514, 250, 25);
        signbg.add(publishertf);

        Homebutton = new JButton("Home");
        Homebutton.setBounds(275, 100, 90, 30);
        Homebutton.setBackground(new Color(139, 69, 19)); 
        Homebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Homebutton.setForeground(Color.WHITE);
        signbg.add(Homebutton);

        Booksbutton = new JButton("Books");
        Booksbutton.setBounds(385, 100, 90, 30);
        Booksbutton.setBackground(new Color(139, 69, 19)); 
        Booksbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Booksbutton.setForeground(Color.WHITE);
        signbg.add(Booksbutton);

        Profilebutton = new JButton("Profile");
        Profilebutton.setBounds(505, 100, 90, 30);
        Profilebutton.setBackground(new Color(139, 69, 19)); 
        Profilebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Profilebutton.setForeground(Color.WHITE);
        signbg.add(Profilebutton);

        Logoutbutton = new JButton("Logout");
        Logoutbutton.setBounds(615, 100, 90, 30);
        Logoutbutton.setBackground(new Color(139, 69, 19)); 
        Logoutbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        Logoutbutton.setForeground(Color.WHITE);
        signbg.add(Logoutbutton);

        aboutbutton = new JButton("About");
        aboutbutton.setBounds(725, 100, 90, 30);
        aboutbutton.setBackground(new Color(139, 69, 19)); 
        aboutbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        aboutbutton.setForeground(Color.WHITE);
        signbg.add(aboutbutton);

        addbutton = new JButton("Add");
        addbutton.setBounds(100, 552, 90, 30);
        addbutton.setBackground(new Color(139, 69, 19));
        addbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        addbutton.setForeground(Color.WHITE);
        signbg.add(addbutton);

        updatebutton = new JButton("Update");
        updatebutton.setBounds(210, 552, 90, 30);
        updatebutton.setBackground(new Color(139, 69, 19)); 
        updatebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        updatebutton.setForeground(Color.WHITE);
        signbg.add(updatebutton);

        deletebutton = new JButton("Delete");
        deletebutton.setBounds(320, 552, 90, 30);
        deletebutton.setBackground(new Color(139, 69, 19)); 
        deletebutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        deletebutton.setForeground(Color.WHITE);
        signbg.add(deletebutton);

        clearbutton = new JButton("Clear");
        clearbutton.setBounds(430, 552, 90, 30);
        clearbutton.setBackground(new Color(139, 69, 19)); 
        clearbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        clearbutton.setForeground(Color.WHITE);
        signbg.add(clearbutton);
        
        IssuedBooksButton = new JButton("Issued Books");
        IssuedBooksButton.setBounds(670, 450, 150, 30);
        IssuedBooksButton.setBackground(new Color(139, 69, 19)); 
        IssuedBooksButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        IssuedBooksButton.setForeground(Color.WHITE);
        signbg.add(IssuedBooksButton);
        

        Profilebutton.addActionListener(this);
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        addbutton.addActionListener(this);
        clearbutton.addActionListener(this);
        deletebutton.addActionListener(this);
        updatebutton.addActionListener(this);
        IssuedBooksButton.addActionListener(this);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                int numberofrow = table.getSelectedRow();
                Integer id = Integer.parseInt((String) model.getValueAt(numberofrow, 0));
                String title = model.getValueAt(numberofrow, 1).toString();
                String author = model.getValueAt(numberofrow, 2).toString();
                String quantity = model.getValueAt(numberofrow, 3).toString();
                String pid = model.getValueAt(numberofrow, 5).toString();

                booktf.setText(title);
                authortf.setText(author);
                quantitytf.setText(quantity);
                publishertf.setText(pid);

            }
        });
        this.add(panel);  
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        int numberofrow = table.getSelectedRow();
        if (e.getSource() == Profilebutton) {
            LibrarianProfile p1 = new LibrarianProfile(loggedInUsername); 
            p1.setVisible(true);
            dispose(); 
        } else if (e.getSource() == Homebutton) {
            LibrarianHomeFrame a1 = new LibrarianHomeFrame(loggedInUsername);
            a1.setVisible(true);
            dispose();
        } else if (e.getSource() == Booksbutton) {
            LibrarianBookFrame b1 = new LibrarianBookFrame(loggedInUsername);
            b1.setVisible(true);
            dispose();
        } else if (e.getSource() == aboutbutton) {
            JOptionPane.showMessageDialog(null, "Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);

        }else if(e.getSource()==Logoutbutton){
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if(x==JOptionPane.YES_OPTION){
                WelcomeFrame w1=new WelcomeFrame();
                w1.setVisible(true);
                dispose();
            }
        }else if (e.getSource() == IssuedBooksButton) {
        	LibrarianIssuedBooks b1 = new LibrarianIssuedBooks(loggedInUsername);
            b1.setVisible(true);
            dispose();

        }else if (e.getSource() == addbutton) {
        	String title = booktf.getText();
        	String author = authortf.getText();
        	String quantity = quantitytf.getText();
        	String pid = publishertf.getText();
        	
        	if (title.isEmpty() || author.isEmpty() || quantity.isEmpty() ||  pid.isEmpty()) {
                JOptionPane.showMessageDialog(null,"All fields are required to add a book.");
            }else {
                BookRepository br1 = new BookRepository();
                PublisherRepository pr1 = new PublisherRepository();
                Publisher p1 = pr1.getPublisherById(Integer.parseInt(pid));

                Book b1 = new Book(title, author, Integer.parseInt(quantity), p1);

                try {
                    br1.addBook(b1);
                    model.setRowCount(0);
                    populateBookTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            	booktf.setText("");
            	authortf.setText("");
            	quantitytf.setText("");
            	publishertf.setText("");
            }
        }
        else if (e.getSource() == clearbutton) {
        	
        	booktf.setText("");
        	authortf.setText("");
        	quantitytf.setText("");
        	publishertf.setText("");
        	
        }else if (e.getSource() == deletebutton) {
            if (numberofrow >= 0) {
            	Integer id = Integer.parseInt((String) model.getValueAt(numberofrow, 0)) ;
                String title = model.getValueAt(numberofrow, 1).toString();
                String author = model.getValueAt(numberofrow, 2).toString();
                int quantity = Integer.parseInt(model.getValueAt(numberofrow, 3).toString());
                int pid = Integer.parseInt(model.getValueAt(numberofrow, 5).toString());
                
                PublisherRepository pr1 = new PublisherRepository();
                Publisher p1 = pr1.getPublisherById(pid);
                Book b1 = new Book(id,title, author, quantity, p1);
                BookRepository br1 = new BookRepository();
                
                try {
                    br1.removeBook(b1);
                    model.setRowCount(0);
                    populateBookTable();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No row has been selected");
            }
        	booktf.setText("");
        	authortf.setText("");
        	quantitytf.setText("");
        	publishertf.setText("");


        }else if (e.getSource() == updatebutton) {
        	if (numberofrow >= 0) {
        		Integer id = Integer.parseInt((String) model.getValueAt(numberofrow, 0)) ;
        		String title = booktf.getText();
            	String author = authortf.getText();
            	String quantity = quantitytf.getText();
            	String publisher = publishertf.getText();
            	
            	if (title.isEmpty() || author.isEmpty() || quantity.isEmpty() ||  publisher.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"All fields are required to add a book.");
                }else {
                    BookRepository br1 = new BookRepository();
                    PublisherRepository pr1 = new PublisherRepository();
                    Publisher p1 = pr1.getPublisherById(Integer.parseInt(publisher));
                    Book b1 = new Book(id,title, author, Integer.parseInt(quantity), p1);
                    
                    try {
                        br1.updateBook(b1);
                        model.setRowCount(0);
                        populateBookTable();                       
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    } 
                }
            } else {
                JOptionPane.showMessageDialog(null, "No row has been selected");
            }
        	booktf.setText("");
        	authortf.setText("");
        	quantitytf.setText("");
        	publishertf.setText("");
        }
    }
    private void populateBookTable() {
        BookRepository br1 = new BookRepository();
        try {
            books = br1.getAvailableBooks();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading available books");
        }
        for (int i=0; i<books.length; i++) {
        	Book book = books[i];
            String[] rowData = {Integer.toString(book.getID()),
			            		book.getTitle(), 
			            		book.getAuthor(),
			            		Integer.toString(book.getQuantity()), 
			            		book.getPublisher().getUsername(),
			            		String.valueOf(book.getPublisher().getID())}; 
            model.addRow(rowData);
        } 
    }

   
}
