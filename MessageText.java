/**
 * @file MessageText.java
 * @author Luke Harvey and Samuel O'Reilly
 * @date 11 Dec 2016
 * @see Message.java
 */
public class MessageText extends Message {
	private String m_text;

    /**
     * Sets the account message is sent from, and the the text that is being sent.
     * This sets the send time to current system time
     * @param accountSend Account sending the message
     * @param msgText What the message consists of
     */
	public MessageText(Account accountSend, String msgText) {
		super(accountSend);
		m_text = msgText;
	}

    /**
     * Sets the account message is sent from, and the the text that is being sent.
     * This sets the send time to the timestamp given
     * @param accountSend Account sending the message
     * @param msgText What the message consists of
     * @param timestamp Time the message was sent
     */
	public MessageText(Account accountSend, String msgText, String timestamp) {
		super(accountSend, timestamp);
		m_text = msgText;
	}

    /**
     * Get the text the message contains
     * @return Text of this message
     */
	public String getText() {
		return m_text;
	}

    /**
     * Implements abstract method from Message, returns a string containing the username of account that sent the message
     * and the text message itself
     * @return Formatted string containing the account who sent the message and it's contents
     */
	public String display() {
		return m_accountSend.getUser() + ": " + m_text;
	}
}
