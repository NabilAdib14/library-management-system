package gui;
import entity.*;
import repository.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


class FrameIntro extends JFrame implements MouseListener, ActionListener
{
    private ImageIcon img;
    private JLabel userLabel, passLabel, imgLabel,loginbg;
    private JTextField userTF;
    private JPasswordField passPF;
    private JButton loginBtn, exitBtn,signBtn;
    private JRadioButton r1, r2, r3;
    private JCheckBox c1, c2, c3;
    private ButtonGroup bg1;
    private JComboBox combo;
    private JPanel panel;
    private Color myColor;
    private Font myFont;
    private String username;

    public FrameIntro()
    {
        super("Login");
        this.setSize(1060,650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        myColor = new Color(210, 230, 135);
        myFont = new Font("helvetica", Font.PLAIN, 28);
        
        ImageIcon loginbgImg = new ImageIcon("gui\\image\\ss.jpeg");
        Image img = loginbgImg.getImage();
        img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        loginbgImg = new ImageIcon(img);

        loginbg = new JLabel(loginbgImg);
        loginbg.setBounds(0, 0, getWidth(), getHeight());
        add(loginbg);

        
        CardLayout loginLayout = new CardLayout();
        
        panel = new JPanel(loginLayout);
        panel.setLayout(null);

        
        userLabel = new JLabel("Username : ");
        userLabel.setBounds(660, 240, 160, 30);
        userLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        userLabel.setBackground(Color.BLACK);
        loginbg.add(userLabel);
        
        userTF = new JTextField();
        userTF.setBounds(675, 278, 200, 30);
        userTF.setBorder(BorderFactory.createEmptyBorder());
        userTF.setFont(new Font("Helvetica", Font.BOLD, 18));
        userTF.setOpaque(false);
        loginbg.add(userTF);
        
        passLabel = new JLabel("Password : ");
        passLabel.setBounds(660, 336, 100, 30);
        passLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        loginbg.add(passLabel);
        
        passPF = new JPasswordField();
        passPF.setBounds(675, 375, 100, 30);
        passPF.setBorder(BorderFactory.createEmptyBorder());
        passPF.setFont(new Font("Helvetica", Font.BOLD, 18));
        passPF.setOpaque(false);
        passPF.setEchoChar('*');
        loginbg.add(passPF);
        
        loginBtn = new JButton("Login");
        loginBtn.setBounds(730, 450, 80, 30);
        loginBtn.setBackground(Color.GREEN);
        loginBtn.addMouseListener(this);
        loginBtn.addActionListener(this);
        loginbg.add(loginBtn);
        
        
        exitBtn = new JButton("Exit");
        exitBtn.setBounds(730, 500, 80, 30);
        exitBtn.setBackground(Color.RED);
        exitBtn.addMouseListener(this);
        exitBtn.addActionListener(this);
        loginbg.add(exitBtn);

        signBtn = new JButton("Sign Up");
        signBtn.setBounds(240, 300, 80, 30);
        signBtn.setBackground(Color.CYAN);
        signBtn.addMouseListener(this);
        signBtn.addActionListener(this);
        loginbg.add(signBtn);
        
        r1 = new JRadioButton("Admin");
        r1.setBounds(620, 150, 100, 20);
        r1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginbg.add(r1);

        r2 = new JRadioButton("Student");
        r2.setBounds(720, 150, 100, 20);
        r2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginbg.add(r2);
        
        r3 = new JRadioButton("Librarian");
        r3.setBounds(820, 150, 100, 20);
        r3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginbg.add(r3);
         
        bg1 = new ButtonGroup();
        bg1.add(r1);
        bg1.add(r2);
        bg1.add(r3);
        

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        int centerX = (int) (screenSize.getWidth() / 2.0 - this.getWidth() / 2.0);
        int centerY = (int) (screenSize.getHeight() / 2.0 - this.getHeight() / 2.0);


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
    public void actionPerformed(ActionEvent ae){
        String username = userTF.getText();
        String password = new String(passPF.getPassword());
        String command = ae.getActionCommand();
        FileReader fr = null;
        BufferedReader br = null;
        
        if (loginBtn.getText().equals(command)) {
            boolean flag = false;
            if (r1.isSelected() || r2.isSelected() || r3.isSelected()) {
                try{
                    String filename = "";
                    if (r1.isSelected()) {
                        filename = "repository/data/admin.txt";
                    } else if (r2.isSelected()) {
                        filename = "repository/data/student.txt";
                    } else if (r3.isSelected()) {
                        filename = "repository/data/librarian.txt";
                    }
                    fr = new FileReader(filename);
                    br = new BufferedReader(fr);
                    String line = null;
                    
                    while ((line = br.readLine()) != null) {
                        String words[] = line.split("\t");
                        if (words[0].equals(username) && words[1].equals(password)) {
                            flag = true;
                            break;
                        }
                    }
                    
                    if (flag) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
      
                        if(r3.isSelected()){
                            LibrarianFrame l1=new LibrarianFrame();
                            l1.setVisible(true);
                            dispose();
                        }
                        else if(r2.isSelected()){
                            StudentFrame s1=new StudentFrame();
                            s1.setVisible(true);
                            dispose();
                        }
                        else if(r1.isSelected()){
                            AdminHome a1=new AdminHome();
                            a1.setVisible(true);
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed\nCheck Credentials");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    try {
                        fr.close();
                        br.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a role.");
            }
        }
        else if(signBtn.getText().equals(command)){
            SignUp s1=new SignUp();
		    s1.setVisible(true);
            dispose();
        }
        else if(exitBtn.getText().equals(command))
        {
            System.exit(0);
        }else{}
    }
}
