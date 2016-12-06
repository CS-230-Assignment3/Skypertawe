import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Trace extends JPanel implements MouseListener, MouseMotionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mouseX ;
    private int mouseY;
    private int mouseX1;
    private int mouseY1;
    
    public Trace(String name) {
        super();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        JFrame fr = new JFrame(name);
        fr.add(this);
        fr.setSize(500, 500);
        fr.setBackground(Color.WHITE);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);

    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(6));
        g2d.setColor(Color.PINK);
        g2d.drawLine(getMouseX1(), getMouseY1(), getMouseX(), getMouseY());
    }


	public void mouseDragged(MouseEvent e) {
		
    	setMouseX(e.getX());
    	setMouseY(e.getY());
    	setMouseX1(e.getX());
    	setMouseY1(e.getY());
    	try {
			saveDrawing(e.getX());
			saveDrawing(e.getY());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
    	
        repaint();
    }

    public void mousePressed(MouseEvent e) {
    	setMouseX1(e.getX());
    	setMouseY1(e.getY());
    }

    public void mouseClicked(MouseEvent e) {
    	setMouseX1(e.getX());
    	setMouseY1(e.getY());
        repaint();
    }
    
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    
    public void saveDrawing(Integer drawing) throws Exception {
		FileWriter f0 = new FileWriter("ParticalDrawing.txt",true);
		f0.write(drawing+",");
		f0.close();
	}    
    
    public static void main(String[] args) {
        Trace trace = new Trace("Trace");
      
    
    }
    public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public int getMouseX1() {
		return mouseX1;
	}

	public void setMouseX1(int mouseX1) {
		this.mouseX1 = mouseX1;
	}

	public int getMouseY1() {
		return mouseY1;
	}

	public void setMouseY1(int mouseY1) {
		this.mouseY1 = mouseY1;
	}

}


