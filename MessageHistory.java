/**
 * @file MessageHistory.java
 * @author Luke Harvey, Sam O'Reilly
 * @date 11th dec 2016
 *
 * Constructs a way to format messages send to each user and
 * save them to a text file; it also reads from a text file.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class MessageHistory {
	private String m_message;
	private String m_time;
	private String m_date;
	private String m_fileName;
	private String m_groupChatName;
	private String[] m_users;
	private String[] m_messages;
	private int[] m_accountColour;
	private Account m_accountSend;
	private Account m_accountRecieve;
	private ArrayList<Account> m_accountsRecieve;

	/**
	 * A constructor for structuring single peer-to-peer messages
	 * @param accountSend The account sending the message
	 * @param accountRecieve The account receiving the message
	 */
	public MessageHistory(Account accountSend, Account accountRecieve) {
		m_accountSend = accountSend;
		m_accountRecieve = accountRecieve;

		if (accountSend.getUser().compareTo(accountRecieve.getUser()) <= 0) {
			m_fileName = "messages\\" + accountSend.getUser() + "_" + accountRecieve.getUser() + ".txt";
		} else {
            m_fileName = "messages\\" + accountRecieve.getUser() + "_" + accountSend.getUser() + ".txt";
        }
	}

	/**
	 * A constructor for structuring multiple user's messages
	 * @param accountSend The account sending the message
	 * @param accountRecieve The accounts receiving the message
	 */
	public MessageHistory(Account accountSend, ArrayList<Account> accountRecieve) {
		m_accountSend = accountSend;
		m_accountsRecieve = accountRecieve;
		
		String userFileName = "";
		Collections.sort(accountRecieve);
		for (Account s : accountRecieve) {
			userFileName += s.getUser() + "_";
		}
		
		m_fileName = "GroupFiles\\" + userFileName + ".txt";
	}

	/**
	 * Gets the string array of users
	 * @return String array of users
	 */
	public String[] getUsers() {
		return m_users;
	}

	/**
	 * Gets the message
	 * @return String containing the message
	 */
	public String getMessage() {
		return m_message;
	}

	/**
	 * Gets the time
	 * @return String containing the time
	 */
	public String getTime() {
		return m_time;
	}

	/**
	 * Gets the date
	 * @return String containing the date
	 */
	public String getDate() {
		return m_date;
	}

	/**
	 * Gets the account colour
	 * @return int array containing the hex of the colour
	 */
	public int[] getAccountColour() {
		return m_accountColour;
	}

	/**
	 * Sets the message
	 * @param fileName The file location
	 * @param message The message string
	 */
	public void setMessage(String fileName, String message) {
		m_message = message;
	}

	/**
	 * Sets the account colour
	 * @param colour The colour to set
	 */
	public void setAccountColour(int[] colour) {
		m_accountColour = colour;
	}

	/**
	 * Sets the users attribute
	 * @param users The users to set
	 */
	public void setUsers(String[] users) {
		m_users = users;
	}

	/**
	 * Sets the time
	 * @param time The time to set
	 */
	public void setTime(String time) {
		m_time = time;
	}

	/**
	 * Sets the date
	 * @param date The date to set
	 */
	public void setDate(String date) {
		m_date = date;
	}

	/**
	 * Writes the message to file with the collective date
	 * @param message The message to write
	 */
	public void writeToFile(String message) {
		try {
			File chatFile = new File(m_fileName);
            FileWriter writer = new FileWriter(chatFile, true);
            Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String timestamp = ft.format(date).toString();
            String line = m_accountSend.getUser() + "," + timestamp + "," + message + "\n";
            writer.write(line);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Makes an ArrayList of each line in a chat file. The array list contains a
	 * String[], where index 0 is the username of the sending account, index 1
	 * is the timestamp of the message sent and, index 2 is the message itself
	 *
	 * @return String[] where 0 is the username, 1 is the timestamp, 2 is the
	 *         message
	 */
	public ArrayList<String[]> readFromFile() {
		File chatFile = new File(m_fileName);
		Scanner read = null;
		ArrayList<String[]> chat = new ArrayList<>();
		try {
			read = new Scanner(chatFile);
			while (read.hasNext()) {
				Scanner line = new Scanner(read.next());
				String[] lineArray = new String[3];
				lineArray[0] = line.next();
				lineArray[1] = line.next();
				lineArray[2] = line.next();
				chat.add(lineArray);
				line.close();
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.err.println(m_fileName + " not found " + e.getStackTrace());
		}
		return chat;
	}

	/**
	 * Creates the group file needed to save messages under
	 * @param groupName The name of the group chat
	 * @param otherAccounts The accounts in this group
	 */
	public void createGroupFile(String groupName, ArrayList<Account> otherAccounts) {
		m_accountsRecieve.add(m_accountSend);
		m_accountsRecieve.addAll(otherAccounts);
		String userFileName;
		userFileName = "";
		for (Account s : m_accountsRecieve) {
			userFileName += "_" + s.getUser();
		}

		String filePath = "GroupFiles\\" + userFileName + ".txt";
		Collections.sort(m_accountsRecieve);

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
