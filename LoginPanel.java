import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginPanel extends JFrame {
    private String m_username;
    private String m_password;
    private AccountsGraph graph;

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

    public void loadAssets() {
        JPanel panel = new JPanel();
        panel.setSize(this.getWidth(), this.getHeight());
        panel.setLayout(null);

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        JLabel usernameText = new JLabel("Username: ");
        JLabel passwordText = new JLabel("Password: ");

        JButton loginBtn = new JButton("Login");
        JButton cancelBtn = new JButton("Cancel");

        usernameText.setBounds(this.getWidth() / 5 + 20, this.getHeight() / 5, 150, 25);
        passwordText.setBounds(usernameText.getX(), usernameText.getY() + 30, 150, 25);
        username.setBounds(usernameText.getX() + 100, usernameText.getY(), 150, 25);
        password.setBounds(passwordText.getX() + 100, passwordText.getY(), 150, 25);
        loginBtn.setBounds(passwordText.getX(), passwordText.getY() + 30, 150, 50);
        cancelBtn.setBounds(loginBtn.getX() + 160, loginBtn.getY(), 150, 50);

        usernameText.setFont(new Font("Eras Light IT", Font.PLAIN, 15));
        passwordText.setFont(new Font("Eras Light IT", Font.PLAIN, 15));

        loginBtn.setFont(new Font("Eras Light IT", Font.PLAIN, 20));
        loginBtn.setForeground(Color.black);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelBtn.setFont(new Font("Eras Light IT", Font.PLAIN, 20));
        cancelBtn.setForeground(Color.black);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_username = username.getText();
                m_password = Arrays.toString(password.getPassword());

                AccountValidation account = new AccountValidation(m_username, m_password);
                if(account.checkLogin()) {
                    submitForm();
                }
                else {
                    JOptionPane.showMessageDialog(panel, "Account credentials unknown.");
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelForm();
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

    private void submitForm() {
        new MainWindow(graph);
    }

    private void cancelForm() {
        new WelcomePanel(graph);
        this.dispose();
    }
}
