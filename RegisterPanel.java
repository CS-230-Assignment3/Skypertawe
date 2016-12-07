import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JFrame implements ActionListener {
    public RegisterPanel() {
        this.setTitle("Skypertawe - Register");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        loadAssets();
    }

    private void loadAssets() {
        JPanel panel = new JPanel();
        panel.setSize(this.getWidth(), this.getHeight());
        panel.setLayout(null);

        JTextField usrName = new JTextField();
        JTextField password = new JTextField();
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField phoneNumber = new JTextField();
        JTextField dob = new JTextField();
        JTextField city = new JTextField();

        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");

        JLabel textOne = new JLabel("Username: ");
        JLabel textTwo = new JLabel("Password: ");
        JLabel textThree = new JLabel("Firstname: ");
        JLabel textFour = new JLabel("Lastname: ");
        JLabel textFive = new JLabel("Phone Number: ");
        JLabel textSix = new JLabel("Date of Birth: ");
        JLabel textSeven = new JLabel("City: ");

        submit.setBounds(50, 50, 150, 150);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelForm();
            }
        });

        panel.add(usrName);
        panel.add(password);
        panel.add(firstName);
        panel.add(lastName);
        panel.add(phoneNumber);
        panel.add(dob);
        panel.add(city);
        panel.add(textOne);
        panel.add(textTwo);
        panel.add(textThree);
        panel.add(textFour);
        panel.add(textFive);
        panel.add(textSix);
        panel.add(textSeven);

        this.add(panel);
    }

    private void submitForm() {
        //Validation here
        //Oh and submit the form
    }

    private void cancelForm() {
        new WelcomePanel();
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
