
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
	}
	
	public Message[] readFromFile() {
	}
	
	private boolean isFileValid(String filePath) {
	}
}
