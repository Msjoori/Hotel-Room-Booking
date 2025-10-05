

package hotel_room_booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Login extends JFrame {

    private JPanel panelEMG;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panelC;
    private JPanel panel3;

    private JLabel labelEmail;
    private JTextField fieldEmail;
    private JLabel labelPass;
    private JTextField fieldPass;
    private JButton buttonLogin;

    public Login() {
        setTitle("Login");
        setSize(400, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buildPanel();
        add(panelEMG, BorderLayout.NORTH);
        add(panelC, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(Color.PINK);

        buttonLogin.addActionListener(new LoginButtonListener());
    }

    private void buildPanel() {
        panelEMG = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panelC = new JPanel();

        ImageIcon imageIcon = new ImageIcon("user.gif");
        JLabel imageLabel = new JLabel(imageIcon);

        labelEmail = new JLabel("Email");
        fieldEmail = new JTextField(20);
        labelPass = new JLabel("Password");
        fieldPass = new JTextField(10);
        buttonLogin = new JButton("Login");

        panelEMG.add(imageLabel);
        panel1.add(labelEmail);
        panel1.add(fieldEmail);
        panel2.add(labelPass);
        panel2.add(fieldPass);
        panelC.add(panel1);
        panelC.add(panel2);
        panel3.add(buttonLogin);

        panelEMG.setBackground(Color.PINK);
        panel1.setBackground(Color.PINK);
        panel2.setBackground(Color.PINK);
        panel3.setBackground(Color.PINK);
        panelC.setBackground(Color.PINK);
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String email = fieldEmail.getText();
            String password = fieldPass.getText();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(Login.this, "Please enter both email and password");
            }

            if (isUserValid(email, password)==true) {
                JOptionPane.showMessageDialog(Login.this, "Login successful!");
                MainBookingMenu menu = new MainBookingMenu();//to open the main menu window
                setVisible(false); //to close Login window
            } else {
                JOptionPane.showMessageDialog(Login.this, "The entered email or password is incorrect.\nPlease check and enter it correctly");
            }
        }
    }

   private boolean isUserValid(String email, String password){
    
    File file = new File("userData.txt");

    if (!file.exists()){
        return false;
    }
    
     Scanner readF = null; //final لازم نحط له قيمه مسبقه عشان ينجح معانا اقفال السكانير الي في ال
    try {
           
        readF = new Scanner(file); //reading from file
        
        while (readF.hasNextLine()) {
            
            String content = readF.nextLine();
            String[] userData = content.split(","); //split the words till (,)
            String storedEmail = userData[2];
            String storedPassword = userData[1];
            
            if (storedEmail.equals(email) && storedPassword.equals(password)){
                return true;}
        }
    } 
    catch (IOException e){
        JOptionPane.showMessageDialog(this, "An error occurred while accessing user data.");
        return false;
    }
    finally {
    
      readF.close(); // close the file
    
    }
    
    return false;
 } 
}