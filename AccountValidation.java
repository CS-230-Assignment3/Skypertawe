import java.util.ArrayList;

public class AccountValidation {

    private String m_checkUsername;
    private String m_checkPassword;
    private ArrayList<Account> m_accounts = new ArrayList<Account>();
    ;
    private AccountsFile m_accountsFile = new AccountsFile();

    public AccountValidation(String username, String password) {
        m_checkUsername = username;
        m_checkPassword = password;
        m_accounts = m_accountsFile.makeAccounts();
    }

    public boolean checkRegister() {
        boolean ans = true;
        int counter = 0;
        while (ans = true && m_accounts.size() > counter) {
            Account checkAccount = m_accounts.get(counter);
            if (checkAccount.getUser() == m_checkUsername) {
                ans = false;
            }
            counter++;
        }
        return ans;
    }

    public boolean checkLogin() {
        boolean ans = false;
        int counter = 0;
        while (ans = true && m_accounts.size() > counter) {
            Account checkAccount = m_accounts.get(counter);
            if (checkAccount.getUser() == m_checkUsername && checkAccount.getPassword() == m_checkPassword) {
                ans = true;
            }
            counter++;
        }
        return ans;
    }


}
