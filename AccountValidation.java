import java.util.ArrayList;

public class AccountValidation {
    private String m_username;
    private String m_password;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private AccountsFile accountsFile = new AccountsFile();

    public AccountValidation(String username, String password) {
        m_username = username;
        m_password = password;
        accounts = accountsFile.makeAccounts();
    }

    public AccountValidation(String username) {
        m_username = username;
        accounts = accountsFile.makeAccounts();
    }

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
