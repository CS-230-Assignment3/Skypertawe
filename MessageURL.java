import java.util.ArrayList;

/**
 * Created by Sam on 11/12/2016.
 */
public class MessageURL extends DescriptionMessage {
    public MessageURL(Account accountSend, Account accountRecieve, String path, String description) {
        super(accountSend, accountRecieve, path, description);
    }

    public MessageURL(Account accountSend, ArrayList<Account> accountRecieve, String path, String description) {
        super(accountSend, accountRecieve, path, description);
    }

    public String display() {
        String line = "";
        line = m_accountSend + ": " + m_path + " Description:\n " + m_textDescription;
        return line;
    }
}
