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

public class MainWindow extends JFrame {
	private AccountsGraph graph;
	private Account currUser;

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
		loadContacts();
		loadAssets();
	}

	/**
	 * Loads the contact panel on the left
	 */
    private void loadContacts(){
    	ContactPanel panel = new ContactPanel(graph, currUser);
    	JPanel panel1 = panel.buildPanel();
    	panel1.setBounds(100, 50, 500,400);
    	this.setContentPane(panel1);
	}

	/**
	 * Loads some components on the right
	 */
	private void loadAssets() {
		//Use this method to add general JPanels, JLabels ect
		//That have no context
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

    	if(pane.YES_NO_OPTION == pane.YES_OPTION) {
			exitCode = EXIT_ON_CLOSE;
		}
		return exitCode;
	}
}