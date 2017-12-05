import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Food  {
   
   private JLabel Dongdo , Daegi , Ang ; // �� �̸�
   private JLabel DongEx, DaeEx , AngEx; // �� ��ġ ����
   private JLabel DongCall, DaeCall, AngCall; // ��ȭ��ȣ
   private JLabel DongMenu[],DaeMenu[],AngMenu[];//�޴�
   private ImageIcon DongIcon , DaeIcon , AngIcon; // �� ���� ���� ����
   private JLabel DongPic,DaePic,AngPic;
   public Food() {
            
      Font fnt = new Font("�߳��� ��ü R",Font.PLAIN,25);
      Dongdo = new JLabel("���ֱ踸��");
      Dongdo.setFont(fnt);
      DongEx = new JLabel("���� ���ֽ� �ϼ��� 65 �ﵵ 2�� 1158-36");
      DongEx.setFont(new Font("�߳��� ��ü R",Font.PLAIN,23));
      DongCall = new JLabel("<html>010-0000-0000<br></html>");
      DongCall.setFont(fnt);
      DongMenu = new JLabel[3];
      for(int i=0;i<3;i++)
      {
         DongMenu[i] = new JLabel();
         DongMenu[i].setFont(fnt);
      }
      DongIcon = new ImageIcon("Images/dong.png");
      DongMenu[0].setText("<html><br>�����̳� ��� : 5,500��</html>");
      DongMenu[1].setText("<html><br>�����Ź� : 6,500��</html>");
      DongMenu[2].setText("<html><br>�������ָԹ� : 5,000��</html>");
      DongPic = new JLabel(DongIcon);
      
      Daegi = new JLabel("�ڸű���");
      Daegi.setFont(fnt);
      DaeEx = new JLabel("���� ���ֽ� �Ｚ�� 67 �ϵ� 2�� 1034-10");
      DaeEx.setFont(new Font("�߳��� ��ü R",Font.PLAIN,23));
      DaeMenu = new JLabel[3];
      for(int i=0;i<3;i++)
      {
         DaeMenu[i] = new JLabel();
         DaeMenu[i].setFont(fnt);
      }
      DaeIcon = new ImageIcon("Images/daegi.png");
      DaeMenu[0].setText("<html><br>��ⱹ�� : 7,000��</html>");
      DaeMenu[1].setText("<html><br>�ư���(�̴�����) : 20,000��</html>");
      DaeMenu[2].setText("<html><br>������� : 30,000��</html>");
      DaePic = new JLabel(DaeIcon);
      DaeCall = new JLabel("<html>010-0000-0000<br></html>");
      DaeCall.setFont(fnt);
      
      Ang = new JLabel("���絷");
      Ang.setFont(fnt);
      AngEx = new JLabel("���� ���ֽ� ����� 19 ������ 3086-3");
      AngEx.setFont(new Font("�߳��� ��ü R",Font.PLAIN,23));
      AngMenu = new JLabel[2];
      for(int i=0;i<2;i++)
      {
         AngMenu[i] = new JLabel();
         AngMenu[i].setFont(fnt);
      }
      AngIcon = new ImageIcon("Images/ang.png");
      AngPic = new JLabel(AngIcon);
      AngMenu[0].setText("<html><br>�����(400g) : 36,000��</html>");
      AngMenu[1].setText("<html><br>�Ϲݵ���(400g) : 28,000��</html>");
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