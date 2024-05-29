package gui;
import entity.*;
import repository.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
 
 
 
class SignUp extends JFrame implements MouseListener, ActionListener
{
    private ImageIcon img;
    private JLabel userLabel, passLabel, imgLabel,signbg, emLabel;
    private JTextField userTF,emTF;
    private JPasswordField passPF;
    private JButton loginBtn, exitBtn,signBtn;
    private JRadioButton r1, r2, r3;
    private JCheckBox c1, c2, c3;
    private ButtonGroup bg1;
    private JComboBox combo;
    private JPanel panel;
    private Color myColor;
    private Font myFont;
 
    public SignUp()
    {
        super("Sign Up");
        this.setSize(1060,650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        myColor = new Color(210, 230, 135);
        myFont = new Font("helvetica", Font.PLAIN, 28);
       
        ImageIcon signbgImg = new ImageIcon("gui\\image\\SignUp.jpg");
        Image img = signbgImg.getImage();
        img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);
 
        signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight());
        add(signbg);
 
       
        CardLayout loginLayout = new CardLayout();
       
        panel = new JPanel(loginLayout);
        panel.setLayout(null);
 
       
        userLabel = new JLabel("User Name : ");
        userLabel.setBounds(200, 220, 160, 30);
        userLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        userLabel.setBackground(Color.BLACK);
        signbg.add(userLabel);
       
        userTF = new JTextField();
        userTF.setBounds(203, 250, 200, 30);
        userTF.setBorder(BorderFactory.createEmptyBorder());
        userTF.setFont(new Font("Helvetica", Font.BOLD, 18));
        userTF.setOpaque(false);
        signbg.add(userTF);
       
        passLabel = new JLabel("Password : ");
        passLabel.setBounds(200, 300, 100, 30);
        passLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        signbg.add(passLabel);
       
        passPF = new JPasswordField();
        passPF.setBounds(203, 340, 100, 30);
        passPF.setBorder(BorderFactory.createEmptyBorder());
        passPF.setFont(new Font("Helvetica", Font.BOLD, 18));
        passPF.setOpaque(false);
        passPF.setEchoChar('*');
        signbg.add(passPF);
 
        emLabel = new JLabel("Email : ");
        emLabel.setBounds(200, 385, 160, 30);
        emLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        emLabel.setBackground(Color.BLACK);
        signbg.add(emLabel);
       
        emTF = new JTextField();
        emTF.setBounds(203, 430, 200, 30);
        emTF.setBorder(BorderFactory.createEmptyBorder());
        emTF.setFont(new Font("Helvetica", Font.BOLD, 18));
        emTF.setOpaque(false);
        signbg.add(emTF);
 
        r1 = new JRadioButton("Student");
        r1.setBounds(200, 160, 100, 20);
        r1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signbg.add(r1);
 
        /* r2 = new JRadioButton("Student");
        r2.setBounds(200, 160, 100, 20);
        r2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(r2); */
       
        r3 = new JRadioButton("Librarian");
        r3.setBounds(300, 160, 100, 20);
        r3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signbg.add(r3);
         
        /* bg1 = new ButtonGroup();
        bg1.add(r1);
        bg1.add(r2);
        bg1.add(r3); */
       
        loginBtn = new JButton("Login");
        loginBtn.setBounds(745, 380, 80, 30);
        loginBtn.setBackground(Color.GREEN);
        loginBtn.addMouseListener(this);
        loginBtn.addActionListener(this);
        signbg.add(loginBtn);
       
       
        exitBtn = new JButton("Exit");
        exitBtn.setBounds(260, 550, 80, 30);
        exitBtn.setBackground(Color.RED);
        exitBtn.addMouseListener(this);
        exitBtn.addActionListener(this);
        signbg.add(exitBtn);
 
        signBtn = new JButton("Sign Up");
        signBtn.setBounds(260, 500, 80, 30);
        signBtn.setBackground(Color.CYAN);
        signBtn.addMouseListener(this);
        signBtn.addActionListener(this);
        signbg.add(signBtn);
        signBtn.addActionListener(new ActionListener() {
           
        @Override
 
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Sign Up")){
                String username = userTF.getText();
                String password = new String(passPF.getPassword());
                String email = emTF.getText();
                FileWriter fw = null;
 
                if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
                    JOptionPane.showMessageDialog(SignUp.this, "Please fill in all the fields");
                }else{
                    if(r1.isSelected()){
                    try{
                        Student s1 = new Student(username, password, email);
                        StudentRepository sr1 = new StudentRepository();
                        sr1.addStudent(s1);
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    }
                    else if(r3.isSelected()){
                        try{
                            Librarian l1 = new Librarian(username, password, email);
                            LibrarianRepository lr1 = new LibrarianRepository();
                            lr1.addLibrarian(l1);
                        }catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        } finally {
                            try {
                                if(fw!= null) fw.close();
                            }catch (Exception ex1) {
                                System.out.println(ex1.getMessage());
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(SignUp.this, "Registration Successful");
                }
 
                FrameIntro a1=new FrameIntro();
                a1.setVisible(true);
                dispose();
            }
        }}
        );
       
        //this.add(panel);
 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 
        // Calculate the center coordinates for the frame
        int centerX = (int) (screenSize.getWidth() / 2.0 - this.getWidth() / 2.0);
        int centerY = (int) (screenSize.getHeight() / 2.0 - this.getHeight() / 2.0);
 
        // Set the frame location to the center coordinates
        this.setLocation(centerX, centerY);
 
        this.add(panel);
       
    }
    public void mouseClicked(MouseEvent me){}
    public void mousePressed(MouseEvent me){}
    public void mouseReleased(MouseEvent me){}
    public void mouseEntered(MouseEvent me)
    {
        if(me.getSource() == loginBtn)
        {
            loginBtn.setBackground(Color.BLUE);
            loginBtn.setForeground(Color.WHITE);
        }
        else if(me.getSource() == exitBtn)
        {
            exitBtn.setBackground(Color.BLUE);
            exitBtn.setForeground(Color.WHITE);
        }
        else{}
    }
    public void mouseExited(MouseEvent me){
        if(me.getSource() == loginBtn)
        {
            loginBtn.setBackground(Color.GREEN);
            loginBtn.setForeground(Color.BLACK);
        }
        else if(me.getSource() == exitBtn)
        {
            exitBtn.setBackground(Color.RED);
            exitBtn.setForeground(Color.BLACK);
        }
        else{}
    }
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
       
        if(loginBtn.getText().equals(command))
        {
            FrameIntro f2=new FrameIntro();
            f2.setVisible(true);
            dispose();
        }
        else if(signBtn.getText().equals(command))
        {
            //System.exit(0);
        }
        else if(exitBtn.getText().equals(command))
        {
            System.exit(0);
        }else{}
    }
}
 