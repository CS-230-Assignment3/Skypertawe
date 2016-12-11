/**
 * Created by Sam on 11/12/2016.
 */
public class MessageURL extends DescriptionMessage {
    public MessageURL(Account accountSend, String path, String description) {
        super(accountSend, path, description);
    }

    public MessageURL(Account accountSend, String path, String description, String timestamp) {
        super(accountSend, path, description, timestamp);
    }

    public String display() {
        String line = "";
        line = m_accountSend.getUser() + ": " + m_path + " Description:\n " + m_textDescription;
        return line;
    }
}
