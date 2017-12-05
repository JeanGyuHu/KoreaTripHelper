import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Stay {

      private JLabel Vill, Hidden, Suri; // 각 이름
      private JLabel VillEx, HiddenEx, SuriEx; // 각 위치 설명
      private JLabel VillCall, HiddenCall, SuriCall; // 전화번호
      private ImageIcon VillIcon , HiddenIcon , SuriIcon;// 각 정보사진
      private JLabel VillPic , HiddenPic , SuriPic;// 사진 넣을 라벨
      
      public Stay() {
      Font fnt = new Font("야놀자 야체 R",Font.PLAIN,25);
      Vill = new JLabel("빌라드애월");
      Vill.setFont(fnt);
      VillEx = new JLabel("제주 제주시 애월읍 애월해안로 516-7");
      VillEx.setFont(fnt);
      VillCall = new JLabel("064-720-9000");
      VillCall.setFont(fnt);
      VillIcon = new ImageIcon("Images/bill.png");
      VillPic = new JLabel(VillIcon);
      
      Hidden = new JLabel("히든클리프 호텔");
      Hidden.setFont(fnt);
      HiddenEx = new JLabel("예래해안로 542 서귀포");
      HiddenEx.setFont(fnt);
      HiddenCall = new JLabel("064-752-7777");
      HiddenCall.setFont(fnt);
      HiddenIcon = new ImageIcon("Images/hidden.png");
      HiddenPic = new JLabel(HiddenIcon);
      
      Suri = new JLabel("청수리아파트");
      Suri.setFont(fnt);
      SuriEx = new JLabel("제주시 한경명 청수서 2길 96 1층");
      SuriEx.setFont(fnt);
      SuriCall = new JLabel("070-4117-4186");
      SuriCall.setFont(fnt);
      SuriIcon = new ImageIcon("Images/suri.png");
      SuriPic = new JLabel(SuriIcon);
      
      }

      public JLabel getVill()   {
         return Vill;
      }
      public JLabel getVillEx()   {
         return VillEx;
      }
      public JLabel getVillCall()   {
         return VillCall;
      }
      public JLabel getVillPic()   {
         return VillPic;
      }
      
      
      public JLabel getHidden()   {
         return Hidden;
      }
      public JLabel getHiddenEx()   {
         return HiddenEx;
      }
      public JLabel getHiddenCall()   {
         return HiddenCall;
      }
      public JLabel getHiddenPic()   {
         return HiddenPic;
      }
      
      public JLabel getSuri()   {
         return Suri;
      }
      public JLabel getSuriEx()   {
         return SuriEx;
      }
      public JLabel getSuriCall()   {
         return SuriCall;
      }
      public JLabel getSuriPic()   {
         return SuriPic;
      }
      
      
}