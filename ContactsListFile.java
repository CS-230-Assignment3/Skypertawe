import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @file ContactsListFile.java
 * @author Samuel O'Reilly (824712)
 * @date 01 Dec 2016
 * @see Account.java for Account objects
 */
public class ContactsListFile {
    private final String CONTACTS_LIST_FILEPATH = "graphFiles\\contact.txt";
    private final String TEMP_CONTACTS_LIST_FILEPATH = "graphFiles\\contactsTemp.txt";
    private final String INVITES_FILEPATH = "graphFiles\\pendingContacts.txt";
    private final String TEMP_INVITES_FILEPATH = "graphFiles\\pendingContactsTemp.txt";

    /**
     * Default constructor
     */
    public ContactsListFile() {
    }

    /**
     * Adds Account objects to the friends list of other Account objects if the relationship is present in contacts file
     *
     * @param accounts list of all Account objects to add friends too
     */
    public void formContactsList(ArrayList<Account> accounts) {
        File contactsList = new File(CONTACTS_LIST_FILEPATH);
        //Scanner used to read each line of contacts file
        Scanner in;
        try {
            in = new Scanner(contactsList);
            //Loop while there is an unread line in contacts file
            while (in.hasNext()) {
                //Scanner used to parse through the current line in contacts file, and get two usernames(these two are friends)
                Scanner contactsScanner = new Scanner(in.nextLine());
                contactsScanner.useDelimiter(",");
                String firstUsername = contactsScanner.next();
                String secondUsername = contactsScanner.next();
                Account firstAccount = null;
                Account secondAccount = null;

                boolean found = false;
                int curIndex = 0;
                //Loop through accounts until both accounts are found, or end of list is reached
                while (!found || curIndex < accounts.size()) {
                    //If first username in contacts file equals current account username
                    if (firstUsername.equals(accounts.get(curIndex).getUser())) {
                        firstAccount = accounts.get(curIndex);
                        //If second username in contacts file equals current account username
                    } else if (secondUsername.equals(accounts.get(curIndex).getUser())) {
                        secondAccount = accounts.get(curIndex);
                    }
                    curIndex++;
                    //Set found to true when both first and second accounts are found
                    found = firstAccount != null && secondAccount != null;
                } // end while

                //Add each account to the friends list of the other
                firstAccount.addFriend(secondAccount);
                secondAccount.addFriend(firstAccount);
                contactsScanner.close();
            } // end while

            in.close();

        } catch (FileNotFoundException fileNotFoundEx) {
            System.err.println("Cannot find file " + CONTACTS_LIST_FILEPATH +
                    " " + fileNotFoundEx.getStackTrace());
        }
    }

    public void formInvitesList(ArrayList<Account> accounts) {
        File contactsList = new File(INVITES_FILEPATH);
        //Scanner used to read each line of contacts file
        Scanner in;
        try {
            in = new Scanner(contactsList);
            //Loop while there is an unread line in contacts file
            while (in.hasNext()) {
                //Scanner used to parse through the current line in contacts file, and get two usernames(these two are friends)
                Scanner contactsScanner = new Scanner(in.nextLine());
                contactsScanner.useDelimiter(",");
                String firstUsername = contactsScanner.next();
                String secondUsername = contactsScanner.next();
                Account firstAccount = null;
                Account secondAccount = null;

                boolean found = false;
                int curIndex = 0;
                //Loop through accounts until both accounts are found, or end of list is reached
                while (!found || curIndex < accounts.size()) {
                    //If first username in contacts file equals current account username
                    if (firstUsername.equals(accounts.get(curIndex).getUser())) {
                        firstAccount = accounts.get(curIndex);
                        //If second username in contacts file equals current account username
                    } else if (secondUsername.equals(accounts.get(curIndex).getUser())) {
                        secondAccount = accounts.get(curIndex);
                    }
                    curIndex++;
                    //Set found to true when both first and second accounts are found
                    found = firstAccount != null && secondAccount != null;
                } // end while

                //Add each account to the friends list of the other
                firstAccount.addInvite(secondAccount);
                contactsScanner.close();
            } // end while

            in.close();

        } catch (FileNotFoundException fileNotFoundEx) {
            System.err.println("Cannot find file " + CONTACTS_LIST_FILEPATH +
                    " " + fileNotFoundEx.getStackTrace());
        }
    }

    /**
     * Adds the contact relationship between two accounts to the contacts file. Does not add
     * contacts to each others friends list. This is handled in AccountsGraph.
     *
     * @param firstAccount  account to add to file
     * @param secondAccount account to add to file
     */
    public void addContactToFile(Account firstAccount, Account secondAccount) {
        File contactsList = new File(CONTACTS_LIST_FILEPATH);
        FileWriter writer = null;
        try {
            //Writer appends to contacts file
            writer = new FileWriter(contactsList, true);

            String newLine;
            // If firstAccount username <= secondAccount username
            if (firstAccount.getUser().compareTo(secondAccount.getUser()) <= 0) {
                newLine = firstAccount.getUser() + "," + secondAccount.getUser();
            } else { // Else firstAccount username > secondAccount username
                newLine = secondAccount.getUser() + "," + secondAccount.getUser();
            }
            writer.write(newLine + "\n");
            writer.close();
        } catch (IOException ioEx) {
            System.err.println("IOException " + ioEx.getMessage());
        }
    }

    /**
     * Removes contact relationship between two accounts from the contacts file
     *
     * @param firstAccount  account to remove from second account
     * @param secondAccount account to remove from first account
     */
    public void removeContactFromFile(Account firstAccount, Account secondAccount) {
        File originalFile = new File(CONTACTS_LIST_FILEPATH);
        File tempFile = new File(TEMP_CONTACTS_LIST_FILEPATH);
        try {
            tempFile.createNewFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        //Scanner used to read each line of accounts file
        Scanner reader = null;
        //Writer used to write each line of accounts file
        PrintWriter writer = null;
        try {
            reader = new Scanner(originalFile);
            writer = new PrintWriter(tempFile);
        } catch (FileNotFoundException notFound) {
            System.err.println(CONTACTS_LIST_FILEPATH + " or " + TEMP_CONTACTS_LIST_FILEPATH
                    + " " + notFound.getMessage());
            return;
        }
        //Loop while there is an unread line in accounts file
        while (reader.hasNext()) {
            //Scanner used to parse each account
            Scanner contacts = new Scanner(reader.nextLine());
            contacts.useDelimiter(",");
            String firstUsername = contacts.next();
            String secondUsername = contacts.next();
            //Determines if line should not be written to file
            boolean skipLine = false;

            /*If the two usernames we are removing friends from equal the current accounts
            * on the line in contacts file, skip writing this line
             */
            if (firstUsername.equals(firstAccount.getUser()) &&
                    secondUsername.equals(secondAccount.getUser())) {
                skipLine = true;
            } else if (secondUsername.equals(firstAccount.getUser()) &&
                    firstUsername.equals(secondAccount.getUser())) {
                skipLine = true;
            }
            contacts.close();
            //If line should not be skipped, write it
            if (!skipLine) {
                writer.println(firstUsername + "," + secondUsername);
            }
        }
        reader.close();
        writer.close();
        //Delete original accounts file and rename temporary to original file name
        originalFile.delete();
        tempFile.renameTo(originalFile);

    }

    /**
     * Writes the invite to file, in form fromAccount,toAccount
     * @param fromAccount who the invite was from
     * @param toAccount who the invite was to
     */
    public void writeInviteContact(Account fromAccount, Account toAccount) {
        File invites = new File(INVITES_FILEPATH);
        FileWriter writer = null;
        try {
            //Writer appends to contacts file
            writer = new FileWriter(invites, true);
            writer.write(fromAccount.getUser() + "," + toAccount.getUser());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Accepts the invite and removes the correct line from pendingFile
     * @param receiveAccount ho the invite was to
     * @param sendAccount who the invite was from
     */
    public void acceptInvite(Account receiveAccount, Account sendAccount) {
        File originalFile = new File(INVITES_FILEPATH);
        File tempFile = new File(TEMP_INVITES_FILEPATH);
        try {
            tempFile.createNewFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        //Scanner used to read each line of accounts file
        Scanner reader = null;
        //Writer used to write each line of accounts file
        PrintWriter writer = null;
        try {
            reader = new Scanner(originalFile);
            writer = new PrintWriter(tempFile);
        } catch (FileNotFoundException notFound) {
            System.err.println(CONTACTS_LIST_FILEPATH + " or " + TEMP_CONTACTS_LIST_FILEPATH
                    + " " + notFound.getMessage());
            return;
        }
        //Loop while there is an unread line in accounts file
        while (reader.hasNext()) {
            //Scanner used to parse each account
            Scanner contacts = new Scanner(reader.nextLine());
            contacts.useDelimiter(",");
            String firstUsername = contacts.next();
            String secondUsername = contacts.next();
            //Determines if line should not be written to file
            boolean skipLine = false;

            if (firstUsername.equals(sendAccount.getUser()) &&
                    secondUsername.equals(receiveAccount.getUser())) {
                skipLine = true;
            }
            contacts.close();
            //If line should not be skipped, write it
            if (!skipLine) {
                writer.println(firstUsername + "," + secondUsername);
            }
        }
        reader.close();
        writer.close();
        //Delete original accounts file and rename temporary to original file name
        originalFile.delete();
        tempFile.renameTo(originalFile);
    }
}