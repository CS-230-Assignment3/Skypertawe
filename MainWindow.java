import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
	public MainWindow() {
		this.setTitle("Skypertawe - Login");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		loadAssets();
	}

	private void loadAssets() {

	}

	public static void main(String[] args) {
		new WelcomePanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {



	}
}

