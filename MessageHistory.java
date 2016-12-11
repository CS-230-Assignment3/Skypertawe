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

    public MessageHistory(Account accountSend, Account accountRecieve, AccountsGraph accountsGraph) {
        m_accountSend = accountSend;
        m_accountRecieve = accountRecieve;
        m_accountsGraph = accountsGraph;
        // If accountSend username <= accountRecieve username
        if (accountSend.getUser().compareTo(accountRecieve.getUser()) <= 0) {
			m_fileName = "messages\\" + accountSend.getUser() + "_" + accountRecieve.getUser() + ".txt";
		} else { // Else firstAccount username > secondAccount username
            m_fileName = "messages\\" + accountRecieve.getUser() + "_" + accountSend.getUser() + ".txt";
        }
	}
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
	public void writeToFile(String message) {
		File chatFile = new File(m_fileName);
		try {
            FileWriter writer = new FileWriter(m_fileName, true);
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
	 * Makes an ArrayList of each line in a chat file. The array list contains a
	 * String[], where index 0 is the username of the sending account, index 1
	 * is the timestamp of the message sent and, index 2 is the message itself
	 * 
	 * @return String[] where 0 is the username, 1 is the timestamp, 2 is the
	 *         message
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

                //If message is MessageText
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

	public String[] getUsers() {
		return m_users;
	}

	public String getMessage() {
		return m_message;
	}

	public String getTime() {
		return m_time;
	}

	public String getDate() {
		return m_date;
	}

	public int[] getAccountColour() {
		return m_accountColour;
	}

	public void setMessage(String fileName, String message) {
		m_message = message;
	}

	public void setAccountColour(int[] colour) {
		m_accountColour = colour;
	}

	public void setUsers(String[] users) {
		m_users = users;
	}

	public void setTime(String time) {
		m_time = time;
	}

	public void setDate(String date) {
		m_date = date;
	}
}
