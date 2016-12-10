import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame {
	private AccountsGraph graph;
	private Account m_currUser;
	
    public MainWindow(AccountsGraph graph, Account currUser) {
    	
    	m_currUser = currUser;
		this.setTitle("Skypertawe - Welcome ###username here ??");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		this.graph = graph;
        //loadAssets();
		loadContacts();
	}

	private void loadAssets() {
        //Use this method to add general JPanels, JLabels ect
		//That have no context
    }

    private void loadContacts(){
    	ContactPanel panel = new ContactPanel(m_currUser);
    	JPanel panel1 = panel.buildPanel();
    	panel1.setBounds(100, 50, 500,400);
    	this.setContentPane(panel1);
  
	}

}

