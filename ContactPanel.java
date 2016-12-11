
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class ContactPanel extends JFrame {
	private ArrayList<Account> contactList;
	private ArrayList<String> groupList = new ArrayList<String>();
	private Account currUser;
	private AccountsGraph graph;

	public ContactPanel(AccountsGraph graph, Account currUser) {
		this.currUser = currUser;
		this.graph = graph;
	}

	public ArrayList<Account> getGroups() {
		return null;
	}

	public JPanel buildPanel() {
		JScrollPane scrollPane = groupPanel();
		JScrollPane scrollPane1 = contactPanel();

		JLabel contactLbl = new JLabel("Contacts");
		JLabel groupLbl = new JLabel("Group");

		JPanel contactTitle = new JPanel();
		contactTitle.add(contactLbl);
		contactTitle.setBounds(0, 10, 250, 70);

		JPanel groupTitle = new JPanel();
		groupTitle.setBounds(250, 10, 250, 70);
		groupTitle.add(groupLbl);

		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500, 400));
		contentPane.add(scrollPane);
		contentPane.add(scrollPane1);
		contentPane.add(contactTitle);
		contentPane.add(groupTitle);

		return contentPane;
	}

	private JScrollPane contactPanel() {
		JPanel displayContact = new JPanel();
		ArrayList<Account> a = currUser.getFriends();

		for (int i = 0; i < a.size(); i++) {
			String[] parts = a.get(i).getUser().split(",");
			String part1 = parts[0];

			JButton Ai = new JButton(part1);
			displayContact.add(Ai);

			UnreadMessages unreadInfo = new UnreadMessages(currUser, a.get(i));
			int numOfUnreadMessages = unreadInfo.unreadMessageCount();
			JLabel Bi = new JLabel("New Messages: " + String.valueOf(numOfUnreadMessages));
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
					new ChatPanel(currUser, g, graph);
				}
			});
		}
		displayContact.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane(displayContact);
		scrollPane.setBounds(0, 200, 250, 330);

		return scrollPane;
	}

	public JScrollPane groupPanel() {
		ArrayList<String> a = currUser.getGroupsFileNames();
		JPanel displayContact = new JPanel();
		Groups s = new Groups(graph, currUser);
	    s.makeGroups();
	    s.makeUserGroups();

		for (int i = 0; i < a.size(); i++) {
		    ArrayList<ArrayList<Account>> a1 = currUser.getGroups();

			String[] parts = a.get(i).split(",");
			String part1 = parts[0];
			
			ArrayList<Account> allUser = s.getRightUsers(a1, part1);
			allUser.add(currUser);
			
			String groupName = s.readGroupName(part1);
			
			JButton contactBtn = new JButton(groupName);
			
			displayContact.add(contactBtn);
		
			UnreadMessages unreadInfo = new UnreadMessages(currUser, allUser);
			int numOfUnreadMessages = unreadInfo.unreadMessageCountGroup();
			
			JLabel newMessage = new JLabel("New Messages: " + String.valueOf(numOfUnreadMessages));
			displayContact.add(newMessage);

			if (numOfUnreadMessages != 0) {
				newMessage.setBackground(new Color(116, 255, 133, 255));
				newMessage.setOpaque(true);
			}
			
			if (unreadInfo.getTimeofLastSentMessage() != null) {
				JLabel lastMessage = new JLabel("Time of last message: " + unreadInfo.getTimeofLastSentMessage());
				displayContact.add(lastMessage);
			}

			contactBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChatPanel(currUser, allUser, graph);
				}
			});
		}
		displayContact.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane(displayContact);
		scrollPane.setBounds(250,200, 250, 330);

		return scrollPane;
	}
}
