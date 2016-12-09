import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
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
        ArrayList<String> lines = getMessages(m_messagesFile);

        boolean unreadMessage = false;
        int curIndex = lines.size() - 1;
        int[] lastLoginArray = splitTimeStamp(m_currentAccount.getLastLogInTime());

        do {
            Scanner lineReader = new Scanner(lines.get(curIndex));
            lineReader.useDelimiter(",");

            String sendAccount = lineReader.next();

            if (sendAccount.equals(m_otherAccount.getUser())) {
                String timestamp = lineReader.next();
                int[] fileDateTimeArray = splitTimeStamp(timestamp);
                unreadMessage = messageUnread(fileDateTimeArray, lastLoginArray);
            }
            lineReader.close();
            curIndex--;
        } while (!unreadMessage && curIndex >= 0);
        return unreadMessage;
    }

    public int unreadMessageCount() {
        ArrayList<String> lines = getMessages(m_messagesFile);

        int unreadMessageCount = 0;
        int curIndex = lines.size() - 1;
        int[] lastLoginArray = splitTimeStamp(m_currentAccount.getLastLogInTime());

        boolean laterThanLastLogIn = false;

        do {
            Scanner lineReader = new Scanner(lines.get(curIndex));
            lineReader.useDelimiter(",");

            String sendAccount = lineReader.next();

            if (sendAccount.equals(m_otherAccount.getUser())) {
                String timestamp = lineReader.next();
                int[] fileDateTimeArray = splitTimeStamp(timestamp);
                if (messageUnread(fileDateTimeArray, lastLoginArray)) {
                    unreadMessageCount++;
                } else {
                    laterThanLastLogIn = true;
                }
            }
            lineReader.close();
            curIndex--;
        } while (!laterThanLastLogIn && curIndex >= 0);

        return unreadMessageCount;
    }

    private int[] splitTimeStamp(String timestamp) {
        String[] dateAndTime = timestamp.split(" ");
        String date = dateAndTime[0];
        String time = dateAndTime[1];

        String[] dateArray = date.split("/");
        String[] timeArray = time.split(":");
        String[] dateTimeArray = new String[dateArray.length + timeArray.length];
        int[] dateTimeArrayInt = new int[dateTimeArray.length];

        for (int i = 0; i < dateTimeArrayInt.length; i++) {
            if (i < 3) {
                dateTimeArrayInt[i] = Integer.parseInt(dateArray[i]);
            } else {
                dateTimeArrayInt[i] = Integer.parseInt(timeArray[i - 3]);
            }
        }

        return dateTimeArrayInt;
    }

    private ArrayList<String> getMessages(File file) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner in = new Scanner(m_messagesFile);
            while (in.hasNext()) {
                lines.add(in.nextLine());
            }
            in.close();
            return lines;

        } catch (FileNotFoundException notFoundEx) {
            System.err.println("Messages file for " + m_currentAccount.getUser() + " "
                    + m_otherAccount.getUser() + "not found\n" + notFoundEx.getStackTrace());
            return null;
        }
    }

    private boolean messageUnread(int[] fileArray, int[] lastLoginArray) {
        LocalDateTime fileDateTime = LocalDateTime.of(fileArray[0], fileArray[1], fileArray[2],
                fileArray[3], fileArray[4], fileArray[5]);

        LocalDateTime lastLogInDateTime = LocalDateTime.of(lastLoginArray[0], lastLoginArray[1], lastLoginArray[2],
                lastLoginArray[3], lastLoginArray[4], lastLoginArray[5]);

        boolean messagesUnread = false;
        if (fileDateTime.isAfter(lastLogInDateTime)) {
            messagesUnread = true;
        }

        return messagesUnread;
    }

}
