/**
 * @file Message.java
 * @author Luke Harvey
 * @date 11th dec 2016
 *
 * An abstract method used for sending and receiving messages
 * through MessageHistory
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Message {
	protected String m_timeSent;
	protected Account m_accountSend;

	/**
	 * A constructor used for making a new message
	 * @param accountSend The account sending the message
	 */
	public Message(Account accountSend) {
		m_accountSend = accountSend;
		formatTime();
	}

	/**
	 * A constructor used for loading an exisiting message
	 * @param accountSend The account sending the message
	 * @param timestamp time message was sent
	 */
	public Message(Account accountSend, String timestamp) {
		m_accountSend = accountSend;
		m_timeSent = timestamp;
	}

	/**
	 * Gets the time that the message was sent
	 * @return The time sent
	 */
	public String getSendTime() {
		return m_timeSent;
	}

	/**
	 * Used to format the time that the message was sent
	 */
	private void formatTime() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		m_timeSent = ft.format(date).toString();
	}

	/**
	 * An abstract method used for displaying the messages
	 */
	public abstract String display();
}
