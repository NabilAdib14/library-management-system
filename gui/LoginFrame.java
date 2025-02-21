package gui;
import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class LoginFrame extends JFrame implements MouseListener, ActionListener {
    private JLabel userLabel, passLabel, loginbg;
    private JTextField userTF;
    private JPasswordField passPF;
    private JButton loginBtn, exitBtn, signBtn;
    private JRadioButton r1, r2, r3;
    private ButtonGroup bg1;
    private DatabaseConnection dbc;

    public LoginFrame() { 
        super("Login");
        this.setSize(1060, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        dbc = new DatabaseConnection();

        ImageIcon loginbgImg = new ImageIcon("gui\\image\\LoginFrame.png");
        Image img = loginbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        loginbgImg = new ImageIcon(img);

        loginbg = new JLabel(loginbgImg);
        loginbg.setBounds(0, 0, getWidth(), getHeight());
        add(loginbg);

        userLabel = new JLabel("Username : ");
        userLabel.setBounds(152, 285, 160, 30);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginbg.add(userLabel);

        userTF = new JTextField();
        userTF.setBounds(152, 325, 188, 28);
        userTF.setFont(new Font("SansSerif", Font.BOLD, 18));
        userTF.setOpaque(false);
        loginbg.add(userTF);

        passLabel = new JLabel("Password : ");
        passLabel.setBounds(152, 356, 100, 30);
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginbg.add(passLabel);

        passPF = new JPasswordField();
        passPF.setBounds(152, 392, 188, 28);
        passPF.setFont(new Font("SansSerif", Font.BOLD, 18));
        passPF.setOpaque(false);
        passPF.setEchoChar('*');
        loginbg.add(passPF);


        Font btnFont = new Font("SansSerif", Font.BOLD, 18);
        Color btnColor = new Color(139, 69, 19);
        Color textColor = Color.WHITE;

        loginBtn = new JButton("Login");
        customizeButton(loginBtn, 170, 475, btnFont, btnColor, textColor);
        loginbg.add(loginBtn);

        exitBtn = new JButton("Exit");
        customizeButton(exitBtn, 170, 525, btnFont, btnColor, textColor);
        loginbg.add(exitBtn);

        signBtn = new JButton("Sign Up");
        customizeButton(signBtn, 792, 465, btnFont, btnColor, textColor);
        loginbg.add(signBtn);

        r1 = new JRadioButton("Manager");
        r1.setBounds(201, 215, 80, 20);
        loginbg.add(r1);

        r2 = new JRadioButton("Student");
        r2.setBounds(250, 244, 80, 20);
        loginbg.add(r2);

        r3 = new JRadioButton("Librarian");
        r3.setBounds(152, 244, 80, 20);
        loginbg.add(r3);

        bg1 = new ButtonGroup();
        bg1.add(r1);
        bg1.add(r2);
        bg1.add(r3);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) (screenSize.getWidth() / 2.0 - this.getWidth() / 2.0),
                (int) (screenSize.getHeight() / 2.0 - this.getHeight() / 2.0));
    }

    private void customizeButton(JButton btn, int x, int y, Font font, Color bgColor, Color fgColor) {
        btn.setBounds(x, y, 150, 40);
        btn.setFont(font);
        btn.setBackground(bgColor);
        btn.setForeground(fgColor);
        btn.setOpaque(true);
        btn.setFocusable(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(this);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String username = this.userTF.getText().trim();
      String password = new String(this.passPF.getPassword()).trim();
      
      if (e.getSource()==loginBtn) {
         boolean flag = false;
         if (!(this.r1.isSelected() || this.r2.isSelected() || this.r3.isSelected())) {
            JOptionPane.showMessageDialog((Component)null, "Please select a role.");
         } else {
        	String query = " "; 
        	String role = " ";
           
           if (this.r1.isSelected()){
              role="Manager";
              query="SELECT * FROM manager WHERE M_NAME='"+username+"'AND M_PWD='"+password+"';";
           } else if (this.r2.isSelected()) {
               role="Student";
               query="SELECT * FROM student WHERE S_NAME='"+username+"'AND S_PWD='"+password+"';"; 
           } else if (this.r3.isSelected()) {
               role="Librarian";
               query="SELECT * FROM librarian WHERE L_NAME='"+username+"'AND L_PWD='"+password+"';"; 
           }
           
           try {
               dbc.openConnection();
               dbc.result = dbc.st.executeQuery(query);
               if (dbc.result.next()) {
                   flag = true;
               }
               if (flag==true) {
                   JOptionPane.showMessageDialog(null, "Login Successful");
                   if ("Librarian".equals(role)) {
                       LibrarianHomeFrame lf1 = new LibrarianHomeFrame(username);
                       lf1.setVisible(true);
                       this.dispose();
                   } else if ("Student".equals(role)) {
                       StudentHomeFrame sf1 = new StudentHomeFrame(username);
                       sf1.setVisible(true);
                       this.dispose();
                   } else if ("Manager".equals(role)) {
                       AdminHomeFrame ah1 = new AdminHomeFrame();
                       ah1.setVisible(true);
                       this.dispose();
                   }
               } else {
                   JOptionPane.showMessageDialog(null, "Login Failed\nCheck Credentials");
               }
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(null,ex.getMessage());
            } finally {
                dbc.closeConnection();

            }
         }
      } else if (e.getSource()==signBtn) {
         SignUpFrame s1 = new SignUpFrame();
         s1.setVisible(true);
         this.dispose();
      } else if (e.getSource()==exitBtn) {
         System.exit(0);
      }

   }

    @Override
    public void mouseEntered(MouseEvent me) {
        JButton source = (JButton) me.getSource();
        source.setBackground(new Color(160, 82, 45));
    }

    @Override
    public void mouseExited(MouseEvent me) {
        JButton source = (JButton) me.getSource();
        source.setBackground(new Color(139, 69, 19));
    }

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

}
