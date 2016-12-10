import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MessageHistory {
	private String m_message;
	private String m_time;
	private String m_date;
	private String m_fileName;
	private String m_groupChatName;
	private String[] m_users;
	private String[] m_messages;
	private int[] m_accountColour;
	private Account m_accountSend;
	private Account m_accountRecieve;
	private ArrayList<Account> m_accountsRecieve;
	
	public MessageHistory(Account accountSend, Account accountRecieve) {
		m_accountSend = accountSend;
		m_accountRecieve = accountRecieve;
	
	}
	
	public MessageHistory(Account accountSend, ArrayList<Account> accountRecieve) {
		m_accountSend = accountSend;
		m_accountsRecieve = accountRecieve;
		
		m_accountsRecieve.add(m_accountSend);
		m_accountsRecieve.add(m_accountSend);
		Collections.sort(m_accountsRecieve);

		String userFileName= "";
		
		for (Account s : m_accountsRecieve)
		{
			userFileName +=  s.getUser() + "_" ;
		}
		
		m_fileName = "GroupFiles\\"+userFileName+".txt";
	}
	

	public void writeToFile(String message) {	
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(m_fileName))) {
			bw.write(message);
			//bw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readFromFile() {
		String line = "";
		Scanner in = null;
		File read = null;
		
		try {
			read = new File(m_fileName);
			in = new Scanner(read);
			
			if(read.exists() && read.isDirectory()) {
				while(in.hasNextLine()) {
					line = in.nextLine();
				}
			}
			in.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	
	public void createGroupFile (String groupName,ArrayList<Account> otherAccounts) {

		m_accountsRecieve.add(m_accountSend);
		m_accountsRecieve.addAll(otherAccounts);
		String userFileName;
		userFileName = "";
		for (Account s : m_accountsRecieve)
		{
			userFileName += "_" + s.getUser() ;
		}
		
		
		String filePath = "GroupFiles\\"+userFileName+".txt";
		Collections.sort(m_accountsRecieve);
		
	 	try {
	 		File file = new File(filePath);

		    if (file.createNewFile()){
		       System.out.println("File is created!");
		       FileWriter f0 = new FileWriter(filePath,true);
		       f0.write(groupName+"\n");
		       f0.close();
		    }else{
		    	System.out.print("File exists already!");
		    }
		      
	    	} catch (IOException e) {
		      e.printStackTrace();
		}
	}

	public String[] getUsers() {
		return m_users;
	}
	
	public String getMessage() {
		return m_message;
	}
	
	public String getTime() {
		return m_time;
	}
	
	public String getDate() {
		return m_date;
	}
	
	public int[] getAccountColour() {
		return m_accountColour;
	}
	
	public void setMessage(String fileName, String message) {
		m_message = message;
	}
	
	public void setAccountColour(int[] colour) {
		m_accountColour = colour;
	}
	
	public void setUsers(String[] users) {
		m_users = users;
	}
	
	public void setTime(String time) {
		m_time = time;
	}
	
	public void setDate(String date) {
		m_date = date;
	}
}
