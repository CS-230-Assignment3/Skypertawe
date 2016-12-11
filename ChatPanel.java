/**
 * @file ChatPanel.java
 * @author Luke Harvey (869608)
 * @date 11th dec 2016
 * @see MainWindow.java
 * @see AccountValidation.java
 * @see Account.java
 * @see AccountsGraph.java
 *
 * This class loads up the chat panel when
 * the user clicks on one of their contacts
 */

import javax.swing.*;
import java.awt.*;
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
    private AccountsGraph accountsGraph;
    private boolean groupChat;

    /**
     * This constructor is used to set values for a
     * single peer-to-peer chat
     * @param currentAccount The person sending the message
     * @param otherAccount The person receiving the message
     * @param accountsGraph The graph of all accounts in system
     */
    public ChatPanel(Account currentAccount, Account otherAccount, AccountsGraph accountsGraph) {
        this.setTitle("Skypertawe Chat - " + otherAccount.getUser());
        this.setSize(800, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.currentAccount = currentAccount;
        this.otherAccount = otherAccount;
        this.accountsGraph = accountsGraph;
        chatHistory = new MessageHistory(currentAccount, otherAccount, accountsGraph);
        groupChat = false;
        loadAssets();
    }

    /**
     * This constructor is used to ser values for a
     * group chat
     * @param currentAccount The person sending the message
     * @param otherAccounts The people receiving the message
     * @param accountsGraph The graph of all accounts in system
     */
    public ChatPanel(Account currentAccount,  ArrayList<Account> otherAccounts, AccountsGraph accountsGraph) {
        String title = "";
        for(Account obj : otherAccounts) {
            title = obj.getUser();
        }

        this.setTitle("Skypertawe Chat - " + title);
        this.setSize(800, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.currentAccount = currentAccount;
        this.otherAccounts = otherAccounts;
        this.accountsGraph = accountsGraph;
        chatHistory = new MessageHistory(currentAccount, otherAccounts, accountsGraph);
        groupChat = true;
        loadAssets();
    }

    /**
     * This is used to load components on to the
     * panel and set actions for the buttons
     */
    private void loadAssets() {
        JPanel panel = new JPanel(null);
        panel.setSize(this.getWidth(), this.getHeight());

        loadProfilePics(panel);

        JTextArea textArea = new JTextArea();
        JScrollPane chatPane = new JScrollPane(textArea);
        JTextField chatField = new JTextField();
        JButton sendBtn = new JButton("Send");

        textArea.setEnabled(false);
        textArea.setDisabledTextColor(new Color(0, 0, 0,255));
        textArea.setFont(new Font("Arial", Font.BOLD, 15));

        chatPane.setSize(new Dimension(this.getWidth(), this.getHeight() - 150));
        /*Added/changed this next line to your code, set y = 70*/
        chatPane.setBounds(0,70,this.getWidth(),this.getHeight()-150);
        chatPane.setBackground(new Color(255,255,255,255));
        //Added y + 70
        chatField.setBounds(0, chatPane.getHeight() + 70, this.getWidth() - 150, 40);
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
        loadMessages(textArea);
        panel.add(sendBtn);
        panel.add(chatField);
        panel.add(chatPane);
        this.add(panel);
        this.setVisible(true);
    }

    private void loadProfilePics(JPanel panel) {
        ArrayList<Account> allAccounts = new ArrayList<>();
        allAccounts.add(currentAccount);
        if (groupChat) {
            allAccounts.addAll(otherAccounts);
        } else {
            allAccounts.add(otherAccount);
        }

        ArrayList<JLabel> pictureList = new ArrayList<>();
        ArrayList<JTextArea> descriptionList = new ArrayList<>();

        int count = 0;
        for (Account account : allAccounts) {
            ImageIcon unscaledImage = new ImageIcon(account.getProfilePic());
            ImageIcon scaledImage = new ImageIcon(unscaledImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

            JLabel pictureLabel = new JLabel(scaledImage);
            pictureLabel.setEnabled(true);
            pictureLabel.setSize(new Dimension(50, 50));
            pictureLabel.setBounds(50 * count, 0, 50, 50);
            pictureList.add(pictureLabel);

            JTextArea pictureDescription = new JTextArea();
            pictureDescription.setEnabled(false);
            pictureDescription.setBounds(50 * count, 52, 50, 15);
            pictureDescription.setText(account.getUser());
            pictureDescription.setBackground(new Color(255,255,255,0));
            pictureDescription.setFont(new Font("Arial", Font.PLAIN, 15));

            descriptionList.add(pictureDescription);

            count++;
        }

        for (int i = 0;i<pictureList.size();i++) {
            panel.add(pictureList.get(i));
            panel.add(descriptionList.get(i));
        }
    }

    private void loadMessages(JTextArea text) {
        MessageHistory messageHistory;
        if (groupChat) {
            messageHistory = new MessageHistory(currentAccount, otherAccounts, accountsGraph);
        } else {
            messageHistory = new MessageHistory(currentAccount, otherAccount, accountsGraph);
        }

        ArrayList<Message> messages = messageHistory.readFromFile();

        for (Message currentMessage:messages) {
            text.append(currentMessage.display() + "\n");
        }

    }

    /**
     * This method is used to structure how the messages
     * are sent into the chat.
     * @param text The area that will display the message
     * @param message The message that the user has typed into the field
     */
    private void sendMessage(JTextArea text, JTextField message) {
        MessageText messageText = new MessageText(currentAccount, message.getText());
        text.append(messageText.display() + "\n");
        chatHistory.writeToFile(messageText);
        message.setText("");
    }
}
