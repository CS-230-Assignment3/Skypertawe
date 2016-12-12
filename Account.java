import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @file Accounts.java
 * @author Samuel O'Reilly (824712), On Yiu (689480)
 * @date 11 Dec 2016
 *
 * A object of account, all data of account will be saved
 */
public class Account implements Comparable<Account> {
	
	/** The user name of a account*/
	private String m_username;
	/** The first name of a account user*/
	private String m_firstName;
	/** The last name of a account user*/
	private String m_lastName;
	/** The birthday of a account user*/
	private String m_birthday;
	/** The city of a account user which he is living*/
	private String m_city;
	/** The password of a account*/
	private String m_password;
	/** The path to the profile picture of a account*/
	private String m_profilePicPath;
	/** The last log in time of a account*/
	private String m_lastLogInTime;
	/** The phone number of a account user*/
	private String m_phoneNum;
	/** The number of unread messages of a account*/
	private int m_newMessageNum;
	/** The ArrayList of the all friends of a account*/
	private ArrayList<Account> friends = new ArrayList<Account>();
	/** The ArrayList of the all group chat of a account*/
	private ArrayList<ArrayList<Account>> groups = new ArrayList<ArrayList<Account>>();
	/** The ArrayList of String of txt file name of all group chat of a account*/
	private ArrayList<String> currUserGroupsFileNames = new ArrayList<String>();
	/** The ArrayList of account who request of add friends to this account*/
	private ArrayList<Account> invites = new ArrayList<Account>();

	
	
	/**
	 * Constructor:
     * Creates a Account.
     * @param username The user name of a account
     * @param firstName The first name of a account user
     * @param lastName The last name of a account user
     * @param profilePicPath The path to the profile picture of a account
     * @param birthday The birthday of a account user
     * @param city The city of a account user which he is living
     * @param password The password of a account
     * @param phoneNum The phone number of a account user
     */
	public Account(String username, String firstName, String lastName, String profilePicPath, String birthday, String city, String password, String phoneNum){
		this.m_username = username;
		this.m_firstName = firstName;
		this.m_lastName = lastName;
		this.m_profilePicPath = profilePicPath;
		this.m_birthday = birthday;
		this.m_city = city;
		this.m_password = password;
		this.m_phoneNum = phoneNum;
	}

	/**
	 * @return the invite list
	 */
	public ArrayList<Account> getInvites() {
		return invites;
	}

	/**
	 * @return the user name
	 */
	public String getUser(){
		return m_username;
	}

	/**
	 * @return the first name
	 */
	public String getFirst(){
		return m_firstName;
	}

	/**
	 * @return the last name
	 */
	public String getLastName(){
		return m_lastName;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday(){
		return m_birthday;
	}

	/**
	 * @return the city
	 */
	public String getCity(){
		return m_city;
	}

	/**
	 * @return the password
	 */
	public String getPassword(){
		return m_password;
	}

	/**
	 * @return the path to the profile picture
	 */
	public String getProfilePic(){
		return m_profilePicPath;
	}

	/**
	 * @return the phone number
	 */
	public String getPhoneNum(){
		return m_phoneNum;
	}

	/**
	 * @return the number of unread messages
	 */
	public int getNewMessageNum(){
		return m_newMessageNum;
	}

	/**
	 * @return the last log in time
	 */
	public String getLastLogInTime(){
		return m_lastLogInTime;
	}

	/**
	 * @return the ArrayList of all friends
	 */
	public ArrayList<Account> getFriends(){
		return friends;
	}
	
	/**
	 * @return the ArrayList of all group chat
	 */
	public ArrayList<ArrayList<Account>> getGroups() {
		return groups;
	}

	/**
	 * @return the ArrayList of the txt file name of all group chat
	 */
	public ArrayList<String> getGroupsFileNames() {
		return currUserGroupsFileNames;
	}
	
	/**
	 * @param newFirst reset the first name
	 */
	public void setFirstName(String newFirst){
		m_firstName = newFirst;
	}

	/**
	 * @param newLast reset the last name
	 */
	public void setLastName(String newLast){
		m_lastName = newLast;
	}

	/**
	 * @param newBirthday reset the birthday
	 */
	public void setBirthday(String newBirthday){
		m_birthday = newBirthday;
	}

	/**
	 * @param newCity reset the city
	 */
	public void setCity(String newCity){
		m_city = newCity;
	}

	/**
	 * @param newPassword reset the password
	 */
	public void setPassword(String newPassword){
		m_password = newPassword;
	}

	/**
	 * @param newProfilePicPath reset the path to the profile picture
	 * Use to change profile picture
	 */
	public void setProfilePic(String newProfilePicPath){
		m_profilePicPath = newProfilePicPath;
	}

	/**
	 * @param newPhoneNum reset the phone number
	 */
	public void setPhoneNum(String newPhoneNum){
		m_phoneNum = newPhoneNum;
	}

	/**
	 * @param updateMessageNum reset the number of unread messages
	 */
	public void setNewMessageNum(int updateMessageNum){
		m_newMessageNum = updateMessageNum;
	}

	/**
	 * @param updateLogInTime reset the last log in time
	 */
	public void setLastLogInTime(String updateLogInTime){
		m_lastLogInTime = updateLogInTime;
	}

	/**
	 * @param updateFriends reset all friends
	 */
	public void setFriends(ArrayList<Account> updateFriends){
		friends = updateFriends;
	}
	
	/**
	 * @param m_CurrUserGroups reset all group
	 */
	public void setGroups(ArrayList<ArrayList<Account>> m_CurrUserGroups) {
		groups = m_CurrUserGroups;
	}
	
	/**
	 * @param CurrUserGroupsFileNames reset the txt filename of all group chat
	 */
	public void setGroupsFileNames(ArrayList<String> CurrUserGroupsFileNames) {
		currUserGroupsFileNames.addAll(CurrUserGroupsFileNames);
	}

	/**
	 * @param account add a new for to a account
	 */
	public void addFriend(Account account) {
		friends.add(account);
	}

	/**
	 * @param account remove a friend from ArrayList of a account
	 */
	public void removeFriend(Account account) {
		String accountName = account.getUser();
		for(Account obj : friends) {
			if(accountName.equals(obj.getUser())) {
				friends.remove(obj);
				break;
			}
		}
	}

	/**
	 * @param newAccount the account who request to add friend to this account
	 * add it to the invite list
	 */
	public void addInvite(Account newAccount) {
		invites.add(newAccount);
	}

	/**
	 * @param user the account which used to compare
	 * in order to sort the account in alphabetical order of username
	 * @return the sorting result
	 */
	@Override
	public int compareTo(Account user) {
		return m_username.compareTo(user.getUser());
	}
}
