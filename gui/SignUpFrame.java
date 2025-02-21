package gui;
import entity.*;
import repository.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SignUpFrame extends JFrame implements MouseListener, ActionListener {
    private JLabel userLabel, passLabel, signbg, emLabel, phoneLabel, idLabel;
    private JTextField userTF, emTF, phoneTF, idTF;
    private JPasswordField passPF;
    private JButton loginBtn, signBtn;
    private JRadioButton r1;
    private JPanel panel;
    private Color myColor;
    private Font myFont;

    public SignUpFrame() {
        super("Sign Up");
        this.setSize(1060, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        myColor = new Color(210, 230, 135);
        myFont = new Font("SansSerif", Font.PLAIN, 28);

        ImageIcon signbgImg = new ImageIcon("gui\\image\\SignupFrame.png");
        Image img = signbgImg.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        signbgImg = new ImageIcon(img);

        signbg = new JLabel(signbgImg);
        signbg.setBounds(0, 0, getWidth(), getHeight());
        add(signbg);

        panel = new JPanel(null);

        userLabel = new JLabel("User Name : ");
        userLabel.setBounds(146, 223, 160, 30);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        signbg.add(userLabel);

        userTF = new JTextField();
        userTF.setBounds(146, 254, 188, 28);
        userTF.setFont(new Font("SansSerif", Font.BOLD, 16));
        userTF.setOpaque(false);
        userTF.setBackground(new Color(0, 0, 0, 0));
        signbg.add(userTF);

        idLabel = new JLabel("ID:");
        idLabel.setBounds(146, 190, 160, 30);
        idLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        signbg.add(idLabel);

        idTF = new JTextField();
        idTF.setBounds(170, 190, 200, 30);
        idTF.setFont(new Font("SansSerif", Font.BOLD, 16));
        idTF.setEditable(false);
        idTF.setOpaque(false);
        idTF.setBackground(new Color(0, 0, 0, 0));
        signbg.add(idTF);

        passLabel = new JLabel("Password : ");
        passLabel.setBounds(146, 287, 100, 30);
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        signbg.add(passLabel);

        passPF = new JPasswordField();
        passPF.setBounds(146, 321, 188, 28);
        passPF.setFont(new Font("SansSerif", Font.BOLD, 18));
        passPF.setOpaque(false);
        passPF.setBackground(new Color(0, 0, 0, 0));
        passPF.setEchoChar('*');
        signbg.add(passPF);

        emLabel = new JLabel("Email : ");
        emLabel.setBounds(388, 223, 160, 30);
        emLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        signbg.add(emLabel);

        emTF = new JTextField();
        emTF.setBounds(388, 254, 188, 28);
        emTF.setFont(new Font("SansSerif", Font.BOLD, 16));
        emTF.setOpaque(false);
        emTF.setBackground(new Color(0, 0, 0, 0));
        signbg.add(emTF);

        phoneLabel = new JLabel("Phone Number : ");
        phoneLabel.setBounds(388, 287, 160, 30);
        phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        signbg.add(phoneLabel);

        phoneTF = new JTextField();
        phoneTF.setBounds(388, 321, 188, 28);
        phoneTF.setFont(new Font("SansSerif", Font.BOLD, 16));
        phoneTF.setOpaque(false);
        phoneTF.setBackground(new Color(0, 0, 0, 0));
        signbg.add(phoneTF);

        r1 = new JRadioButton("Student");
        r1.setBounds(310, 160, 100, 20);
        signbg.add(r1);
        r1.setSelected(true);
        
        loginBtn = new JButton("Login");
        loginBtn.setBounds(413, 505, 120, 30);
        loginBtn.setBackground(new Color(139, 69, 19));
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(this);
        signbg.add(loginBtn);

        signBtn = new JButton("Sign Up");
        signBtn.setBounds(297, 394, 120, 30);
        signBtn.setBackground(new Color(139, 69, 19));
        signBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        signBtn.setForeground(Color.WHITE);
        signBtn.addActionListener(this);
        signbg.add(signBtn);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) (screenSize.getWidth() / 2.0 - this.getWidth() / 2.0);
        int centerY = (int) (screenSize.getHeight() / 2.0 - this.getHeight() / 2.0);
        this.setLocation(centerX, centerY);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==signBtn) {
            String username = userTF.getText().trim();
            String password = new String(passPF.getPassword()).trim();
            String email = emTF.getText().trim();
            String phone = phoneTF.getText().trim();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields!");
                return;
            }
            if (r1.isSelected()) {
                try {
                    StudentRepository sr = new StudentRepository();
                    Student st1 = new Student(username, password, email, phone);
                    Integer existingId = sr.getGeneratedID(st1);

                    if (existingId != -1) {
                        JOptionPane.showMessageDialog(this, "You are already registered, your ID is: " + existingId);
                    } else {
                        Student student = new Student(username, password, email, phone); 
                        sr.addStudent(student);
                        Integer generatedId = sr.getGeneratedID(student);
                        if(generatedId!=-1) {
                        	idTF.setText(String.valueOf(generatedId));
                        	JOptionPane.showMessageDialog(this, "Registration Successful! Your ID is: " + generatedId);
                        }
                        else {
                        	JOptionPane.showMessageDialog(this, "Error, Try Again");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,ex.getMessage());
                }
            }

        } else if (e.getSource()==loginBtn) {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            this.dispose();
        }

     
}    

    public void mouseClicked(MouseEvent me){}
    public void mousePressed(MouseEvent me){}
    public void mouseReleased(MouseEvent me){}
    public void mouseEntered(MouseEvent me) {
        if(me.getSource() == loginBtn || me.getSource() == signBtn) {
            ((JButton)me.getSource()).setBackground(Color.BLUE);
            ((JButton)me.getSource()).setForeground(Color.WHITE);
        }
    }

    public void mouseExited(MouseEvent me) {
        if(me.getSource() == loginBtn || me.getSource() == signBtn) {
            ((JButton)me.getSource()).setBackground(new Color(139, 69, 19));
            ((JButton)me.getSource()).setForeground(Color.WHITE);
        }
    }
}
