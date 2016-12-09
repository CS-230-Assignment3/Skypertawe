/**
 * @file DescriptionMessage.java
 * @author Samuel O'Reilly (824712)
 * @date 09 Dec 2016
 * @see Message.java
 */
public abstract class DescriptionMessage extends Message {
    String m_filePath;
    String m_textDescription;

    /**
     * Sets the account message is sent from, account message is to be sent to, the filepath to the item being sent
     * and a description of what is being sent
     *
     * @param accountSend     account sending the message
     * @param accountRecieve  account receiving the message
     * @param filePath        path to file being sent
     * @param textDescription short description of file being sent
     * @see Message.java
     */
    DescriptionMessage(Account accountSend, Account accountRecieve, String filePath, String textDescription) {
        //Pass parameter to superclass
        super(accountSend, accountRecieve);
        //Assign member variables
        m_filePath = filePath;
        m_textDescription = textDescription;
    }

    /**
     * Sets the filepath of file being sent
     * @param filePath new filepath
     */
    public void setFilePath(String filePath) {
        m_filePath = filePath;
    }

    /**
     * Returns the filepath of file being sent
     * @return String containing filepath being sent
     */
    public String getFilePath() {
        return m_filePath;
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
