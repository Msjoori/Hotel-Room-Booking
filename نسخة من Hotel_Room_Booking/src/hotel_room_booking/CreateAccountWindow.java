package hotel_room_booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

public class CreateAccountWindow extends JFrame {
    JLabel usernameLabel;
    JTextField usernameField;
    
    JLabel emailLabel;
    JTextField emailField;
    
    JLabel passwordLabel;
    JTextField passwordField;
    
    JButton createAccountButton;
    JButton existButton;
    
    JPanel panelUserName;
    JPanel panelEmail;
    JPanel panelPassword;
    
    public CreateAccountWindow() {
        setTitle("Create Account");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        getContentPane().setBackground(Color.PINK);


        JPanel formPanel = new JPanel(new GridLayout(4, 1));

        usernameLabel = new JLabel("Full Name:");
        usernameField = new JTextField(25);
        
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        passwordLabel = new JLabel("Password:");
        passwordField = new JTextField(10);
        
        panelUserName = new JPanel();
        panelUserName.setBackground(Color.PINK);
        panelUserName.add(usernameLabel);
        panelUserName.add(usernameField);
        
        panelEmail = new JPanel();
        panelEmail.setBackground(Color.PINK);
        panelEmail.add(emailLabel);
        panelEmail.add(emailField);
        
        panelPassword = new JPanel();
        panelPassword.setBackground(Color.PINK);
        panelPassword.add(passwordLabel);
        panelPassword.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new AccountButtonListener());
        existButton = new JButton("I Have Account");
        existButton.addActionListener(new AccountButtonListener());

        buttonPanel.add(createAccountButton);
        buttonPanel.add(existButton);

        formPanel.add(panelUserName);
        formPanel.add(panelEmail);
        formPanel.add(panelPassword);
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private class AccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createAccountButton) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String email = emailField.getText();

                if (username.isEmpty() | password.isEmpty() |email.isEmpty()) {
                    JOptionPane.showMessageDialog(CreateAccountWindow.this, "Please fill in all fields");
                } else {
                    deleteBookingDetailsFile();
                    saveUserData(username, password, email);
                    JOptionPane.showMessageDialog(CreateAccountWindow.this, "Account created successfully");
                    Login x = new Login();
                    setVisible(false); // Close the Create Account window
                }
            } else if (e.getSource() == existButton) {
                setVisible(false); // Close the Create Account window 
                Login y = new Login();
            }
        }
    }

    private void saveUserData(String username, String password, String email) {
        try (FileWriter fw = new FileWriter("userData.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(username + "," + password + "," + email);
        } catch (IOException e) {
            System.out.println("An error occurred while saving userdata: " + e.getMessage());
        }
    }

    private void deleteBookingDetailsFile() {
        try (PrintWriter pw = new PrintWriter(new File("BookingDetails.dat"))) {
            pw.print(""); 
        } catch (IOException e) {
            System.out.println("Failed to clear BookingDetails.dat file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        new CreateAccountWindow();

    }
}



