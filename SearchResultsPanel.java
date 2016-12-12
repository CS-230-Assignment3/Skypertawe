import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jamie Hutton (837949)
 * @file SearchResultsPanel.java
 * @date 11 Dec 2016
 * Class that searches for a user and asks if you wish to remove or add the user
 */
public class SearchResultsPanel extends JFrame {
	private Account m_CurrUser;
	private AccountsGraph m_Graph;
	
	 /**
     * Constructor for SearchResultsPanel, takes the current graph and then current user 
     * logged in to find the intended results.
     */
	public SearchResultsPanel(Account currUser, AccountsGraph graph) {
		m_CurrUser = currUser;
		m_Graph = graph;
		this.setTitle("Search");
		this.setContentPane(buildPanel());
		this.setSize(250, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	
	/**
     * Finds a user account , if in graph, and asks if you want to add it or not.
     * @return Content panel contain search results
     */
	public JPanel buildPanel() {

		JPanel contentPane = new JPanel(null);
		JLabel searchLabel = new JLabel("Search");
		searchLabel.setBounds(30, 30, 50, 20);

		JTextField searchBox = new JTextField(20);
		searchBox.setBounds(100, 30, 130, 20);

		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(100, 70, 130, 20);
		/*
		 * Listens for submit button and response to the user input. 
		 */
		submitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String content = searchBox.getText();
				Account newAccount = m_Graph.findAccount(content);
				 contentPane.validate();
			     contentPane.repaint();
				
				if (newAccount != null) {
					contentPane.remove(searchLabel);
					contentPane.remove(searchBox);
					contentPane.remove(submitButton);
					
					//new SearchResultsPanel(m_CurrUser, m_Graph) ;
					String addFriendString = "Add " + newAccount.getUser() + " to Contacts?";
					JButton addFriend = new JButton(addFriendString);
					addFriend.setBounds(0, 150, 250, 50);
					contentPane.add(addFriend);

					/*
					 * Listens for if the user wants to add the account to contacts their
					 * and ask if they are sure.
					 */	
					addFriend.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int dialogButton = JOptionPane.YES_NO_OPTION;
							int dialogResult = JOptionPane.showConfirmDialog(null,
							"Sure you want to add person?", "Warning", dialogButton);		
							if (dialogResult == 0) {
								m_Graph.addContact(m_CurrUser, newAccount);
								dispose();
							}
							
						}
					});
					String removeFriendString = "Remove " + newAccount.getUser() + ", From Contacts?";
					JButton removeFriend = new JButton(removeFriendString);
					removeFriend.setBounds(0, 210, 250, 50);
					contentPane.add(removeFriend);
					
					/*
					 * Listens for if the user wants to remove the account from contacts their
					 *  and ask if they are sure.
					 */	
					removeFriend.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int dialogButton = JOptionPane.YES_NO_OPTION;
							int dialogResult = JOptionPane.showConfirmDialog(null,
									"Sure you want to remove person?", "Warning", dialogButton);
							if (dialogResult == 0) {
								m_Graph.removeContact(m_CurrUser, newAccount);
								dispose();
							}

						}
					});
					
				} else {
					JOptionPane.showMessageDialog(buildPanel(), "No reults Found, Please try again");
					
				}
				
				JButton searchAgain = new JButton("Search Again?");
				searchAgain.setBounds(100, 70, 130, 20);
				
				contentPane.add(searchAgain);
				searchAgain.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						new SearchResultsPanel(m_CurrUser,  m_Graph);
					}
				});
			}
			
		});
		 
	
		
		/*
		 * Builds the content panel read for display.
		 */

		JLabel displayLabel = new JLabel("Results");
		displayLabel.setBounds(30, 100, 50, 20);

		contentPane.setPreferredSize(new Dimension(250, 400));
		contentPane.add(searchLabel);
		contentPane.add(searchBox);
		contentPane.add(displayLabel);
		contentPane.add(submitButton);

		return contentPane;
	}
}
