package gui;
import entity.*;
import repository.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame {

    private JButton startButton;
    private ImageIcon img;
    private JLabel logoLabel;
    private JPanel panel;

    public WelcomeFrame() {
        super("Library Management System");
        this.setSize(1080, 650);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        
        ImageIcon imgIcon = new ImageIcon("gui\\image\\WelcomeToLibraryManage.png");
        Image img = imgIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

        ImageIcon png = new ImageIcon("gui\\image\\icon-01.png");
        setIconImage(png.getImage());
        panel=new JPanel(null);

        logoLabel = new JLabel(imgIcon);
        logoLabel.setBounds(0, 0, getWidth(), getHeight());
        this.add(logoLabel);

        startButton = new JButton("Start");
        startButton.setFocusable(false);
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        startButton.setOpaque(true);
        startButton.setForeground(Color.WHITE); 
        startButton.setOpaque(true);
        
        startButton.setFont(new Font("SansSerif", Font.BOLD, 31));
    
        startButton.setBounds(450, 350, 180, 50);
        startButton.setBackground(new Color(139, 69, 19));
        logoLabel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginFrame a1 = new LoginFrame();
                a1.setVisible(true);
                WelcomeFrame.this.dispose(); 
            }
        });
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        this.setLocation(x, y);
        this.setVisible(true);
        this.add(panel);
    }
}
