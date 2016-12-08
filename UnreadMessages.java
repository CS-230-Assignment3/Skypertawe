import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sam on 08/12/2016.
 */
public class UnreadMessages {
    private Account m_currentAccount;
    private Account m_otherAccount;
    private File m_messagesFile;

    public UnreadMessages(Account currentAccount, Account otherAccount) {
        m_currentAccount = currentAccount;
        m_otherAccount = otherAccount;

        //message files are saved with usernames in alphabetical order
        //if m_currentAccount <= m_otherAccount
        if (m_currentAccount.getUser().compareTo(m_otherAccount.getUser()) <= 0) {
            m_messagesFile = new File("messages\\" + m_currentAccount.getUser() + "_"
                    + m_otherAccount.getUser() + ".txt");
        } else {
            m_messagesFile = new File("messages\\" + m_otherAccount.getUser() + "_"
                    + m_currentAccount.getUser() + ".txt");
        }
    }

    public boolean isUnreadMessages() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner in = new Scanner(m_messagesFile);
            while (in.hasNext()) {
                lines.add(in.nextLine());
            }
            in.close();

        } catch (FileNotFoundException notFoundEx) {
            System.err.println("Messages file for " + m_currentAccount.getUser() + " "
                    + m_otherAccount.getUser() + "not found\n" + notFoundEx.getStackTrace());
            return false;
        }

        boolean unreadMessage = false;
        int curIndex = lines.size();
        int[] lastLoginArray = splitTimeStamp(m_currentAccount.getLastLogInTime());

        while (!unreadMessage || curIndex >= 0) {
            Scanner lineReader = new Scanner(lines.get(curIndex));
            lineReader.useDelimiter(",");

            String sendAccount = lineReader.next();

            if (sendAccount.equals(m_currentAccount.getUser())) {
                String timestamp = lineReader.next();
                int[] fileDateTimeArray = splitTimeStamp(timestamp);
                boolean laterDate = false;
                laterDate = fileDateTimeArray[0] >= lastLoginArray[0] &&
                        fileDateTimeArray[1] >= lastLoginArray[1] &&
                        fileDateTimeArray[2] >= lastLoginArray[2];

                boolean laterTime = false;
                laterTime = fileDateTimeArray[3] >= lastLoginArray[3] &&
                        fileDateTimeArray[4] >= lastLoginArray[4] &&
                        fileDateTimeArray[5] > lastLoginArray[5];

                unreadMessage = laterDate && laterTime;
            }
        }
        return unreadMessage;
    }

    public int[] splitTimeStamp(String timestamp) {
        String[] dateAndTime = timestamp.split(" ");
        String date = dateAndTime[0];
        String time = dateAndTime[1];

        String[] dateArray = date.split("/");
        String[] timeArray = time.split(":");
        String[] dateTimeArray = new String[dateArray.length + timeArray.length];
        int[] dateTimeArrayInt = new int[dateTimeArray.length];

        for (int i = 0; i < dateAndTime.length; i++) {
            if (i < 3) {
                dateTimeArrayInt[i] = Integer.parseInt(timeArray[i]);
            } else {
                dateTimeArrayInt[i] = Integer.parseInt(timeArray[i]);
            }
        }

        return dateTimeArrayInt;
    }


}
