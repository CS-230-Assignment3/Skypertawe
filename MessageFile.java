import java.io.*;
import java.util.Scanner;

/**
 * @file DescriptionMessage.java
 * @author Luke Harvey and Samuel O'Reilly
 * @date 11 Dec 2016
 * @see DescriptionMessage.java
 */
public class MessageFile extends DescriptionMessage {

	/**
	 * Sets the account message is sent from, the filepath to the item being sent
	 * and a description of what is being sent. This sets the send time to current system time
	 * @param accountSend  account sending the message
	 * @param path path to file being sent
	 * @param textDescription short description of file being sent
	 * @see DescriptionMessage.java
	 */
	public MessageFile(Account accountSend, String path, String textDescription) {
		super(accountSend, path, textDescription);
	}

	/**
	 * Sets the account message is sent from, the filepath to the item being sent
	 * and a description of what is being sent. This sets the send time to a given timestamp
	 * @param accountSend  account sending the message
	 * @param path path to file being sent
	 * @param textDescription short description of file being sent
	 * @see DescriptionMessage.java
	 */
	public MessageFile(Account accountSend, String path, String textDescription, String timestamp) {
		super(accountSend, path, textDescription, timestamp);
	}

	/**
	 * Reads the contents of the file, and outputs it as a string
	 * @return Contents of the file as a String
	 */
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

	/**
	 * Implements abstract method from Message, returns a string containing the username of account that sent the message
	 * and the contents of the file, and a short text description
	 * @return Formatted string containing the account who sent the message, the file's content, and a description
	 * @see fileReader()
	 */
	public String display() {
		return m_accountSend.getUser() + ": \n" + fileReader() + m_textDescription;
	}
}