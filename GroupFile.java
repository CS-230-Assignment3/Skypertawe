import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GroupFile {

	
	private Account m_currentAccount;
	private ArrayList<Account> m_otherAccounts;
	private String m_message;
	private int[] m_accountColour;
	private String date;
	private String time;
	private List<String> allGroups = new ArrayList<String>();
	
	public GroupFile(Account currentAccount, ArrayList<Account> otherAccounts) {
		m_currentAccount = currentAccount;
		m_otherAccounts = otherAccounts;
			
	}
	

	public void openGroup(String FileName) {
		
		//get all groups a current user is in
		//open a file that user wants using Gui
		//returns file path or set user file name
		
	}
	
	
	
	public void createGroupFile (String groupName) throws IOException{
		String userFileName;
		m_otherAccounts.add(m_currentAccount);
		Collections.sort(m_otherAccounts);
			
		userFileName = "";
		for (Account s : m_otherAccounts)
		{
			userFileName +=  s.getUser()+"_"  ;
		}
		
		System.out.println(userFileName);
	 	try {
	 		File file = new File("GroupFiles\\"+userFileName+".txt");

		    if (file.createNewFile()){
		       System.out.println("File is created!");
		       FileWriter f0 = new FileWriter("GroupFiles\\"+userFileName+".txt",true);
		       f0.write(groupName+"\n");
		       System.out.println(userFileName);
		       f0.close();
		    }else{
		    	System.out.println("File already exists.");
		    }
		      
	    	} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	public void writeGroupChat(String userFileName) {	
		
		try {
			String filePath = "GroupFiles\\" + userFileName;
			
			 File f = new File(filePath);

			  if(f.exists()){
				  FileWriter f0 = new FileWriter(filePath,true);
				  f0.write(m_currentAccount.getUser()+','+date+','+time+','+m_message+"\n");
				  f0.close();
			  }else{
				  System.out.println("File not found!");
			  }
	
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public void readGroupChat(){
		//read from file using file name
	}

	public static void main(String args[]) throws IOException{
		
	AccountsGraph account1 = new AccountsGraph();
    	Account cur = account1.findAccount("katie");
    	Account oth = account1.findAccount("sam");
    	Account oth1 = account1.findAccount("jez");
    	Account oth2 = account1.findAccount("john");
    	
    	Account oth3 = account1.findAccount("yam");
    	Account oth4 = account1.findAccount("jez");
    	Account oth5 = account1.findAccount("john");
    	
    	
    	
    	ArrayList<Account> h = new ArrayList<Account>();
    	h.add(oth);
    	h.add(oth1);
    	h.add(oth2);
    	
    	ArrayList<Account> h1 = new ArrayList<Account>();
    	h1.add(oth3);
    	h1.add(oth4);
    	h1.add(oth5);
    	GroupFile n1 = new GroupFile(cur, h1);
    	
    	n1.createGroupFile("jamie1");
    	//n1.writeGroupChat("djkasl","12/12","12pm","_jez_john_katie_yam.txt");
    	
		GroupFile n = new GroupFile(cur, h);
		n.createGroupFile("jamie");
		//n.writeGroupChat("djkasl","12/12","12pm","_jez_john_katie_sam.txt");
		System.out.print(n.allGroups);
	}
}
