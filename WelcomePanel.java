import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WelcomePanel extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	public WelcomePanel() {
		this.setTitle("Skypertawe");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		loadAssets();
	}

	private void loadAssets() {
		JPanel panel = new JPanel();
		panel.setSize(this.getWidth(), this.getHeight());
		panel.setLayout(null);

		JLabel title = new JLabel("Skypertawe");
		JButton loginBtn = new JButton("Login");
		JButton regBtn = new JButton("Register");

		title.setFont(new Font("Arial_Thin", Font.PLAIN, 50));
		title.setBounds((this.getWidth() / 3 + 20), 150, this.getWidth(), 55);

		loginBtn.setBounds((this.getWidth() / 3), this.getHeight() / 3, 150, 80);
		regBtn.setBounds((this.getWidth() / 3 + 160), this.getHeight() / 3, 150, 80);

		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadLogin();
			}
		});

		regBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadRegister();
			}
		});

		panel.add(title);
		panel.add(loginBtn);
		panel.add(regBtn);
		this.add(panel);
	}

	private void loadLogin() {
		new LoginPanel();
		this.dispose();
	}

	private void loadRegister() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
