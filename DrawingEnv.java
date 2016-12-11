import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DrawingEnv extends JFrame {

    /**
     * First account the drawing is between
     */
    private Account m_firstAccount;

    /**
     * Second account the drawing is between
     */
    private Account m_secondAccount;

    /**
     * The width and height of the drawing window
     */
    public final static int FRAME_WIDTH = 550;

    public final static int FRAME_HEIGHT = 350;

    /** The width and height of the button which change the colour of element*/
    public final static int COLOURBUTTON_WIDTH = 20;

    public final static int COLOURBUTTON_HEIGHT = 20;

    /** The width and height of the button which change the drawing element*/
    public final static int MODEBUTTON_WIDTH = 200;

    public final static int MODEBUTTON_HEIGHT = 50;

    /** The width and height of the message displayed*/
    public final static int MESSAGE_WIDTH = 300;

    public final static int MESSAGE_HEIGHT = 25;

    public DrawingEnv(Account firstAccount, Account secondAccount) {
        m_firstAccount = firstAccount;
        m_secondAccount = secondAccount;
        this.setTitle("This is a drawing environment");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        loadAssets();
    }

    private void loadAssets() {
        DrawingElemPanel paintPanel = new DrawingElemPanel(m_firstAccount, m_secondAccount);
        paintPanel.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        paintPanel.setLayout(null);

        JLabel message = new JLabel("Draw something here");
        /* Create two button to switch the mode */
        JButton moveToLine = new JButton("Line Element");
        JButton moveToParticle = new JButton("Particle Effect");
	    /* Create the colour button to switch the colour */
        JButton blueColour = new JButton();
        JButton blackColour = new JButton();
        JButton redColour = new JButton();
        JButton greenColour = new JButton();

        message.setBounds(FRAME_WIDTH / 55, FRAME_HEIGHT - (MESSAGE_HEIGHT * 3), MESSAGE_WIDTH, MESSAGE_HEIGHT);
        moveToLine.setBounds(FRAME_WIDTH / 11, FRAME_HEIGHT / 35, MODEBUTTON_WIDTH, MODEBUTTON_HEIGHT);
        moveToParticle.setBounds(FRAME_WIDTH / 2, FRAME_HEIGHT / 35, MODEBUTTON_WIDTH, MODEBUTTON_HEIGHT);
        blueColour.setBounds(15, FRAME_HEIGHT / 35, COLOURBUTTON_WIDTH, COLOURBUTTON_HEIGHT);
        blackColour.setBounds(15, FRAME_HEIGHT / 10, COLOURBUTTON_WIDTH, COLOURBUTTON_HEIGHT);
        redColour.setBounds(FRAME_WIDTH - (COLOURBUTTON_WIDTH * 3), FRAME_HEIGHT / 35, COLOURBUTTON_WIDTH, COLOURBUTTON_HEIGHT);
        greenColour.setBounds(FRAME_WIDTH - (COLOURBUTTON_WIDTH * 3), FRAME_HEIGHT / 10, COLOURBUTTON_WIDTH, COLOURBUTTON_HEIGHT);

        blueColour.setBackground(Color.BLUE);
        blackColour.setBackground(Color.BLACK);
        redColour.setBackground(Color.RED);
        greenColour.setBackground(Color.GREEN);

        moveToLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                paintPanel.setMode("Line");
            }
        });

        moveToParticle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                paintPanel.setMode("Trace");
            }
        });

        blueColour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                paintPanel.setElemColor(Color.BLUE);
            }
        });

        blackColour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                paintPanel.setElemColor(Color.BLACK);
            }
        });

        redColour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                paintPanel.setElemColor(Color.RED);
            }
        });

        greenColour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                paintPanel.setElemColor(Color.GREEN);
            }
        });

        paintPanel.add(moveToLine);
        paintPanel.add(moveToParticle);
        paintPanel.add(blueColour);
        paintPanel.add(blackColour);
        paintPanel.add(redColour);
        paintPanel.add(greenColour);
        paintPanel.add(message);
        this.add(paintPanel);
        this.setVisible(true);

    }

}
