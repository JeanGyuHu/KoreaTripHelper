import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyPage extends JDialog {
	
	private JScrollPane giftScroll;
	private JScrollPane reserveScroll;
	private JLabel giftTitle;
	private JLabel reserveTitle;
	private JPanel gifticonList;
	private JPanel reservationList;
	private JPanel nowPanel;
	private String jdbcUrl = "jdbc:mysql://localhost/javadb";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	private String strUser = "root";
	private String strPassword = "123123";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String strId;
	private JLabel lblMake,lblGiftInfo,lblReserveInfo;
	private int i=0,j=0;
	private GridBagLayout Gbag = new GridBagLayout();
	private GridBagConstraints gbc1;
	private Font fnt;
	
	public MyPage(JFrame f, String s,String id){
		
		super(f,s,true);
		
		setSize(500,700);
		setBackground(Color.white);
		setLayout(null);
		setResizable(false);
		
		strId = id;
		
		fnt = new Font("야놀자 야체 R", Font.PLAIN, 15);
		
		nowPanel = new JPanel();
		nowPanel.setBackground(new Color(250,224,212));
		nowPanel.setBounds(0, 0, 500, 700);
		nowPanel.setLayout(null);
		add(nowPanel);
		
		gbc1 = new GridBagConstraints();
		gbc1.fill = GridBagConstraints.BOTH;
		gbc1.weightx = 1.0;
		gbc1.weighty = 1.0;
		
		gifticonList = new JPanel();
		gifticonList.setBounds(20,30,440,280);
		//gifticonList.setPreferredSize(new Dimension(440,280));
		gifticonList.setBackground(new Color(240,240,240));
		gifticonList.setLayout(Gbag);
		nowPanel.add(gifticonList);
		
		giftScroll = new JScrollPane(/*gifticonList*/);
		giftScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		giftScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		giftScroll.setBounds(20,30,440,280);
		//giftScroll.getVerticalScrollBar().setValue(giftScroll.getVerticalScrollBar().getMaximum());
		giftScroll.setViewportView(gifticonList);
		
		nowPanel.add(giftScroll);
		
		reservationList = new JPanel();
		reservationList.setBounds(20,340,440,280);
		reservationList.setBackground(new Color(240,240,240));
		reservationList.setLayout(Gbag);
		nowPanel.add(reservationList);
		
		reserveScroll = new JScrollPane(reservationList);
		reserveScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		reserveScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		reserveScroll.setBounds(20,340,440,280);
		nowPanel.add(reserveScroll);
		
		giftTitle = new JLabel("기프티콘 내역");
		giftTitle.setBounds(10,10,480,40);
		giftTitle.setFont(new Font("야놀자 야체 R", Font.PLAIN, 20));
		gbAdd(giftTitle,0,0,1,1);
		gifticonList.add(giftTitle);
		
		reserveTitle = new JLabel("숙박 예약 내역");
		reserveTitle.setBounds(10,10,480,40);
		reserveTitle.setFont(new Font("야놀자 야체 R", Font.PLAIN, 20));
		gbAdd(reserveTitle,0,0,1,1);
		reservationList.add(reserveTitle);
		
		
		lblReserveInfo = new JLabel("숙소 이름        방 종류                체크인 날짜                 체크아웃 날짜                    숙박인원");
		lblReserveInfo.setBounds(10,50,480,30);
		lblReserveInfo.setFont(fnt);
		gbAdd(lblReserveInfo,0,1,1,1);
		reservationList.add(lblReserveInfo);
		
		
		lblGiftInfo = new JLabel("종류                                      사용기한                                                    일련번호");
		lblGiftInfo.setBounds(10, 50, 480, 30);
		lblGiftInfo.setFont(fnt);
		gbAdd(lblGiftInfo,0,1,1,1);
		gifticonList.add(lblGiftInfo);
		
		readReserveDB();
		readGifticonDB();
		
	}//myPage();
	
	private void gbAdd(Component c, int x, int y, int w, int h) {

	      gbc1.gridx = x;
	      gbc1.gridy = y; 
	      //가장 왼쪽 위 gridx, gridy값은 0 
	      gbc1.gridwidth  = w;	//넓이
	      gbc1.gridheight = h;	//높이
	      //gridwidth를 GridBagConstraints.REMAINDER 값으로 설정하면 현재 행의 마지막 셀이되고, 
	      //gridheight를 GridBagConstraints.REMAINDER 값으로 설정하면 현재 열의 마지막 셀이됩니다. 
	      //gridwidth를 GridBagConstraints. RELATIVE 값으로 설정하면 현재 행의 다음 셀부터 마지막 셀까지 차지하고, 
	      //gridheight를 GridBagConstraints. RELATIVE 값으로 설정하면 현재 열의 다음 셀부터 마지막 셀까지 차지하도록 합니다.
	      
	      Gbag.setConstraints(c, gbc1); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치
	 
	      add(c);

	   }

	public void setI(int num) { i = num; }
	public void setJ(int num) { j = num; }
	
	public void writeGifticon(Date date,int due) {
		
		lblMake = new JLabel("카페라떼          " +date+"                         "+due);
        lblMake.setBounds(10, 80+30*i, 480, 30);
        lblMake.setFont(fnt);
        //create_form2(lblMake,10,80+30*i,480,30);
        gifticonList.add(lblMake);
       
        i++;
	}
	
	public void writeReservation(String name, Date date1,Date date2, int num) {
		
		lblMake = new JLabel(name+ "    " +date1+"        "+date2+"     "+num);
        lblMake.setBounds(10, 80+30*j, 480, 30);
        lblMake.setFont(fnt);
        //create_form2(lblMake,10,80+30*i,480,30);
        gifticonList.add(lblMake);
       
        j++;
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
	
   public void readReserveDB() {
		   
	     String sql = "select * from reserve2";	  
	     int num =3;
	     
	     connectDB();
	     
	     try {
	    	 pstmt = conn.prepareStatement(sql);

		     rs = pstmt.executeQuery();

	         while (rs.next()) {
	            if (strId.equals(rs.getString("id"))) {
	               
	               lblMake = new JLabel(rs.getString("stayname")+"         "+rs.getString("roomName")+"             "+rs.getDate("firstday")+"                "+rs.getDate("lastday")+"                          "+rs.getInt("totalnum"));
	               lblMake.setBounds(10, 70, 480, 80+30*j);
	               lblMake.setFont(fnt);
	               gbAdd(lblMake,0,num,1,1);
	               reservationList.add(lblMake);
		           num++;
	               j++;
	               
	               setJ(j);
	            }
	         }
	     } catch (SQLException e) {
	    	 	e.printStackTrace();
	     	}
		      closeDB();
		   }
   public void readGifticonDB() {
	   
	     String sql = "select * from gifticon";
		      
	     int i=0,num=3;
		     
	     connectDB();
	     
	     try {
	    	 pstmt = conn.prepareStatement(sql);

		     rs = pstmt.executeQuery();

	         while (rs.next()) {
	            if (strId.equals(rs.getString("id"))) {
	               
	               lblMake = new JLabel("카페라떼                          " +rs.getDate("due")+"                                                "+rs.getInt("num"));
	               lblMake.setBounds(10, 70, 480, 80+30*i);
	               lblMake.setFont(fnt);
	               gbAdd(lblMake,0,num,1,1);
	               gifticonList.add(lblMake);
	               i++;
	               num++;
	               setI(i);
	            }
	         }
	     } catch (SQLException e) {
	    	 	e.printStackTrace();
	     	}
		      closeDB();
		   }
}