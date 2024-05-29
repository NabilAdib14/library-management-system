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

        // Create content pane with background image
        
        ImageIcon imgIcon = new ImageIcon("gui\\image\\nn.jpeg");
        Image img = imgIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

        ImageIcon png = new ImageIcon("gui\\image\\icon-01.png");
        setIconImage(png.getImage());
        panel=new JPanel(null);

        logoLabel = new JLabel(imgIcon);
        logoLabel.setBounds(0, 0, getWidth(), getHeight());
        this.add(logoLabel);
       // Optional layout manager

        startButton = new JButton("Start");
        startButton.setFocusable(false);
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        startButton.setBackground(Color.cyan);
        startButton.setOpaque(true);
        startButton.setForeground(Color.BLACK);
        startButton.setOpaque(true);
        
        startButton.setFont(new Font("century", Font.BOLD, 31));
    
        startButton.setBounds(450, 350, 180, 50);
        startButton.setBackground(new Color(0, 196, 204));
         logoLabel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrameIntro a1 = new FrameIntro();
                a1.setVisible(true);
                WelcomeFrame.this.dispose(); // Disposes the WelcomeFrame
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
