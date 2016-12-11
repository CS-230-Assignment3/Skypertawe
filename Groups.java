import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Groups {
	private static Account m_CurrUser;
	private ArrayList<String> m_CurrUserGroupsFileNames = new ArrayList<String>();
	private ArrayList<ArrayList<Account>> m_CurrUserGroups = new ArrayList<ArrayList<Account>>();
	private ArrayList<Account> m_NewGroup = new ArrayList<Account>();
	private ArrayList<String> allGroups = new ArrayList<String>();

	private AccountsGraph m_graph;

	public Groups(AccountsGraph graph, Account currUser) {
		m_graph = graph;
		m_CurrUser = currUser;

	}

	public ArrayList<String> makeGroups() {

		File[] files = new File("GroupFiles\\").listFiles();

		for (File file : files) {
			if (file.isFile()) {
				allGroups.add(file.getName().replace(".txt", ""));
			}
		}

		return allGroups;

	}

	public void makeUserGroups() {

		ArrayList<String> parts = new ArrayList<String>();
		ArrayList<Account> parts1 = new ArrayList<Account>();
		String[] temp = null;

		for (int i = 0; i < allGroups.size(); i++) {
			parts.add(allGroups.get(i).replace(".txt", " "));

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

		ArrayList<Account> currentLis4t = new ArrayList<Account>();
		for (int i = 0; g.size() > i; i++) {

			ArrayList<Account> currentList = g.get(i);

			Collections.sort(currentList);
			if (account.equals(currentList)) {
				currentLis4t = currentList;
			}

		}

		return currentLis4t;
	}

	public void createGroupFile(String groupName, ArrayList<Account> otherAccounts) {

		m_NewGroup.add(m_CurrUser);
		m_NewGroup.addAll(otherAccounts);
		String userFileName;
		userFileName = "";
		for (Account s : m_NewGroup) {
			userFileName += "_" + s.getUser();
		}

		String filePath = "GroupFiles\\" + userFileName + ".txt";
		Collections.sort(m_NewGroup);

		try {
			File file = new File(filePath);

			if (file.createNewFile()) {
				System.out.println("File is created!");
				FileWriter f0 = new FileWriter(filePath, true);
				f0.write(groupName + "\n");
				f0.close();
			} else {
				System.out.print("File exists already!");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
