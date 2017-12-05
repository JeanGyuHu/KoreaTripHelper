import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Hotplace {
   
   private JLabel Rainbow , Yaho , Acua; // 각 이름
   private JLabel RainEx, YaEx , AcuaEx; // 각 위치 설명
   private JLabel RainInfo, YaInfo, AcuaInfo;
   private JLabel RainCall, YaCall, AcuaCall;
   private ImageIcon RainIcon , YaIcon , AcuaIcon; // 각 정보 관련 사진
   private JLabel RainPic, YaPic,AcuaPic;
   public Hotplace() {
      
      Font fnt = new Font("야놀자 야체 R",Font.PLAIN,25);
      Rainbow = new JLabel("산굼부리");
      Rainbow.setFont(fnt);
      RainEx = new JLabel("제주 제주시 조천읍 비자림로 768");
      RainEx.setFont(fnt);
      RainIcon = new ImageIcon("Images/sangumburi.png");
      RainCall = new JLabel("064-783-9900");
      RainCall.setFont(fnt);
      RainInfo = new JLabel("<html><br>성인 : 6,000원  청소년 : 3,000원<br><br>매일 9시~18시, 11월~2월 : 9시~17시<br><br>바다가 보이진 않지만 억새들이 움직이는 <br>파도를 볼 수 있다.</html>");
      RainInfo.setFont(fnt);
      RainPic = new JLabel(RainIcon);
      
      
      Yaho = new JLabel("성 이시돌 목장");
      Yaho.setFont(fnt);
      YaEx = new JLabel("제주 제주시 한립읍 산록남로 53");
      YaEx.setFont(fnt);
      YaIcon = new ImageIcon("Images/sung.png");
      YaCall = new JLabel("전화번호 없음");
      YaCall.setFont(fnt);
      YaInfo = new JLabel("<html><br>입장료 없음!! <br><br>이라크 바그다드 건축 양식의 기원을 둔 <br>'테쉬폰'을 볼 수 있다</html>");
      YaInfo.setFont(fnt);
      YaPic = new JLabel(YaIcon);
      
      Acua = new JLabel("한담해변");
      Acua.setFont(fnt);
      AcuaEx = new JLabel("제주시 애월읍 애월리 (한담해안산책로입구)");
      AcuaEx.setFont(new Font("야놀자 야체 R",Font.PLAIN,23));
      AcuaCall = new JLabel("전화번호 없음");
      AcuaCall.setFont(fnt);
      AcuaInfo = new JLabel("<html><br>입장료 없음!! <br><br>한담산책로 라고도 불리는 곳으로 <br> \"효리네 민박\"에서 이효리와 아이유가 <br>일몰을 감상하는 장면이 <br>방송되기도 하였다.<br>특히 일몰과 일출을 보기 좋다</html>");
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