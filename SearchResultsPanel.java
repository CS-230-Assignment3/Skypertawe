import javax.swing.*;
import java.awt.*;

/**
 * Created by Sam on 07/12/2016.
 */
public class SearchResultsPanel extends JFrame {
    private AccountsGraph m_accountsGraph;
    private JPanel searchPanel;
    private JPanel resultsPanel;

    public SearchResultsPanel(AccountsGraph accountsGraph) {
        m_accountsGraph = accountsGraph;

        this.setTitle("Search");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);


    }
}
