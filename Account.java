import java.util.ArrayList;

public class Account implements Comparable<Account> {
	private String m_username;
	private String m_firstName;
	private String m_lastName;
	private String m_birthday;
	private String m_city;
	private String m_password;
	private String m_profilePicPath;
	private String m_lastLogInTime;
	private int m_phoneNum;
	private int m_newMessageNum;
	private ArrayList<Account> friends = new ArrayList<Account>();
	private ArrayList<Account> invites = new ArrayList<Account>();

	public Account(String username, String firstName, String lastName, String profilePicPath, String birthday, String city, String password, int phoneNum){
		this.m_username = username;
		this.m_firstName = firstName;
		this.m_lastName = lastName;
		this.m_password = password;
		this.m_phoneNum = phoneNum;
		this.m_birthday = birthday;
		this.m_city = city;
		this.m_profilePicPath = profilePicPath;
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
		return friends;
	}

	public void setFirstName(String newFirst){
		m_firstName = newFirst;
	}

	public void setLastName(String newLast){
		m_lastName = newLast;
	}

	public void setBirthday(String newBirthday){
		m_birthday = newBirthday;
	}

	public void setCity(String newCity){
		m_city = newCity;
	}

	public void setPassword(String newPassword){
		m_password = newPassword;
	}

	public void setProfilePic(String newProfilePicPath){
		m_profilePicPath = newProfilePicPath;
	}

	public void setPhoneNum(int newPhoneNum){
		m_phoneNum = newPhoneNum;
	}

	public void setNewMessageNum(int updateMessageNum){
		m_newMessageNum = updateMessageNum;
	}

	public void setLastLogInTime(String updateLogInTime){
		m_lastLogInTime = updateLogInTime;
	}

	public void setFriends(ArrayList<Account> updateFriends){
		friends = updateFriends;
	}

	public void addFriend(Account account) {
		friends.add(account);
	}

	public void removeFriend(Account account) {
		String accountName = account.getUser();
		for(Account obj : friends) {
			if(accountName.equals(obj.getUser())) {
				friends.remove(obj);
				break;
			}
		}
	}

	public void addInvite(Account newAccount) {
		invites.add(newAccount);
	}

	@Override
	public int compareTo(Account user) {
		return m_username.compareTo(user.getUser());
	}
}
