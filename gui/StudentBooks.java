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

public class StudentBooks extends JFrame implements ActionListener  {

    private JPanel panel;
    private JLabel label, booknamelabel, authornamelabel, imagelabel,bookquantitylabel;
    private ImageIcon img;
    private JTextField booktf, authortf,bookquantitytf;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private String[] columns = {"Book Name", "Author Name", "Book Quantity"};
    private String[] rows = new String[3];
    private Book[] books;
    

    private JButton Homebutton, Booksbutton, Profilebutton, Logoutbutton, aboutbutton, returnbutton, borrowbutton;

    public StudentBooks(){

        super("Student Homepage");
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

        label = new JLabel("Student Book Information");
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


        booknamelabel = new JLabel("Book Name:");
        booknamelabel.setFont(infoFont2);
        booknamelabel.setBounds(10, 290, 120, 80);
        panel.add(booknamelabel);

        authornamelabel = new JLabel("Author Name:");
        authornamelabel.setFont(infoFont2);
        authornamelabel.setBounds(10, 340, 160, 80);
        panel.add(authornamelabel);

        bookquantitylabel = new JLabel("Quantity:");
        bookquantitylabel.setFont(infoFont2);
        bookquantitylabel.setBounds(10, 390, 200, 80);
        panel.add(bookquantitylabel);

        booktf = new JTextField();
        booktf.setBounds(135, 320, 120, 20);
        panel.add(booktf);

        authortf = new JTextField();
        authortf.setBounds(150, 371, 120, 20);
        panel.add(authortf);

        bookquantitytf=new JTextField();
        bookquantitytf.setBounds(190,421,120,20);
        panel.add(bookquantitytf);

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

        returnbutton = new JButton("Return");
        returnbutton.setBounds(10, 500, 90, 30);
        returnbutton.setBackground(Color.CYAN);
        returnbutton.setOpaque(true);
        returnbutton.setForeground(Color.BLACK);
        returnbutton.setOpaque(true);
        panel.add(returnbutton);

        borrowbutton = new JButton("Borrow");
        borrowbutton.setBounds(120, 500, 90, 30);
        borrowbutton.setBackground(Color.CYAN);
        borrowbutton.setOpaque(true);
        borrowbutton.setForeground(Color.BLACK);
        borrowbutton.setOpaque(true);
        panel.add(borrowbutton);



        Profilebutton.addActionListener(this);
        Homebutton.addActionListener(this);
        Booksbutton.addActionListener(this);
        Logoutbutton.addActionListener(this);
        aboutbutton.addActionListener(this);
        returnbutton.addActionListener(this);
        borrowbutton.addActionListener(this);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int numberofrow = table.getSelectedRow();

                String bookname = model.getValueAt(numberofrow, 0).toString();
                String authorname = model.getValueAt(numberofrow, 1).toString();

                booktf.setText(bookname);
                authortf.setText(authorname);

            }
        });
        this.add(panel);


    }

    public void updateData(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("repository\\data\\book.txt"));
            for (int i=0; i<model.getRowCount(); i++){
                for(int j=0; j<model.getColumnCount(); j++){
                    bw.write(model.getValueAt(i, j).toString() + "\t");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());

        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Profilebutton) {
            StudentProfile p1 = new StudentProfile();
            p1.setVisible(true);
            dispose();
        } else if (e.getSource() == Homebutton) {
            StudentFrame a1 = new StudentFrame();
            a1.setVisible(true);
            dispose();
        } else if (e.getSource() == Booksbutton) {
            StudentBooks b1 = new StudentBooks();
            b1.setVisible(true);
            dispose();
        }
        else if (e.getSource() == aboutbutton) {
            JOptionPane.showMessageDialog(null, " Umar Farouk Ahmed-(23-53185-3) \n Md. Nabil Adibur Rahman-(23-53187-3) \n Maisha Tahseen-(23-53206-3)", "Contributors", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == returnbutton) {

            int numberofrow = table.getSelectedRow();

            String bookname = booktf.getText();
            String authorname = authortf.getText();
            String quantity = (String)model.getValueAt(numberofrow,2);

            int num=Integer.parseInt(quantity);
            int num1=Integer.parseInt(bookquantitytf.getText());
            int num2=num+num1;
            String quantity2=String.valueOf(num2);
            if(num2>0){
                model.setValueAt(quantity2, numberofrow, 2);
            }
            else{
                JOptionPane.showMessageDialog(null,"Not Returned\nPlease Try Again");
            }
            try{
                FileWriter Writer=new FileWriter("repository\\data\\returnedbooks.txt",true);
                Writer.write(bookname+" "+authorname+" "+num1+"\n");
                Writer.flush();
                Writer.close();
                JOptionPane.showMessageDialog(null,"Updated");
                setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error");
            }
            updateData();
        }
        else if (e.getSource() == borrowbutton) {

            int numberofrow = table.getSelectedRow();

            String bookname = booktf.getText();
            String authorname = authortf.getText();
            String quantity = (String)model.getValueAt(numberofrow,2);

//            model.setValueAt(bookname, numberofrow, 0);
//            model.setValueAt(authorname, numberofrow, 1);
            int num=Integer.parseInt(quantity);
            int num1=Integer.parseInt(bookquantitytf.getText());
            int num2=num-num1;
            String quantity2=String.valueOf(num2);
            if(num2>0){
                model.setValueAt(quantity2, numberofrow, 2);
                try{
                    FileWriter Writer=new FileWriter("repository\\data\\borrowedbooks.txt",true);
                    Writer.write(bookname+" "+authorname+" "+num1+"\n");
                    Writer.flush();
                    JOptionPane.showMessageDialog(null,"Updated");
                    setVisible(true);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Error");
                }
                updateData();
            }
            else{
                JOptionPane.showMessageDialog(null,"Not Available");
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