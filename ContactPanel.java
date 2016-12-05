
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;


public class ContactPanel extends JFrame {

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String date = "12/12/12" ;
		String numOfMessages = "12";

		ArrayList<String> list = ReadContactFile.contactReader();

		JFrame frame = new JFrame();

		
		JPanel displayContact = new JPanel();
		JLabel contactLbl = new JLabel("Contacts");

		for (int i = 0; i < list.size(); i++) {
			JButton Ai = new JButton(list.get(i));
			displayContact.add(Ai);
			JLabel Bi = new JLabel(numOfMessages);
			displayContact.add(Bi);
			JLabel Ci = new JLabel(date);
			displayContact.add(Ci);
			Ai.setBackground(Color.BLACK);
			Ai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Ai.setForeground(Color.WHITE);
				}
			});
		}
		displayContact.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane(displayContact);
		scrollPane.setBounds(0, 70, 250, 330);
		
		
		JPanel displayGroups = new JPanel();
		JLabel groupLbl = new JLabel("Group");

		for (int i = 0; i < list.size(); i++) {
			JButton Ai = new JButton(list.get(i));
			displayGroups.add(Ai);
			JLabel Bi = new JLabel(numOfMessages);
			displayGroups.add(Bi);
			JLabel Ci = new JLabel(date);
			displayGroups.add(Ci);
			Ai.setBackground(Color.BLACK);
			Ai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Ai.setForeground(Color.WHITE);
				}
			});
		}
		displayGroups.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane1 = new JScrollPane(displayGroups);
		scrollPane1.setBounds(250, 70, 250, 330);

		JPanel contactTitle = new JPanel();
		contactTitle.add(contactLbl);
		contactTitle.setBounds(0, 0, 250, 70);

		JPanel groupTitle = new JPanel();
		groupTitle.setBounds(250, 0, 250, 70);
		groupTitle.add(groupLbl);

		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500, 400));
		contentPane.add(scrollPane);
		contentPane.add(scrollPane1);
		contentPane.add(contactTitle);
		contentPane.add(groupTitle);

		frame.setContentPane(contentPane);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}


}