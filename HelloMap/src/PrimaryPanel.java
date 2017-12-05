import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;
import sun.audio.*;
import java.io.*;

public class PrimaryPanel extends JPanel {

   private LoginPanel loginPanel;
   private WholeMap wholeMap;
   private Event event;
   private MyPage myPage;
   private LocalMap localMap;
   private LocalMap1 localMap1;
   private LocalMap2 localMap2;
   private LocalMap3 localMap3;
   private Reservation reservation;
   private Reservation1 reservation1;
   private Reservation2 reservation2;
   private JPanel topPanel;
   private JPanel mainPanel;
   private int localNum =0;
   private JLabel lblId;
   private Timer m_timer;
   private TimerTask m_task;
   private String saveId;
   private String saveName;
   private JButton gifticonButton, reservationButton,logOutButton;
   
   private ButtonListener btnL;
   
   private JFrame f;
   
   public PrimaryPanel(JFrame ff) {
   
      f= ff;
      
      setPreferredSize(new Dimension(1000,1000));
      setBackground(Color.white);
      setLayout(null);
      
      int i=0;
      topPanel = new JPanel();
      mainPanel = new JPanel();
      
      btnL = new ButtonListener();
      
      loginPanel = new LoginPanel(this);
      wholeMap = new WholeMap(this);
      event = new Event(f,"EVENT!!",this);
      
      localMap = new LocalMap(this);
      localMap1 = new LocalMap1(this);
      localMap2 = new LocalMap2(this);
      localMap3 = new LocalMap3(this);
      reservation = new Reservation(this,saveId);
      reservation1 = new Reservation1(this,saveId);
      reservation2 = new Reservation2(this,saveId);
      
      
      saveId = loginPanel.getId();
         
      lblId = new JLabel("반갑습니다 "+saveName +"님");
      lblId.setBounds(50, 15, 300, 40);
      lblId.setFont(new Font("야놀자 야체 R",Font.PLAIN,25));
      lblId.setForeground(new Color(200,100,50));
      topPanel.add(lblId);
      
      topPanel.setBounds(50,50,900,70);
      topPanel.setLayout(null);
      topPanel.setVisible(false);
      add(topPanel);
      
      mainPanel.setBounds(50, 150, 900, 800);
      mainPanel.setLayout(null);
      mainPanel.setVisible(false);
      add(mainPanel);
      
      localMap.setBounds(0,0,900,800);
      localMap.setVisible(false);
      mainPanel.add(localMap);
      
      localMap1.setBounds(0,0,900,800);
      localMap1.setVisible(false);
      mainPanel.add(localMap1);
      
      localMap2.setBounds(0,0,900,800);
      localMap2.setVisible(false);
      mainPanel.add(localMap2);
      
      localMap3.setBounds(0,0,900,800);
      localMap3.setVisible(false);
      mainPanel.add(localMap3);
      
      Font fnt = new Font("야놀자 야체 R",Font.PLAIN,18);
      gifticonButton = new JButton("기프티콘 확인");
      gifticonButton.setBackground(Color.white);
      gifticonButton.setFont(fnt);
      gifticonButton.setBounds(400,15,120,40);
      gifticonButton.addActionListener(btnL);
      topPanel.add(gifticonButton);
      
      reservationButton = new JButton("예약 목록 확인");
      reservationButton.setBackground(Color.white);
      reservationButton.setFont(fnt);
      reservationButton.setBounds(550, 15, 120, 40);
      reservationButton.addActionListener(btnL);
      topPanel.add(reservationButton);
            
      logOutButton = new JButton("로그아웃");
      logOutButton.setBackground(Color.white);
      logOutButton.setFont(fnt);
      logOutButton.setBounds(700,15,120,40);
      logOutButton.addActionListener(btnL);
      topPanel.add(logOutButton);
      
      loginPanel.setBounds(0, 0, 1000, 1000);
      add(loginPanel);
      
      wholeMap.setBounds(0,0,900,800);
      wholeMap.setVisible(false);
      mainPanel.add(wholeMap);
      
      event.setBounds(175,175,650,650);
      
       Timer m_timer = new Timer();
       TimerTask m_task = new TimerTask() {
       @Override
       public void run() {
          
          try{        
             InputStream in = new FileInputStream("Audios/가위바위보.wav");
             AudioStream as = new AudioStream(in);
             AudioPlayer.player.start(as);
           }
       
       catch (Exception er) 
       {
              System.out.println(er.getMessage());
       }
          
             event.resetGame();
             event.show();
                        
          }
       };
       m_timer.schedule(m_task,500000,100000);
      
      reservation.setBounds(0,0,900,800);
      reservation.setVisible(false);
      mainPanel.add(reservation);
      
      reservation1.setBounds(0,0,900,800);
      reservation1.setVisible(false);
      mainPanel.add(reservation1);
      
      reservation2.setBounds(0,0,900,800);
      reservation2.setVisible(false);
      mainPanel.add(reservation2);
   }
   public void logOut() {
      
      int result;
      
      result = JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?","확인",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
      
      if(result == JOptionPane.YES_OPTION) {
        
        setVisibleLoginPanel();
         
         reservation.init();
        reservation1.init();
        reservation2.init();
        
        localMap.init();
         localMap1.init();
         localMap2.init();
         localMap3.init();
      } else if(result == JOptionPane.NO_OPTION) {
         
      }
   }
   
   public int getLocalNum() {   return localNum; }
   public void setLocalNum(int i)   {   this.localNum=i; }
   public void setShowMyPage() {myPage.show();}
   public void setEventDispose() { event.dispose(); }
   public String getSaveId() { return saveId; }
   public void setWriteGifticon(Date date, int due) {myPage.writeGifticon(date, due);   }
   public void setWriteReservation(String name,Date date1,Date date2, int num) {myPage.writeReservation(name, date1, date2, num);}
   public void setAllLocalMapInit() { localMap.init(); localMap1.init(); localMap2.init(); localMap3.init(); }
   
   
   public void setVisibleWholeMap() {
   
      saveName = loginPanel.getName();
      saveId = loginPanel.getId();
      
      reservation.setSaveId(saveId);
      reservation1.setSaveId(saveId);
      reservation2.setSaveId(saveId);
      
      lblId.setText("반갑습니다   "+saveName +"님");
      myPage = new MyPage(f,"MyPage",loginPanel.getId());
      myPage.setBounds(250,200,500,700);
      mainPanel.setVisible(true);
      topPanel.setVisible(true);
      loginPanel.setVisible(false);
      wholeMap.setVisible(true);
      event.setVisible(false);
      localMap.setVisible(false);
      localMap1.setVisible(false);
      localMap2.setVisible(false);
      localMap3.setVisible(false);
      reservation.setVisible(false);
      reservation1.setVisible(false);
      reservation2.setVisible(false);
      
   }   //setVisibleWholeMap()
   
   public void setVisibleLoginPanel() {
      
	   loginPanel.setVisible(true);
      wholeMap.setVisible(false);
      event.setVisible(false);
      //gifticon.setVisible(false);
       localMap.setVisible(false);
      localMap1.setVisible(false);
      localMap2.setVisible(false);
      localMap3.setVisible(false);
      reservation.setVisible(false);
      reservation1.setVisible(false);
      reservation2.setVisible(false);
      //myPage.setVisible(false);
      topPanel.setVisible(false);
      mainPanel.setVisible(false);
      
   }   //setVisiblemainFrame()
   
   public void setVisibleLocalMap1() {
      
      mainPanel.setVisible(true);
      topPanel.setVisible(true);
      loginPanel.setVisible(false);
      wholeMap.setVisible(false);
      event.setVisible(false);
      //gifticon.setVisible(false);
      localMap.setVisible(true);
      localMap1.setVisible(false);
      localMap2.setVisible(false);
      localMap3.setVisible(false);
      reservation.setVisible(false);
      reservation1.setVisible(false);
      reservation2.setVisible(false);
      myPage.setVisible(false);
      
   }   //setVisibleLocalMap1()
   
   public void setVisibleLocalMap2() {
      
      mainPanel.setVisible(true);
      topPanel.setVisible(true);
      loginPanel.setVisible(false);
      wholeMap.setVisible(false);
      event.setVisible(false);
      //gifticon.setVisible(false);
     localMap.setVisible(false);
      localMap1.setVisible(true);
      localMap2.setVisible(false);
      localMap3.setVisible(false);
      reservation.setVisible(false);
      reservation1.setVisible(false);
      reservation2.setVisible(false);
      myPage.setVisible(false);
      
   }   //setVisibleLocalMap2()
   public void setVisibleLocalMap3() {
      
      mainPanel.setVisible(true);
      topPanel.setVisible(true);
      loginPanel.setVisible(false);
      wholeMap.setVisible(false);
      event.setVisible(false);
      //gifticon.setVisible(false);
     localMap.setVisible(false);
      localMap1.setVisible(false);
      localMap2.setVisible(true);
      localMap3.setVisible(false);
      reservation.setVisible(false);
      reservation1.setVisible(false);
      reservation2.setVisible(false);
      myPage.setVisible(false);
      
   }   //setVisibleLocalMap3()
   
   public void setVisibleLocalMap4() {
      
      mainPanel.setVisible(true);
      topPanel.setVisible(true);
      loginPanel.setVisible(false);
      wholeMap.setVisible(false);
      event.setVisible(false);
      //gifticon.setVisible(false);
     localMap.setVisible(false);
      localMap1.setVisible(false);
      localMap2.setVisible(false);
      localMap3.setVisible(true);
      reservation.setVisible(false);
      reservation1.setVisible(false);
      reservation2.setVisible(false);
      //myPage.setVisible(false);
      
   }   //setVisibleLocalMap4()
   
   public void setVisibleReservation1() {
      
      mainPanel.setVisible(true);
      topPanel.setVisible(true);
      loginPanel.setVisible(false);
      wholeMap.setVisible(false);
      event.setVisible(false);
      //gifticon.setVisible(false);
      localMap.setVisible(false);
      localMap1.setVisible(false);
      localMap2.setVisible(false);
      localMap3.setVisible(false);
      reservation.setVisible(true);
      reservation1.setVisible(false);
      reservation2.setVisible(false);
      //myPage.setVisible(false);
      
   }   //setVisibleReservation()
   
   public void setVisibleReservation2() {
      
      mainPanel.setVisible(true);
      topPanel.setVisible(true);
      loginPanel.setVisible(false);
      wholeMap.setVisible(false);
      event.setVisible(false);
      //gifticon.setVisible(false);
      localMap.setVisible(false);
      localMap1.setVisible(false);
      localMap2.setVisible(false);
      localMap3.setVisible(false);
      reservation.setVisible(false);
      reservation1.setVisible(true);
      reservation2.setVisible(false);
      //myPage.setVisible(false);
      
   }   //setVisibleReservation()

   public void setVisibleReservation3() {
   
   mainPanel.setVisible(true);
   topPanel.setVisible(true);
   loginPanel.setVisible(false);
   wholeMap.setVisible(false);
   event.setVisible(false);
   //gifticon.setVisible(false);
   localMap.setVisible(false);
   localMap1.setVisible(false);
   localMap2.setVisible(false);
   localMap3.setVisible(false);
   reservation.setVisible(false);
   reservation1.setVisible(false);
   reservation2.setVisible(true);
   //myPage.setVisible(false);
   
}   //setVisibleReservation()
   private class ButtonListener implements ActionListener {
      
      public void actionPerformed (ActionEvent event) {
         
         Object obj =event.getSource();

         if(obj == gifticonButton || obj == reservationButton) {
            
            myPage.show();
            
         
         }else if(obj == logOutButton) {
         
            logOut();
            
         }
         
         
      }   //actionPerformed()
      
   }   //ButtonListener Class
   
}   //PrimaryPanel Class