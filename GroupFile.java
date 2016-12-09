import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;


public class GroupFile {

	
	private Account m_currentAccount;
	private Account m_otherAccount;
	private ArrayList<Account> m_otherAccounts;


	public GroupFile(Account currentAccount, ArrayList<Account> otherAccounts) {
		m_currentAccount = currentAccount;
		m_otherAccounts = otherAccounts;

		//vaildate user
		
	}

	public void createGroupFile (String groupName) throws IOException{
		m_otherAccounts.add(m_currentAccount);
		Collections.sort(m_otherAccounts);
		String fileName = "";
		for (Account s : m_otherAccounts)
		{
			fileName += s.getUser() + "_";
		}

		System.out.println(fileName);
	 	try {

		      File file = new File("GroupFiles\\"+fileName+".txt");

		      if (file.createNewFile()){
		        System.out.println("File is created!");
		      }else{
		        System.out.println("File already exists.");
		      }
		      
	    	} catch (IOException e) {
		      e.printStackTrace();
		}
		FileWriter f0 = new FileWriter("GroupFiles\\"+fileName+".txt",true);
		f0.write(groupName+"\n");
		f0.close();
	}
	
	
	
	public void writeGroupChat(Account user, String message, String date, String time,String m_filename) {	
		
		try {
			FileWriter f0 = new FileWriter("GroupFiles\\"+m_filename,true);
		f0.write(user.getUser()+','+date+','+time+','+message+"\n");
		f0.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readGroupChat(){
		
	}

	public static void main(String args[]) throws IOException{
		
		AccountsGraph account1 = new AccountsGraph();
    	Account cur = account1.findAccount("katie");
    	Account oth = account1.findAccount("sam");
    	Account oth1 = account1.findAccount("jez");
    	Account oth2 = account1.findAccount("john");
    	ArrayList<Account> h = new ArrayList<Account>();
    	h.add(oth);
    	h.add(oth1);
    	h.add(oth2);
		GroupFile n = new GroupFile(cur, h);
		//n.createGroupFile("jamie");
		n.writeGroupChat(cur,"djkasl","12/12","12pm","jez_john_katie_sam_.txt");
	}
}
