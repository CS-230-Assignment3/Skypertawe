import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame implements ActionListener {
    public MainWindow() {
		this.setTitle("Skypertawe - Welcome");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
        //loadAssets();
		//loadContacts();
	}

	private void loadAssets() {
        //Use this method to add general JPanels, JLabels ect
		//That have no context
    }

    private void loadContacts() {
		//Jamie, add your contactslist here
		//Add them to the window by doing "this.add(<jpanelhere>);"
		//Don't create JFrame cause it's extending it
		//Also wouldn't use pack() cause it'll fuck the window dimensions up ;)
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}

