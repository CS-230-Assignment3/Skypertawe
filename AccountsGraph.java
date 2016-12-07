import java.util.ArrayList;

/**
 * @author Samuel O'Reilly (824712)
 * @file AccountsGraph.java
 * @date 24 Nov 2016
 * <p>
 * Class that manages the holding and creating of Account objects, as well as the adding and removal of a contact relationship.
 */
public class AccountsGraph {
    //list of all Accounts in system, is always in alphabetical order, by username of Account
    private ArrayList<Account> m_accounts;
    private AccountsFile m_accountsFile = new AccountsFile();
    private ContactsListFile m_contactsListFile = new ContactsListFile();

    public AccountsGraph() {
        //This makes all accounts from the accounts.txt file, and saves them to m_accounts
        m_accounts = m_accountsFile.makeAccounts();
        //This takes the list of accounts and gives them the friends specified from the contact.txt file
        m_contactsListFile.formContactsList(m_accounts);
    }

    public ArrayList<Account> getAccounts() {
        return m_accounts;
    }

    public void addAccount(Account newAccount) {
        String newUsername = newAccount.getUser();

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
        while (!found || m_accounts.size() < curIndex) {
            Account curAccount = m_accounts.get(curIndex);
            if (curAccount.getUser().equals(username)) {
                account = curAccount;
                found = true;
            }
            curIndex++;
        } // end while

        return account;
    }

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

    public void inviteContact(Account fromAccount, Account targetAccount) {
        targetAccount.addInvite(fromAccount);
    }

    public void addContact(Account firstAccount, Account secondAccount) {
        firstAccount.addFriend(secondAccount);
        secondAccount.addFriend(firstAccount);
        //Adds new contacts to contacts.txt
        m_contactsListFile.addContactToFile(firstAccount, secondAccount);
    }

    public void removeContact(Account firstAccount, Account secondAccount) {
        firstAccount.removeFriend(secondAccount);
        secondAccount.removeFriend(firstAccount);
        //Removes contacts from contacts.txt
        m_contactsListFile.removeContactFromFile(firstAccount, secondAccount);
    }
}
