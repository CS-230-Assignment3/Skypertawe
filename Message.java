import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Message {
	protected String m_timeSent;
	protected Account m_accountSend;
	protected ArrayList<Account> m_accountRecieve;
	
	public abstract void Display();
	
	public Message(Account accountSend, Account accountRecieve) {
		m_accountSend = accountSend;
		m_accountRecieve = new ArrayList<Account>;
		m_accountRecieve.add(accountRecieve);
		setSendTime();
	}
	
	public Message(Account accountSend, ArrayList<Account> accountRecieve) {
		m_accountSend = accountSend;
		m_accountRecieve = accountRecieve;
		setSendTime();
	}
	
	public String getSendTime() {
		return m_timeSent;
	}
	
	private void setSendTime() {
		Date d = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd.mm.yy HH:mm:ss");
		m_timeSent = ft.toString();
	}
}
