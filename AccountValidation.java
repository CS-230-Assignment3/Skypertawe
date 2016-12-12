import java.util.ArrayList;

/**
 * @file AccountValidation.java
 * @author On Yiu (689480), Luke Harvey
 * @date 11th dec 2016
 * @see ChatPanel.java, RegisterPanel.java
 */
public class AccountValidation {
    private String m_username;
    private String m_password;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private AccountsFile accountsFile = new AccountsFile();

    /**
     * This constructor is used for the validation of login
     * @param username The username the user has entered
     * @param password The password the user has entered
     */
    public AccountValidation(String username, String password) {
        m_username = username;
        m_password = password;
        accounts = accountsFile.makeAccounts();
    }

    /**
     * This constructor is used for the validation of register
     * @param username The username the user has entered
     */
    public AccountValidation(String username) {
        m_username = username;
        accounts = accountsFile.makeAccounts();
    }

    /**
     * This method checks to see if
     * the username that the user has
     * entered is available
     * @return True is available, false otherwise
     */
    public boolean checkRegister() {
        boolean isAccountFree = true;
        for(Account obj : accounts) {
            if(obj.getUser().equals(m_username)) {
                isAccountFree = false;
                break;
            }
        }
        return isAccountFree;
    }

    /**
     * This method checks to see if the
     * login credentials that the user has
     * entered are valid
     * @return True if they are valid, false otherwise
     */
    public boolean checkLogin() {
        boolean isAccountValid = false;
        for(Account obj : accounts) {
            if(obj.getUser().equals(m_username) && obj.getPassword().equals(m_password)) {
                isAccountValid = true;
                break;
            }
        }
        return isAccountValid;
    }
}
