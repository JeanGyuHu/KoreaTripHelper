import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Food  {
   
   private JLabel Dongdo , Daegi , Ang ; // 각 이름
   private JLabel DongEx, DaeEx , AngEx; // 각 위치 설명
   private JLabel DongCall, DaeCall, AngCall; // 전화번호
   private JLabel DongMenu[],DaeMenu[],AngMenu[];//메뉴
   private ImageIcon DongIcon , DaeIcon , AngIcon; // 각 정보 관련 사진
   private JLabel DongPic,DaePic,AngPic;
   public Food() {
            
      Font fnt = new Font("야놀자 야체 R",Font.PLAIN,25);
      Dongdo = new JLabel("제주김만복");
      Dongdo.setFont(fnt);
      DongEx = new JLabel("제주 제주시 북성로 65 삼도 2동 1158-36");
      DongEx.setFont(new Font("야놀자 야체 R",Font.PLAIN,23));
      DongCall = new JLabel("<html>010-0000-0000<br></html>");
      DongCall.setFont(fnt);
      DongMenu = new JLabel[3];
      for(int i=0;i<3;i++)
      {
         DongMenu[i] = new JLabel();
         DongMenu[i].setFont(fnt);
      }
      DongIcon = new ImageIcon("Images/dong.png");
      DongMenu[0].setText("<html><br>만복이네 김밥 : 5,500원</html>");
      DongMenu[1].setText("<html><br>전복컵밥 : 6,500원</html>");
      DongMenu[2].setText("<html><br>통전복주먹밥 : 5,000원</html>");
      DongPic = new JLabel(DongIcon);
      
      Daegi = new JLabel("자매국수");
      Daegi.setFont(fnt);
      DaeEx = new JLabel("제주 제주시 삼성로 67 일도 2동 1034-10");
      DaeEx.setFont(new Font("야놀자 야체 R",Font.PLAIN,23));
      DaeMenu = new JLabel[3];
      for(int i=0;i<3;i++)
      {
         DaeMenu[i] = new JLabel();
         DaeMenu[i].setFont(fnt);
      }
      DaeIcon = new ImageIcon("Images/daegi.png");
      DaeMenu[0].setText("<html><br>고기국수 : 7,000원</html>");
      DaeMenu[1].setText("<html><br>아강발(미니족발) : 20,000원</html>");
      DaeMenu[2].setText("<html><br>돔베고기 : 30,000원</html>");
      DaePic = new JLabel(DaeIcon);
      DaeCall = new JLabel("<html>010-0000-0000<br></html>");
      DaeCall.setFont(fnt);
      
      Ang = new JLabel("돈사돈");
      Ang.setFont(fnt);
      AngEx = new JLabel("제주 제주시 우평로 19 노형동 3086-3");
      AngEx.setFont(new Font("야놀자 야체 R",Font.PLAIN,23));
      AngMenu = new JLabel[2];
      for(int i=0;i<2;i++)
      {
         AngMenu[i] = new JLabel();
         AngMenu[i].setFont(fnt);
      }
      AngIcon = new ImageIcon("Images/ang.png");
      AngPic = new JLabel(AngIcon);
      AngMenu[0].setText("<html><br>흑돼지(400g) : 36,000원</html>");
      AngMenu[1].setText("<html><br>일반돼지(400g) : 28,000원</html>");
      AngCall = new JLabel("<html>010-0000-0000<br></html>");
      AngCall.setFont(fnt);
   
   }

   public JLabel getAng() {
      return Ang;
   }

   public JLabel getAngEx() {
      return AngEx;
   }

   public JLabel getAngCall() {
      return AngCall;
   }

   public JLabel[] getAngMenu() {
      return AngMenu;
   }

   public JLabel getAngPic() {
      return AngPic;
   }

   public JLabel getDaegi() {
      return Daegi;
   }

   public JLabel getDaeEx() {
      return DaeEx;
   }

   public JLabel getDaeCall() {
      return DaeCall;
   }

   public JLabel[] getDaeMenu() {
      return DaeMenu;
   }

   public JLabel getDaePic() {
      return DaePic;
   }

   public JLabel[] getDongMenu() {
      return DongMenu;
   }

   public JLabel getDongdo() {
      return Dongdo;
   }

   public JLabel getDongEx() {
      return DongEx;
   }

   public JLabel getDongCall() {
      return DongCall;
   }

   public JLabel getDongPic() {
      return DongPic;
   }
   
}