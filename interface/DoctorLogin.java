import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.*;
import static java.util.logging.Logger.getLogger;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DoctorLogin extends JFrame {

    public DoctorLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        Show = new JToggleButton();
        Login_Button = new JButton();
        Doctorname = new JTextField();
        Password_Label = new JLabel();
        Password = new JPasswordField();
        Login_Label = new JLabel();
        Signup_Label = new JLabel();
        Username_Label = new JLabel();
        jSeparator1 = new JSeparator();
        Back = new JButton();
        Forward = new JButton();
        BackgroundImage = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        Show.setFont(new Font("Arial", 0, 14));
        Show.setText("Show");
        Show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ShowActionPerformed(evt);
            }
        });
        getContentPane().add(Show);
        Show.setBounds(530, 380, 70, 50);

        Login_Button.setFont(new Font("Arial", 1, 24));
        Login_Button.setText("Login");
        Login_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Login_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Login_ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(Login_Button);
        Login_Button.setBounds(440, 480, 160, 50);

        Doctorname.setFont(new Font("Arial", 1, 36));
        getContentPane().add(Doctorname);
        Doctorname.setBounds(160, 260, 440, 50);

        Password_Label.setFont(new Font("Arial", 1, 24));
        Password_Label.setForeground(new Color(255, 255, 255));
        Password_Label.setText("Password");
        getContentPane().add(Password_Label);
        Password_Label.setBounds(160, 320, 130, 50);

        Password.setFont(new Font("Arial", 1, 36));
        getContentPane().add(Password);
        Password.setBounds(160, 380, 440, 50);

        Login_Label.setFont(new Font("Arial", 1, 36));
        Login_Label.setForeground(new Color(255, 255, 255));
        Login_Label.setText("Doctor Login");
        getContentPane().add(Login_Label);
        Login_Label.setBounds(260, 90, 240, 50);

        Signup_Label.setFont(new Font("Arial", 0, 18));
        Signup_Label.setForeground(new Color(255, 255, 255));
        Signup_Label.setText("Create Account?");
        Signup_Label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Signup_Label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Signup_LabelMouseClicked(evt);
            }
        });
        getContentPane().add(Signup_Label);
        Signup_Label.setBounds(160, 490, 140, 30);

        Username_Label.setFont(new Font("Arial", 1, 24));
        Username_Label.setForeground(new Color(255, 255, 255));
        Username_Label.setText("Username");
        getContentPane().add(Username_Label);
        Username_Label.setBounds(160, 200, 130, 50);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(160, 150, 440, 10);

        Back.setFont(new Font("Segoe UI Semilight", 1, 24));
        Back.setText("<-");
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        getContentPane().add(Back);
        Back.setBounds(6, 18, 67, 43);

        Forward.setFont(new Font("Segoe UI Semilight", 1, 24));
        Forward.setText("->");
        Forward.setEnabled(false);
        getContentPane().add(Forward);
        Forward.setBounds(675, 18, 67, 43);

        BackgroundImage.setIcon(new ImageIcon(getClass().getResource("/icon/blue.png")));
        BackgroundImage.setText("BackgroundImage");
        getContentPane().add(BackgroundImage);
        BackgroundImage.setBounds(0, 0, 740, 740);

        setSize(new Dimension(750, 750));
        setLocationRelativeTo(null);
    }

    private void Login_ButtonActionPerformed(ActionEvent evt) {
        try {
            File file = new File("Data/DoctorSignup.txt");
            Scanner sc = new Scanner(file);
            BufferedReader br = new BufferedReader(new FileReader("Data/DoctorSignup.txt"));

            String line = null;
            boolean login = false;

            String User = Doctorname.getText();
            String Pass = Password.getText();

            try {
                String[] studData = null;
                while ((line = br.readLine()) != null)
                {
                    studData = line.split("/");
                    if (studData[0].equalsIgnoreCase(User) && studData[1].equalsIgnoreCase(Pass)){
                        login = true;
                    }
                }
                    if (login){
                        DoctorPortal dp = new DoctorPortal();
                        dp.setVisible(true);
                        setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                    }
                    sc.close();
                }
                catch (IOException ex)
                {
                    getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, "Sorry Error 404");
                }
            }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Data file Not found Try Signup to create Data file");
            }
    }

    private void Signup_LabelMouseClicked(MouseEvent evt) {

        DoctorSignup signup = new DoctorSignup();
        signup.setVisible(true);
        setVisible(false);
    }

    private void BackActionPerformed(ActionEvent evt) {

        Home h = new Home();
        h.setVisible(true);
        setVisible(false);
    }

    private void ShowActionPerformed(ActionEvent evt) {

        if(Show.isSelected()){
            Show.setText("Hide");
            Password.setEchoChar((char)0);
        }
        else{
            Show.setText("Show");
            Password.setEchoChar('\u25cf');
        }
    }

    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoctorLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DoctorLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DoctorLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DoctorLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorLogin().setVisible(true);
            }
        });
    }

    private JButton Back;
    private JLabel BackgroundImage;
    private JTextField Doctorname;
    private JButton Forward;
    private JButton Login_Button;
    private JLabel Login_Label;
    private JPasswordField Password;
    private JLabel Password_Label;
    private JToggleButton Show;
    private JLabel Signup_Label;
    private JLabel Username_Label;
    private JSeparator jSeparator1;

}
