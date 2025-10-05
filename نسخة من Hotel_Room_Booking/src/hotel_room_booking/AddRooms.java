package hotel_room_booking;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;

public class AddRooms extends JFrame {

    private JPanel panel1;
    private JPanel panel3;
    private JPanel panel4;

    private JLabel labelRoomType;
    private JLabel labelIwant;
    private JCheckBox box_With_Balcony;
    private JCheckBox box_With_Breakfast;
    private JButton buttonBack;
    private JButton buttonNext;
    private JComboBox<String> TypeRoomBox;
   

    public AddRooms() {
        setTitle("Add Room");
        setSize(540, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(5, 1, 1, 1));

        buildPanel();
        add(panel1);
        add(panel3);
        add(panel4);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(Color.PINK);
        
        buttonNext.addActionListener(new ActionListener() { // to save data and go to the Date window
            @Override
            public void actionPerformed(ActionEvent e){
                saveRoomData(); //calling a method
            }
        });
             
            buttonBack.addActionListener(new ActionListener() { // to go back to the main menu
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                MainBookingMenu Menu = new  MainBookingMenu();
                
            }
        });
    }

    private void buildPanel() {
        panel1 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        ImageIcon imageIcon = new ImageIcon("twin room.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        labelRoomType = new JLabel("Room Type: ");
        String[] roomTypes = { "single room", "twin room", "superior room" };
        TypeRoomBox = new JComboBox<>(roomTypes);

        labelIwant = new JLabel("I Want: ");
        box_With_Balcony = new JCheckBox(" with balcony ");
        box_With_Breakfast = new JCheckBox(" with breakfast ");
        buttonBack = new JButton("Back");
        buttonNext = new JButton("Next");

        panel1.add(labelRoomType);
        panel1.add(TypeRoomBox);
        panel3.add(labelIwant);
        panel3.add(box_With_Balcony);
        panel3.add(box_With_Breakfast);
        panel4.add(buttonBack);
        panel4.add(buttonNext);

        panel1.setBackground(Color.PINK);
        panel3.setBackground(Color.PINK);
        panel4.setBackground(Color.PINK);
    }

    private void saveRoomData() {
        String roomType = (String) TypeRoomBox.getSelectedItem();
        String withBalcony = box_With_Balcony.isSelected() ? "Yes" : "No";
        String withBreakfast = box_With_Breakfast.isSelected() ? "Yes" : "No";

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("BookingDetails.dat", true))) {
            //to write into the file : BookingDetails.dat
            dos.writeUTF("Room Type: " + roomType + " \nWith Balcony: "+ withBalcony+"\nWith Breakfast: " + withBreakfast +"\n");

            JOptionPane.showMessageDialog(this, "Room details saved successfully!");
            
            Date date = new Date(); //to open Date window
            setVisible(false); //to close our window

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to save room details. Please try again.");
        }
    }
    public static void main(String[] args) {
        new AddRooms();

    }
}