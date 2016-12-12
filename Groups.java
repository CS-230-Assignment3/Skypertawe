import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
/**
 * @file Groups.java
 * @author Jamie Hutton
 * @date 11th dec 2016
 *
 * Used to create groups and create a list of all groups.
 */
public class Groups {
	private static Account m_CurrUser;
	private ArrayList<String> m_CurrUserGroupsFileNames = new ArrayList<String>();
	private ArrayList<ArrayList<Account>> m_CurrUserGroups = new ArrayList<ArrayList<Account>>();
	private ArrayList<Account> m_NewGroup = new ArrayList<Account>();
	private ArrayList<String> allGroups = new ArrayList<String>();
	private AccountsGraph m_graph;
	
	/**
     * Allow for group file manipulation group users to be added. 
     *
     * @param accountSend     account sending the message
     * @param currUser The current user logged in. 
     * @param graph Passes the current graph built.
     */
	public Groups(AccountsGraph graph, Account currUser) {
		m_graph = graph;
		m_CurrUser = currUser;

	}
	
	/**
     * Creates a list of groups that are contained with in "GroupsFiles\\" folder. 
     * 
     *@return allGroups Returns a list of the groups that are stored in the system. 
     */
	public ArrayList<String> makeGroups() {

		File[] files = new File("GroupFiles\\").listFiles();

		for (File file : files) {
			if (file.isFile()) {
				allGroups.add(file.getName().replace(".txt", ""));
			}
		}

		return allGroups;

	}

	/**
     * This add the group files names and the user's that belong to the group 
     * to the current users account. It does this by get allGroup and splitting it
     * then so that only the names are left. The finds the users account in the graph and 
     * ands it to the current person logged in account. 
     * 
     * 
     */
	public void makeUserGroups() {

		ArrayList<String> parts = new ArrayList<String>();
		ArrayList<Account> parts1 = new ArrayList<Account>();
		String[] temp = null;

		for (int i = 0; i < allGroups.size(); i++) {
			parts.add(allGroups.get(i).replace(".txt", ""));

		}

		for (int i = 0; i < parts.size(); i++) {
			temp = parts.get(i).split("_");
			ArrayList<Account> Pi = new ArrayList<Account>(parts1);
			for (int j = 0; j < temp.length; j++) {

				if (!temp[j].equals(m_CurrUser.getUser())) {
					Pi.add(m_graph.findAccount(temp[j]));
				}

				if (temp[j].contains(m_CurrUser.getUser())) {
					m_CurrUserGroupsFileNames.add(parts.get(i).replace(" ", ".txt"));
				}

			}

			m_CurrUserGroups.add(Pi);

		}

		m_CurrUser.setGroups(m_CurrUserGroups);
		m_CurrUser.setGroupsFileNames(m_CurrUserGroupsFileNames);
	}
	
	/**
     * This method is used to find a tuple with in a 2D array list. It first makes the 
     * target accounts by taking the file name and converting the names in to accounts.
     * Then it searched the 2D array list for the targeted tuple.
     * Then return an array list of accounts of the desired names from the file name.
     * 
     * @return finalList Returns a list of users taken from a 2D array list 
     * 
     */
	public ArrayList<Account> getRightUsers(ArrayList<ArrayList<Account>> g, String g1) {
		String[] temp = g1.replace(".txt", "").split("_");
		Arrays.sort(temp);
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<Account> account = new ArrayList<Account>();

		for (String item : temp)
			if (!m_CurrUser.getUser().equals(item))
				result.add(item);

		for (int i = 0; result.size() > i; i++) {
			Account p = m_graph.findAccount(result.get(i));
			account.add(p);
			Collections.sort(account);
		}

		ArrayList<Account> finalList = new ArrayList<Account>();
		for (int i = 0; g.size() > i; i++) {

			ArrayList<Account> currentList = g.get(i);

			Collections.sort(currentList);
			if (account.equals(currentList)) {
				finalList = currentList;
			}

		}

		return finalList;
	}
	
	/**
     * This method reads the group names from the first line in the file
     * 
     * 
     * @param  groupName The group name.
     * @return groupNameReturn Returns a group name. 
     * 
     */
	public String readGroupName(String groupName){
		
		File chatFile = new File("GroupFiles\\"+groupName+".txt");
		Scanner read = null;
		String groupNameReturn = null;
		try {
			read = new Scanner(chatFile);
			groupNameReturn = read.next();
			read.close();
		} catch (FileNotFoundException e) {
			System.err.println(groupName + " not found " + e.getStackTrace());
		}

		return groupNameReturn;
	}
	
	
	/**
     * This method creates the group file in the correct naming convention. 
     * 
     * @param  groupName The group name.
     * @param otherAccounts List of account to be added to the group. 
     * 
     */
	public void createGroupFile(String groupName, ArrayList<Account> otherAccounts) {

		m_NewGroup.add(m_CurrUser);
		m_NewGroup.addAll(otherAccounts);
		String userFileName;
		userFileName = "";
		for (Account s : m_NewGroup) {
			userFileName += s.getUser() + "_";
		}

		String filePath = "GroupFiles\\" + userFileName + ".txt";
		Collections.sort(m_NewGroup);

		try {
			File file = new File(filePath);

			if (file.createNewFile()) {
				FileWriter f0 = new FileWriter(filePath, true);
				f0.write(groupName + "\n");
				f0.close();
			} 

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
