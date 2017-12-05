import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Stay {

      private JLabel Vill, Hidden, Suri; // �� �̸�
      private JLabel VillEx, HiddenEx, SuriEx; // �� ��ġ ����
      private JLabel VillCall, HiddenCall, SuriCall; // ��ȭ��ȣ
      private ImageIcon VillIcon , HiddenIcon , SuriIcon;// �� ��������
      private JLabel VillPic , HiddenPic , SuriPic;// ���� ���� ��
      
      public Stay() {
      Font fnt = new Font("�߳��� ��ü R",Font.PLAIN,25);
      Vill = new JLabel("�����ֿ�");
      Vill.setFont(fnt);
      VillEx = new JLabel("���� ���ֽ� �ֿ��� �ֿ��ؾȷ� 516-7");
      VillEx.setFont(fnt);
      VillCall = new JLabel("064-720-9000");
      VillCall.setFont(fnt);
      VillIcon = new ImageIcon("Images/bill.png");
      VillPic = new JLabel(VillIcon);
      
      Hidden = new JLabel("����Ŭ���� ȣ��");
      Hidden.setFont(fnt);
      HiddenEx = new JLabel("�����ؾȷ� 542 ������");
      HiddenEx.setFont(fnt);
      HiddenCall = new JLabel("064-752-7777");
      HiddenCall.setFont(fnt);
      HiddenIcon = new ImageIcon("Images/hidden.png");
      HiddenPic = new JLabel(HiddenIcon);
      
      Suri = new JLabel("û��������Ʈ");
      Suri.setFont(fnt);
      SuriEx = new JLabel("���ֽ� �Ѱ�� û���� 2�� 96 1��");
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