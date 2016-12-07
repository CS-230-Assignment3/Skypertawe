import java.util.ArrayList;

public class Account {

	private String m_username;
	private String m_firstName;
	private String m_lastName;
	private String m_birthday;
	private String m_city;
	private String m_password;
	private String m_profilePicPath;
	private int m_phoneNum;
	private int m_newMessageNum;
	private String m_lastLogInTime;
	private ArrayList<Account> m_friends = new ArrayList<Account>();
	private ArrayList<Account> m_invites = new ArrayList<Account>();

	public Account(String username, String firstName, String lastName, String birthday, String city, String password, String profilePicPath, int phoneNum){
		this.m_username = username;
		this.m_firstName = firstName;
		this.m_lastName = lastName;
		this.m_birthday = birthday;
		this.m_city = city;
		this.m_password = password;
		this.m_profilePicPath = profilePicPath;
		this.m_phoneNum = phoneNum;
	}

	public void setFirstName(String newFirst){
		this.m_firstName = newFirst;
	}

	public void setLastName(String newLast){
		this.m_lastName = newLast;
	}

	public void setBirthday(String newBirthday){
		this.m_birthday = newBirthday;
	}

	public void setCity(String newCity){
		this.m_city = newCity;
	}

	public void setPassword(String newPassword){
		this.m_password = newPassword;
	}

	public void setProfilePic(String newProfilePicPath){
		this.m_profilePicPath = newProfilePicPath;
	}

	public void setPhoneNum(int newPhoneNum){
		this.m_phoneNum = newPhoneNum;
	}

	public void setNewMessageNum(int updateMessageNum){
		this.m_newMessageNum = updateMessageNum;
	}

	public void setLastLogInTime(String updateLogInTime){
		this.m_lastLogInTime = updateLogInTime;
	}

	public void setFriends(ArrayList<Account> updateFriends){
		this.m_friends = updateFriends;
	}

	public void addFriend(Account account) {
		m_friends.add(account);
	}

	public void removeFriend(Account account) {
		boolean found = false;
		int currentIndex = 0;
		while (!found || currentIndex < m_friends.size()) {
			if (account.getUser().equals(m_friends.get(currentIndex).getUser())) {
				m_friends.remove(currentIndex);
				found = true;
			}
			currentIndex++;
		}
	}

	public String getUser(){
		return m_username;
	}

	public String getFirst(){
		return m_firstName;
	}

	public String getLastName(){
		return m_lastName;
	}

	public String getBirthday(){
		return m_birthday;
	}

	public String getCity(){
		return m_city;
	}

	public String getPassword(){
		return m_password;
	}

	public String getProfilePic(){
		return m_profilePicPath;
	}

	public int getPhoneNum(){
		return m_phoneNum;
	}

	public int getNewMessageNum(){
		return m_newMessageNum;
	}

	public String getLastLogInTime(){
		return m_lastLogInTime;
	}

	public ArrayList<Account> getFriends(){
		return m_friends;
	}

	public void addInvite(Account newAccount) {
		m_invites.add(newAccount);
	}

}
