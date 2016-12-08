
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;


public class ContactPanel {
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> contactList = new ArrayList<String>();
	private ArrayList<String> groupList = new ArrayList<String>(); 


	
	public JPanel buildPanel () throws Exception{
		
		JScrollPane scrollPane = groupPanel();
		JScrollPane scrollPane1 = contactPanel();
		
		
		JLabel contactLbl = new JLabel("Contacts");
		JLabel groupLbl = new JLabel("Group");
		
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
		
		return contentPane;
		
	}

	public JScrollPane groupPanel() throws Exception{
		JPanel displayGroups = new JPanel();
		setGroupList();
		for (int i = 0; i < getGroupList().size(); i++) {
			String[] parts = getGroupList().get(i).split(",");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			JButton Ai = new JButton(part1);
			displayGroups.add(Ai);
			JLabel Bi = new JLabel(part2);
			displayGroups.add(Bi);
			if(!part2.replaceAll("\\s+","").equals("0")){
				Bi.setBackground(Color.CYAN);
				Bi.setOpaque(true);
			}
			JLabel Ci = new JLabel(part3);
			displayGroups.add(Ci);
			Ai.setBackground(Color.BLACK);
			Ai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//new ChatPanel();
				}
			});
		}
		displayGroups.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane1 = new JScrollPane(displayGroups);
		scrollPane1.setBounds(250, 70, 250, 330);
		
		return scrollPane1;
		
	}
	
	public JScrollPane contactPanel() throws Exception{

		JPanel displayContact = new JPanel();
		setContactList();
		
		for (int i = 0; i < getContactList().size(); i++) {
			String[] parts = getContactList().get(i).split(",");
			//get(i) unreadmessages
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			JButton Ai = new JButton(part1);
			displayContact.add(Ai);
			JLabel Bi = new JLabel(part2);
			displayContact.add(Bi);
			if(!part2.replaceAll("\\s+","").equals("0")){
				Bi.setBackground(Color.CYAN);
				Bi.setOpaque(true);
			}
			JLabel Ci = new JLabel(part3);
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
		
		return scrollPane;
		
	}
	
	public ArrayList<String> setContactList() throws Exception {
		
		return contactList = ReadContactFile.contactReader();
	}
	
	public ArrayList<String> setGroupList() throws Exception {
		return groupList = ReadContactFile.contactReader(); //reads in groups list here instead of contact list
	}
	public ArrayList<String> getGroupList() {
		return groupList;
	}
	
	public ArrayList<String> getContactList() {
		return contactList;
	}
	
	public static void main(String[] args) throws Exception {
		new ContactPanel();
	}

}