import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WelcomePanel extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	public WelcomePanel() {
		this.setTitle("Skypeartawe");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		loadPanel();
	}

	private void loadPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 190));
		panel.setSize(this.getWidth(), this.getHeight());
		panel.setLayout(null);

		JLabel title = new JLabel("Skypeartwe");
		JButton loginBtn = new JButton("Login");
		JButton regBtn = new JButton("Register");

		title.setFont(new Font("Arial_Thin", Font.PLAIN, 50));
		title.setForeground(new Color(255,255,255,255));
		title.setBounds((this.getWidth() / 3 + 20), 150, this.getWidth(), 55);

		loginBtn.setBounds((this.getWidth() / 3), this.getHeight() / 3, 150, 80);
		regBtn.setBounds((this.getWidth() / 3 + 160), this.getHeight() / 3, 150, 80);

		panel.add(title);
		panel.add(loginBtn);
		panel.add(regBtn);
		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
