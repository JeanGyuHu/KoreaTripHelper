import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Hotplace {
   
   private JLabel Rainbow , Yaho , Acua; // �� �̸�
   private JLabel RainEx, YaEx , AcuaEx; // �� ��ġ ����
   private JLabel RainInfo, YaInfo, AcuaInfo;
   private JLabel RainCall, YaCall, AcuaCall;
   private ImageIcon RainIcon , YaIcon , AcuaIcon; // �� ���� ���� ����
   private JLabel RainPic, YaPic,AcuaPic;
   public Hotplace() {
      
      Font fnt = new Font("�߳��� ��ü R",Font.PLAIN,25);
      Rainbow = new JLabel("����θ�");
      Rainbow.setFont(fnt);
      RainEx = new JLabel("���� ���ֽ� ��õ�� ���ڸ��� 768");
      RainEx.setFont(fnt);
      RainIcon = new ImageIcon("Images/sangumburi.png");
      RainCall = new JLabel("064-783-9900");
      RainCall.setFont(fnt);
      RainInfo = new JLabel("<html><br>���� : 6,000��  û�ҳ� : 3,000��<br><br>���� 9��~18��, 11��~2�� : 9��~17��<br><br>�ٴٰ� ������ ������ ������� �����̴� <br>�ĵ��� �� �� �ִ�.</html>");
      RainInfo.setFont(fnt);
      RainPic = new JLabel(RainIcon);
      
      
      Yaho = new JLabel("�� �̽õ� ����");
      Yaho.setFont(fnt);
      YaEx = new JLabel("���� ���ֽ� �Ѹ��� ��ϳ��� 53");
      YaEx.setFont(fnt);
      YaIcon = new ImageIcon("Images/sung.png");
      YaCall = new JLabel("��ȭ��ȣ ����");
      YaCall.setFont(fnt);
      YaInfo = new JLabel("<html><br>����� ����!! <br><br>�̶�ũ �ٱ״ٵ� ���� ����� ����� �� <br>'�׽���'�� �� �� �ִ�</html>");
      YaInfo.setFont(fnt);
      YaPic = new JLabel(YaIcon);
      
      Acua = new JLabel("�Ѵ��غ�");
      Acua.setFont(fnt);
      AcuaEx = new JLabel("���ֽ� �ֿ��� �ֿ��� (�Ѵ��ؾȻ�å���Ա�)");
      AcuaEx.setFont(new Font("�߳��� ��ü R",Font.PLAIN,23));
      AcuaCall = new JLabel("��ȭ��ȣ ����");
      AcuaCall.setFont(fnt);
      AcuaInfo = new JLabel("<html><br>����� ����!! <br><br>�Ѵ��å�� ��� �Ҹ��� ������ <br> \"ȿ���� �ι�\"���� ��ȿ���� �������� <br>�ϸ��� �����ϴ� ����� <br>��۵Ǳ⵵ �Ͽ���.<br>Ư�� �ϸ��� ������ ���� ����</html>");
      AcuaInfo.setFont(fnt);
      AcuaIcon = new ImageIcon("Images/sea.png");
      AcuaPic = new JLabel(AcuaIcon);
      
   }
   public JLabel getAcua() {
      return Acua;
   }
   public JLabel getAcuaEx() {
      return AcuaEx;
   }
   public JLabel getAcuaInfo() {
      return AcuaInfo;
   }
   public JLabel getAcuaCall() {
      return AcuaCall;
   }
   public JLabel getAcuaPic() {
      return AcuaPic;
   }
   public JLabel getYaho() {
      return Yaho;
   }
   public JLabel getYaEx() {
      return YaEx;
   }
   public JLabel getYaInfo() {
      return YaInfo;
   }
   public JLabel getYaCall() {
      return YaCall;
   }
   public JLabel getYaPic() {
      return YaPic;
   }
   public JLabel getRainbow() {
      return Rainbow;
   }
   public JLabel getRainEx() {
      return RainEx;
   }
   public JLabel getRainInfo() {
      return RainInfo;
   }
   public JLabel getRainCall() {
      return RainCall;
   }
   public JLabel getRainPic() {
      return RainPic;
   }
   
}