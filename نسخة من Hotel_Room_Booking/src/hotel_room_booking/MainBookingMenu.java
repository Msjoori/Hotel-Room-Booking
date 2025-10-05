
package hotel_room_booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainBookingMenu extends JFrame {
    JLabel mainlabel;
    JPanel panel1;
    JRadioButtonMenuItem lightItem;
    JRadioButtonMenuItem darkItem;
    JMenuItem addReservationItem;
    JMenuItem viewReservationItem;

    public MainBookingMenu() {
        setTitle("Main Menu");
        setSize(600, 500); // Set size to the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new FlowLayout());

        getContentPane().setBackground(Color.PINK);// Set Background Color of Main Frame (Light mode as default)

        JMenuBar menuBar = new JMenuBar(); //menu creation
        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ExitListener());
        fileMenu.add(exitItem);

        // Reservation menu
        JMenu reservationMenu = new JMenu("Reservation");
        addReservationItem = new JMenuItem("Add Reservation");
        viewReservationItem = new JMenuItem("View Reservation");
        
        addReservationItem.addActionListener(new ActionListenerR());
        viewReservationItem.addActionListener(new ActionListenerR());
        
        reservationMenu.add(addReservationItem);
        reservationMenu.add(viewReservationItem);

        // Appearance menu
        JMenu appearanceMenu = new JMenu("Appearance");
        lightItem = new JRadioButtonMenuItem("Light", true);// the window color is pink
        
        darkItem = new JRadioButtonMenuItem("Dark"); // the window color will be in a dark color
        ButtonGroup group = new ButtonGroup();// Crreate a button group for the jradio buttons
        group.add(lightItem);
        group.add(darkItem);
        
        lightItem.addActionListener(new ColorListener());
        darkItem.addActionListener(new ColorListener());
        
        appearanceMenu.add(lightItem);
        appearanceMenu.add(darkItem);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(reservationMenu);
        menuBar.add(appearanceMenu);

        setJMenuBar(menuBar);
        ImageIcon imageIcon = new ImageIcon("mainhotel.png");
        JLabel imageLabel = new JLabel(imageIcon);

        panel1 = new JPanel();
        panel1.setBackground(Color.PINK);

        mainlabel = new JLabel("Booking Hotel Reservation");
        mainlabel.setFont(new Font ("Serif" , Font.BOLD , 30));//set the font of the label
        mainlabel.setForeground(Color.DARK_GRAY);//set the color of the label
        panel1.add(mainlabel);//added to the panel

        // Add the components to the Frame
        add(panel1);
        add(imageLabel);

        setVisible(true);// To make the window visible
    }
    
    private class ActionListenerR implements ActionListener {   //to add a reservation
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addReservationItem) {
                File file = new File("BookingDetails.dat");
                if (file.exists() && file.length() > 0) {
                    int response = JOptionPane.showConfirmDialog(null,"You already have a reservation. Do you want to delete it and create a new reservation?");
                    if (response == JOptionPane.YES_OPTION) {
                        deleteBookingDetailsFile();
                        AddRooms ad = new AddRooms();
                        setVisible(false); // Make the window invisible
                    }
                } else {
                    AddRooms ad = new AddRooms();
                    setVisible(false); // Make the window invisible
                }
            } else if (e.getSource() == viewReservationItem) {  //to go to view reservation window
                viewReservationInfo vri = new viewReservationInfo();
                setVisible(false); // Make the window invisible
            }
        }
    }

    private class ExitListener implements ActionListener { // for stops the program
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
        }
    
    private class ColorListener implements ActionListener { // to change into light or dark mode
        @Override
        public void actionPerformed(ActionEvent e) {
            if (lightItem.isSelected()) {
                getContentPane().setBackground(Color.PINK);
                panel1.setBackground(Color.PINK);
                mainlabel.setForeground(Color.DARK_GRAY);
            } else if (darkItem.isSelected()) {
                getContentPane().setBackground(Color.DARK_GRAY);
                panel1.setBackground(Color.DARK_GRAY);
                mainlabel.setForeground(Color.PINK);
            }
        }
    }

    
    private void deleteBookingDetailsFile() { //if the user want to add another reservation,this method will delete the old one
        File file = new File("BookingDetails.dat");
        if (file.exists()) {
            file.delete();
            System.out.println("Reservation file deleted.");
        }
    }
    public static void main(String[] args) {
        new MainBookingMenu();

    }
    
    
}

