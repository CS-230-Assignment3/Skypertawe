
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;


public class ContactPanel extends JFrame{
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	private ArrayList<Account> contactList;
	private ArrayList<String> groupList = new ArrayList<String>(); 
	private Account m_currUser;
	private AccountsGraph m_Graph; 
	
	public ContactPanel(AccountsGraph graph, Account currUser){
		m_currUser = currUser;
		m_Graph = graph;
		
	}

	public JPanel buildPanel (){
		
		//JScrollPane scrollPane = groupPanel();
		JScrollPane scrollPane1 = contactPanel();
		
		
		JLabel contactLbl = new JLabel("Contacts");
		JLabel groupLbl = new JLabel("Group");
		
		JPanel contactTitle = new JPanel();
		contactTitle.add(contactLbl);
		contactTitle.setBounds(0, 170, 250, 70);

		JPanel groupTitle = new JPanel();
		groupTitle.setBounds(250, 170, 250, 70);
		groupTitle.add(groupLbl);

		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500, 400));
		//contentPane.add(scrollPane);
		contentPane.add(scrollPane1);
		contentPane.add(contactTitle);
		contentPane.add(groupTitle);
		
		return contentPane;
		
	}
	
	public ArrayList<Account> getGroups(){
		return null;
		
	}

/*	public JScrollPane groupPanel() {
		JPanel displayGroups = new JPanel();
		ArrayList<String> l = m_currUser.getGroups();
		for (int i = 0; i < l.size(); i++) {
			String[] parts = l.get(i).split(",");
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
		
	}*/
	
	public JScrollPane contactPanel() {

		JPanel displayContact = new JPanel();
		
		ArrayList<Account> a = m_currUser.getFriends();
		
		for (int i = 0; i < a.size(); i++) {
			String[] parts = a.get(i).getUser().split(",");
			
			String part1 = parts[0];
			
			JButton Ai = new JButton(part1);
			displayContact.add(Ai);
			
			UnreadMessages unreadInfo = new UnreadMessages(m_currUser,a.get(i));
			int numOfUnreadMessages = unreadInfo.unreadMessageCount();
			JLabel Bi = new JLabel("New Messages:" + String.valueOf(numOfUnreadMessages));// convert to string
			displayContact.add(Bi);
		
			if( numOfUnreadMessages == 0){
				Bi.setBackground(Color.CYAN);
				Bi.setOpaque(true);
			}
			JLabel Ci = new JLabel("Time of last message:"+"\n"+unreadInfo.getTimeofLastSentMessage());
			displayContact.add(Ci);
			
			Account g = a.get(i);
			Ai.setBackground(Color.BLACK);
			Ai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChatPanel(m_currUser,g);
				}
			});
		}
		displayContact.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane(displayContact);
		scrollPane.setBounds(0, 200, 250, 330);
		
		return scrollPane;
		
	}
}