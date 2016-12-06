import java.util.ArrayList;

public class Account {
	
	private String username;
	private String firstName;
	private String lastName;
	private String birthday;
	private String city;
	private String password;
	private String profilePicPath;
	private int phoneNum;
	private int newMessageNum;
	private int lastLogOutTime;
	private ArrayList<String> friends = new ArrayList<String>();
	
	public Account(String username, String firstName, String lastName, String birthday, String city, String password, String profilePicPath, int phoneNum){
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.city = city;
		this.password = password;
		this.profilePicPath = profilePicPath;
		this.phoneNum = phoneNum;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setProfilePic(String profilePicPath){
		this.profilePicPath = profilePicPath;
	}
	
	public void setNewMessageNum(int newMessageNum){
		this.newMessageNum = newMessageNum;
	}
	
	public void setLastLogOutTime(int lastLogOutTime){
		this.lastLogOutTime = lastLogOutTime;
	}
	
	public void setFriends(ArrayList<String> friends){
		this.friends = friends;
	}
	
	public String getUser(){
		return username;
	}
	
	public String getFirst(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getBirthday(){
		return birthday;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getProfilePic(){
		return profilePicPath;
	}
	
	public int getNewMessageNum(){
		return newMessageNum;
	}
	
	public int getLastLogOutTime(){
		return lastLogOutTime;
	}
	
	public ArrayList<String> getFriends(ArrayList<String> friends){
		return friends;
	}

}
