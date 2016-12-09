import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @file UnreadMessages.java
 * @author Samuel O'Reilly (824712)
 * @date 08 Dec 2016
 * @see Account for account details
 */
public class UnreadMessages {
    private Account m_currentAccount;
    private Account m_otherAccount;
    private File m_messagesFile;

    /**
     * Constructor takes two account objects, it compares unread messages from the second account to the first account
     *
     * @param currentAccount account to see if there are unread messages from the second to this one
     * @param otherAccount   account to see if unread messages have been sent by
     */
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

    /**
     * Determines if there have been messages sent to current account since last login time by second account
     * @return true if there are unread message, otherwise false
     * @see getMessages
     * @see splitTimeStamp
     * @see isMessageUnread
     */
    public boolean isUnreadMessages() {
        //ArrayList of all messages in chat between current account and other account
        ArrayList<String> lines = getMessages(m_messagesFile);

        /* array of ints representing the timestamp of the last login time
         * format is [year,month,day,hour,minute,second]
         */
        int[] lastLoginArray = splitTimeStamp(m_currentAccount.getLastLogInTime());
        //boolean of whether there is an unread message present
        boolean unreadMessage = false;
        //Start index, start at the max so you read from newest message to oldest message
        int curIndex = lines.size() - 1;
        do {
            //Scanner to parse each line of messages from ArrayList lines
            Scanner lineReader = new Scanner(lines.get(curIndex));
            lineReader.useDelimiter(",");

            //Account sending the message
            String sendAccount = lineReader.next();

            //If account was send by other user
            if (sendAccount.equals(m_otherAccount.getUser())) {
                //Get the timestamp
                String timestamp = lineReader.next();
                //Put timestamp into array
                int[] fileDateTimeArray = splitTimeStamp(timestamp);
                //Determine if message was sent after last login
                unreadMessage = isMessageUnread(fileDateTimeArray, lastLoginArray);
            }
            lineReader.close();
            curIndex--;
        }
        while (!unreadMessage && curIndex >= 0); //Loop while not found an unread message, and not reached start of messages
        return unreadMessage;
    }

    /**
     * Gets the number of unread message, that is the number of messages sent to current account
     * by other account since last login of current account
     * @return integer of number of unread messages
     * @see getMessages
     * @see splitTimeStamp
     * @see isMessageUnread
     */
    public int unreadMessageCount() {
        //ArrayList of all messages in chat between current account and other account
        ArrayList<String> lines = getMessages(m_messagesFile);

        /* array of ints representing the timestamp of the last login time
         * format is [year,month,day,hour,minute,second]
         */
        int[] lastLoginArray = splitTimeStamp(m_currentAccount.getLastLogInTime());

        int unreadMessageCount = 0;
        int curIndex = lines.size() - 1;
        //boolean of whether
        boolean laterThanLastLogIn = false;

        do {
            Scanner lineReader = new Scanner(lines.get(curIndex));
            lineReader.useDelimiter(",");

            String sendAccount = lineReader.next();

            if (sendAccount.equals(m_otherAccount.getUser())) {
                String timestamp = lineReader.next();
                int[] fileDateTimeArray = splitTimeStamp(timestamp);
                if (isMessageUnread(fileDateTimeArray, lastLoginArray)) {
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

    private boolean isMessageUnread(int[] fileArray, int[] lastLoginArray) {
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
