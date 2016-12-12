import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<Account> m_otherAccountList;
    private ArrayList<String> m_messages;
    
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

        if (m_messagesFile.exists()) {
            m_messages = getMessages(m_messagesFile);
        }

    }
    
    public UnreadMessages(Account currentAccount, ArrayList<Account> otherAccountList) {
       	  m_currentAccount = currentAccount;
          m_otherAccountList = otherAccountList;

          String userFileName = "";
  		Collections.sort(otherAccountList);
  		for (Account s : otherAccountList) {
  			userFileName += s.getUser() + "_";
  		}
  		
  		m_messagesFile = new File("GroupFiles\\" + userFileName + ".txt");
  		m_messages = getMessages(m_messagesFile);
  		m_messages.remove(0);
    }
    
    /**
     * Determines if there have been messages sent to current account since last login time by second account
     * @return true if there are unread message, otherwise false
     * @see getMessages
     * @see splitTimeStamp
     * @see isMessageUnread
     */
    public boolean isUnreadMessages() {

        //If there is no chat file, then there are no unread messages
        if (!m_messagesFile.exists()) {
            return false;
        }

        //ArrayList of all messages in chat between current account and other account
        ArrayList<String> lines = m_messages;

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

            //If message was send by other user
            if (!sendAccount.equals(m_currentAccount.getUser())) {
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

        //If there is no chat file, then there are no unread messages
        if (!m_messagesFile.exists()) {
            return 0;
        }

        //ArrayList of all messages in chat between current account and other account
        ArrayList<String> lines = m_messages;

        /* array of ints representing the timestamp of the last login time
         * format is [year,month,day,hour,minute,second]
         */
        int[] lastLoginArray = splitTimeStamp(m_currentAccount.getLastLogInTime());

        int unreadMessageCount = 0;
        int curIndex = lines.size() - 1;
        /* boolean of whether the last message checked is later than last login, when true all unread messages
         * must have been read, so loop can exit
         */
        boolean laterThanLastLogIn = false;

        do {
            //Scanner to parse each line of messages from ArrayList lines
            Scanner lineReader = new Scanner(lines.get(curIndex));
            lineReader.useDelimiter(",");

            //Account sending the message
            String sendAccount = lineReader.next();

            //If message was send by other user
            if (!sendAccount.equals(m_currentAccount.getUser())) {
                //Get the timestamp
                String timestamp = lineReader.next();
                //Put timestamp into array
                int[] fileDateTimeArray = splitTimeStamp(timestamp);
                //If message was sent after last login, increment unreadMessageCount
                if (isMessageUnread(fileDateTimeArray, lastLoginArray)) {
                    unreadMessageCount++;
                    //Else all unread messages have been found
                } else {
                    laterThanLastLogIn = true;
                }
            }
            lineReader.close();
            curIndex--;
        } while (!laterThanLastLogIn && curIndex >= 0);

        return unreadMessageCount;
    }

    /**
     * Takes a timestamp in the format "yyyy/MM/dd HH:mm:ss" and splits it into an array in
     * the format [year,month,day,hour,minute,second]
     *
     * @param timestamp String of format "yyyy/MM/dd HH:mm:ss"
     * @return array with format [year,month,day,hour,minute,second]
     */
    private int[] splitTimeStamp(String timestamp) {
        //Split string into date and time
        String[] dateAndTime = timestamp.split(" ");
        String date = dateAndTime[0];
        String time = dateAndTime[1];

        //Split date into array of Strings
        String[] dateArray = date.split("/");
        //Split time into array of Strings
        String[] timeArray = time.split(":");

        //Loop through dateArray and timeArray, convert to int and add to dateTimeArrayInt
        int[] dateTimeArrayInt = new int[dateArray.length + timeArray.length];
        for (int i = 0; i < dateTimeArrayInt.length; i++) {
            if (i < 3) {
                dateTimeArrayInt[i] = Integer.parseInt(dateArray[i]);
            } else {
                dateTimeArrayInt[i] = Integer.parseInt(timeArray[i - 3]);
            }
        }

        return dateTimeArrayInt;
    }

    /**
     * Takes a chat file and adds each line to an ArrayList
     * @param file chat file read
     * @return ArrayList of each line in chat file, or null if file is not found
     */
    private ArrayList<String> getMessages(File file) {

        ArrayList<String> lines = new ArrayList<>();
        try {
            //Scanner to read each line in chat file
            Scanner in = new Scanner(m_messagesFile);
            //Loop while chat file has an unread line
            while (in.hasNext()) {
                //Add line to ArrayList
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

    /**
     * Finds the last message sent and returns the time it was sent
     * @return Time last message was sent
     */
	public String getTimeofLastSentMessage() {
	    if (m_messages == null) {
	        return null;
        }

		String lastLine =  m_messages.get(m_messages.size()-1);
		String[] lastLineArray = lastLine.split(",");

		return lastLineArray[1];
	}


    /**
     * Determines if date and time in first array if later than second array
     *
     * @param firstArray  array with format [year,month,day,hour,minute,second]
     * @param secondArray array with format [year,month,day,hour,minute,second]
     * @return true if date and time in first array is later than second array
     */
    private boolean isMessageUnread(int[] firstArray, int[] secondArray) {
        //Convert first array to LocalDateTime
        LocalDateTime firstDateAndTime = LocalDateTime.of(firstArray[0], firstArray[1], firstArray[2],
                firstArray[3], firstArray[4], firstArray[5]);
        //Convert second array to LocalDateTime
        LocalDateTime lastLogInDateTime = LocalDateTime.of(secondArray[0], secondArray[1], secondArray[2],
                secondArray[3], secondArray[4], secondArray[5]);

        boolean messagesUnread = false;
        if (firstDateAndTime.isAfter(lastLogInDateTime)) {
            messagesUnread = true;
        }

        return messagesUnread;
    }

}
