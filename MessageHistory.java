import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	public MessageHistory(Account accountSend, Account accountRecieve) {
		m_accountSend = accountSend;
		m_accountRecieve = accountRecieve;
        // If accountSend username <= accountRecieve username
        if (accountSend.getUser().compareTo(accountRecieve.getUser()) <= 0) {
            m_fileName = "messages\\" + accountSend.getUser() + "_" + accountRecieve.getUser() + ".txt";
        } else { // Else firstAccount username > secondAccount username
            m_fileName = "messages\\" + accountRecieve.getUser() + "_" + accountRecieve.getUser() + ".txt";
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

	public void writeToFile(String message) {	
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(m_fileName))) {
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String timestamp = ft.format(date).toString();
            String line = m_accountSend.getUser() + "," + timestamp + "," + message;
            bw.write(line);
            bw.close();

        } catch(IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * Makes an ArrayList of each line in a chat file. The array list contains a String[], where
     * index 0 is the username of the sending account,
     * index 1 is the timestamp of the message sent and,
     * index 2 is the message itself
     *
     * @return String[] where 0 is username, 1 is timestamp, 2 is message
     */
    public ArrayList<String[]> readFromFile() {

        File chatFile = new File(m_fileName);
        Scanner in = null;


		/*
        String line = "";
		Scanner in = null;
		File read = null;
		
		try {
			read = new File(m_fileName);
			in = new Scanner(read);
			
			if(read.exists() && read.isDirectory()) {
				while(in.hasNextLine()) {
					line = in.nextLine();
				}
			}
			in.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return line;
		*/
    }
}
