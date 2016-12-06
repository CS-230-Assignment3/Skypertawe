import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	protected final int M_FRAME_WIDTH = 1200;
	protected final int M_FRAME_HEIGHT = 750;
	
	public MainWindow() {
		this.setTitle("Skypertawe version 1.0.0");
		this.setSize(M_FRAME_WIDTH, M_FRAME_HEIGHT);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}
}
