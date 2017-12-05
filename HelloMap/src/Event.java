import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.util.*;
import sun.audio.*;
import java.io.*;

public class Event extends JDialog {

   private JButton userR,userC,userP;
   private JPanel backPanel;
   private JLabel lblTitle,lblresult,lblCom;
   private int user,computer;
   private ButtonListener btnL;
   private Gifticon gifticonWin;
   private Random gen = new Random();
   private PrimaryPanel primary;
   private String jdbcUrl = "jdbc:mysql://localhost/javadb";
   private String jdbcDriver = "com.mysql.jdbc.Driver";
   private String strUser = "root";
   private String strPassword = "123123";
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   private Date myDate;
   private String saveId;
   private int code;
   private ImageIcon imageR,imageS,imageP;
   
   public Event(JFrame f, String s,PrimaryPanel p) {
      
      super(f,s,true);
      
      setSize(650,650);
      setBackground(Color.white);
      setLayout(null);
      setResizable(false);
      
      primary = p;
      saveId = primary.getSaveId();
      
      btnL = new ButtonListener();
      
      computer = gen.nextInt(3);
      
      
      backPanel = new JPanel();
      backPanel.setBounds(0,0,650,650);
      backPanel.setBackground(Color.lightGray);
      backPanel.setLayout(null);
      add(backPanel);
      
      imageR = new ImageIcon("Images/πŸ¿ß.PNG");
      imageS = new ImageIcon("Images/∞°¿ß.PNG");
      imageP = new ImageIcon("Images/∫∏.PNG");
      
      lblresult = new JLabel("");
      lblresult.setBounds(225,200,220,200);
      lblresult.setForeground(Color.blue);
      lblresult.setFont(new Font("a≈∏¿”∏”Ω≈",Font.PLAIN,60));
      lblresult.setHorizontalAlignment(SwingConstants.CENTER);
      lblresult.setVerticalAlignment(SwingConstants.CENTER);
      backPanel.add(lblresult);
      
      
      
      lblCom = new JLabel("?");
      lblCom.setBounds(240, 50, 170, 170);
      lblCom.setFont(new Font("æﬂ≥Ó¿⁄ æﬂ√º R",Font.PLAIN,30));
      backPanel.add(lblCom);
      
      
      userR = new JButton(imageR);
      userR.setBounds(40,410,150,150);
      userR.setBorderPainted(false);
      userR.setContentAreaFilled(false);
      userR.addActionListener(btnL);
      backPanel.add(userR);
      
      userC = new JButton(imageS);
      userC.setBounds(240, 400, 170,170);
      userC.setBorderPainted(false);
      userC.setContentAreaFilled(false);
      userC.addActionListener(btnL);
      backPanel.add(userC);
      
      userP = new JButton(imageP);
      userP.setBounds(440, 400, 170,170);
      userP.setBorderPainted(false);
      userP.setContentAreaFilled(false);
      userP.addActionListener(btnL);
      backPanel.add(userP);
      
      gifticonWin = new Gifticon(f,"±‚«¡∆ºƒ‹ »πµÊ º∫∞¯!");
      gifticonWin.setBounds(300,300,400,450);
      
   }   //Event()
   
   public void resetGame() {

     
     computer= gen.nextInt(3); 
      lblresult.setText("");
      lblCom.setIcon(null);
      userC.setVisible(true);
      userP.setVisible(true);
      userR.setVisible(true);

      lblCom.setText("");
   }
   public void connectDB() {
         try {
            Class.forName(jdbcDriver);

            conn = DriverManager.getConnection(jdbcUrl, strUser, strPassword);

         } catch (Exception e) {
            e.printStackTrace();
         }

      }

      public void closeDB() {
         try {
            
            pstmt.close();
            rs.close();
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
public void writeGifticonDB() {
      
      String sql = "insert into gifticon values(?,?,?)";
      Random gen = new Random();
      connectDB();
      
      try {
         
         saveId = primary.getSaveId();
         pstmt = conn.prepareStatement(sql);
        
         myDate = java.sql.Date.valueOf("2018-"+(gen.nextInt(12)+1)+"-"+(gen.nextInt(30)+1));
         code = (gen.nextInt(899999)+100000);
         
         System.out.println(saveId + "    " + myDate + "  " + code);
         
         pstmt.setString(1, saveId);
         pstmt.setDate(2,myDate);
         pstmt.setInt(3, code);
      
         pstmt.executeUpdate();
         
      } catch(SQLException e) {
         e.printStackTrace();
      }
      
      
}
   private class ButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent event) {
       
    	  boolean flag=false;
    	  
         Object obj = event.getSource();
         JButton btn = (JButton) event.getSource();
         
         userC.setVisible(false);
         userP.setVisible(false);
         userR.setVisible(false);
         
         if(!flag) {
        	 btn.setVisible(true);
        	 flag = true;
         }
         if(obj == userR) {
            user=0;
         } else if (obj == userC) {
            user=1;
         } else {
            user=2;
         }
         if(user==0 && computer==1) {
            lblresult.setText("W I N !");
            lblresult.setForeground(Color.blue);
            lblCom.setIcon(imageS);
            lblCom.setText(null);
            
    		try{        
    			InputStream in = new FileInputStream("Audios/¿Ã∞Â¥Ÿ.wav");
    			AudioStream as = new AudioStream(in);
    			AudioPlayer.player.start(as);
    	    }
    	
    	catch (Exception er) 
    	{
    		    System.out.println(er.getMessage());
    	}
            
            
            gifticonWin.setAlwaysOnTop(true);
            primary.setEventDispose();
            gifticonWin.show();
            writeGifticonDB();
            primary.setWriteGifticon(myDate, code);
         }
         else if(user==1 && computer==2) {
            lblresult.setText("W I N !");
            lblresult.setForeground(Color.blue);
            lblCom.setIcon(imageP);
            lblCom.setText(null);
    		
            try{        
    			InputStream in = new FileInputStream("Audios/¿Ã∞Â¥Ÿ.wav");
    			AudioStream as = new AudioStream(in);
    			AudioPlayer.player.start(as);
    	    }
    	
    	catch (Exception er) 
    	{
    		    System.out.println(er.getMessage());
    	}
            
            gifticonWin.setAlwaysOnTop(true);
            primary.setEventDispose();
            gifticonWin.show();
            writeGifticonDB();
            primary.setWriteGifticon(myDate, code);
         }
         else if(user==2 && computer==0) {
            lblresult.setText("W I N !");
            lblresult.setForeground(Color.blue);
            lblCom.setIcon(imageR);
            lblCom.setText(null);
    		
            try{        
    			InputStream in = new FileInputStream("Audios/¿Ã∞Â¥Ÿ.wav");
    			AudioStream as = new AudioStream(in);
    			AudioPlayer.player.start(as);
    	    }
    	
    	catch (Exception er) 
    	{
    		    System.out.println(er.getMessage());
    	}
            
            gifticonWin.setAlwaysOnTop(true);
            primary.setEventDispose();
            gifticonWin.show();
            writeGifticonDB();
            primary.setWriteGifticon(myDate, code);
         }
         else 
         {
            lblresult.setText("LOSE !");
    		try{        
    			InputStream in = new FileInputStream("Audios/¡Æπˆ∑¡∂À.wav");
    			AudioStream as = new AudioStream(in);
    			AudioPlayer.player.start(as);
    	    }
    	
    	catch (Exception er) 
    	{
    		    System.out.println(er.getMessage());
    	}
            if(computer ==0) lblCom.setIcon(imageR);
            else if(computer == 1)lblCom.setIcon(imageS);
            else if(computer == 2)lblCom.setIcon(imageP);
         }
      }   //actionPerformed()
      
   }   //ButtonListener class
   
}   //Event Class