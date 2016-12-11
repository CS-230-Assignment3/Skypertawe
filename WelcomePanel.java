/**
 * @file WelcomePanel.java
 * @author Luke Harvey
 * @date 11th dec 2016
 *
 * This class is the first GUI interface that the
 * user sees and has two buttons which will either
 * take the user to the login screen or the register screen
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WelcomePanel extends JFrame {
	private AccountsGraph graph;

	/**
	 * This constructor sets the values for the JFrame
	 * and loads the assets on to the panel
	 * @param graph The accounts
	 */
	public WelcomePanel(AccountsGraph graph) {
		this.setTitle("Skypertawe");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.graph = graph;
		loadAssets();
	}

	/**
	 * Loads all JComponents on to the panel and
	 * sets actions for the buttons
	 */
	private void loadAssets() {
		JPanel panel = new JPanel();
		panel.setSize(this.getWidth(), this.getHeight());
		panel.setBackground(new Color(42, 100,255,255));
		panel.setLayout(null);

		JLabel title = new JLabel("Skypertawe");
		JButton loginBtn = new JButton("Login");
		JButton regBtn = new JButton("Register");

		title.setFont(new Font("Bauhaus 93", Font.PLAIN, 65));
		title.setForeground(Color.white);
		title.setBounds(this.getWidth() / 3, 150, this.getWidth(), 70);

		loginBtn.setBounds((this.getWidth() / 3), this.getHeight() / 3, 150, 80);
		loginBtn.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginBtn.setFocusPainted(false);
		loginBtn.setBorderPainted(false);

		regBtn.setBounds((this.getWidth() / 3 + 190), this.getHeight() / 3, 150, 80);
		regBtn.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		regBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		regBtn.setBorderPainted(false);
		regBtn.setFocusPainted(false);

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
		this.setVisible(true);
	}

	/**
	 * This is a method executed when
	 * the user presses the login button
	 */
	private void loadLogin() {
		new LoginPanel(graph);
		this.dispose();
	}

	/**
	 * This is a method executed when
	 * the user presses the register button
	 */
	private void loadRegister() {
		new RegisterPanel(graph);
		this.dispose();
	}
}
