import java.util.ArrayList;

public class MessageText extends Message {
    private String m_text;

    public MessageText(Account accountSend, String msgText) {
        super(accountSend);
        m_text = msgText;
    }

    public MessageText(Account accountSend, String msgText, String timestamp) {
        super(accountSend, timestamp);
        m_text = msgText;
    }

    public String getText() {
        return m_text;
    }

    public String display() {
        return m_accountSend.getUser() + ": " + m_text;
    }
}
