import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @author Samuel O'Reilly (824712)
 * @file AccountsGraph.java
 * @date 24 Nov 2016
 * @see Account.java
 * @see AccountsFile.java for how Account objects are read from file
 * @see ContactsListFile.java for how contact relationships are created
 * Class that manages the holding and creating of Account objects, as well as the adding and removal of a contact relationship.
 */
public class AccountsGraph {
    //list of all Accounts in system, is always in alphabetical order, by username of Account
    private ArrayList<Account> m_accounts;
    //File read/write classes
    private AccountsFile m_accountsFile = new AccountsFile();
    private ContactsListFile m_contactsListFile = new ContactsListFile();


    /**
     * Constructor for AccountsGraph, it creates all Account objects using AccountsFile and also forms contact relationships
     * using ContactsListFile
     */
    public AccountsGraph() {
        //This makes all accounts from the accounts.txt file, and saves them to m_accounts
        m_accounts = m_accountsFile.makeAccounts();
        //This takes the list of accounts and gives them the friends specified from the contact.txt file
        m_contactsListFile.formContactsList(m_accounts);
        m_contactsListFile.formInvitesList(m_accounts);
    }

    /**
     * Gets the list of all accounts in Skypertawe
     *
     * @return ArrayList of all Account objects
     */
    public ArrayList<Account> getAccounts() {
        return m_accounts;
    }

    /**
     * Adds a new account to the list of accounts, as well as writing it to file. It also makes a copy of the profile pic path
     * into the local directory profilePics
     * @param newAccount Account to be added
     */
    public void addAccount(Account newAccount) {
        String newUsername = newAccount.getUser();

        /*
         * Copy profile picture and change path to relative copy
         */

        String oldPath = newAccount.getProfilePic();


        String newPath = "profilePics\\" + newAccount.getUser() + ".png";

        Path profilePic = Paths.get(oldPath);
        Path newFile = Paths.get(newPath);

        //Move file
        try {
            Files.copy(profilePic, newFile, REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Failed to copy profile pic" + "\n" + e.getStackTrace());
        }

        int curIndex = 0;
        boolean inserted = false;

        while (!inserted || m_accounts.size() < curIndex) {
            // get username of the current Account in loop
            String currentUsername = m_accounts.get(curIndex).getUser();

            // if newUsername <= currentUsername, add at current position
            if (newUsername.compareTo(currentUsername) <= 0) {
                m_accounts.add(curIndex, newAccount);
                inserted = true;

            } else { // else move onto the next account
                curIndex++;
            }
        } // end while

        newAccount.setProfilePic(newPath);

        //Saves new account to accounts.txt
        m_accountsFile.addAccount(newAccount);
    }

    /**
     * Method that returns an Account object with a given username, if no account has this username it returns null
     *
     * @param username username of Account to find
     * @return Account with given username, or if not found returns null
     */
    public Account findAccount(String username) {
        Account account = null;
        boolean found = false;
        int curIndex = 0;
        while (!found && curIndex < m_accounts.size()) {
            Account curAccount = m_accounts.get(curIndex);
            if (curAccount.getUser().equals(username)) {
                account = curAccount;
                found = true;
            }
            curIndex++;
        } // end while

        return account;
    }

    /**
     * Determine if a given username already exists
     * @param newUsername username to check if exists
     * @return true if it does not exists, otherwise false
     */
    public boolean isFreeUsername(String newUsername) {
        boolean exists = false;
        int curIndex = 0;
        while (!exists || m_accounts.size() < curIndex) {
            Account curAccount = m_accounts.get(curIndex);
            if (curAccount.getUser().equals(newUsername)) {
                exists = true;
            }
        } // end while

        return exists;
    }

    /**
     * Adds an invite to the second account to become a contact of the first
     * @param fromAccount who the invite is if from
     * @param targetAccount who the invite is directed to
     */
    public void inviteContact(Account fromAccount, Account targetAccount) {
        targetAccount.addInvite(fromAccount);
        m_contactsListFile.writeInviteContact(fromAccount, targetAccount);
    }

    /**
     * Accepts an invite to the sendAccount from receiveAccount, and adds them as contacts
     * @param receiveAccount who the invite was from
     * @param sendAccount who the invite was to
     */
    public void acceptInvite(Account receiveAccount, Account sendAccount) {
        m_contactsListFile.acceptInvite(receiveAccount, sendAccount);
        addContact(receiveAccount, sendAccount);
    }

    /**
     * Adds a contact relationship between two given accounts, and updates the file
     * @param firstAccount contact to add to second account
     * @param secondAccount contact to add to first account
     */
    public void addContact(Account firstAccount, Account secondAccount) {
        firstAccount.addFriend(secondAccount);
        secondAccount.addFriend(firstAccount);
        //Adds new contacts to contacts.txt
        m_contactsListFile.addContactToFile(firstAccount, secondAccount);
    }

    /**
     * Removes a contact relationship between two accounts, and updates file
     * @param firstAccount remove second account from this account
     * @param secondAccount remove first account from this account
     */
    public void removeContact(Account firstAccount, Account secondAccount) {
        firstAccount.removeFriend(secondAccount);
        secondAccount.removeFriend(firstAccount);
        //Removes contacts from contacts.txt
        m_contactsListFile.removeContactFromFile(firstAccount, secondAccount);
    }

    /**
     * Takes an account and a timestamp and sets this accounts lastLogIn to the given timestamp
     * @param account account to change
     * @param timestamp timestamp to change to
     */
    public void setLastLogin(Account account, String timestamp) {
        account.setLastLogInTime(timestamp);
        m_accountsFile.setLastLoginTime(account, timestamp);
    }
}
