import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResultsPanel extends JFrame {

    public SearchResultsPanel() {
        this.setTitle("Search");
		this.setContentPane(buildPanel());
		this.setSize(250, 400);
		this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
    }
    
    public JPanel buildPanel () {
		
    	AccountsGraph searchResults = new AccountsGraph();
    	JPanel contentPane = new JPanel(null);
    	 JLabel searchLabel = new JLabel("Search");
         searchLabel.setBounds(30, 30, 50, 20);

         JTextField searchBox = new JTextField(20);
         searchBox.setBounds(100, 30, 130, 20);
        
         
         JButton submitButton = new JButton("Submit");
         submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String content = searchBox.getText();
					Account newAccount = searchResults.findAccount(content);
					String newLine = newAccount.getUser() + "," + newAccount.getFirst() + "," + newAccount.getLastName();
					JButton ResultLinkButton = new JButton(newLine);
					ResultLinkButton.setBounds(0, 150, 250, 50);
					contentPane.add(ResultLinkButton);
					ResultLinkButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								System.out.print("it works, kinda. Needs to vaildate input at graph!");						
							}
						});
				}
			});
         
        submitButton.setBounds(100, 70, 130, 20);
         
        JLabel displayLabel = new JLabel("Results");
        displayLabel.setBounds(30, 100, 50, 20);
        
    	contentPane.setPreferredSize(new Dimension(250, 400));
		contentPane.add(searchLabel);	
		contentPane.add(searchBox);	
		contentPane.add(displayLabel);
		contentPane.add(submitButton);
	
		
		return contentPane;	
    }
    
    
	public static void main(String[] args){
		new SearchResultsPanel();
		
	}
}
