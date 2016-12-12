/**
 * @file DescriptionMessage.java
 * @author Samuel O'Reilly (824712)
 * @date 11 Dec 2016
 * @see DescriptionMessage.java
 */
public class MessageURL extends DescriptionMessage {
    /**
     * Sets the account message is sent from, the path to the URL being sent and a description of what is being sent.
     * This sets the send time to current system time
     * @param accountSend  account sending the message
     * @param path path to URL being sent
     * @param textDescription short description of URL being sent
     * @see DescriptionMessage.java
     */
    public MessageURL(Account accountSend, String path, String description) {
        super(accountSend, path, description);
    }

    /**
     * Sets the account message is sent from, the path to the URL being sent and a description of what is being sent.
     * This sets the send time to the given timestamp
     * @param accountSend  account sending the message
     * @param path path to URL being sent
     * @param textDescription short description of URL being sent
     * @see DescriptionMessage.java
     */
    public MessageURL(Account accountSend, String path, String description, String timestamp) {
        super(accountSend, path, description, timestamp);
    }

    /**
     * Implements abstract method from Message, returns a string containing the username of account that sent the message
     * the URL, and a short text description
     * @return Formatted string containing the account who sent the message, URL, and a description
     */
    public String display() {
        String line = "";
        line = m_accountSend.getUser() + ": \n" + "Description: " + m_textDescription + "\n" + m_path;
        return line;
    }
}
