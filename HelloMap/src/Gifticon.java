import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Date;


public class Gifticon extends JDialog {

   //instance data
	private JPanel giftImagePanel;
	private JLabel lblDue,lblNumber,lblGiftImage;
	private JButton btnBack;
	private ImageIcon giftImage;
	private Random gen;
	//private ButtonListener btnL;

   //methods
   public Gifticon(JFrame f, String s) {
      
      setSize(400,600);
      setBackground(Color.pink);
	  setLayout(null);
	  setResizable(false);
	  
   //   btnL = new ButtonListener();
      
      giftImagePanel = new JPanel();
      giftImagePanel.setBounds(0,0,400,600);
      giftImagePanel.setBackground(Color.white);
      add(giftImagePanel);
      
      giftImage = new ImageIcon("Images/기프티콘.PNG");
      lblGiftImage = new JLabel("",giftImage,SwingConstants.CENTER);
      lblGiftImage.setBounds(30,30,300,400);
      giftImagePanel.add(lblGiftImage);
      
      
      
   }   //Gifticon()


   
/*   private class ButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         Object obj = event.getSource();
         
         if (obj == btnBack) {
            //event1.setAllDisvisible();
         }
      }
   }*/
}   //Gifticon class