import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.*;


public class LocalMap extends JPanel {
      private JCheckBox foodCheck,stayCheck,HotCheck;
      private JButton foodButton[] = new JButton[3];
      private JButton stayButton[] = new JButton[3];
      private JButton hotButton[] = new JButton[3];
      private JButton reservation[] = new JButton[3];
      private JLabel foodLabel[] = new JLabel[3];
      private JLabel stayLabel[] = new JLabel[3];
      private JLabel hotLabel[] = new JLabel[3];
      private JLabel jejuImageLabel;
      private ImageIcon jejuBackGroundImage;
      private ImageIcon foodImageIcon1,hotImageIcon1,stayImageIcon1; // 버튼에 이미지 넣으려고
      public JPanel foodPanel[] = new JPanel[3]; // 이것들은 아직 함수랑 연결안돼서! 
      private JPanel stayPanel[] = new JPanel[3]; // 밑에꺼 대신 패널!!
      private JPanel hotPanel[] = new JPanel[3];
      Food foodMap=new Food();
      Stay stayMap = new Stay();
      Hotplace hotPlace = new Hotplace();
      private ButtonListener buttonL;
      private CheckBoxListener checkL;
      private JButton goBack = new JButton("< 뒤로가기");
      private PrimaryPanel primary; // 뒤로가려고
      private JLabel info[];
      private JLabel LocalName;
   
   public LocalMap(PrimaryPanel p)
   {
      primary = p;
      Font fnt = new Font("야놀자 야체 R",Font.PLAIN,24);
      setPreferredSize(new Dimension(900,830));
      setBackground(Color.white);
      setLayout(null);
      
       checkL = new CheckBoxListener(); //리스너 생성
       buttonL = new ButtonListener();
       
      foodImageIcon1 = new ImageIcon("Images/food.png"); 
      Image or = foodImageIcon1.getImage();
      Image ch = or.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // 이미지가 너무 커서 이미지 크기 변경하는 코드
      foodImageIcon1 = new ImageIcon(ch);
      
      stayImageIcon1 = new ImageIcon("Images/stay1.png");
      Image or1 = stayImageIcon1.getImage();
      Image ch1 = or1.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      stayImageIcon1 = new ImageIcon(ch1);
      
      hotImageIcon1 = new ImageIcon("Images/hotplace.png");
      Image or2 = hotImageIcon1.getImage();
      Image ch2 = or2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      hotImageIcon1 = new ImageIcon(ch2);
      for(int i=0;i<3;i++)
      {
         foodButton[i] = new JButton(foodImageIcon1);
         foodButton[i].addActionListener(buttonL);
         foodButton[i].setBorderPainted(false);
         foodButton[i].setContentAreaFilled(false);
         foodButton[i].setVisible(false);
         add(foodButton[i]);
         
         stayButton[i] = new JButton(stayImageIcon1);
         stayButton[i].addActionListener(buttonL);
         stayButton[i].setBorderPainted(false);
         stayButton[i].setContentAreaFilled(false);
         stayButton[i].setVisible(false);
         add(stayButton[i]);

         hotButton[i] = new JButton(hotImageIcon1);
         hotButton[i].addActionListener(buttonL);
         hotButton[i].setBorderPainted(false);
         hotButton[i].setContentAreaFilled(false);
         hotButton[i].setVisible(false);
         add(hotButton[i]);
         
         reservation[i] = new JButton(" 예 약 ");
         reservation[i].setBackground(Color.white);
         reservation[i].setFont(fnt);
         reservation[i].setBounds(560, 620, 300, 40);
         reservation[i].addActionListener(buttonL);
         reservation[i].setVisible(false);
         add(reservation[i]);
      }
      
      foodButton[0].setBounds(470, 430, 22, 22);
      foodButton[1].setBounds(300, 505, 22, 22);
      foodButton[2].setBounds(230, 380, 22,22);
      
      stayButton[0].setBounds(250, 503, 22, 22);
      stayButton[1].setBounds(337, 483, 22, 22);
      stayButton[2].setBounds(280, 405, 22, 22);
      
      hotButton[0].setBounds(300, 465, 22, 22);
      hotButton[1].setBounds(135, 413, 22, 22);
      hotButton[2].setBounds(350, 405, 22, 22);
      
      
            
      foodCheck = new JCheckBox("  맛  집");
      foodCheck.setFont(fnt);
      foodCheck.setBounds(100, 230, 100, 30);
      foodCheck.setBackground(Color.white);
      foodCheck.setSelected(false);
      foodCheck.addItemListener(checkL);
      add(foodCheck);
      
      stayCheck = new JCheckBox("  숙  소");
      stayCheck.setFont(fnt);
      stayCheck.setBounds(200,230,100,30);
      stayCheck.setBackground(Color.white);
      stayCheck.setSelected(false);
      stayCheck.addItemListener(checkL);
      add(stayCheck);
      
      HotCheck = new JCheckBox("  명  소");
      HotCheck.setFont(fnt);
      HotCheck.setBounds(300,230,100,30);
      HotCheck.setBackground(Color.white);
      HotCheck.setSelected(false);
      HotCheck.addItemListener(checkL);
      add(HotCheck);
      
      goBack.setBounds(400, 230, 120, 30);
      goBack.setFont(fnt);
       goBack.setBackground(Color.white);
       goBack.addActionListener(buttonL);
       add(goBack);
      
       
       for(int i=0;i<3;i++) {
          foodPanel[i] = new JPanel();
          foodPanel[i].setBounds(560, 100, 300, 600);
          foodPanel[i].setBackground(Color.lightGray);
          add(foodPanel[i]);
          foodPanel[i].setVisible(false);
          
          foodLabel[i] = new JLabel();
          foodLabel[i].setBounds(580,200,300,500);
          foodLabel[i].setFont(fnt);
          foodPanel[i].add(foodLabel[i]);
          
          stayPanel[i] = new JPanel();
           stayPanel[i].setBounds(560, 100, 300, 500);
           stayPanel[i].setBackground(Color.lightGray);
           add(stayPanel[i]);
           stayPanel[i].setVisible(false);
           
           stayLabel[i] = new JLabel();
           stayLabel[i].setBounds(580, 200, 300, 500);
           stayLabel[i].setFont(fnt);
           stayPanel[i].add(stayLabel[i]);

          
          hotPanel[i] = new JPanel();
          hotPanel[i].setBounds(560, 100, 300, 600);
          hotPanel[i].setBackground(Color.lightGray);
          add(hotPanel[i]);
          hotPanel[i].setVisible(false);
          
          hotLabel[i] = new JLabel();
          hotLabel[i].setBounds(580,200,300,500);
          hotLabel[i].setFont(fnt);
          hotPanel[i].add(hotLabel[i]);
       }
       
       foodPanel[0].add(foodMap.getDongPic());
       foodPanel[0].add(foodMap.getDongdo());
       foodPanel[0].add(foodMap.getDongEx());
       foodPanel[0].add(foodMap.getDongCall());
       JLabel tmp1[] = foodMap.getDongMenu();
       for(int i=0;i<3;i++)
       {
          foodPanel[0].add(tmp1[i]);
       }
       foodPanel[1].add(foodMap.getDaePic());
       foodPanel[1].add(foodMap.getDaegi());
       foodPanel[1].add(foodMap.getDaeEx());
       foodPanel[1].add(foodMap.getDaeCall());
       JLabel tmp2[] = foodMap.getDaeMenu();
       for(int i=0;i<3;i++)
       {
          foodPanel[1].add(tmp2[i]);
       }
       foodPanel[2].add(foodMap.getAngPic());
       foodPanel[2].add(foodMap.getAng());
       foodPanel[2].add(foodMap.getAngEx());
       foodPanel[2].add(foodMap.getAngCall());
       JLabel tmp3[] = foodMap.getAngMenu();
       for(int i=0;i<2;i++)
       {
          foodPanel[2].add(tmp3[i]);
       }
       stayPanel[0].add(stayMap.getVillPic());
       stayPanel[0].add(stayMap.getVill());
       stayPanel[0].add(stayMap.getVillEx());
       stayPanel[0].add(stayMap.getVillCall());
          
       stayPanel[1].add(stayMap.getHiddenPic());
       stayPanel[1].add(stayMap.getHidden());
       stayPanel[1].add(stayMap.getHiddenEx());
       stayPanel[1].add(stayMap.getHiddenCall());
          
       stayPanel[2].add(stayMap.getSuriPic());
       stayPanel[2].add(stayMap.getSuri());
       stayPanel[2].add(stayMap.getSuriEx());
       stayPanel[2].add(stayMap.getSuriCall());

       hotPanel[0].add(hotPlace.getRainPic());
       hotPanel[0].add(hotPlace.getRainbow());
       hotPanel[0].add(hotPlace.getRainEx());
       hotPanel[0].add(hotPlace.getRainCall());
       hotPanel[0].add(hotPlace.getRainInfo());
       hotPanel[1].add(hotPlace.getYaPic());
       hotPanel[1].add(hotPlace.getYaho());
       hotPanel[1].add(hotPlace.getYaEx());
       hotPanel[1].add(hotPlace.getYaCall());
       hotPanel[1].add(hotPlace.getYaInfo());
       hotPanel[2].add(hotPlace.getAcuaPic());
       hotPanel[2].add(hotPlace.getAcua());
       hotPanel[2].add(hotPlace.getAcuaEx());
       hotPanel[2].add(hotPlace.getAcuaCall());
       hotPanel[2].add(hotPlace.getAcuaInfo());
       
       info = new JLabel[3];
       
       info[0] = new JLabel("맛 집",foodImageIcon1,SwingConstants.CENTER);
       info[0].setVerticalTextPosition(SwingConstants.CENTER);
       info[0].setHorizontalTextPosition(SwingConstants.RIGHT);
       info[0].setBounds(130, 600, 100, 50);
       info[1] = new JLabel("숙 소",stayImageIcon1,SwingConstants.CENTER);
       info[1].setVerticalTextPosition(SwingConstants.CENTER);
       info[1].setHorizontalTextPosition(SwingConstants.RIGHT);
       info[1].setBounds(230, 600, 100, 50);
       info[2] = new JLabel("명 소",hotImageIcon1,SwingConstants.CENTER);
       info[2].setVerticalTextPosition(SwingConstants.CENTER);
       info[2].setHorizontalTextPosition(SwingConstants.RIGHT);
       info[2].setBounds(330, 600, 100, 50);
       for(int i=0;i<3;i++) {
          
         
          info[i].setFont(fnt);
          add(info[i]);
          info[i].setVisible(true);
       }
       LocalName = new JLabel("\"제 주 도\"");
       LocalName.setFont(new Font("a타임머신",Font.PLAIN,50));
       LocalName.setBounds(200, 0, 500, 300);
       add(LocalName);
       
       jejuBackGroundImage = new ImageIcon("Images/jeju.jpg"); //버튼들과 겹쳐서 버튼이 안보여서 주석처리
       jejuImageLabel = new JLabel(jejuBackGroundImage);
       jejuImageLabel.setBounds(10, 200, 550, 500);
       add(jejuImageLabel); //jeju image insertion
           
   }
   private class ButtonListener implements ActionListener //버튼 리스너
   {
      public void actionPerformed(ActionEvent e)
      {
         Object obj = e.getSource();
         
         if(obj == foodButton[0]) // 지도상에 맛집 버튼들 중에 하나를 클릭했을때
         {
            foodPanel[0].setVisible(true); //자신의 패널빼고 다 안보이게 설정
            foodPanel[1].setVisible(false);
            foodPanel[2].setVisible(false);
            
            for(int i=0;i<3;i++)
            {
               stayPanel[i].setVisible(false);
               hotPanel[i].setVisible(false);
               reservation[i].setVisible(false);
            }
            foodLabel[0].setText("\"제주 맛집\"");
         }
         else if(obj == foodButton[1])
         {
            foodPanel[0].setVisible(false);
            foodPanel[1].setVisible(true);
            foodPanel[2].setVisible(false);
            for(int i=0;i<3;i++)
            {
               stayPanel[i].setVisible(false);
               hotPanel[i].setVisible(false);
               reservation[i].setVisible(false);
            }
            foodLabel[1].setText("\"제주 맛집\"");
         }
         else if(obj == foodButton[2])
         {
            foodPanel[0].setVisible(false);
            foodPanel[1].setVisible(false);
            foodPanel[2].setVisible(true);
            for(int i=0;i<3;i++)
            {
               stayPanel[i].setVisible(false);
               hotPanel[i].setVisible(false);
               reservation[i].setVisible(false);
            }
            foodLabel[2].setText("\"제주 맛집\"");
         }
         if(obj == stayButton[0])
         {
            stayPanel[0].setVisible(true);
            stayPanel[1].setVisible(false);
            stayPanel[2].setVisible(false);
            for(int i=0;i<3;i++)
            {
               foodPanel[i].setVisible(false);
               hotPanel[i].setVisible(false);
            }
            stayLabel[0].setText("\"제주 숙소\"");
            
            reservation[0].setVisible(true);
            reservation[1].setVisible(false);
            reservation[2].setVisible(false);
         }
         else if(obj == stayButton[1])
         {
            stayPanel[0].setVisible(false);
            stayPanel[1].setVisible(true);
            stayPanel[2].setVisible(false);
            for(int i=0;i<3;i++)
            {
               foodPanel[i].setVisible(false);
               hotPanel[i].setVisible(false);
            }
            stayLabel[1].setText("\"제주 숙소\"");
            reservation[0].setVisible(false);
            reservation[1].setVisible(true);
            reservation[2].setVisible(false);
         }
         else if(obj == stayButton[2])
         {
            stayPanel[0].setVisible(false);
            stayPanel[1].setVisible(false);
            stayPanel[2].setVisible(true);
            for(int i=0;i<3;i++)
            {
               foodPanel[i].setVisible(false);
               hotPanel[i].setVisible(false);
            }
            stayLabel[2].setText("\"제주 숙소\"");
            reservation[0].setVisible(false);
            reservation[1].setVisible(false);
            reservation[2].setVisible(true);
         }
         if(obj == hotButton[0])
         {
            hotPanel[0].setVisible(true);
            hotPanel[1].setVisible(false);
            hotPanel[2].setVisible(false);
            for(int i=0;i<3;i++)
            {
               foodPanel[i].setVisible(false);
               stayPanel[i].setVisible(false);
               reservation[i].setVisible(false);
            }
            hotLabel[0].setText("\"제주 명소\"");
                        
         }
         else if(obj == hotButton[1])
         {
            hotPanel[0].setVisible(false);
            hotPanel[1].setVisible(true);
            hotPanel[2].setVisible(false);
            for(int i=0;i<3;i++)
            {
               foodPanel[i].setVisible(false);
               stayPanel[i].setVisible(false);
               reservation[i].setVisible(false);
            }
            hotLabel[1].setText("\"제주 명소\"");
         }
         else if(obj == hotButton[2])
         {
            hotPanel[0].setVisible(false);
            hotPanel[1].setVisible(false);
            hotPanel[2].setVisible(true);
            for(int i=0;i<3;i++)
            {
               foodPanel[i].setVisible(false);
               stayPanel[i].setVisible(false);
               reservation[i].setVisible(false);
            }
            hotLabel[2].setText("\"제주 명소\"");
         }
         if(obj == reservation[0])
         {
            primary.setVisibleReservation2();
         }
         else if(obj==reservation[1])
         {
            primary.setVisibleReservation3();
         }
         else if(obj==reservation[2])
         {
            primary.setVisibleReservation1();
         }
         if(obj==goBack)
         {
            for(int i=0;i<3;i++)
            {
               foodButton[i].setVisible(false);
               stayButton[i].setVisible(false);
               hotButton[i].setVisible(false);
               reservation[i].setVisible(false);
               foodPanel[i].setVisible(false);
               stayPanel[i].setVisible(false);
               hotPanel[i].setVisible(false);
               foodCheck.setSelected(false);
               stayCheck.setSelected(false);
               HotCheck.setSelected(false);
            }
            primary.setVisibleWholeMap();
         }
      }
   }
   public void init() {
      for(int i=0;i<3;i++)
      {
         foodButton[i].setVisible(false);
         stayButton[i].setVisible(false);
         hotButton[i].setVisible(false);
         reservation[i].setVisible(false);
         foodPanel[i].setVisible(false);
         stayPanel[i].setVisible(false);
         hotPanel[i].setVisible(false);
         foodCheck.setSelected(false);
         stayCheck.setSelected(false);
         HotCheck.setSelected(false);
      }
   
   }
   class CheckBoxListener implements ItemListener //체크박스 리스너
   {
      public void itemStateChanged(ItemEvent e)
      {
         Object source = e.getItemSelectable();
         if(source == foodCheck)
         {
            if(e.getStateChange() == ItemEvent.DESELECTED)
            {
               for(int i=0;i<3;i++)
               {
                  foodButton[i].setVisible(false);
               }
            }
            else
            {
               for(int i=0;i<3;i++)
               {
                  foodButton[i].setVisible(true);
               }
            }
         }
         if(source == stayCheck)
         {
            if(e.getStateChange() == ItemEvent.DESELECTED)
            {
               for(int i=0;i<3;i++)
               {
                  stayButton[i].setVisible(false);
               }
            }
            else
            {
               for(int i=0;i<3;i++)
               {
                  stayButton[i].setVisible(true);
               }
            }
         }
         if(source == HotCheck)
         {
            if(e.getStateChange() == ItemEvent.DESELECTED)
            {
               for(int i=0;i<3;i++)
               {
                  hotButton[i].setVisible(false);
               }
            }
            else
            {
               for(int i=0;i<3;i++)
               {
                  hotButton[i].setVisible(true);
               }
            }
         }
         
      }
   }
}