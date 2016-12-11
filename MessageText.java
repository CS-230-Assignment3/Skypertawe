import java.util.ArrayList;

public class MessageText extends Message {
    private String m_text;

    public MessageText(Account accountSend, Account accountRecieve, String msgText) {
        super(accountSend, accountRecieve);
        m_text = msgText;
    }

    public MessageText(Account accountSend, ArrayList<Account> accountRecieve, String msgText) {
        super(accountSend, accountRecieve);
        m_text = msgText;
    }

    public String display() {
        return m_accountSend + ": " + m_text;
    }
}
