import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MakeUser extends JPanel {
   private JLabel lbluserId;
   private JLabel lbluserPassword;
   private JLabel lbluserPasswordCheck;
   private JLabel lbluserName;
   private JLabel lblresult;
   private JTextField txtuserId;
   private JPasswordField txtuserPassword;
   private JPasswordField txtuserPasswordCheck;
   private JTextField txtuserName;
   private JButton btnOverlapCheck;
   private JButton btnSubmit;
   private JButton btnClose;
   private JButton btnCheckClose;
   private MakeUserListener btnL;
   private JPanel checkIdPass;
   private boolean OverlapCheck;
   private String userId;
   private String jdbcUrl = "jdbc:mysql://localhost/javadb";
   private String jdbcDriver = "com.mysql.jdbc.Driver";
   private String strUser = "root";
   private String strPassword = "123123";
   private Connection conn;

   private PreparedStatement pstmt;
   private ResultSet rs;

   public MakeUser() {
      setBounds(200, 300, 400, 300);
      setBackground(Color.LIGHT_GRAY);
      setLayout(null);
      setVisible(false);

      lbluserId = new JLabel("아이디");
      lbluserPassword = new JLabel("비밀번호");
      lbluserPasswordCheck = new JLabel("비밀번호재입력");
      lbluserName = new JLabel("이름");
      lblresult = new JLabel("");
      txtuserId = new JTextField();
      txtuserPassword = new JPasswordField();
      txtuserPasswordCheck = new JPasswordField();
      txtuserName = new JTextField();
      btnOverlapCheck = new JButton("중복확인");
      btnSubmit = new JButton("회원가입");
      btnClose = new JButton("x");
      btnCheckClose = new JButton("x");
      btnL = new MakeUserListener();
      checkIdPass = new JPanel();
      Font fnt = new Font("야놀자 야체 R", Font.PLAIN, 18);
      OverlapCheck = false;
      userId = new String();

      lbluserId.setBounds(10, 45, 150, 30);
      lbluserId.setFont(fnt);

      lbluserPassword.setBounds(10, 85, 150, 30);
      lbluserPassword.setFont(fnt);

      lbluserPasswordCheck.setBounds(10, 125, 150, 30);
      lbluserPasswordCheck.setFont(fnt);

      lbluserName.setBounds(10, 165, 150, 30);
      lbluserName.setFont(fnt);

      lblresult.setBounds(10, 50, 180, 70);
      lblresult.setFont(fnt);

      txtuserId.setBounds(170, 45, 120, 30);
      txtuserId.setDocument(new JTextFieldLimit(12));

      txtuserPassword.setBounds(170, 85, 120, 30);
      txtuserPassword.setDocument(new JTextFieldLimit(12));
      txtuserPassword.setEchoChar('*');

      txtuserPasswordCheck.setBounds(170, 125, 120, 30);
      txtuserPasswordCheck.setDocument(new JTextFieldLimit(12));
      txtuserPasswordCheck.setEchoChar('*');

      txtuserName.setBounds(170, 165, 120, 30);
      txtuserName.setDocument(new JTextFieldLimit(8));

      btnOverlapCheck.setBounds(300, 45, 90, 30);
      btnOverlapCheck.setFont(fnt);

      btnSubmit.setBounds(200, 210, 90, 30);
      btnSubmit.setFont(fnt);

      btnClose.setBounds(355, 10, 40, 30);
      btnClose.setFont(new Font("Times", Font.CENTER_BASELINE, 8));

      btnCheckClose.setBounds(155, 10, 40, 30);
      btnCheckClose.setFont(new Font("Times", Font.CENTER_BASELINE, 8));

      btnOverlapCheck.addActionListener(btnL);
      btnSubmit.addActionListener(btnL);
      btnClose.addActionListener(btnL);
      btnCheckClose.addActionListener(btnL);

      checkIdPass.setBounds(30, 30, 200, 120);
      checkIdPass.setLayout(null);
      checkIdPass.setBackground(Color.cyan);
      checkIdPass.add(btnCheckClose);
      checkIdPass.add(lblresult);
      checkIdPass.setVisible(false);

      add(checkIdPass);
      add(txtuserId);
      add(txtuserName);
      add(txtuserPassword);
      add(txtuserPasswordCheck);
      add(btnOverlapCheck);
      add(btnSubmit);
      add(btnClose);
      add(lbluserId);
      add(lbluserName);
      add(lbluserPassword);
      add(lbluserPasswordCheck);

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

   private class MakeUserListener implements ActionListener {

      public void actionPerformed(ActionEvent event) {
         Object obj = event.getSource();

         if (obj == btnOverlapCheck) {
            try {
               if (txtuserId.getText().equals("")) {
                  OverlapCheck = false;
                  lblresult.setText("아이디를 입력하세요");
                  checkIdPass.setVisible(true);
               } else if (!checkUser(txtuserId.getText())) {
                  OverlapCheck = false;
                  txtuserId.setText("");
                  lblresult.setText("이미 있는 아이디입니다");
                  checkIdPass.setVisible(true);
               } else {
                  lblresult.setText("가입 가능한 아이디입니다");
                  OverlapCheck = true;
                  userId = txtuserId.getText();
                  checkIdPass.setVisible(true);
               }
            } catch (IOException e) {
               e.printStackTrace();
            }
         } else if (obj == btnSubmit) {
            if (txtuserId.getText().equals("")) {
               lblresult.setText("아이디를 입력하세요");
               checkIdPass.setVisible(true);
            } else if (txtuserPassword.getText().equals("") || txtuserPasswordCheck.getText().equals("")) {
               lblresult.setText("비밀번호를 입력하세요");
               checkIdPass.setVisible(true);
            } else if (txtuserName.getText().equals("")) {
               lblresult.setText("이름을 입력해주세요");
               checkIdPass.setVisible(true);
            } else if (!txtuserPassword.getText().equals(txtuserPasswordCheck.getText())) {
               txtuserPassword.setText("");
               txtuserPasswordCheck.setText("");
               lblresult.setText("비밀번호가 일치하지 않습니다");
               checkIdPass.setVisible(true);
            } else if (!txtuserId.getText().equals("")) {
               if (!OverlapCheck) {
                  lblresult.setText("아이디 중복체크를 해주세요");
                  checkIdPass.setVisible(true);
               } else if (!txtuserId.getText().equals(userId)) {
                  lblresult.setText("아이디 중복체크를 해주세요");
                  checkIdPass.setVisible(true);
               } else if (txtuserId.getText().equals(userId)) {
                  
            
                  String sql = "insert into member values(?,?,?)";

                  connectDB();

                  try {
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setString(1, txtuserId.getText());
                     pstmt.setString(2, txtuserName.getText());
                     pstmt.setString(3, txtuserPassword.getText());
                     pstmt.executeUpdate();

                  } catch (Exception e) {
                     e.printStackTrace();
                  }

                  txtuserId.setText("");
                  txtuserPassword.setText("");
                  txtuserPasswordCheck.setText("");
                  txtuserName.setText("");
                  
                  closeDB();

                  setVisible(false);
               }
            }

         } else if (obj == btnClose) {
            txtuserId.setText("");
            txtuserName.setText("");
            txtuserPassword.setText("");
            txtuserPasswordCheck.setText("");
            setVisible(false);
         } else if (obj == btnCheckClose) {
            checkIdPass.setVisible(false);
         }
      }

      private boolean checkUser(String userId) throws IOException {
         String savedId = new String();
         boolean check = true;

         String sql = "select * from member";
         connectDB();
         try {
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
               savedId = rs.getString("id");
               if (userId.equals(savedId))
                  check = false;
            }

         } catch (SQLException e) {
            e.printStackTrace();
         }
         closeDB();
         return check;
      }
   }
}