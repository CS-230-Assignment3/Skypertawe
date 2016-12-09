/**
 * Created by Sam on 09/12/2016.
 */
public abstract class DescriptionMessage extends Message {
    String m_filePath;
    String m_textDescription;

    DescriptionMessage(Account accountSend, Account accountRecieve, String filePath, String textDescription) {
        super(accountSend, accountRecieve);
        m_filePath = filePath;
        m_textDescription = textDescription;
    }

    public void setFilePath(String filePath) {
        m_filePath = filePath;
    }

    public String getFilePath() {
        return m_filePath;
    }

    public void setTextDescription(String textDescription) {
        m_textDescription = textDescription;
    }

    public String getTextDescription() {
        return m_textDescription;
    }
}
