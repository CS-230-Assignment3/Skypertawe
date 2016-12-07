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
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);


        searchPanel = new JPanel();
        searchPanel.setBounds(0, 0, 500, 400);

        JLabel searchLabel = new JLabel("Search");
        searchLabel.setBounds(200, 10, 50, 10);
        searchLabel.setSize(50, 10);

        JTextField searchBox = new JTextField();
        searchBox.setBounds(200, 20, 50, 10);
        searchBox.setSize(50, 10);

        JButton searchButton = new JButton();
        searchPanel.add(searchLabel);
        searchPanel.add(searchBox);
        searchLabel.add(searchButton);


        this.add(searchPanel);
        this.setContentPane(searchPanel);
        this.pack();
        this.setVisible(true);


    }
}
