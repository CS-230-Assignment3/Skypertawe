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
	private AccountsGraph m_accountsGraph;

	/**
	 * A constructor for structuring single peer-to-peer messages
	 * @param accountSend The account sending the message
	 * @param accountRecieve The account receiving the message
	 * @param accountsGraph The current graph of all accounts loaded
	 */
	public MessageHistory(Account accountSend, Account accountRecieve, AccountsGraph accountsGraph) {
		m_accountSend = accountSend;
		m_accountRecieve = accountRecieve;
		m_accountsGraph = accountsGraph;

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
	public MessageHistory(Account accountSend, ArrayList<Account> accountRecieve, AccountsGraph accountsGraph) {
		m_accountSend = accountSend;
		m_accountsRecieve = accountRecieve;
		m_accountsGraph = accountsGraph;
		
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
     * This writes a MessageText to file with the collective date
     * @param message
     */
	public void writeToFile(MessageText message) {
		File chatFile = new File(m_fileName);
		try {
			FileWriter writer = new FileWriter(m_fileName, true);
			Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String timestamp = ft.format(date).toString();
			String line = m_accountSend.getUser() + "," + timestamp + ",messageText," + message.getText() + "\n";
			writer.write(line);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * This writes a MessageURL to file with the collective date
     * @param message The message to write
     */
	public void writeToFile(MessageURL message) {
		File chatFile = new File(m_fileName);
		try {
			FileWriter writer = new FileWriter(m_fileName, true);
			Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String timestamp = ft.format(date).toString();
			String line = m_accountSend.getUser() + "," + timestamp + ",messageURL," + message.getPath() + "," + message.getTextDescription() + "\n";
			writer.write(line);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * This writes a MessageFile to file with the collective date
     * @param message The message to write
     */
	public void writeToFile(MessageFile message) {
		File chatFile = new File(m_fileName);
		try {
			FileWriter writer = new FileWriter(m_fileName, true);
			Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String timestamp = ft.format(date).toString();
			String line = m_accountSend.getUser() + "," + timestamp + ",messageFile," + message.getPath() + "," + message.getTextDescription() + "\n";
			writer.write(line);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * This reads all messages from file with collective chat data and outputs each message type
     * as an ArrayList of Message references
     * @return ArrayList of all messages in chat file
     */
    public ArrayList<Message> readFromFile() {

        File chatFile = new File(m_fileName);
        Scanner read = null;
        ArrayList<Message> chat = new ArrayList<>();
        try {
            read = new Scanner(chatFile);

            while (read.hasNext()) {
                Message message = null;
                Scanner line = new Scanner(read.next());
                line.useDelimiter(",");

                String sendUsername = line.next();
                Account sendAccount = m_accountsGraph.findAccount(sendUsername);
                String timestamp = line.next();
                String messageType = line.next();

                //Determine which type of message current line in read is, and format accordingly
                if (messageType.equals("messageText")) {
                    String messageText = line.next();
                    message = new MessageText(sendAccount, messageText, timestamp);
                } else if (messageType.equals("messageURL")) {
                    String path = line.next();
                    String description = line.next();
                    message = new MessageURL(sendAccount, path, description, timestamp);
                } else if (messageType.equals("messageFile")) {
                    String path = line.next();
                    String description = line.next();
                    message = new MessageFile(sendAccount, path, description, timestamp);
                }

                chat.add(message);
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
