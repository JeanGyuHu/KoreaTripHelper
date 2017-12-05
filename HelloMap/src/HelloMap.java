import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class HelloMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("È¥ÀÚ°¡´Ï?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1000, 1000));
		frame.setResizable(false);
		frame.setBackground(Color.WHITE);

		PrimaryPanel startMap = new PrimaryPanel(frame);
		frame.getContentPane().add(startMap);

		frame.pack();
		frame.setVisible(true);
	}

}
