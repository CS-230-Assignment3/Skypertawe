import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sam on 05/12/2016.
 */
public class AccountsFile {

    private final String ACCOUNTS_FILEPATH = "graphFiles\\accounts.txt";
    private final String ACCOUNTS_TEMP_FILEPATH = "graphFiles\\accountsTemp.txt";
    private final String FILE_NOT_FOUND_MESSAGE = "File not found";

    public AccountsFile() {
    }

    public ArrayList<Account> makeAccounts() {
        Scanner in;
        try {
            in = new Scanner(new File(ACCOUNTS_FILEPATH));
        } catch (FileNotFoundException notFound) {
            System.err.println(ACCOUNTS_FILEPATH + FILE_NOT_FOUND_MESSAGE + notFound.getMessage());
            return null;
        }

        ArrayList<Account> accountsList = new ArrayList<>();

        while (in.hasNext()) {
            Scanner accountInfo = new Scanner(in.nextLine());
            accountInfo.useDelimiter(",");

            //Create variables to give to Account
            String username = accountInfo.next();
            String firstName = accountInfo.next();
            String lastName = accountInfo.next();
            int phoneNum = Integer.parseInt(accountInfo.next());
            String password = accountInfo.next();
            String lastLoginTime = accountInfo.next();
            boolean isBirthday = Boolean.parseBoolean(accountInfo.next());
            boolean isCity = Boolean.parseBoolean(accountInfo.next());
            String birthday = null;
            String city = null;

            if (isBirthday) {
                birthday = accountInfo.next();
            }
            if (isCity) {
                city = accountInfo.next();
            }

            Account newAccount = new Account(username.toLowerCase(), firstName, lastName, birthday, city, password, "profilePics\\" + username + ".png", phoneNum);
            newAccount.setLastLogInTime(lastLoginTime);

            accountsList.add(newAccount);
            accountInfo.close();
        }

        in.close();
        // TODO: 06/12/2016 add sorting
        return accountsList;
    }

    // lastLogIn time format "yyyy/MM/dd HH:mm:ss"
    public void setLastLoginTime(Account account, String lastLoginTime) {
        File original = new File(ACCOUNTS_FILEPATH);
        File temp = new File(ACCOUNTS_TEMP_FILEPATH);
        try {
            temp.createNewFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        Scanner reader;
        PrintWriter writer;
        try {
            reader = new Scanner(original);
            writer = new PrintWriter(temp);
        } catch (FileNotFoundException notFound) {
            System.err.println(ACCOUNTS_FILEPATH + " or " + ACCOUNTS_TEMP_FILEPATH
                    + " " + FILE_NOT_FOUND_MESSAGE + notFound.getMessage());
            return;
        }

        while (reader.hasNext()) {
            Scanner accountInfo = new Scanner(reader.nextLine());
            accountInfo.useDelimiter(",");
            String username = accountInfo.next();
            String newLine;
            if (username.equals(account.getUser())) {

                newLine = account.getUser() + "," + account.getFirst() + "," + account.getLastName()
                        + "," + account.getPhoneNum() + "," + account.getPassword() + "," + lastLoginTime;

                newLine += accountDetermineBirthdayCity(account);
            } else {
                newLine = username + accountInfo.nextLine();
            }
            accountInfo.close();

            writer.println(newLine);
        }
        reader.close();
        writer.close();

        boolean del = original.delete();
        temp.renameTo(original);
    }

    public void addAccount(Account newAccount) {
        File accounts = new File(ACCOUNTS_FILEPATH);
        FileWriter writer = null;
        try {
            writer = new FileWriter(accounts, true);
            String newLine = newAccount.getUser().toLowerCase() + "," + newAccount.getFirst() + "," + newAccount.getLastName()
                    + "," + newAccount.getPhoneNum() + "," + newAccount.getPassword() + "," + newAccount.getLastLogInTime();

            newLine += accountDetermineBirthdayCity(newAccount);

            writer.write(newLine + "\n");
            writer.close();
        } catch (FileNotFoundException notFound) {
            System.err.println(ACCOUNTS_FILEPATH + " " + FILE_NOT_FOUND_MESSAGE + " " + notFound.getMessage());
        } catch (IOException ioexception) {
            System.err.println("IOException" + ioexception.getMessage());
        }

    }

    private String accountDetermineBirthdayCity(Account account) {
        String newLine = "";
        if (account.getBirthday() == null && account.getCity() == null) {
            newLine += ",false,false";
        } else if (account.getBirthday() != null && account.getCity() == null) {
            newLine += ",true,false," + account.getBirthday();
        } else if (account.getBirthday() == null && account.getCity() != null) {
            newLine += ",false,true," + account.getCity();
        } else {
            newLine += ",true,true," + account.getBirthday() + "," + account.getCity();
        }
        return newLine;
    }
}
