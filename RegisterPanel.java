/**
 * @file RegisterPanel.java
 * @author Luke Harvey (869608)
 * @date 11th dec 2016
 * @see AccountValidation.java
 * @see AccountsGraph.java
 * @see Account.java
 *
 * This class is used to create a register form for
 * the user to create an account for the program which
 * will then be used to login from.
 *
 */

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Arrays;

public class RegisterPanel extends JFrame {
    private String m_username;
    private String m_firstname;
    private String m_lastname;
    private String m_phoneNumber;
    private String m_password;
    private String m_confirm;
    private String m_dob;
    private String m_city;
    private AccountsGraph graph;

    /**
     * This constructor is used to set the values of the JFrame
     * and then loadAssets which creates the JPanel and JComponents
     * that are going to be displayed on the screen
     * @param graph
     */
    public RegisterPanel(AccountsGraph graph) {
        this.setTitle("Skypertawe - Register");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.graph = graph;
        loadAssets();
    }

    /**
     * This loads all the JComponents on the screen
     * and positions them as well as defining actions
     * on buttons.
     */
    private void loadAssets() {
        JPanel panel = new JPanel(null);
        panel.setSize(this.getWidth(), this.getHeight());

        JTextField username = new JTextField();
        JTextField firstname = new JTextField();
        JTextField lastname = new JTextField();
        JTextField phoneNumber = new JTextField();
        JPasswordField password = new JPasswordField();
        JPasswordField confirm = new JPasswordField();
        JTextField dob = new JTextField();
        JTextField city = new JTextField();

        JButton submitBtn = new JButton("Submit");
        JButton cancelBtn = new JButton("Cancel");

        JLabel textUsername = new JLabel("*Username: ");
        JLabel textFirstName = new JLabel("*Firstname: ");
        JLabel textLastname = new JLabel("*Lastname: ");
        JLabel textPhoneNumber = new JLabel("*Phone Number: ");
        JLabel textPassword = new JLabel("*Password ");
        JLabel textConfirm= new JLabel("*Confirm: ");
        JLabel textDOB = new JLabel("City: ");
        JLabel textCity = new JLabel("Birthday:");

        username.setBounds(textUsername.getX() + 110, 10, 200, 25);
        firstname.setBounds(textFirstName.getX() + 110, 40, 200, 25);
        lastname.setBounds(textLastname.getX() + 110, 70, 200, 25);
        phoneNumber.setBounds(textPhoneNumber.getX() + 110, 100, 200, 25);
        password.setBounds(textPassword.getX() + 110, 130, 200, 25);
        confirm.setBounds(textConfirm.getX() + 110, 160, 200, 25);
        dob.setBounds(textDOB.getX() + 110, 210, 200, 25);
        city.setBounds(textCity.getX() + 110, 240, 200, 25);

        textUsername.setBounds(10, username.getY(), 200, 25);
        textFirstName.setBounds(10, firstname.getY(), 200, 25);
        textLastname.setBounds(10, lastname.getY(), 200, 25);
        textPhoneNumber.setBounds(10, phoneNumber.getY(), 200, 25);
        textPassword.setBounds(10, password.getY(), 200, 25);
        textConfirm.setBounds(10, confirm.getY(), 200, 25);
        textDOB.setBounds(10, dob.getY(), 200, 25);
        textCity.setBounds(10, city.getY(), 200, 25);

        submitBtn.setBounds(this.getWidth() / 5, textCity.getY() + 50, 150, 50);
        cancelBtn.setBounds(this.getWidth() / 5 + 160, submitBtn.getY(), 150, 50);

        submitBtn.setFont(new Font("Eras Light IT", Font.PLAIN, 20));
        submitBtn.setForeground(Color.black);
        submitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitBtn.setFocusPainted(false);

        cancelBtn.setFont(new Font("Eras Light IT", Font.PLAIN, 15));
        cancelBtn.setForeground(Color.black);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelBtn.setFocusPainted(false);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_username = username.getText();
                m_firstname = firstname.getText();
                m_lastname = lastname.getText();
                m_phoneNumber = phoneNumber.getText();
                m_password = Arrays.toString(password.getPassword());
                m_confirm = Arrays.toString(confirm.getPassword());
                m_city = city.getText();
                m_dob = dob.getText();

                if(checkFormValidation()) {
                    AccountValidation account = new AccountValidation(m_username);
                    if(account.checkRegister()) {
                        submitForm();
                    }
                    else {
                        JOptionPane.showMessageDialog(panel, "That account is already taken!");
                    }
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelForm();
            }
        });

        panel.add(username);
        panel.add(password);
        panel.add(confirm);
        panel.add(firstname);
        panel.add(lastname);
        panel.add(phoneNumber);
        panel.add(dob);
        panel.add(city);
        panel.add(textUsername);
        panel.add(textFirstName);
        panel.add(textLastname);
        panel.add(textPhoneNumber);
        panel.add(textPassword);
        panel.add(textConfirm);
        panel.add(textDOB);
        panel.add(textCity);
        panel.add(submitBtn);
        panel.add(cancelBtn);

        this.add(panel);
        this.setVisible(true);
    }

    /**
     * This is a basic form of front-line validation which checks
     * the most basic type of errors such as empty fields.
     * @return True if the form is valid, false otherwise
     */
    private boolean checkFormValidation() {
        boolean m_isValid = false;
        if(m_username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields with an * by it.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(m_firstname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields with an * by it.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(m_lastname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields with an * by it.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(m_phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields with an * by it.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(m_password.toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields with an * by it.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(m_confirm.toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields with an * by it.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if(m_username.length() <= 5) {
                JOptionPane.showMessageDialog(this, "Username must be longer then 5 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(m_phoneNumber.length() != 11) {
                JOptionPane.showMessageDialog(this, "Please enter a valid UK phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(m_password.toString().length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be longer then 5 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(!m_password.equals(m_confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                m_isValid = true;
            }
        }
        return m_isValid;
    }

    /**
     * This method is used to submit the form
     * after the basic validation has returned true
     */
    private void submitForm() {
        Account newAccount;
        newAccount = new Account(m_username,
                m_firstname,
                m_lastname,
                "", //Add profile pic path here
                m_dob,
                m_city,
                m_password,
                m_phoneNumber);
        graph.addAccount(newAccount);
    }

    /**
     * This method is called once the cancel button is pressed
     */
    private void cancelForm() {
        new WelcomePanel(graph);
        this.dispose();
    }
}