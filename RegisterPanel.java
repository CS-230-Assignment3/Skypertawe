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


    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
