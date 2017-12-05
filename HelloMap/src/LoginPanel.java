import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends JPanel {

   private ImageIcon icon;
   private JButton btnLogin;
   private JButton btnMakeId;
   private JButton btnLoginCondition;
   private JTextField txtUserId;
   private JPasswordField txtUserPassword;
   private JLabel lbltitleName;
   private JLabel lblId;
   private JLabel lblPassword;
   private JLabel lblLoginCondition;
   private JLabel lblThanksTo;
   private ButtonListener btnL;
   private JPanel LoginCondition;
   private MakeUser makeUser;
   private String jdbcUrl = "jdbc:mysql://localhost/javadb";
   private String jdbcDriver = "com.mysql.jdbc.Driver";
   private String strUser = "root";
   private String strPassword = "123123";
   private Connection conn;
   private PrimaryPanel primary;
   private PreparedStatement pstmt;
   private ResultSet rs;
   private String saveId;
   private String saveName;
   
   public LoginPanel(PrimaryPanel p) {

      primary = p;
      setBounds(50, 50, 900, 900);
      setBackground(Color.white);
      setLayout(null);

      lbltitleName = new JLabel();
      lblId = new JLabel();
      lblPassword = new JLabel();
      lblLoginCondition = new JLabel();
      txtUserId = new JTextField();
      txtUserPassword = new JPasswordField();
      btnLogin = new JButton("로그인");
      btnMakeId = new JButton("회원가입");
      btnLoginCondition = new JButton("x");
      LoginCondition = new JPanel();
      makeUser = new MakeUser();
      btnL = new ButtonListener();
      icon = new ImageIcon("Images/서울 5.jpg");

      lbltitleName.setFont(new Font("야놀자 야체 R", Font.PLAIN, 120));
      lbltitleName.setText("혼자 가니?");
      lbltitleName.setBounds(30, 100, 870, 150);
      lbltitleName.setHorizontalAlignment(SwingConstants.CENTER);

      Font fnt = new Font("야놀자 야체 R", Font.PLAIN, 20);

      lblThanksTo = new JLabel("Thanks to 이민영씨");
      lblThanksTo.setBounds(800,915,150,30);
      lblThanksTo.setFont(fnt);
      add(lblThanksTo);
      
      lblId.setText("아이디");
      lblId.setFont(fnt);
      lblId.setBounds(500, 400, 100, 30);
      lblId.setHorizontalAlignment(SwingConstants.CENTER);

      lblPassword.setText("비밀번호");
      lblPassword.setFont(fnt);
      lblPassword.setBounds(500, 440, 100, 30);
      lblPassword.setHorizontalAlignment(SwingConstants.CENTER);

      lblLoginCondition.setFont(new Font("야놀자 야체 R", Font.PLAIN, 18));
      lblLoginCondition.setText("비밀번호가 틀렸습니다 ");
      lblLoginCondition.setBounds(10, 40, 180, 80);

      txtUserId.setBounds(610, 400, 100, 30);
      txtUserId.setDocument(new JTextFieldLimit(12));
      txtUserPassword.setBounds(610, 440, 100, 30);
      txtUserPassword.setDocument(new JTextFieldLimit(12));
      txtUserPassword.setEchoChar('*');
      txtUserPassword.addActionListener(btnL);
      
      btnLogin.setText("Login");
      btnLogin.setBackground(Color.white);
      btnLogin.setFont(new Font("야놀자 야체 R",Font.PLAIN,16));
      btnLogin.setBounds(720, 400, 80, 70);
      

      btnMakeId.setText("회원가입");
      btnMakeId.setBackground(Color.white);
      btnMakeId.setFont(new Font("야놀자 야체 R",Font.PLAIN,16));
      btnMakeId.setBounds(610, 480, 100, 30);

      btnLoginCondition.setBounds(155, 10, 40, 30);
      btnLoginCondition.setFont(new Font("야놀자 야체 R", Font.PLAIN, 14));

      btnLogin.addActionListener(btnL);
      btnMakeId.addActionListener(btnL);
      btnLoginCondition.addActionListener(btnL);

      LoginCondition.setBounds(300, 400, 200, 130);
      LoginCondition.setLayout(null);
      LoginCondition.add(lblLoginCondition);
      LoginCondition.add(btnLoginCondition);
      LoginCondition.setVisible(false);

      add(makeUser);
      add(LoginCondition);
      add(btnLogin);
      add(btnMakeId);
      add(txtUserId);
      add(txtUserPassword);
      add(lblPassword);
      add(lblId);
      add(lbltitleName);
   }// MainFrame()

   public void paintComponent(Graphics page) {
      super.paintComponent(page);
      page.drawImage(icon.getImage(), 50, 50, 900, 900, this);
   }
   
   public String getId()    {   return saveId;    }
   public String getName()  {   return saveName;  }
   
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

   private class ButtonListener implements ActionListener {
      private String getTextValue;

      public void actionPerformed(ActionEvent event) {
         Object obj = event.getSource();
         getTextValue = new String();
         if (obj == btnLogin || obj == txtUserPassword) {
            if ((getTextValue = txtUserId.getText()) != null && getTextValue.equals("")) {
               lblLoginCondition.setText("아이디를 입력해주세요");
               LoginCondition.setVisible(true);
            } else if ((getTextValue = txtUserPassword.getText()) != null && getTextValue.equals("")) {
               lblLoginCondition.setText("비밀번호를 입력해주세요");
               LoginCondition.setVisible(true);
            } else {

               try {
                  if (!checkUser(txtUserId.getText())) {
                     txtUserId.setText("");
                     txtUserPassword.setText("");   
                     lblLoginCondition.setText("존재하지 않는 회원입니다");
                     LoginCondition.setVisible(true);
                  } else if (checkUser(txtUserId.getText())) {
                     if (!checkPassword(txtUserPassword.getText())) {
                        lblLoginCondition.setText("비밀번호가 틀렸습니다");
                        txtUserPassword.setText("");
                        LoginCondition.setVisible(true);
                     } else {
                        saveId = txtUserId.getText();
                        txtUserId.setText("");
                        txtUserPassword.setText("");
                        primary.setVisibleWholeMap();
                        
                     }
                  }
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
         } else if (obj == btnMakeId) {
            makeUser.setVisible(true);
         } else if (obj == btnLoginCondition) {
            LoginCondition.setVisible(false);
         }
      }

      private boolean checkUser(String userId) throws IOException {
         String savedId = new String();
         boolean check = false;
         String sql = "select * from member";
         connectDB();
         try {
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
               savedId = rs.getString("id");
               if (userId.equals(savedId))
                  check = true;
            }

         } catch (SQLException e) {
            e.printStackTrace();
         }

         return check;
      }

      private boolean checkPassword(String userPassword) throws IOException {
         String savedPassword = new String();
         String savedId = new String();
         boolean check = false;
         
         String sql = "select * from member";

         try {

            connectDB();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            savedId = txtUserId.getText();
            
            while (rs.next()) {
               if(savedId.equals(rs.getString("id"))) {
                  savedPassword = rs.getString("passward");
                  if (userPassword.equals(savedPassword)) {
                     saveName = rs.getString("username");
                     check = true;
                  }
               }
            }
               
         } catch (SQLException e) {
            e.printStackTrace();
         }

         closeDB();

         return check;
      }
   }
}