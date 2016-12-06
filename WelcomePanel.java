import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends MainWindow {
	private static final long serialVersionUID = 1L;

	public WelcomePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Skypeartwe");
		JButton loginBtn = new JButton("Login");
		JButton regBtn = new JButton("Register");
		
		title.setFont(new Font("Arial_Thin", Font.PLAIN, 30));
		
		loginBtn.setSize(new Dimension(250, 250));
		loginBtn.setAlignmentY(CENTER_ALIGNMENT);
		regBtn.setSize(new Dimension(250, 250));
		regBtn.setAlignmentY(CENTER_ALIGNMENT);
		
		panel.add(title);
		panel.add(loginBtn);
		panel.add(regBtn);
		
		super.add(panel);
		super.validate();
	}
}
