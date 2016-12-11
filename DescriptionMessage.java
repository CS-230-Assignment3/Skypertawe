import java.util.ArrayList;

/**
 * @file DescriptionMessage.java
 * @author Samuel O'Reilly (824712)
 * @date 09 Dec 2016
 * @see Message.java
 */
public abstract class DescriptionMessage extends Message {
    String m_path;
    String m_textDescription;

    /**
     * Sets the account message is sent from, account message is to be sent to, the filepath to the item being sent
     * and a description of what is being sent
     *
     * @param accountSend     account sending the message
     * @param accountRecieve  account receiving the message
     * @param path        path to item being sent
     * @param textDescription short description of file being sent
     * @see Message.java
     */
    DescriptionMessage(Account accountSend, Account accountRecieve, String path, String textDescription) {
        //Pass parameter to superclass
        super(accountSend, accountRecieve);
        //Assign member variables
        m_path = path;
        m_textDescription = textDescription;
    }

    /**
     * Sets the account message is sent from, and the ArrayList of Accounts the message is being sent to, the path to the
     * item being sent and a description of what is being sent
     *
     * @param accountSend     account sending the message
     * @param accountRecieve  ArrayList of accounts receiving the message
     * @param path            path to item being sent
     * @param textDescription short description of file being sent
     * @see Message.java
     */
    DescriptionMessage(Account accountSend, ArrayList<Account> accountRecieve, String path, String textDescription) {
        //Pass parameter to superclass
        super(accountSend, accountRecieve);
        //Assign member variables
        m_path = path;
        m_textDescription = textDescription;
    }

    /**
     * Sets the filepath of file being sent
     * @param filePath new filepath
     */
    public void setPath(String path) {
        m_path = path;
    }

    /**
     * Returns the filepath of file being sent
     * @return String containing filepath being sent
     */
    public String getPath() {
        return m_path;
    }

    /**
     * Sets the text description of file being sent
     * @param textDescription new description
     */
    public void setTextDescription(String textDescription) {
        m_textDescription = textDescription;
    }

    /**
     * Returns the text description of file being sent
     * @return String containing the description of file being sent
     */
    public String getTextDescription() {
        return m_textDescription;
    }
}
