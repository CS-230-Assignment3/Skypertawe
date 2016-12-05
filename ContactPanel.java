import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;

public class ContactPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactPanel frame = new ContactPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public ContactPanel() throws Exception {
		String date = "12/12/12" ;
		String numOfMessages = "12";

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(10, 0, 0, 0));
			
		ArrayList<String> list = ReadContactFile.contactReader();
		
		for(int i = 0; i < list.size(); i++) {
			JButton Ai = new JButton(list.get(i));
			Ai.setBackground(Color.BLACK);
			Ai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Ai.setForeground(Color.WHITE);
				}
			});
			JLabel Bi = new JLabel(date);
			contentPane.add(Bi);
			JLabel Ci = new JLabel(numOfMessages);
			contentPane.add(Ci);
			
			contentPane.add(Ai);
			System.out.println(list.get(i));
		}	
		
		
	
	} 
	
	
}
