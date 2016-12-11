/**
 * @file LoginPanel.java
 * @author Luke Harvey (869608)
 * @date 11th dec 2016
 * @see MainWindow.java
 * @see AccountValidation.java
 * @see Account.java
 *
 * This is the panel that loads when the user selects the
 * login button from the front page. It contains validation and
 * actions on the login.
 */

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginPanel extends JFrame {
    private String m_username;
    private String m_password;
    private AccountsGraph graph;

    /**
     * This constructor sets the JFrame values
     * and loads the assets on to the JFrame
     * @param graph
     */
    public LoginPanel(AccountsGraph graph) {
        this.setTitle("Skypertawe - Login");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.graph = graph;
        loadAssets();
    }

    /**
     * This loads all the components on to the
     * JFrame and sets actions on the button
     */
    private void loadAssets() {
        JPanel panel = new JPanel();
        panel.setSize(this.getWidth(), this.getHeight());
        panel.setLayout(null);

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        JLabel usernameText = new JLabel("Username: ");
        JLabel passwordText = new JLabel("Password: ");

        JButton loginBtn = new JButton("Login");
        JButton cancelBtn = new JButton("Cancel");

        usernameText.setBounds(this.getWidth() / 5 + 20, this.getHeight() / 5, 120, 25);
        passwordText.setBounds(usernameText.getX(), usernameText.getY() + 30, 110, 25);
        username.setBounds(usernameText.getX() + 100, usernameText.getY(), 150, 25);
        password.setBounds(passwordText.getX() + 100, passwordText.getY(), 150, 25);
        loginBtn.setBounds(passwordText.getX(), passwordText.getY() + 30, 150, 50);
        cancelBtn.setBounds(loginBtn.getX() + 160, loginBtn.getY(), 150, 50);

        usernameText.setFont(new Font("Eras Light IT", Font.PLAIN, 15));
        passwordText.setFont(new Font("Eras Light IT", Font.PLAIN, 15));

        loginBtn.setFont(new Font("Eras Light IT", Font.PLAIN, 20));
        loginBtn.setForeground(Color.black);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.setFocusPainted(false);

        cancelBtn.setFont(new Font("Eras Light IT", Font.PLAIN, 15));
        cancelBtn.setForeground(Color.black);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelBtn.setFocusPainted(false);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_username = username.getText();
                char[] passwordArray = password.getPassword();
                m_password = "";
                for (char c : passwordArray) {
                    m_password += c;
                }
                tryLogin();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelForm();
            }
        });

        password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tryLogin();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        panel.add(usernameText);
        panel.add(passwordText);
        panel.add(username);
        panel.add(password);
        panel.add(loginBtn);
        panel.add(cancelBtn);
        this.add(panel);
        this.setVisible(true);
    }

    /**
     * This method is used to check account validation
     * and execute certain actions based on the results
     */
    private void tryLogin() {
        AccountValidation account = new AccountValidation(m_username, m_password);
        if(account.checkLogin()) {
            submitForm();
        }
        else {
            JOptionPane.showMessageDialog(this, "Account credentials unknown.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is executed when the
     * submit button is pressed which
     * loads the main window
     */
    private void submitForm() {
        Account user = graph.findAccount(m_username);
        new MainWindow(graph, user);
        this.dispose();
    }

    /**
     * This method is executed when the
     * cancel button is pressed
     */
    private void cancelForm() {
        new WelcomePanel(graph);
        this.dispose();
    }
}
