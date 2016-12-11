
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

public class ContactPanel extends JFrame {
	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 */
	private ArrayList<Account> contactList;
	private ArrayList<String> groupList = new ArrayList<String>();
	private Account m_CurrUser;
	private AccountsGraph m_Graph;

	public ContactPanel(AccountsGraph graph, Account currUser) {
		m_CurrUser = currUser;
		m_Graph = graph;

	}

	public JPanel buildPanel() {

		JScrollPane scrollPane = groupPanel();
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
		contentPane.add(scrollPane);
		contentPane.add(scrollPane1);
		contentPane.add(contactTitle);
		contentPane.add(groupTitle);

		return contentPane;

	}

	public ArrayList<Account> getGroups() {
		return null;

	}

	
	public JScrollPane groupPanel() {

		ArrayList<String> a = m_CurrUser.getGroupsFileNames();
		JPanel displayContact = new JPanel();
		Groups s = new Groups(m_Graph,m_CurrUser);
	    s.makeGroups();
	    s.makeUserGroups();
	    
		for (int i = 0; i < a.size(); i++) {
		
			
		    ArrayList<ArrayList<Account>> a1 = m_CurrUser.getGroups();
			
			
			String[] parts = a.get(i).split(",");
			String part1 = parts[0];
			System.out.print(parts[0]);
			
			ArrayList<Account> allUser = s.getRightUsers(a1, part1);
			allUser.add(m_CurrUser);
			JButton Ai = new JButton(part1);
			displayContact.add(Ai);
			
			
			
			/*UnreadMessages unreadInfo = new UnreadMessages(m_CurrUser, a.get(i));
			int numOfUnreadMessages = unreadInfo.unreadMessageCount();
			JLabel Bi = new JLabel("New Messages:" + String.valueOf(numOfUnreadMessages));
			displayContact.add(Bi);

			if (numOfUnreadMessages != 0) {
				Bi.setBackground(Color.CYAN);
				Bi.setOpaque(true);
			}*/
			/*
			if (unreadInfo.getTimeofLastSentMessage() != null) {
				JLabel Ci = new JLabel("Time of last message:" + unreadInfo.getTimeofLastSentMessage());
				displayContact.add(Ci);
			}
*/
			
			Ai.setBackground(Color.BLACK);
			Ai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChatPanel(m_CurrUser, allUser);
				}
			});
		}
		displayContact.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane(displayContact);
		scrollPane.setBounds(250,200, 250, 330);

		return scrollPane;

	}


	public JScrollPane contactPanel() {

		JPanel displayContact = new JPanel();

		ArrayList<Account> a = m_CurrUser.getFriends();

		for (int i = 0; i < a.size(); i++) {
			String[] parts = a.get(i).getUser().split(",");

			String part1 = parts[0];

			JButton Ai = new JButton(part1);
			displayContact.add(Ai);

			UnreadMessages unreadInfo = new UnreadMessages(m_CurrUser, a.get(i));
			int numOfUnreadMessages = unreadInfo.unreadMessageCount();
			JLabel Bi = new JLabel("New Messages:" + String.valueOf(numOfUnreadMessages));
			displayContact.add(Bi);

			if (numOfUnreadMessages != 0) {
				Bi.setBackground(Color.CYAN);
				Bi.setOpaque(true);
			}
			if (unreadInfo.getTimeofLastSentMessage() != null) {
				JLabel Ci = new JLabel("Time of last message:" + unreadInfo.getTimeofLastSentMessage());
				displayContact.add(Ci);
			}

			Account g = a.get(i);
			Ai.setBackground(Color.BLACK);
			Ai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChatPanel(m_CurrUser, g);
				}
			});
		}
		displayContact.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane(displayContact);
		scrollPane.setBounds(0, 200, 250, 330);

		return scrollPane;

	}
}