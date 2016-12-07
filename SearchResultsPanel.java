import javax.swing.*;
import java.awt.*;

/**
 * Created by Sam on 07/12/2016.
 */
public class SearchResultsPanel extends JFrame {
    private AccountsGraph m_accountsGraph;

    public SearchResultsPanel(AccountsGraph accountsGraph) {
        m_accountsGraph = accountsGraph;
        JPanel searchPanel = new JPanel();

        this.setTitle("Search");
        this.setSize(250, 500);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //set bounds doesn't seem to actually change the components
        searchPanel = new JPanel();
        searchPanel.setBounds(0, 0, 250, 500);

        JLabel searchLabel = new JLabel("Search");
        searchLabel.setBounds(110, 10, 10, 10);

        JTextField searchBox = new JTextField();
        searchBox.setBounds(125, 10, 20, 10);

        JButton searchButton = new JButton();
        searchPanel.add(searchLabel);
        searchPanel.add(searchBox);
        searchLabel.add(searchButton);


        this.add(searchPanel);
        this.setContentPane(searchPanel);


    }
}
