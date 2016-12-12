import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @file AccountsFile.java
 * @author Samuel O'Reilly (824712)
 * @date 27 Nov 2016
 * @see Account.java for Account objects
 *
 * Read and writes Account objects to file, as well as building the ArrayList of all Account objects
 */
public class AccountsFile {
    private final String ACCOUNTS_FILEPATH = "graphFiles\\accounts.txt"; //file path of accounts file
    private final String ACCOUNTS_TEMP_FILEPATH = "graphFiles\\accountsTemp.txt"; //file path of temp accounts file
    private final String FILE_NOT_FOUND_MESSAGE = "File not found";

    /**
     * Default constructor
     */
    public AccountsFile() {
    }

    /**
     * Reads all Account objects from file and adds them to an ArrayList
     *
     * @return ArrayList of all account objects, sorted by username
     */
    public ArrayList<Account> makeAccounts() {
        //Scanner used to read each line of the file
        Scanner in;
        try {
            in = new Scanner(new File(ACCOUNTS_FILEPATH));
        } catch (FileNotFoundException notFound) {
            System.err.println(ACCOUNTS_FILEPATH + FILE_NOT_FOUND_MESSAGE + notFound.getMessage());
            return null;
        }

        //List of all accounts
        ArrayList<Account> accountsList = new ArrayList<>();

        //Loop while there is an unread line in the accounts file
        while (in.hasNext()) {
            //Scanner of each account information
            Scanner accountInfo = new Scanner(in.nextLine());
            accountInfo.useDelimiter(",");

            //Create variables to give to Account
            String username = accountInfo.next();
            String firstName = accountInfo.next();
            String lastName = accountInfo.next();
            String phoneNum = accountInfo.next();
            String password = accountInfo.next();
            String lastLoginTime = accountInfo.next();
            boolean isBirthday = Boolean.parseBoolean(accountInfo.next());
            boolean isCity = Boolean.parseBoolean(accountInfo.next());
            String birthday = null;
            String city = null;

            //Set birthday and city if they are given
            if (isBirthday) {
                birthday = accountInfo.next();
            }
            if (isCity) {
                city = accountInfo.next();
            }

            Account newAccount = new Account(username.toLowerCase(), firstName, lastName, "profilePics\\" + username + ".png",
                    birthday, city, password, phoneNum);
            newAccount.setLastLogInTime(lastLoginTime); // lastLogIn time format "yyyy/MM/dd HH:mm:ss"

            accountsList.add(newAccount);
            accountInfo.close();
        }

        in.close();
        //Sort accounts by username
        Collections.sort(accountsList);
        return accountsList;
    }

    /**
     * Sets the last login time of a given account
     *
     * @param account          account object to modify login time of
     * @param newLastLoginTime new login time, format is "yyyy/MM/dd HH:mm:ss"
     * @see accountDetermineBirthdayCity(Account account)
     */
    public void setLastLoginTime(Account account, String newLastLoginTime) {
        File original = new File(ACCOUNTS_FILEPATH);
        File temp = new File(ACCOUNTS_TEMP_FILEPATH);
        try {
            temp.createNewFile(); //Create temp accounts file
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        //Scanner used to read each line of accounts file
        Scanner reader;
        //Writer used to write each line of accounts file
        PrintWriter writer;
        try {
            reader = new Scanner(original);
            writer = new PrintWriter(temp);
        } catch (FileNotFoundException notFound) {
            System.err.println(ACCOUNTS_FILEPATH + " or " + ACCOUNTS_TEMP_FILEPATH
                    + " " + FILE_NOT_FOUND_MESSAGE + notFound.getMessage());
            return;
        }

        //Loop while there is an unread line in accounts file
        while (reader.hasNext()) {
            //Scanner used to parse account information
            Scanner accountInfo = new Scanner(reader.nextLine());
            accountInfo.useDelimiter(",");

            //Username of current account file Account
            String username = accountInfo.next();
            //Line to print to new file
            String newLine;
            //If username of current account file Account equals username we are modifying
            if (username.equals(account.getUser())) {
                //Builds new line, changing the last login time
                newLine = account.getUser() + "," + account.getFirst() + "," + account.getLastName()
                        + "," + account.getPhoneNum() + "," + account.getPassword() + "," + newLastLoginTime;

                //Add birthday and city if they are given
                newLine += accountDetermineBirthdayCity(account);
            } else { //If not the user we are looking for, keep line the same
                newLine = username + accountInfo.nextLine();
            }
            accountInfo.close();
            //Write new line to file
            writer.println(newLine);
        }
        reader.close();
        writer.close();

        //Delete original accounts file and rename temporary to original file name
        original.delete();
        temp.renameTo(original);
    }

    /**
     * Adds a given account to the accounts file
     * @param newAccount account to write to file
     * @see accountDetermineBirthdayCity(Account account)
     */
    public void addAccount(Account newAccount) {
        File accounts = new File(ACCOUNTS_FILEPATH);
        newAccount.setLastLogInTime("1900/01/01 00:00:00");
        //FileWriter used to write new account to file
        FileWriter writer = null;
        try {
            //Open accounts file, and append to bottom when writing
            writer = new FileWriter(accounts, true);
            //Construct new line to write to file
            String newLine = newAccount.getUser().toLowerCase() + "," + newAccount.getFirst() + "," + newAccount.getLastName()
                    + "," + newAccount.getPassword() + "," + newAccount.getPhoneNum() + "," + newAccount.getLastLogInTime();

            //Add birthday and/or city if given
            newLine += accountDetermineBirthdayCity(newAccount);

            //Write new account to file
            writer.write(newLine + "\n");
            writer.close();
        } catch (FileNotFoundException notFound) {
            System.err.println(ACCOUNTS_FILEPATH + " " + FILE_NOT_FOUND_MESSAGE + " " + notFound.getMessage());
        } catch (IOException ioexception) {
            System.err.println("IOException" + ioexception.getMessage());
        }

    }

    /**
     * Finds whether or not account has given optional city and/or birthday, and formats the output to write to file accordingly
     * @param account account to determine if city/birthday is given
     * @return String output formatted to be written to file
     */
    private String accountDetermineBirthdayCity(Account account) {
        String newLine = "";
        if (account.getBirthday() == null && account.getCity() == null) { //if neither city nor birthday is given
            newLine += ",false,false";
        } else if (account.getBirthday() != null && account.getCity() == null) { //if only birthday is given
            newLine += ",true,false," + account.getBirthday();
        } else if (account.getBirthday() == null && account.getCity() != null) { //if only city is given
            newLine += ",false,true," + account.getCity();
        } else { //if bothh city and birthday are given
            newLine += ",true,true," + account.getBirthday() + "," + account.getCity();
        }
        return newLine;
    }
}
