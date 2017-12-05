import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Reservation extends JPanel {

   private int nCnt; // 달력에 몇일인지에 대한 변수
   private int nPic; // 사진 넘기는 것을 알기 위한 변수
   private int i; 
   private int nRoomNumber;
   private JButton btnReserve; // 예약 버튼
   private JButton btnBack; // 뒤로가기 버튼
   private JButton btnLeft, btnRight; // 사진 넘기기 위한 버튼
   private ButtonListener buttonL; 
   private JLabel lblInfo, lblMonth; // 달력정보 라벨
   //private JLabel Ro1, Ro2, Ro3, Ro4; // 방 사진 넣기 위한 라벨
   private JLabel lblImageStay; // Stay Image 넣을 라벨
   private JLabel lblImageFamily[],lblImageCouple[],lblImageSingle[],lblImageDormitory[];
   private JButton btnDate[]; // 달력 날짜 버튼
   private JButton btnDay[]; // 달력 요일 버튼
   private JButton btnNull[]; // 달력 빈날들 버튼
   private JButton btnBackChoice[]; // 방선택 취소를 위한 버튼
   private JButton btnRoomChoice[]; // 방선택을 위한 버큰
   private JTextField txtName, txtPhone, txtMany, txtReadName, txtReadPhone, txtReadMany; // 예역정보를 넣기 위한 창
   private ImageIcon imageIconFamliy[],imageIconCouple[],imageIconSingle[],imageIconDormitory[];
   private ImageIcon imageIconStay; // 예약창 기본 숙소 이미지
   private ImageIcon imageIconX, imageIconRoom,imageIconCal;
   private JLabel lblCal[],lblRoom[];
   private JLabel lblRoomBack[];
   private int nBackNum;
   private PrimaryPanel primary;
   private String jdbcUrl = "jdbc:mysql://localhost/javadb";
   private String jdbcDriver = "com.mysql.jdbc.Driver";
   private String strUser = "root";
   private String strPassword = "123123";
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   private int stayName;
   private String reserveState;
   private char possibleDay[];
   private char reservday[];
   private String saveId;
   private String stay_Name;
   private String stay_Reveal;
   private Date myDate1;
   private Date myDate2;
   private int userReservday[][];
   
   public Reservation(PrimaryPanel p,String s) {
      primary = p;
      saveId =s;
      stay_Name = "청수리";
      stay_Reveal = "";
      setPreferredSize(new Dimension(900, 800));
      setBackground(Color.WHITE);
      setLayout(null); // 패널 크기 설정

      reservday = new char[30];
      userReservday = new int[1][2];
      
      nCnt = 1;
      nPic = 1;
      nRoomNumber=0;
      Font fnt = new Font("야놀자 야체 R", Font.PLAIN, 24);

      buttonL = new ButtonListener();
      btnDate = new JButton[31];
      btnDay = new JButton[7];
      btnNull = new JButton[5];
      btnBackChoice = new JButton[4];
      possibleDay = new char[30]; 
      lblRoomBack = new JLabel[4];

      imageIconX = new ImageIcon("Images/xButton.png");
      for(int i = 0;i<4;i++) {
    	  lblRoomBack[i] = new JLabel("X");
    	  lblRoomBack[i].setFont(fnt);
      }
      
      
      for (i = 0; i < 4; i++) {
    	  btnBackChoice[i] = new JButton(imageIconX);
    	  btnBackChoice[i].setBounds(800, 50 + (90 * i), 50, 70);
    	  btnBackChoice[i].setForeground(Color.black);
    	  btnBackChoice[i].setBackground(Color.white);
    	  btnBackChoice[i].setFont(new Font("야놀자 야체 R", Font.PLAIN, 18));
    	  btnBackChoice[i].setEnabled(false);
    	  btnBackChoice[i].addActionListener(buttonL);
    	  btnBackChoice[i].add(lblRoomBack[i]);
         add(btnBackChoice[i]);
      }

      btnDay[0] = new JButton("일");
      btnDay[1] = new JButton("월");
      btnDay[2] = new JButton("화");
      btnDay[3] = new JButton("수");
      btnDay[4] = new JButton("목");
      btnDay[5] = new JButton("금");
      btnDay[6] = new JButton("토");

      for (i = 0; i < 7; i++) {
    	  btnDay[i].setBounds(50 + (i * 60), 465, 60, 50);
    	  btnDay[i].setFont(new Font("야놀자 야체 R", Font.PLAIN, 25));
    	  btnDay[i].setBackground(Color.white);
    	  btnDay[i].setForeground(Color.black);
    	  btnDay[i].addActionListener(buttonL);
    	  btnDay[i].setEnabled(false);
         add(btnDay[i]);
      }

      for (i = 0; i < 5; i++) {

    	  btnNull[i] = new JButton();
    	  btnNull[i].addActionListener(buttonL);
    	  btnNull[i].setEnabled(false);
    	  btnNull[i].setBackground(Color.white);
         add(btnNull[i]);
      }

      btnNull[0].setBounds(50, 520, 60, 50);
      btnNull[1].setBounds(110, 520, 60, 50);
      btnNull[2].setBounds(170, 520, 60, 50);
      btnNull[3].setBounds(350, 720, 60, 50);
      btnNull[4].setBounds(410, 720, 60, 50);

      imageIconCal = new ImageIcon("Images/calendar.png");
      lblCal = new JLabel[31];
      
      for (i = 0; i < 31; i++) {
    	  btnDate[i] = new JButton(imageIconCal);
    	  lblCal[i] = new JLabel(""+nCnt);
    	  lblCal[i].setFont(fnt);
         btnDate[i].add(lblCal[i]);
         nCnt++;
      }
      
      

      for (i = 0; i < 4; i++) {
    	  btnDate[i].setBounds(50 + (i * 60) + 180, 520, 60, 50);
      }
      for (i = 0; i < 7; i++) {
    	  btnDate[i + 4].setBounds(50 + (i * 60), 570, 60, 50);
    	  btnDate[i + 11].setBounds(50 + (i * 60), 620, 60, 50);
    	  btnDate[i + 18].setBounds(50 + (i * 60), 670, 60, 50);
      }
      for (i = 0; i < 5; i++) {
    	  btnDate[i + 25].setBounds(50 + (i * 60), 720, 60, 50);
      }

      for (i = 0; i < 31; i++) {
    	  btnDate[i].addActionListener(buttonL);
         // date[i].setBackground(Color.white);
         add(btnDate[i]);
      }
      
      for(i=0;i<31;i++) {
    	  btnDate[i].setEnabled(false);
      }
      

      btnRight = new JButton("▷");
      btnLeft = new JButton("◁");

      btnRight.setBounds(250, 335, 60, 60);
      btnRight.setBorderPainted(false);
      btnRight.setBackground(Color.white);
      btnRight.setFont(fnt);
      btnRight.addActionListener(buttonL);
      btnRight.setEnabled(false);
      add(btnRight);

      btnLeft.setBounds(200, 335, 60, 60);
      btnLeft.setBorderPainted(false);
      btnLeft.setBackground(Color.white);
      btnLeft.setFont(fnt);
      btnLeft.addActionListener(buttonL);
      btnLeft.setEnabled(false);
      add(btnLeft);

      btnReserve = new JButton("예약하기");
      btnReserve.setBounds(690, 710, 160, 50);
      btnReserve.setFont(fnt);
      btnReserve.setBackground(Color.white);
      btnReserve.setEnabled(false);
      btnReserve.addActionListener(buttonL);
      add(btnReserve);

      btnBack = new JButton("돌아가기");
      btnBack.setBounds(500, 710, 160, 50);
      btnBack.setFont(fnt);
      btnBack.setBackground(Color.white);
      btnBack.addActionListener(buttonL);
      add(btnBack);

      imageIconStay = new ImageIcon("Images/suri.png");
      lblImageStay = new JLabel(imageIconStay);
      lblImageStay.setBounds(50, 50, 420, 280);
      add(lblImageStay);
      lblImageStay.setVisible(true);
      
      imageIconFamliy = new ImageIcon[3];
      imageIconCouple = new ImageIcon[3];
      imageIconSingle = new ImageIcon[3];
      imageIconDormitory = new ImageIcon[3];
      
      lblImageFamily = new JLabel[3];
      lblImageCouple = new JLabel[3];
      lblImageSingle = new JLabel[3];
      lblImageDormitory = new JLabel[3];
      
      imageIconFamliy[0] = new ImageIcon("Images/Fsuri1.png");
      imageIconFamliy[1] = new ImageIcon("Images/Fsuri2.png");
      imageIconFamliy[2] = new ImageIcon("Images/Fsuri3.png");
      
      for(i=0;i<3;i++) {
         lblImageFamily[i] = new JLabel(imageIconFamliy[i]);
         lblImageFamily[i].setBounds(50, 50, 420, 280);
         lblImageFamily[i].setFont(fnt);
         lblImageFamily[i].setVisible(false);
         add(lblImageFamily[i]);
      }
      
      imageIconCouple[0] = new ImageIcon("Images/Csuri1.png");
      imageIconCouple[1] = new ImageIcon("Images/Csuri2.png");
      imageIconCouple[2] = new ImageIcon("Images/Csuri3.png");
      
      for(i=0;i<3;i++) {
         lblImageCouple[i] = new JLabel(imageIconCouple[i]);
         lblImageCouple[i].setBounds(50, 50, 420, 280);
         lblImageCouple[i].setFont(fnt);
         lblImageCouple[i].setVisible(false);
         add(lblImageCouple[i]);
      }
      
      imageIconSingle[0] = new ImageIcon("Images/Ssuri1.png");
      imageIconSingle[1] = new ImageIcon("Images/Ssuri2.png");
      imageIconSingle[2] = new ImageIcon("Images/Ssuri3.png");
      
      for(i=0;i<3;i++) {
         lblImageSingle[i] = new JLabel(imageIconSingle[i]);
         lblImageSingle[i].setFont(fnt);
         lblImageSingle[i].setBounds(50, 50, 420, 280);
         lblImageSingle[i].setVisible(false);
         add(lblImageSingle[i]);
      }
      
      imageIconDormitory[0] = new ImageIcon("Images/Dsuri1.png");
      imageIconDormitory[1] = new ImageIcon("Images/Dsuri2.png");
      imageIconDormitory[2] = new ImageIcon("Images/Dsuri3.png");
      
      for(i=0;i<3;i++) {
         lblImageDormitory[i] = new JLabel(imageIconDormitory[i]);
         lblImageDormitory[i].setFont(fnt);
         lblImageDormitory[i].setBounds(50, 50, 420, 280);
         lblImageDormitory[i].setVisible(false);
         add(lblImageDormitory[i]);
      }

      lblInfo = new JLabel("투숙객의 예약 정보 입력란");
      lblInfo.setFont(fnt);
      lblInfo.setBounds(580, 400, 270, 50);
      add(lblInfo);

      lblMonth = new JLabel("11월");
      lblMonth.setFont(fnt);
      lblMonth.setBounds(245, 410, 50, 50);
      add(lblMonth);

      imageIconRoom = new ImageIcon("Images/roomButton.png");
      lblRoom = new JLabel[4];
      lblRoom[0] = new JLabel("Family Room 200,000원");
      lblRoom[1] = new JLabel("Couple Room 150,000원");
      lblRoom[2] = new JLabel("Single Room 100,000원");
      lblRoom[3] = new JLabel("Dormitory Room 30,000원");
      
      
      btnRoomChoice = new JButton[4];
      btnRoomChoice[0] = new JButton(imageIconRoom);
      btnRoomChoice[1] = new JButton(imageIconRoom);
      btnRoomChoice[2] = new JButton(imageIconRoom);
      btnRoomChoice[3] = new JButton(imageIconRoom);
      
      for(int i = 0; i < 4 ; i++) {
    	  lblRoom[i].setFont(fnt);
         btnRoomChoice[i].setBounds(520, 50 + (90 * i), 280, 70);
         btnRoomChoice[i].setBackground(Color.white);
         btnRoomChoice[i].setFont(fnt);
         btnRoomChoice[i].addActionListener(buttonL);
         btnRoomChoice[i].setEnabled(true);
         btnRoomChoice[i].add(lblRoom[i]);
          add(btnRoomChoice[i]);      
      }
      

      txtName = new JTextField("", 15);
      txtName.setBounds(580, 500, 270, 50);
      txtName.setEditable(false);
      add(txtName);
      txtReadName = new JTextField("이름", 15);
      txtReadName.setBounds(510, 500, 70, 50);
      txtReadName.setBackground(Color.white);
      txtReadName.setFont(fnt);
      txtReadName.setBorder(null);
      txtReadName.setEditable(false);
      add(txtReadName);

      txtPhone = new JTextField("", 15);
      txtPhone.setBounds(580, 570, 270, 50);
      txtPhone.setEditable(false);
      add(txtPhone);
      txtReadPhone = new JTextField("전화번호", 15);
      txtReadPhone.setBounds(500, 570, 70, 50);
      txtReadPhone.setBackground(Color.white);
      txtReadPhone.setFont(fnt);
      txtReadPhone.setBorder(null);
      txtReadPhone.setEditable(false);
      add(txtReadPhone);

      txtMany = new JTextField("", 15);
      txtMany.setBounds(580, 640, 270, 50);
      txtMany.setEditable(false);
      add(txtMany);
      txtReadMany = new JTextField("숙박인원", 15);
      txtReadMany.setBounds(500, 640, 70, 50);
      txtReadMany.setBackground(Color.white);
      txtReadMany.setFont(fnt);
      txtReadMany.setBorder(null);
      txtReadMany.setEditable(false);
      add(txtReadMany);

   }

   public void setSaveId(String str) {
      saveId = str;
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

   public void readDB(int stayName) {

      String sql = "select * from stay";

      connectDB();
      try {
         pstmt = conn.prepareStatement(sql);

         rs = pstmt.executeQuery();

         while (rs.next()) {
            if (stayName == rs.getInt("stayname")) {
               reserveState = rs.getString("state");
               break;
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      closeDB();
   }

   public void writeStayDB(String states, int name) {
      
      String sql = "update stay set state =? where stayname = ?";
      
      connectDB();
      
      try {
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, states);
         pstmt.setInt(2, name);
      
         pstmt.executeUpdate();
         
      } catch(SQLException e) {
         e.printStackTrace();
      }
      closeDB();
      
   }
   
   public void writeReserve2DB(int date1,int date2) {
      
      String sql = "insert into reserve2 values(?,?,?,?,?,?)";
      
      connectDB();

      try {
         myDate1 = java.sql.Date.valueOf("2017-11-"+date1);
         myDate2 = java.sql.Date.valueOf("2017-11-"+date2);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, saveId);
         pstmt.setString(2, stay_Reveal);
         pstmt.setString(3, stay_Name);
         pstmt.setDate(4, myDate1);
         pstmt.setDate(5, myDate2);
         pstmt.setInt(6, Integer.parseInt(txtMany.getText()));
         pstmt.executeUpdate();

      } catch (Exception e) {
         e.printStackTrace();
      }

      closeDB();
   }
   public void init() {
	  nPic = 1;
	  nRoomNumber = 0;
      stay_Reveal = "";
       lblImageStay.setVisible(true);
       btnRight.setEnabled(false);
       btnLeft.setEnabled(false);
       for(i=0;i<3;i++) {
         lblImageFamily[i].setVisible(false);
         lblImageCouple[i].setVisible(false);
         lblImageSingle[i].setVisible(false);
         lblImageDormitory[i].setVisible(false);
       }
       for(int i =0; i < 4; i++)
       {
    	   btnRoomChoice[i].setEnabled(true);
          btnBackChoice[i].setEnabled(false);
       }
       txtName.setText("");
       txtPhone.setText("");
       txtMany.setText("");
       txtName.setEditable(false);
       txtPhone.setEditable(false);
       txtMany.setEditable(false);
       btnReserve.setEnabled(false);
       for (i = 0; i < 30; i++) {
    	   btnDate[i].setEnabled(false);
    	   lblCal[i].setForeground(Color.black);
        }
   }
   
   private class ButtonListener implements ActionListener {
      
      public void actionPerformed(ActionEvent event) {
         Object obj = event.getSource();
         
         
         for(int i = 0; i < 30; i++) {
            if(obj == btnDate[i]) {
               if(reservday[i] == '1') {
                  reservday[i] = '0';  
                  lblCal[i].setForeground(Color.black);
               }
               else {            
                  reservday[i] = '1';
                  lblCal[i].setForeground(Color.blue);
               }
            }
            
         }
         if(obj == btnRoomChoice[0]) {
        	 nRoomNumber = 0;
        	 stay_Reveal = "F Room";
        	 btnRoomChoice[1].setEnabled(false);
        	 btnRoomChoice[2].setEnabled(false);
        	 btnRoomChoice[3].setEnabled(false); // 나머지 방 선캑버튼 끄기
             btnBackChoice[0].setEnabled(true);
             btnBackChoice[1].setEnabled(false);
             btnBackChoice[2].setEnabled(false);
             btnBackChoice[3].setEnabled(false); // 해당 방 선택 해지키만 활성화
             txtName.setEditable(true);
             txtPhone.setEditable(true);
             txtMany.setEditable(true);
             btnReserve.setEnabled(true); // 예약버튼 및 예약정보 입력란 활성화
             stayName = 1;
             btnRight.setEnabled(true);
             btnLeft.setEnabled(true);
             lblImageStay.setVisible(false);
             lblImageFamily[0].setVisible(true);

             readDB(stayName);
             possibleDay = reserveState.toCharArray();
             for(i=0;i<30;i++) {
            	 btnDate[i].setEnabled(true);
              }

             for (i = 0; i < 30; i++) {
                if (possibleDay[i] == '1') {
                	btnDate[i].setEnabled(false);
                	lblCal[i].setForeground(Color.red);
                }
             }
           
             
             
          }
          else if(obj == btnRoomChoice[1]) {
        	  nRoomNumber = 1;
        	 stay_Reveal = "C Room";
        	 btnRoomChoice[0].setEnabled(false);
        	 btnRoomChoice[2].setEnabled(false);
        	 btnRoomChoice[3].setEnabled(false);
             btnBackChoice[0].setEnabled(false);
             btnBackChoice[1].setEnabled(true);
             btnBackChoice[2].setEnabled(false);
             btnBackChoice[3].setEnabled(false);
             txtName.setEditable(true);
             txtPhone.setEditable(true);
             txtMany.setEditable(true);
             btnReserve.setEnabled(true);
             stayName = 2;
             btnRight.setEnabled(true);
             btnLeft.setEnabled(true);
             lblImageStay.setVisible(false);
             lblImageCouple[0].setVisible(true);

             readDB(stayName);
             possibleDay = reserveState.toCharArray();
             for(i=0;i<30;i++) {
            	 btnDate[i].setEnabled(true);
              }

             for (i = 0; i < 30; i++) {
                if (possibleDay[i] == '1') {
                	btnDate[i].setEnabled(false);
                	lblCal[i].setForeground(Color.red);
                }
             }

          
          }
          else if(obj == btnRoomChoice[2]) {
        	  nRoomNumber = 2;
        	 stay_Reveal = "S Room";
        	 btnRoomChoice[0].setEnabled(false);
        	 btnRoomChoice[1].setEnabled(false);
        	 btnRoomChoice[3].setEnabled(false);
             btnBackChoice[0].setEnabled(false);
             btnBackChoice[1].setEnabled(false);
             btnBackChoice[2].setEnabled(true);
             btnBackChoice[3].setEnabled(false);
             txtName.setEditable(true);
             txtPhone.setEditable(true);
             txtMany.setEditable(true);
             btnReserve.setEnabled(true);
             stayName = 3;
             lblImageStay.setVisible(false);
             btnRight.setEnabled(true);
             btnLeft.setEnabled(true);
             lblImageSingle[0].setVisible(true);

             readDB(stayName);
             possibleDay = reserveState.toCharArray();
             for(i=0;i<30;i++) {
            	 btnDate[i].setEnabled(true);
              }

             for (i = 0; i < 30; i++) {
                if (possibleDay[i] == '1') {
                	btnDate[i].setEnabled(false);
                	lblCal[i].setForeground(Color.red);
                }
             }

          }
          else if(obj == btnRoomChoice[3]) {
        	  nRoomNumber = 3;
        	 stay_Reveal = "D Room";
        	 btnRoomChoice[0].setEnabled(false);
        	 btnRoomChoice[1].setEnabled(false);
        	 btnRoomChoice[2].setEnabled(false);
             btnBackChoice[0].setEnabled(false);
             btnBackChoice[1].setEnabled(false);
             btnBackChoice[2].setEnabled(false);
             btnBackChoice[3].setEnabled(true);
             txtName.setEditable(true);
             txtPhone.setEditable(true);
             txtMany.setEditable(true);
             btnReserve.setEnabled(true);
             stayName = 4;
             lblImageStay.setVisible(false);
             btnRight.setEnabled(true);
             btnLeft.setEnabled(true);
             lblImageDormitory[0].setVisible(true);

             readDB(stayName);
             possibleDay = reserveState.toCharArray();
             for(i=0;i<30;i++) {
            	 btnDate[i].setEnabled(true);
              }

             for (i = 0; i < 30; i++) {
                if (possibleDay[i] == '1') {
                	btnDate[i].setEnabled(false);
                	lblCal[i].setForeground(Color.red);
                }
             }

          }
         
         if (obj == btnBackChoice[0] || obj == btnBackChoice[1] || obj == btnBackChoice[2] || obj == btnBackChoice[3]) {
            for(int i =0; i < 4; i++)
            {
            	btnRoomChoice[i].setEnabled(true);
               btnBackChoice[i].setEnabled(false);
            }
            txtName.setText("");
            txtPhone.setText("");
           txtMany.setText("");
           txtName.setEditable(false);
           txtPhone.setEditable(false);
           txtMany.setEditable(false);
           btnReserve.setEnabled(false);
           for(i=0;i<3;i++) {
              lblImageFamily[i].setVisible(false);
              lblImageCouple[i].setVisible(false);
              lblImageSingle[i].setVisible(false);
              lblImageDormitory[i].setVisible(false);
            }
           lblImageStay.setVisible(true);

            for (i = 0; i < 30; i++) {
            	btnDate[i].setEnabled(false);
            	lblCal[i].setForeground(Color.black);
            }
            btnRight.setEnabled(false);
            btnLeft.setEnabled(false);
         }
      
         else if (obj == btnBack) {

            nBackNum = primary.getLocalNum();

            if (nBackNum == 1) // 제주부른다
            {
               primary.setVisibleLocalMap1();
               nPic = 0;
               nRoomNumber = 0;
               for(i=0;i<3;i++) {
                   lblImageFamily[i].setVisible(false);
                   lblImageCouple[i].setVisible(false);
                   lblImageSingle[i].setVisible(false);
                    lblImageDormitory[i].setVisible(false);
                 }
               lblImageStay.setVisible(true);
               for(int i =0; i < 4; i++)
               {
            	   btnRoomChoice[i].setEnabled(true);
                  btnBackChoice[i].setEnabled(false);
               }
               txtName.setText("");
               txtPhone.setText("");
               txtMany.setText("");
               txtName.setEditable(false);
               txtPhone.setEditable(false);
               txtMany.setEditable(false);
               btnReserve.setEnabled(false);
               for (i = 0; i < 30; i++) {
            	   btnDate[i].setEnabled(false);
            	   lblCal[i].setForeground(Color.black);
                }
               btnRight.setEnabled(false);
               btnLeft.setEnabled(false);
            }
            else if (nBackNum == 2) {
               primary.setVisibleLocalMap2();
               nPic = 0;
               nRoomNumber = 0;
               for(i=0;i<3;i++) {
                    lblImageFamily[i].setVisible(false);
                    lblImageCouple[i].setVisible(false);
                    lblImageSingle[i].setVisible(false);
                    lblImageDormitory[i].setVisible(false);
                 }
                lblImageStay.setVisible(true);
               for(int i =0; i < 4; i++)
               {
            	   btnRoomChoice[i].setEnabled(true);
                  btnBackChoice[i].setEnabled(false);
               }
               txtName.setText("");
               txtPhone.setText("");
               txtMany.setText("");
               txtName.setEditable(false);
               txtPhone.setEditable(false);
               txtMany.setEditable(false);
               btnReserve.setEnabled(false);
               for (i = 0; i < 30; i++) {
            	   btnDate[i].setEnabled(false);
            	   lblCal[i].setForeground(Color.black);
                }
               btnRight.setEnabled(false);
               btnLeft.setEnabled(false);
            }
            else if (nBackNum == 3) {
               primary.setVisibleLocalMap3();
               nPic = 0;
               nRoomNumber = 0;
               for(i=0;i<3;i++) {
                    lblImageFamily[i].setVisible(false);
                    lblImageCouple[i].setVisible(false);
                    lblImageSingle[i].setVisible(false);
                    lblImageDormitory[i].setVisible(false);
                 }
               lblImageStay.setVisible(true);
               for(int i =0; i < 4; i++)
               {
            	   btnRoomChoice[i].setEnabled(true);
            	   lblCal[i].setForeground(Color.white);
               }
               txtName.setText("");
               txtPhone.setText("");
               txtMany.setText("");
               txtName.setEditable(false);
               txtPhone.setEditable(false);
               txtMany.setEditable(false);
               btnReserve.setEnabled(false);
               for (i = 0; i < 30; i++) {
            	   btnDate[i].setEnabled(false);
            	   lblCal[i].setForeground(Color.black);
                }
               btnRight.setEnabled(false);
               btnLeft.setEnabled(false);
            }
            else if (nBackNum == 4) {
               primary.setVisibleLocalMap4();
               nPic = 0;
               nRoomNumber = 0;
               for(i=0;i<3;i++) {
                    lblImageFamily[i].setVisible(false);
                    lblImageCouple[i].setVisible(false);
                    lblImageSingle[i].setVisible(false);
                    lblImageDormitory[i].setVisible(false);
                 }
               lblImageStay.setVisible(true);
               for(int i =0; i < 4; i++)
               {
            	   btnRoomChoice[i].setEnabled(true);
                  btnBackChoice[i].setEnabled(false);
               }
               txtName.setText("");
               txtPhone.setText("");
               txtMany.setText("");
               txtName.setEditable(false);
               txtPhone.setEditable(false);
               txtMany.setEditable(false);
               btnReserve.setEnabled(false);
               for (i = 0; i < 30; i++) {
            	   btnDate[i].setEnabled(false);
            	   lblCal[i].setForeground(Color.black);
                }
               btnRight.setEnabled(false);
               btnLeft.setEnabled(false);
            }
         }

         else if (obj == btnRight) {
        	 nPic++;
            if (nPic == 3) {
            	nPic = 0;
            }
            if (nPic == 0) {
              if(nRoomNumber == 0) {
                 lblImageFamily[0].setVisible(true);
                 lblImageFamily[1].setVisible(false);
                 lblImageFamily[2].setVisible(false);
              }
              else if(nRoomNumber == 1) {
                 lblImageCouple[0].setVisible(true);
                 lblImageCouple[1].setVisible(false);
                 lblImageCouple[2].setVisible(false);
              }
              else if(nRoomNumber == 2) {
                 lblImageSingle[0].setVisible(true);
                 lblImageSingle[1].setVisible(false);
                 lblImageSingle[2].setVisible(false);
              }
              else if(nRoomNumber == 3) {
                 lblImageDormitory[0].setVisible(true);
                 lblImageDormitory[1].setVisible(false);
                 lblImageDormitory[2].setVisible(false);
              }
            }
            else if (nPic == 1) {
               if(nRoomNumber == 0) {
                   lblImageFamily[0].setVisible(false);
                   lblImageFamily[1].setVisible(true);
                   lblImageFamily[2].setVisible(false);
                }
                else if(nRoomNumber == 1) {
                   lblImageCouple[0].setVisible(false);
                   lblImageCouple[1].setVisible(true);
                   lblImageCouple[2].setVisible(false);
                }
                else if(nRoomNumber == 2) {
                   lblImageSingle[0].setVisible(false);
                   lblImageSingle[1].setVisible(true);
                   lblImageSingle[2].setVisible(false);
                }
                else if(nRoomNumber == 3) {
                   lblImageDormitory[0].setVisible(false);
                   lblImageDormitory[1].setVisible(true);
                   lblImageDormitory[2].setVisible(false);
                }
            }
            else if (nPic == 2) {
               if(nRoomNumber == 0) {
                     lblImageFamily[0].setVisible(false);
                     lblImageFamily[1].setVisible(false);
                     lblImageFamily[2].setVisible(true);
                  }
                  else if(nRoomNumber == 1) {
                     lblImageCouple[0].setVisible(false);
                     lblImageCouple[1].setVisible(false);
                     lblImageCouple[2].setVisible(true);
                  }
                  else if(nRoomNumber == 2) {
                     lblImageSingle[0].setVisible(false);
                     lblImageSingle[1].setVisible(false);
                     lblImageSingle[2].setVisible(true);
                  }
                  else if(nRoomNumber == 3) {
                     lblImageDormitory[0].setVisible(false);
                     lblImageDormitory[1].setVisible(false);
                     lblImageDormitory[2].setVisible(true);
                  }
            }
           

         }
         
         else if (obj == btnLeft) {
        	 nPic--;
            if (nPic == -1) {
            	nPic = 2;
            }

            if (nPic == 0) {
               if(nRoomNumber == 0) {
                   lblImageFamily[0].setVisible(true);
                   lblImageFamily[1].setVisible(false);
                   lblImageFamily[2].setVisible(false);
                }
                else if(nRoomNumber == 1) {
                   lblImageCouple[0].setVisible(true);
                   lblImageCouple[1].setVisible(false);
                   lblImageCouple[2].setVisible(false);
                }
                else if(nRoomNumber == 2) {
                   lblImageSingle[0].setVisible(true);
                   lblImageSingle[1].setVisible(false);
                   lblImageSingle[2].setVisible(false);
                }
                else if(nRoomNumber == 3) {
                   lblImageDormitory[0].setVisible(true);
                   lblImageDormitory[1].setVisible(false);
                   lblImageDormitory[2].setVisible(false);
                }
            }
            else if (nPic == 1) {
               if(nRoomNumber == 0) {
                     lblImageFamily[0].setVisible(false);
                     lblImageFamily[1].setVisible(true);
                     lblImageFamily[2].setVisible(false);
                  }
                 else if(nRoomNumber == 1) {
                     lblImageCouple[0].setVisible(false);
                     lblImageCouple[1].setVisible(true);
                     lblImageCouple[2].setVisible(false);
                  }
                  else if(nRoomNumber == 2) {
                     lblImageSingle[0].setVisible(false);
                     lblImageSingle[1].setVisible(true);
                     lblImageSingle[2].setVisible(false);
                  }
                  else if(nRoomNumber == 3) {
                     lblImageDormitory[0].setVisible(false);
                     lblImageDormitory[1].setVisible(true);
                     lblImageDormitory[2].setVisible(false);
                  }
            }
            else if (nPic == 2) {
               if(nRoomNumber == 0) {
                   lblImageFamily[0].setVisible(false);
                   lblImageFamily[1].setVisible(false);
                   lblImageFamily[2].setVisible(true);
                }
                else if(nRoomNumber == 1) {
                   lblImageCouple[0].setVisible(false);
                   lblImageCouple[1].setVisible(false);
                   lblImageCouple[2].setVisible(true);
                }
                else if(nRoomNumber == 2) {
                   lblImageSingle[0].setVisible(false);
                   lblImageSingle[1].setVisible(false);
                   lblImageSingle[2].setVisible(true);
                }
                else if(nRoomNumber == 3) {
                   lblImageDormitory[0].setVisible(false);
                   lblImageDormitory[1].setVisible(false);
                   lblImageDormitory[2].setVisible(true);
                }
            } 
         
         }
         
         else if(obj == btnReserve) {
             reserveState = new String("");
             for(int i = 0; i < 30; i++) {
                if(reservday[i] == '1') {
                   possibleDay[i] = reservday[i];
                }
                reserveState += Character.toString(possibleDay[i]);  
             }
             int j;
             System.out.println(reservday);
             for (int i = 0; i < 30; i++) {
                 
                 if (reservday[i] == '1')
                 {
                    userReservday[0][0] = i+1;
                    
                    for(j = i + 1; j < 30; j++) {
                       if(reservday[j] != '1') {
                          userReservday[0][1] = j;
                          System.out.println(userReservday[0][0]+"   "+userReservday[0][1]);
                          writeReserve2DB(userReservday[0][0], userReservday[0][1]);
                          break;
                       }
                       i = j+1;
                    }
                    if( (i==29 && reservday[29] == '1') || j == 30)
                    {
                 	   userReservday[0][1] = 30;
                 	   writeReserve2DB(userReservday[0][0], userReservday[0][1]);
                    }
                    primary.setWriteReservation(saveId, myDate1, myDate2, Integer.parseInt(txtMany.getText()));
                 }
              }
             
             writeStayDB(reserveState, stayName);
             
             init();
             primary.setAllLocalMapInit();
             primary.setVisibleWholeMap();
             primary.setShowMyPage();
          }
      }
   }
} // Reservation class