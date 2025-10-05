package hotel_room_booking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Payment extends JFrame {
   
    private JPanel panel1;
    private JPanel panelEMG;
    private JPanel panelC1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panelC2;
    private JPanel panel4;
    private JLabel labelMsg ;
    private JRadioButton radioPAY1;
    private JRadioButton radioPAY2;
    private JButton buttonFinish ;
    public Payment() {
        setTitle("Payment");
        setSize(540,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();
        add(panelC1, BorderLayout.NORTH);
        add(panelC2, BorderLayout.CENTER);
        add(panel4, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(Color.PINK);
    }
    private void buildPanel(){
      
      panel1 = new JPanel();
      panelEMG = new JPanel();  
      panelC1 = new JPanel();
      panel2 = new JPanel();
      panel3 = new JPanel();
      panelC2 = new JPanel();
      panel4 = new JPanel();

       labelMsg = new JLabel("How Would You Like To Make The Payment :) ");
       
       ImageIcon imageIcon = new ImageIcon("paymentcrop.gif");
       JLabel imageLabel = new JLabel(imageIcon); 
       
       radioPAY1 = new JRadioButton ("by credit card");
       radioPAY2 = new JRadioButton ("cach upon arrival at hotel");
       ButtonGroup group = new ButtonGroup();
       group.add(radioPAY1 );
       group.add(radioPAY2);
       buttonFinish = new JButton("Finish");
        buttonFinish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //to go to view reservation window
                viewReservationInfo v = new viewReservationInfo();
                setVisible(false); // Make the window invisible
            }
        });
       
       panel1.add(labelMsg);
       panelEMG.add(imageLabel);
       panelC1.add(panel1);
       panelC1.add(panelEMG);
       panel2.add(radioPAY1);
       panel3.add(radioPAY2);
       panelC2.add(panel2);
       panelC2.add(panel3);
       panel4.add(buttonFinish);
              
        panelEMG.setBackground(Color.PINK);
        panel1.setBackground(Color.PINK);
        panel2.setBackground(Color.PINK);
        panel3.setBackground(Color.PINK);
        panel4.setBackground(Color.PINK);
        panelC1.setBackground(Color.PINK);
        panelC2.setBackground(Color.PINK);
        radioPAY1.setBackground(Color.PINK);
        radioPAY2.setBackground(Color.PINK);

    }
    public static void main(String[] args) {
        new Payment();

    }
}
