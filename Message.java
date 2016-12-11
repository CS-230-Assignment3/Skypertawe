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
	protected ArrayList<Account> m_accountRecieve;

	/**
	 * A constructor used for a single account
	 * @param accountSend The account sending the message
	 * @param accountRecieve The account receiving the message
	 */
	public Message(Account accountSend, Account accountRecieve) {
		m_accountSend = accountSend;
		m_accountRecieve = new ArrayList<Account>();
		m_accountRecieve.add(accountRecieve);
		formatTime();
	}

	/**
	 * A constructor used for multiple accounts
	 * @param accountSend The account sending the message
	 * @param accountRecieve The account to receive the messages
	 */
	public Message(Account accountSend, ArrayList<Account> accountRecieve) {
		m_accountSend = accountSend;
		m_accountRecieve = accountRecieve;
		formatTime();
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
	public abstract void Display();
}
