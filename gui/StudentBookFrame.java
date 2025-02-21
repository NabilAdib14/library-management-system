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
import java.io.IOException;

public class StudentBookFrame extends JFrame implements ActionListener{

    private JPanel panel;
    private JLabel label, idlabel, booknamelabel, authornamelabel;
    private JTextField idtf, booktf, authortf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns = {"Book ID", "Book Title", "Author Name", "Quantity", "Publisher"};
    private String loggedInUsername;
    private Book[] books;
    private DatabaseConnection dbc;


    private JButton Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton, returnbutton, borrowbutton;

    public StudentBookFrame(String loggedInUsername) {
        super("Library Book Collection");
        dbc= new DatabaseConnection();
        BookRepository br1 = new BookRepository();
        
        this.loggedInUsername=loggedInUsername;
        this.setSize(1080, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);

        ImageIcon signbgImg = new ImageIcon("gui\\image\\BookFrame.png");
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);

        JLabel signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight());
        panel.add(signbg);

        label = new JLabel("Student Book Information");
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setBounds(370, 115, 800, 100);
        signbg.add(label);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table = new JTable(model);
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

        populateBookTable();

        idlabel = new JLabel("Book ID:");
        idlabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        idlabel.setBounds(130, 370, 100, 80);
        signbg.add(idlabel);

        booknamelabel = new JLabel("Book Title:");
        booknamelabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        booknamelabel.setBounds(130, 420, 160, 80);
        signbg.add(booknamelabel);

        authornamelabel = new JLabel("Author Name:");
        authornamelabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        authornamelabel.setBounds(130, 470, 160, 80);
        signbg.add(authornamelabel);


        idtf = new JTextField();
        idtf.setBounds(270, 400, 235, 20);
        signbg.add(idtf);

        booktf = new JTextField();
        booktf.setBounds(270, 450, 235, 20); 
        signbg.add(booktf);

        authortf = new JTextField();
        authortf.setBounds(270, 500, 235, 20);
        signbg.add(authortf);

        Homebutton = createButton("Home", 275, 100);
        signbg.add(Homebutton);

        Booksbutton = createButton("Books", 385, 100);
        signbg.add(Booksbutton);

        Profilebutton = createButton("Profile", 505, 100);
        signbg.add(Profilebutton);

        Logoutbutton = createButton("Logout", 615, 100);
        signbg.add(Logoutbutton);

        aboutbutton = createButton("About", 725, 100);
        signbg.add(aboutbutton);

        returnbutton = createActionButton("Return", 320, 551, new Color(139, 69, 19));
        signbg.add(returnbutton);

        borrowbutton = createActionButton("Borrow", 210, 551, new Color(139, 69, 19));
        signbg.add(borrowbutton);

        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Profilebutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        returnbutton.addActionListener(this);
        borrowbutton.addActionListener(this);

        
        this.setVisible(true);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                int numberofrow = table.getSelectedRow();
                Integer id = Integer.parseInt((String) model.getValueAt(numberofrow, 0));
                String title = model.getValueAt(numberofrow, 1).toString();
                String author = model.getValueAt(numberofrow, 2).toString();
                

                idtf.setText(String.valueOf(id));
                booktf.setText(title);
                authortf.setText(author);

            }
        });
    }


    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 90, 30);
        button.setBackground(new Color(139, 69, 19));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        return button;
    }

    private JButton createActionButton(String text, int x, int y, Color bgColor) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 90, 30);
        button.setBackground(bgColor);
        button.setOpaque(true);
        button.setForeground(Color.white);
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==Homebutton) {
			StudentHomeFrame shf1 = new StudentHomeFrame(loggedInUsername);
			shf1.setVisible(true);
			dispose();
		}else if(e.getSource()==Booksbutton) {
			StudentBookFrame sbf1 = new StudentBookFrame(loggedInUsername);
			sbf1.setVisible(true);
			dispose();
		}else if(e.getSource()==Profilebutton) {
			StudentProfile sp1 = new StudentProfile(loggedInUsername);
			sp1.setVisible(true);
			dispose();
		}else if(e.getSource()==Logoutbutton) {
			 int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
	            if(x==JOptionPane.YES_OPTION){
	                WelcomeFrame w1=new WelcomeFrame();
	                w1.setVisible(true);
	                dispose();
	            }
		}else if(e.getSource()==aboutbutton) {
            JOptionPane.showMessageDialog(null, " Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);

		} else if (e.getSource() == returnbutton) {
			StudentBorrowedBooks sp1 = new StudentBorrowedBooks(loggedInUsername);
			sp1.setVisible(true);
			dispose();
        } else if (e.getSource() == borrowbutton) {
        	String bookid = String.valueOf(idtf.getText().trim());
            String bookName = booktf.getText().trim();
            String authorName = authortf.getText().trim();

            if (bookid.isEmpty() || bookName.isEmpty() || authorName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }
            BookRepository br = new BookRepository();
            StudentRepository sr = new StudentRepository();
            int bookId = Integer.parseInt(bookid); 
            try {
                Book book = br.getBookById(bookId);
                if (book == null) {
                    JOptionPane.showMessageDialog(this, "Book not found.");
                    return;
                }
                Student student = sr.getStudentByUsername(loggedInUsername);
                if (br.hasStudentBorrowedBook(student.getID(), book.getID())) {
                    JOptionPane.showMessageDialog(this, "You have already borrowed this book.");
                    return;
                }
                if (br.borrowBook(student.getID(), book.getID())==true) {
                    JOptionPane.showMessageDialog(this, "Book borrowed successfully!");
                    idtf.setText("");
                    booktf.setText("");
                    authortf.setText("");
                    model.setRowCount(0);
                    populateBookTable();  
                } else { 
                    JOptionPane.showMessageDialog(this, "Failed to borrow the book. Please try again.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    } 

    private void populateBookTable() {
        BookRepository br = new BookRepository();
        try {
            books = br.getAvailableBooks();            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            String[] rowData = {Integer.toString(book.getID()), 
			            		book.getTitle(), 
			            		book.getAuthor(), 
			            		Integer.toString(book.getQuantity()), 
			            		book.getPublisher().getUsername()};
            model.addRow(rowData);
        }
    }  



	
	
}
