import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class WholeMap extends JPanel {

   //instance data
   private JButton gifticonButton, reservationButton,logOutButton;
   private ImageIcon iKoreaMap;
   private JLabel lblTitle, lblId,lblKoreaMap;
   private JLabel lblHelp;
   private Timer m_timer;
   private TimerTask m_task;
   //Class
  // private Event event;
   private Gifticon gifticon;
   //ButtonListener
   private MyMouseListener mML;
   private PrimaryPanel primary;
   //methods
   
   //Constructor
   public WholeMap (PrimaryPanel p) {
      
      primary = p;
      
      setPreferredSize(new Dimension(900,800));
      setBackground(Color.white);
      setLayout(null);
   
      mML = new MyMouseListener();
      addMouseListener(mML);
      
      lblTitle = new JLabel("혼자 가니~?");
      lblTitle.setBounds(30,10,300,100);
      lblTitle.setForeground(Color.black);
      lblTitle.setFont(new Font("야놀자 야체 R",Font.PLAIN,45));
      add(lblTitle);   
      
      lblHelp = new JLabel("원하는 지역을 선택하세요!");
      lblHelp.setBounds(630,130,300,100);
      lblHelp.setForeground(Color.black);
      lblHelp.setFont(new Font("야놀자 야체 R", Font.PLAIN,30));
      add(lblHelp);
      
      iKoreaMap = new ImageIcon("Images/한반도11.png");
      lblKoreaMap = new JLabel("",iKoreaMap,SwingConstants.CENTER);
      lblKoreaMap.setBounds(0,50, 900, 750);
      add(lblKoreaMap);
      
   }   //WholeMap()
   
   
   private class MyMouseListener implements MouseListener {
      
      public void mouseClicked(MouseEvent e) {
         int x = e.getX();
         int y = e.getY();
         
         
         if(x<650&&x>550&&y<750&&y>700) {            //제주범위
            primary.setLocalNum(1);
            primary.setVisibleLocalMap1();
            
         }   else if(x>=330&&x<380&&y>=220&&y<270) {      //서울범위
            primary.setLocalNum(2);
            primary.setVisibleLocalMap2();
            
         }   else if(x>460&&x<510&&y>460&&y<510) {      //김천 밤위
            primary.setLocalNum(3);
            primary.setVisibleLocalMap3();
            
         }   else if(x>600&&x<650&&y>580&&y<630) {      //부산 범위
            primary.setLocalNum(4);
            primary.setVisibleLocalMap4();
            
         }
         
      }
      public void mousePressed(MouseEvent e) {}
      public void mouseReleased(MouseEvent e) {}
      public void mouseEntered(MouseEvent e) {}
      public void mouseExited(MouseEvent e) {}
   
   }   //MyMouseListener Class
   
}   //wholeMap class