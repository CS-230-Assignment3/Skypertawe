import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sam on 06/12/2016.
 */
public class ContactsListFile {
    private final String CONTACTS_LIST_FILEPATH = "graphFiles\\contact.txt";
    private final String TEMP_CONTACTS_LIST_FILEPATH = "graphFiles\\contactsTemp.txt";

    public ContactsListFile() {

    }

    public void formContactsList(ArrayList<Account> accounts) {
        File contactsList = new File(CONTACTS_LIST_FILEPATH);
        Scanner in;
        try {
            in = new Scanner(contactsList);
            while (in.hasNext()) {
                Scanner contactsScanner = new Scanner(in.nextLine());
                contactsScanner.useDelimiter(",");
                String firstUsername = contactsScanner.next();
                String secondUsername = contactsScanner.next();
                Account firstAccount = null;
                Account secondAccount = null;
                boolean found = false;
                int curIndex = 0;

                while (!found || curIndex < accounts.size()) {
                    if (firstUsername.equals(accounts.get(curIndex).getUser())) {
                        firstAccount = accounts.get(curIndex);
                    } else if (secondUsername.equals(accounts.get(curIndex).getUser())) {
                        secondAccount = accounts.get(curIndex);
                    }
                    curIndex++;
                    found = firstAccount != null && secondAccount != null;
                } // end while

                firstAccount.addFriend(secondAccount);
                secondAccount.addFriend(firstAccount);
                contactsScanner.close();
            } // end while

            in.close();

        } catch (FileNotFoundException fileNotFoundEx) {
            // TODO: 06/12/2016 catch
        }
    }

    public void addContactToFile(Account firstAccount, Account secondAccount) {
        File contactsList = new File(CONTACTS_LIST_FILEPATH);
        FileWriter writer = null;
        try {
            writer = new FileWriter(contactsList, true);

            String newLine;
            // If firstAccount username <= secondAccount username
            if (firstAccount.getUser().compareTo(secondAccount.getUser()) <= 0) {
                newLine = firstAccount.getUser() + "," + secondAccount.getUser();
            } else {
                newLine = secondAccount.getUser() + "," + secondAccount.getUser();
            }
            writer.write(newLine + "\n");
            writer.close();
        } catch (IOException ioEx) {
            System.err.println("IOException " + ioEx.getMessage());
        }
    }

    public void removeContactFromFile(Account firstAccount, Account secondAccount) {
        File originalFile = new File(CONTACTS_LIST_FILEPATH);
        File tempFile = new File(TEMP_CONTACTS_LIST_FILEPATH);
        try {
            tempFile.createNewFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        Scanner reader = null;
        PrintWriter writer = null;
        try {
            reader = new Scanner(originalFile);
            writer = new PrintWriter(tempFile);
        } catch (FileNotFoundException notFound) {
            System.err.println(CONTACTS_LIST_FILEPATH + " or " + TEMP_CONTACTS_LIST_FILEPATH
                    + " " + notFound.getMessage());
            return;
        }

        while (reader.hasNext()) {
            Scanner contacts = new Scanner(reader.nextLine());
            contacts.useDelimiter(",");
            String firstUsername = contacts.next();
            String secondUsername = contacts.next();
            boolean skipLine = false;

            if (firstUsername.equals(firstAccount.getUser()) &&
                    secondUsername.equals(secondAccount.getUser())) {
                skipLine = true;
                firstAccount.removeFriend(secondAccount);
                secondAccount.removeFriend(firstAccount);

            } else if (secondUsername.equals(firstAccount.getUser()) &&
                    firstUsername.equals(secondAccount.getUser())) {
                skipLine = true;
            }

            contacts.close();

            if (!skipLine) {
                writer.println(firstUsername + "," + secondUsername);
            }
        }

        reader.close();
        writer.close();

        originalFile.delete();
        tempFile.renameTo(originalFile);

    }
}
