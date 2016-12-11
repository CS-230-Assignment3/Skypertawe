/**
 * @file WelcomePanel.java
 * @author Luke Harvey (869608)
 * @date 11th dec 2016
 * @see AccountsGraph.java
 * @see Main.java Where the file is loaded from
 *
 * This class is the first GUI interface that the
 * user sees and has two buttons which will either
 * take the user to the login screen or the register screen
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WelcomePanel extends JFrame {
	private AccountsGraph graph;

	/**
	 * This constructor sets the values for the JFrame
	 * and loads the assets on to the panel
	 * @param graph The accounts
	 */
	public WelcomePanel(AccountsGraph graph) {
		this.setTitle("Skypertawe Version 1.0.0");
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
		panel.setBackground(new Color(17, 80, 120,255));
		panel.setLayout(null);

		JLabel title = new JLabel("Skypertawe");
		JLabel versionNo = new JLabel("Version 1.0.0");
		JButton loginBtn = new JButton("Login");
		JButton regBtn = new JButton("Register");

		title.setBounds(this.getWidth() / 5 + 70, 150, this.getWidth(), 130);
		title.setFont(new Font("Eras Light ITC", Font.PLAIN, 100));
		title.setForeground(Color.white);

		versionNo.setBounds(this.getWidth() / 3 + 100, this.getHeight() / 3 + 10, 200, 50);
		versionNo.setFont(new Font("Eras Light ITC", Font.PLAIN, 30));
		versionNo.setForeground(Color.white);

		loginBtn.setBounds((this.getWidth() / 3), title.getY() + 170, 160, 70);
		loginBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginBtn.setBackground(new Color(33, 33, 33,255));
		loginBtn.setForeground(new Color(255,255,255,255));
		loginBtn.setFocusPainted(false);
		loginBtn.setBorderPainted(false);

		regBtn.setBounds((this.getWidth() / 3 + 190), loginBtn.getY(), 160, 70);
		regBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		regBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		regBtn.setBackground(new Color(41,157,2,255));
		regBtn.setForeground(new Color(255,255,255,255));
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

		loginBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setBackground(new Color(33, 33, 33).brighter());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setBackground(new Color(33, 33, 33,255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		regBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				regBtn.setBackground(new Color(41,157,2,255).brighter());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				regBtn.setBackground(new Color(41,157,2,255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		panel.add(title);
		panel.add(versionNo);
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
