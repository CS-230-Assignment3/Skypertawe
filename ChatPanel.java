import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JFrame {
    private AccountsGraph graph;

    public ChatPanel(AccountsGraph graph) {
        this.setTitle("Skypertawe - Chat");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.graph = graph;
        loadAssets();
    }

    private void loadAssets() {
        JPanel panel = new JPanel(null);
        panel.setSize(this.getWidth(), this.getHeight());

        JTextField chatArea = new JTextField();
        JScrollPane chat = new JScrollPane(chatArea);
        JButton sendMsg = new JButton("Send");

        chat.setBounds(20, 0, this.getWidth() - 40, this.getHeight() - 200);
        chatArea.setBounds(0, chat.getHeight(), this.getWidth(), 30);
        sendMsg.setBounds(this.getWidth() / 3, chatArea.getY() + 30, 150, 50);

        chatArea.setFont(new Font("Arial", Font.PLAIN, 20));

        sendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!chatArea.getText().isEmpty()) {
                    chat.add(chatArea.getText());
                    chatArea.setText("");
                }
            }
        });

        panel.add(chatArea);
        panel.add(sendMsg);
        panel.add(chat);
        this.add(panel);
        this.setVisible(true);
    }
}
