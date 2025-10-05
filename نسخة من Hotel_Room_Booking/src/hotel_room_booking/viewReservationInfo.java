
package hotel_room_booking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class viewReservationInfo extends JFrame {
    JButton homebutton;
    JLabel yourreserv;
    JPanel panelnorth;
    JPanel center;
    JTextArea infofield;
    JPanel southpanel;

    public viewReservationInfo() {
        setTitle("View Reservation Info Window");
        setSize(430, 600); // Size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.PINK);

        // Load Image
        ImageIcon imageIcon = new ImageIcon("cardview⁩.png");
        JLabel imageLabel = new JLabel(imageIcon);

        homebutton = new JButton("HOME");
        homebutton.setBackground(Color.CYAN);
        homebutton.setForeground(Color.PINK);
        homebutton.addActionListener(new ActionListenerH());

        // Reservation Label
        yourreserv = new JLabel("Your Reservation:");
        yourreserv.setFont(new Font("Serif", Font.BOLD, 30));
        yourreserv.setForeground(Color.DARK_GRAY);

        // North Panel
        panelnorth = new JPanel();
        panelnorth.setBackground(Color.PINK);
        panelnorth.add(homebutton);

        // Center Panel
        center = new JPanel(new FlowLayout());
        center.setBackground(Color.PINK);

        infofield = new JTextArea(15, 20); // For displaying info
        infofield.setEditable(false);

        center.add(yourreserv);
        center.add(infofield);

        // South Panel for the image
        southpanel = new JPanel();
        southpanel.setBackground(Color.PINK);
        southpanel.add(imageLabel);

        // Add panels to the frame
        add(panelnorth, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(southpanel, BorderLayout.SOUTH);

        // Read and display the reservation information
        loadReservationInfo();

        setVisible(true); // Make the window visible
    }

    private class ActionListenerH implements ActionListener { //to go back to the main menu
        @Override
        public void actionPerformed(ActionEvent e) {
            MainBookingMenu Menu = new MainBookingMenu();
            setVisible(false); // Make the window invisible
        }
    }
    private void loadReservationInfo() { //method for read and display data in the Jlist
    File file = new File("BookingDetails.dat");
    if (file.exists()) {
        String content = ""; // Initialize a String to accumulate text
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream("BookingDetails.dat"))) {
            try {
                while (true) { // Continue reading until end of file
                    String reservationInfo = dataInputStream.readUTF(); // Read each piece of data as UTF string
                    content += reservationInfo + "\n"; // Concatenate each line to content
                }
            } catch (EOFException e) {
                // End of file reached, stop reading
            }
            infofield.setText(content);
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an error occurs
            infofield.setText("Error reading the reservation file.");
        }
    } else {
        infofield.setText("BookingDetails.dat not found.");
    }
}
}