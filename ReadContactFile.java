import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class ReadContactFile {
	public static ArrayList<String> contactReader () throws Exception {
		FileReader fr = new FileReader("1.txt");
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
