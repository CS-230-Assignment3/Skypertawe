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
import java.util.ArrayList;

public class MainWindow extends JFrame {
	private AccountsGraph graph;
	private Account currUser;
	private ArrayList<Account> contactList;
	private ArrayList<String> groupList = new ArrayList<String>();

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
		JPanel leftContainer = new JPanel();
		leftContainer.setSize(new Dimension(this.getWidth() / 2, this.getHeight()));
		leftContainer.setLayout(null);

		JPanel rightContainer = new JPanel();
		rightContainer.setBounds(leftContainer.getWidth(), 0, leftContainer.getWidth(), leftContainer.getHeight());
		rightContainer.setLayout(null);

		JPanel contactsPanel = new JPanel();
		contactsPanel.setLayout(null);
		JScrollPane contactsPane = new JScrollPane(contactsPanel);

		JPanel groupsPanel = new JPanel();
		contactsPanel.setLayout(null);
		JScrollPane groupsPane = new JScrollPane(groupsPanel);

		contactsPanel.setBackground(new Color(170,170,170,255));
		groupsPanel.setBackground(new Color(187,187,187,187));

		JLabel contactHeader = new JLabel("Contacts");
		JLabel groupHeader = new JLabel("Groups");

		contactsPanel.setBounds(0, 0, leftContainer.getWidth() / 2, leftContainer.getHeight());
		groupsPanel.setBounds(contactsPanel.getWidth(), 0, leftContainer.getWidth() / 2, leftContainer.getHeight());

		contactsPane.setSize(new Dimension(contactsPanel.getWidth(), contactsPanel.getHeight()));
		groupsPane.setSize(new Dimension(groupsPanel.getWidth(), groupsPanel.getHeight()));

		contactsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		groupsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		contactHeader.setFont(new Font("Arial", Font.BOLD, 20));
		groupHeader.setFont(new Font("Arial", Font.BOLD, 20));

		contactHeader.setBounds(contactsPanel.getWidth() / 3, 0, contactsPanel.getWidth(), 50);
		groupHeader.setBounds(groupsPanel.getWidth() / 3, 0, contactsPanel.getWidth(), 50);

		contactsPanel.add(contactHeader);
		groupsPanel.add(groupHeader);
		leftContainer.add(insertFriends(contactsPanel));
		leftContainer.add(insertGroups(groupsPanel));

		this.add(leftContainer);
		this.add(rightContainer);
		this.setVisible(true);
	}

	private JComponent insertGroups(JComponent panel) {
		int y = 0;
		ArrayList<String> groupList = currUser.getGroupsFileNames();
		Groups groups = new Groups(graph, currUser);
		groups.makeGroups();
		groups.makeUserGroups();

		for(int i = 0; i < groupList.size(); i++) {
			ArrayList<ArrayList<Account>> group1 = currUser.getGroups();
			String[] parts = groupList.get(i).split(",");
			String part1 = parts[0];

			ArrayList<Account> allUsers = groups.getRightUsers(group1, part1);
			allUsers.add(currUser);
			String groupName = groups.readGroupName(part1);
			panel.add(structureGroupButton(groupName, panel, i, allUsers));
			y = y + 60;
		}
		return panel;
	}

	private JComponent structureGroupButton(String groupName, JComponent panel, int y, ArrayList<Account> allUsers) {
		JButton groupBtn = new JButton();
		groupBtn.setBounds(panel.getX(), 50 + y, panel.getWidth(), 50);
		groupBtn.setFocusPainted(false);
		groupBtn.setBackground(new Color(33, 33, 33,255));
		groupBtn.setForeground(new Color(255,255,255,255));
		groupBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		groupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChatPanel(currUser, allUsers, graph);
			}
		});
		return groupBtn;
	}

	private JComponent insertFriends(JComponent panel) {
		int i = 0;
		ArrayList<Account> friendList = currUser.getFriends();

		for(Account obj : friendList) {
			String name = obj.getUser();
			panel.add(structureFriendButton(obj, name, i, panel));
			i = i + 60;
		}
		return panel;
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