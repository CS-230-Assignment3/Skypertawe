
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
	
	public void writeToFile(String message) {
	}
	
	public Message[] readFromFile() {
	}
	
	public String getUsers() {
	}
	
	public void setUsers(String[] users) {
	}
	
	public String getMessage() {
	}
	
	public void setMessage(String fileName, String message) {
	}
	
	public String getTime() {
	}
	
	public void setTime(String time) {
	}
	
	public String getDate() {
	}
	
	public void setDate(String date) {
	}
	
	public int[] getAccountColour() {
	}
	
	public void setAccountColour(int[] colour) {
	}
	
	private boolean isFileValid(String filePath) {
	}
}
