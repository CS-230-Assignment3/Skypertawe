import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Message {
	protected String m_timeSent;
	protected Account m_accountSend;
    protected boolean setSendTime = false;

    public Message(Account accountSend) {
        m_accountSend = accountSend;
		setSendTime();
	}

    public Message(Account accountSend, String timestamp) {
        m_accountSend = accountSend;
        m_timeSent = timestamp;
    }

    public String getSendTime() {
		return m_timeSent;
	}
	
	private void setSendTime() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		m_timeSent = ft.format(date).toString();
	}

	public abstract String display();
}
