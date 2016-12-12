/**
 * @file MainWindow.java
 * @author Luke Harvey (869608), Jamie Hutton (837949)
 * @date 11th dec 2016
 * @see ChatPanel.java
 * @see Account.java
 * @see AccountsGraph.java
 * @see ContactPanel.java
 *
 * This class is the main window that will contain
 * the contacts list so that when the user clicks on
 * a friend, it will open a chat dialog with them.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainWindow extends JFrame {
	private AccountsGraph graph;
	private Account currUser;
	private ArrayList<Account> contactList;
	private ArrayList<String> groupList = new ArrayList<String>();
	private LocalDateTime m_lastMessageLocalDateTime;
	private String m_lastMessageDataTime;

	/**
	 * This constructor sets teh values for the
	 * JFrame, loads the contact panel and loads
	 * the components on to the panel
	 * @param graph The accounts to pass through
	 * @param currUser The current user
	 */
    public MainWindow(AccountsGraph graph, Account currUser) {
		this.currUser = currUser;
		this.setTitle("Skypertawe - " + "Hello, " + currUser.getUser());
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(exitProgram());
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		this.graph = graph;
		loadAssets();
	}

	/**
	 * Loads components on to the panel and inserts data
	 */
	private void loadAssets() {
		JPanel leftContainer = new JPanel(null);
		leftContainer.setBounds(0, 0, this.getWidth() / 2, this.getHeight());

		JPanel rightContainer = new JPanel(null);
		rightContainer.setBounds(500, 0, 500, this.getHeight());

		JPanel contactsPanel = new JPanel(null);
		JScrollPane contactsPane = new JScrollPane(contactsPanel);

		JPanel groupsPanel = new JPanel(null);
		JScrollPane groupsPane = new JScrollPane(groupsPanel);

		JPanel rightPanel = new JPanel(null);
		JScrollPane rightPane = new JScrollPane(rightPanel);

		contactsPanel.setBackground(new Color(170,170,170,255));
		groupsPanel.setBackground(new Color(187,187,187,187));
		rightPanel.setBackground(new Color(253, 253, 253,255));

		JLabel contactHeader = new JLabel("Contacts");
		JLabel groupHeader = new JLabel("Groups");
		JLabel unreadHeader = new JLabel("Unread Messages");

		contactsPanel.setBounds(0, 0, leftContainer.getWidth() / 2, leftContainer.getHeight());
		groupsPanel.setBounds(contactsPanel.getWidth(), 0, leftContainer.getWidth() / 2, leftContainer.getHeight());
		rightPanel.setBounds(0, 0, rightContainer.getWidth(), this.getHeight());

		contactsPane.setSize(new Dimension(contactsPanel.getWidth(), contactsPanel.getHeight()));
		groupsPane.setSize(new Dimension(groupsPanel.getWidth(), groupsPanel.getHeight()));
		rightPane.setSize(new Dimension(rightPanel.getWidth(), rightPanel.getHeight()));

		contactHeader.setFont(new Font("Arial", Font.BOLD, 20));
		groupHeader.setFont(new Font("Arial", Font.BOLD, 20));
		unreadHeader.setFont(new Font("Arial", Font.BOLD, 20));

		contactHeader.setBounds(contactsPanel.getWidth() / 3, 0, contactsPanel.getWidth(), 50);
		groupHeader.setBounds(groupsPanel.getWidth() / 3, 0, contactsPanel.getWidth(), 50);
		unreadHeader.setBounds(rightPanel.getWidth() / 3, 90, rightPanel.getWidth(), 50);

		JButton searchBar = new JButton("Search");
		searchBar.setBounds(0,0, 495, 80);
		searchBar.setFocusPainted(false);
		searchBar.setBackground(new Color(33, 33, 33,255));
		searchBar.setForeground(new Color(255,255,255,255));
		searchBar.setCursor(new Cursor(Cursor.HAND_CURSOR));

		searchBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchResultsPanel(currUser, graph);
			}
		});

		contactsPanel.add(contactHeader);
		groupsPanel.add(groupHeader);
		rightPanel.add(searchBar);
		rightPanel.add(unreadHeader);

		leftContainer.add(insertFriends(contactsPanel, rightPanel));
		leftContainer.add(insertGroups(groupsPanel));
		rightContainer.add(rightPanel);
		this.add(leftContainer);
		this.add(rightContainer);
		this.setVisible(true);
	}

	private JComponent insertGroups(JComponent panel) {
		int i = 0;
		ArrayList<String> groupList = currUser.getGroupsFileNames();
		Groups groups = new Groups(graph, currUser);
		groups.makeGroups();
		groups.makeUserGroups();

		for(String obj : groupList) {
			String name = obj;
			ArrayList<ArrayList<Account>> group1 = currUser.getGroups();

			ArrayList<Account> allUsers = groups.getRightUsers(group1, name);
			allUsers.add(currUser);
			String groupName = groups.readGroupName(name);
			panel.add(structureGroupButton(groupName, panel, i, allUsers));
			i = i + 60;
		}
		return panel;
	}

	private JComponent structureGroupButton(String groupName, JComponent panel, int y, ArrayList<Account> allUsers) {
		JButton groupBtn = new JButton(groupName);
		groupBtn.setBounds(0, 50 + y, panel.getWidth(), 50);
		groupBtn.setFocusPainted(false);
		groupBtn.setBackground(new Color(33, 33, 33,255));
		groupBtn.setForeground(new Color(255,255,255,255));
		groupBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		groupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChatPanel(currUser, allUsers, graph, groupName);
			}
		});
		return groupBtn;
	}

	private JComponent insertFriends(JComponent panel, JComponent seconaryPanel) {
		int i = 0;
		int unreadMessages = 0;
		ArrayList<Account> friendList = currUser.getFriends();

		for(Account obj : friendList) {
			String name = obj.getUser();
			panel.add(structureFriendButton(obj, name, i, panel));
			unreadMessages += insertUnreadChat(seconaryPanel, obj, unreadMessages);
			i = i + 60;
		}

		JLabel timeOfLastMsg = new JLabel();
		timeOfLastMsg.setFont(new Font("Arial", Font.PLAIN, 17));
		timeOfLastMsg.setBounds(10, 180, this.getWidth() / 2, 100);
		timeOfLastMsg.setText("Time of last message: " + m_lastMessageDataTime);
		seconaryPanel.add(timeOfLastMsg);
		return panel;
	}


	private int insertUnreadChat(JComponent panel, Account obj, int unreadMessages) {
		UnreadMessages unread = new UnreadMessages(currUser, obj);
		int numOfUnread = unread.unreadMessageCount();

		JLabel newMessages = new JLabel();
		JLabel noNewMessages = new JLabel();

		newMessages.setFont(new Font("Arial", Font.PLAIN, 17));
		noNewMessages.setFont(new Font("Arial", Font.PLAIN, 17));


		newMessages.setBounds(50, 110, 200, 100);
		noNewMessages.setBounds(50, 110, 200, 100);

		if(numOfUnread > 0) {
			unreadMessages += numOfUnread;
			newMessages.setText("You have " +  unreadMessages + " new messages!");
			newMessages.setForeground(new Color(107, 178, 40,255));
			panel.add(newMessages);
			noNewMessages = null;
		} else if (unreadMessages == 0) {
			noNewMessages.setText("You have no new messages.");
			noNewMessages.setForeground(new Color(0,0,0,255));
			panel.add(noNewMessages);
			newMessages = null;
		}

		if(unread.getTimeofLastSentMessage() != null) {

			if (m_lastMessageLocalDateTime == null) {
			    m_lastMessageDataTime = unread.getTimeofLastSentMessage();
				String[] unreadDateTime = m_lastMessageDataTime.split(" ");
				String[] unreadDate = unreadDateTime[0].split("/");
				String[] unreadTime = unreadDateTime[1].split(":");
				int[] intArray = new int[unreadDate.length + unreadTime.length];

				int i = 0;
				for (String s : unreadDate) {
					intArray[i] = Integer.parseInt(s);
					i++;
				}
				for (String s : unreadTime) {
					intArray[i] = Integer.parseInt(s);
					i++;
				}

				m_lastMessageLocalDateTime = LocalDateTime.of(intArray[0], intArray[1], intArray[2],
						intArray[3], intArray[4], intArray[5]);

			} else {
			    String newUnreadDateTime = unread.getTimeofLastSentMessage();
				String[] unreadDateTime = newUnreadDateTime.split(" ");
				String[] unreadDate = unreadDateTime[0].split("/");
				String[] unreadTime = unreadDateTime[1].split(":");
				int[] intArray = new int[unreadDate.length + unreadTime.length];

				int i = 0;
				for (String s : unreadDate) {
					intArray[i] = Integer.parseInt(s);
					i++;
				}
				for (String s : unreadTime) {
					intArray[i] = Integer.parseInt(s);
					i++;
				}

				LocalDateTime newLastMessageTime = LocalDateTime.of(intArray[0], intArray[1], intArray[2],
						intArray[3], intArray[4], intArray[5]);

				if (newLastMessageTime.isAfter(m_lastMessageLocalDateTime)) {
					m_lastMessageLocalDateTime = newLastMessageTime;
					m_lastMessageDataTime = newUnreadDateTime;
				}

			}


		}

		return unreadMessages;
	}

	private JComponent structureFriendButton(Account obj, String username, int y, JComponent panel) {
		JButton friendBtn = new JButton(username);
		friendBtn.setBounds(0, 50 + y, panel.getWidth(), 50);
		friendBtn.setFocusPainted(false);
		friendBtn.setBackground(new Color(33, 33, 33,255));
		friendBtn.setForeground(new Color(255,255,255,255));
		friendBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		friendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChatPanel(currUser, obj, graph);
			}
		});
		return friendBtn;
	}

	/**
	 * A simple method to check if the user
	 * actually wants to exit the program
	 * @return The exit code that's given
	 */
	private int exitProgram() {
    	int exitCode = DO_NOTHING_ON_CLOSE;
    	JOptionPane pane;
    	pane = new JOptionPane("Are you sure you want to exit?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
    	pane.setVisible(true);

    	if(pane.YES_NO_OPTION == pane.YES_OPTION) {
			exitCode = EXIT_ON_CLOSE;
		}
		return exitCode;
	}
}