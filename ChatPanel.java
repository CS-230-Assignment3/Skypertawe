/**
 * @file ChatPanel.java
 * @author Luke Harvey
 * @date 11th dec 2016
 *
 * This class loads up the chat panel when
 * the user clicks on one of their contacts
 */

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ChatPanel extends JFrame {
    private Account currentAccount;
    private Account otherAccount;
    private MessageHistory chatHistory;
    private ArrayList<Account> otherAccounts;

    /**
     * This constructor is used to set values for a
     * single peer-to-peer chat
     * @param currentAccount The person sending the message
     * @param otherAccount The person receiving the message
     */
    public ChatPanel(Account currentAccount, Account otherAccount) {
        this.setTitle("Skypertawe Chat - " + otherAccount.getUser());
        this.setSize(800, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.currentAccount = currentAccount;
        this.otherAccount = otherAccount;
        chatHistory = new MessageHistory(currentAccount, otherAccount);
        loadAssets();
    }

    /**
     * This constructor is used to ser values for a
     * group chat
     * @param currentAccount The person sending the message
     * @param otherAccounts The people receiving the message
     */
    public ChatPanel(Account currentAccount,  ArrayList<Account> otherAccounts) {
        this.setTitle("Skypertawe Chat - " + otherAccounts);
        this.setSize(800, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.currentAccount = currentAccount;
        this.otherAccounts = otherAccounts;
        chatHistory = new MessageHistory(currentAccount, otherAccounts);
        loadAssets();
    }

    /**
     * This is used to load components on to the
     * panel and set actions for the buttons
     */
    private void loadAssets() {
        JPanel panel = new JPanel(null);
        panel.setSize(this.getWidth(), this.getHeight());

        JTextArea textArea = new JTextArea();
        JScrollPane chatPane = new JScrollPane(textArea);
        JTextField chatField = new JTextField();
        JButton sendBtn = new JButton("Send");

        textArea.setEnabled(false);
        textArea.setDisabledTextColor(new Color(0, 0, 0,255));
        textArea.setFont(new Font("Arial", Font.BOLD, 15));

        chatPane.setSize(new Dimension(this.getWidth(), this.getHeight() - 150));
        chatPane.setBackground(new Color(255,255,255,255));

        chatField.setBounds(0, chatPane.getHeight(), this.getWidth() - 150, 40);
        chatField.setFont(new Font("Arial", Font.PLAIN, 15));

        sendBtn.setBounds(chatField.getWidth(), chatField.getY(), 150, 40);

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(textArea, chatField);
            }
        });

        chatField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage(textArea, chatField);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        panel.add(sendBtn);
        panel.add(chatField);
        panel.add(chatPane);
        this.add(panel);
        this.setVisible(true);
    }

    /**
     * This method is used to structure how the messages
     * are sent into the chat.
     * @param text The area that will display the message
     * @param message The message that the user has typed into the field
     */
    private void sendMessage(JTextArea text, JTextField message) {
        text.append(currentAccount.getUser() + ": " + message.getText() + "\n");
        chatHistory.writeToFile(message.getText());
        message.setText("");
    }
}
