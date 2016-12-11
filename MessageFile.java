import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MessageFile extends DescriptionMessage {

	public MessageFile(Account accountSend, String path, String textDescription) {
		super(accountSend, path, textDescription);
	}

	public MessageFile(Account accountSend, String path, String textDescription, String timestamp) {
		super(accountSend, path, textDescription, timestamp);
	}

	public String fileReader() {
		String fileContent = "Contents of file " + m_path + " :\n";
		File file = new File(m_path);
		Scanner in = null;
		try {
			in = new Scanner(file);
			while (in.hasNext()) {
				fileContent += in.nextLine() + "\n";
			}
		} catch (FileNotFoundException fileNotFound) {
			System.err.println(m_path + " not found\n" + fileNotFound.getStackTrace());
		} finally {
			in.close();
		}

		return fileContent;
	}

	public String display() {
		return m_accountSend.getUser() + ": \n" + fileReader() + m_textDescription;
	}
}