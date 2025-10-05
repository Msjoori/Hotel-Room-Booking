package hotel_room_booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Date extends JFrame {

    JLabel dateAddress;
    JComboBox<String> day;
    JComboBox<String> month;
    JComboBox<String> year;

    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panelNorth;

    JLabel dayCountLabel;
    JTextField countField;
    JButton increaseButton;
    JButton decreaseButton;
    JPanel countPanel;

    JLabel dateLabel;
    JTextField dateField;
    JPanel datePanel;

    JPanel panelCenter;

    JButton backButton;
    JButton nextButton;
    JPanel panelButton;  

    public Date() {
        setTitle("Date ");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.PINK);

        String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};
        String[] years = {"2024", "2025", "2026"};
        day = new JComboBox<>(days);
        month = new JComboBox<>(months);
        year = new JComboBox<>(years);
        
        dateAddress = new JLabel("Your check-in will be on :");
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panelNorth = new JPanel();

        panel1.add(day);
        panel1.setBackground(Color.PINK);
        
        panel2.add(month);
        panel2.setBackground(Color.PINK);
        
        panel3.add(year);
        panel3.setBackground(Color.PINK);
        
        panelNorth.add(dateAddress);
        panelNorth.add(panel1);
        panelNorth.add(panel2);
        panelNorth.add(panel3);
        panelNorth.setBackground(Color.PINK);
        
        dayCountLabel = new JLabel("Number of days:");
        countField = new JTextField(4);
        countField.setEditable(false);
        
        increaseButton = new JButton("+");
        increaseButton.setForeground(Color.GREEN);
        decreaseButton = new JButton("-");
        decreaseButton.setForeground(Color.RED);
        countPanel = new JPanel();
        countPanel.setBackground(Color.PINK);
        countPanel.add(dayCountLabel);
        countPanel.add(countField);
        
        increaseButton.addActionListener(new CountButtonListener());
        decreaseButton.addActionListener(new CountButtonListener());
        
        JPanel incDecPanel = new JPanel(new GridLayout(2, 1));
        incDecPanel.add(increaseButton);
        incDecPanel.add(decreaseButton);
        countPanel.add(incDecPanel);
        
        dateField = new JTextField(12);
        dateField.setEditable(false);
        datePanel = new JPanel();
        
        datePanel.add(dateAddress);
        datePanel.add(dateField);
        datePanel.setBackground(Color.PINK);
        
        panelCenter = new JPanel();
        panelCenter.setBackground(Color.PINK);
        panelCenter.add(countPanel);
        panelCenter.add(datePanel);
        
        backButton = new JButton("BACK");
        backButton.addActionListener(new ActionListenerB());
        nextButton = new JButton("NEXT");
        nextButton.addActionListener(new ActionListenerB());
        
        panelButton = new JPanel();
        panelButton.setBackground(Color.PINK);
        
        panelButton.add(backButton);
        panelButton.add(nextButton);
        
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
        
        setVisible(true);
        
        ComboBoxListener comboBoxListener = new ComboBoxListener();
        day.addActionListener(comboBoxListener);
        month.addActionListener(comboBoxListener);
        year.addActionListener(comboBoxListener);
    }
    //to write the date that user select with combo boxes into the textfield
    private class ComboBoxListener implements ActionListener{ 
        @Override
        public void actionPerformed(ActionEvent e) {
            String userDay = (String) day.getSelectedItem();
            String userMonth = (String) month.getSelectedItem();
            String userYear = (String) year.getSelectedItem();
            dateField.setText(userDay + " " + userMonth + " " + userYear);
        }
    }
    
    private class CountButtonListener implements ActionListener { //for increase/decrease the number of days
        int count;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == increaseButton) {
                count++;
                countField.setText(String.valueOf(count));
            } else if (e.getSource() == decreaseButton) {
                if (count > 0) {
                    count--;
                }
                countField.setText(String.valueOf(count));
            }
        }
    }
    
    private class ActionListenerB implements ActionListener { //for buttons
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nextButton) {
                saveBookingDetails();//call the method for saving the data
                Payment p = new Payment();
                setVisible(false); // Close the Date window
            } else if (e.getSource() == backButton) {
                AddRooms a = new AddRooms();
                setVisible(false); // Close the Date window
            }
        }

        private void saveBookingDetails() {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("BookingDetails.dat", true))) {
                String selectedDay = (String) day.getSelectedItem();
                String selectedMonth = (String) month.getSelectedItem();
                String selectedYear = (String) year.getSelectedItem();
                String checkInDate = selectedDay + " " + selectedMonth + " " + selectedYear;
                
                dos.writeUTF( "Check-in Date: " + checkInDate + "\nNumber of Days: " + countField.getText());
            
                } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving booking details: " + ex.getMessage());
            }
        }
    }
    
}
