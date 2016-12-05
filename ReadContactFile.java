import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class ReadContactFile {
	public static ArrayList<String> contactReader () throws Exception {
		FileReader fr = new FileReader("info.txt");
		BufferedReader br = new BufferedReader(fr);
		String s;
		ArrayList<String> list = new ArrayList<String>();
		while((s = br.readLine()) != null) {
			list.add(s);
		}
		fr.close();
		return list;
	}
	
	public static ArrayList<String> messageReader () throws Exception {
		FileReader fr = new FileReader("NewMessage.txt");
		BufferedReader br = new BufferedReader(fr);
		String s;
		ArrayList<String> list = new ArrayList<String>();
		while((s = br.readLine()) != null) {
			list.add(s);
		}
		fr.close();
		return list;
		} 
}
