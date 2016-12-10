import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame {
	private AccountsGraph graph;

    public MainWindow(AccountsGraph graph) {
		this.setTitle("Skypertawe - Welcome ###username here ??");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		this.graph = graph;
        //loadAssets();
		//loadContacts();
	}

	private void loadAssets() {
        //Use this method to add general JPanels, JLabels ect
		//That have no context
    }

    private void loadContacts() throws Exception {
    	ContactPanel panel = new ContactPanel();
    	JPanel panel1 = panel.buildPanel();
    	panel1.setBounds(0, 100, 500,400);
    	this.setContentPane(panel1);
    	
    	
    	
    	
    	//Jamie, add your contactslist here
		//Add them to the window by doing "this.add(<jpanelhere>);"
		//Don't create JFrame cause it's extending it
		//Also wouldn't use pack() cause it'll fuck the window dimensions up ;)
	}
}

