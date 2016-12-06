import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final int M_FRAME_WIDTH = 1200;
	private final int M_FRAME_HEIGHT = 750;;
	
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
	
	public int getFrameWidth() {
		return M_FRAME_WIDTH;
	}
	
	public int getFrameHeight() {
		return M_FRAME_HEIGHT;
	}
	
	public static void main(String[] args) {
		new WelcomePanel();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
