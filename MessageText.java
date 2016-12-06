public class MessageText {
	private String m_msgText;
	private Account m_accountSend;
	private Account m_accountRecieve;
	
	public MessageText(String msgText, Account accountSend, Account accountRecieve) {
		m_msgText = msgText;
		m_accountSend = accountSend;
		m_accountRecieve = accountRecieve;
	}
	
	public void send() {
	}
	
	public String display() {
		return "Test";
	}
}
