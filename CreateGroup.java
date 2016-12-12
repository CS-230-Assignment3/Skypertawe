import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @file Groups.java
 * @author Jamie Hutton
 * @date 11th dec 2016
 *
 * Creates a GUI that allows the user to create a group.
 */
public class CreateGroup extends JFrame {
	private Account m_CurrUser;
	private AccountsGraph m_Graph;
	private ArrayList<Account> selectedFriends = new ArrayList<Account>();
	/**
     * Allow for group file manipulation group users to be added. 
     *
     * @param CurrUser The current user logged in. 
     * @param graph Passes the current graph built.
     */
	public CreateGroup(Account CurrUser, AccountsGraph graph) {
		m_CurrUser = CurrUser;
		m_Graph = graph;
		this.setTitle("Create Group");
		this.setContentPane(buildPanel());
		this.setSize(250, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Opens a JFrame in order to select the members that the user wish to be in a 
	 * group. It gets the user's currents friends, displays them and the allows them 
	 * to add them to agroup. 
	 * 
	 * @return Content panel contain search results
	 */

	public JPanel buildPanel() {

		ArrayList<Account> friends = m_CurrUser.getFriends();

		JPanel displayContact = new JPanel();

		for (int i = 0; i < friends.size(); i++) {

			displayContact.add(new JCheckBox(friends.get(i).getUser()));

		}

		displayContact.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane(displayContact);
		scrollPane.setBounds(15, 90, 200, 150);

		JLabel tilteLable = new JLabel("Create Groups");
		tilteLable.setBounds(80, 30, 100, 20);

		JLabel friendsTitle = new JLabel("Select Friends :");
		friendsTitle.setBounds(15, 65, 100, 20);
		
		JLabel groupNameLabel = new JLabel("Enter Group Name :");
		groupNameLabel.setBounds(15, 255, 100, 20);
		
		JTextField groupName = new JTextField(20);
		groupName.setBounds(15, 280, 130, 20);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(100, 320, 130, 20);

		
		/*
		 * Listens for submit button and response to the user input.
		 */
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String groupname = groupName.getText();
				Component[] components = displayContact.getComponents();
				for (int i = 0; i < components.length; i++) {
					JCheckBox cb = (JCheckBox) components[i];
					if (cb.isSelected()) {
						selectedFriends.add(m_Graph.findAccount(cb.getText()));
					}
				}

				Groups newGroup = new Groups(m_Graph, m_CurrUser);
				if(!selectedFriends.isEmpty()){
					newGroup.createGroupFile(groupname, selectedFriends);
					JOptionPane.showMessageDialog(buildPanel(), "Group Created");
					dispose();
				}else{
					JOptionPane.showMessageDialog(buildPanel(), "Please select an option.");
				}
			}
		});

		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(250, 400));
		contentPane.add(groupNameLabel);
		contentPane.add(groupName);
		contentPane.add(scrollPane);
		contentPane.add(tilteLable);
		contentPane.add(friendsTitle);
		contentPane.add(submitButton);

		return contentPane;
	}

}