import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JPanel;


public class DrawingElemPanel extends JPanel {

    /**
     * The width of the point of ParticleEffect
     */
    private final int OVAL_WIDTH = 4;

    /** The height of the point of ParticleEffect*/
    private final int OVAL_HEIGHT = 4;

    /** The maximum number of drawable points. */
    private final int MAX_POINTS = 10000;

    /** count number of points of ParticleEffect*/
    private int m_PointCount = 0;

    /** A array of the all points of ParticleEffect*/
    private Point m_Points[] = new Point[MAX_POINTS];

    /** A String of the mode of drawing, start at drawing Line*/
    private String m_mode = "Line";

    /**
     * The start point of the Line which is drawing
     */
    private Point m_starting = new Point(0, 0);

    /** The end point of the Line which is drawing*/
    private Point m_stoping;

    /** The ArrayList of all the Line which has drawn*/
    private ArrayList<Line> m_Line = new ArrayList<Line>();

    /** The Color of the Line element*/
    private Color m_colour = Color.BLACK;

    /** The array of Color of the ParticleEffect element*/
    private Color[] m_colourpoint = new Color[MAX_POINTS];

    /** Two txt file for saving the all element which has drawn*/
    private String m_linefile;

    private String m_particalfile;

    /**
     * First account the drawing is between
     */
    private Account m_firstAccount;

    /**
     * Second account the drawing is between
     */
    private Account m_secondAccount;


    /**
     * @return the all lines which has drawn
     */
    public ArrayList<Line> getLines(){
        return m_Line;
    }

    /**
     * @return the current number of points
     */
    public int getPointCount() {
        return m_PointCount;
    }

    /**
     * @return TRUE on success
     */
    public boolean incrementPointCount() {
        m_PointCount++;
        return true;
    }

    /**
     * @return the current number of points
     */
    public Point[] getPoints() {
        return m_Points;
    }

    /**
     * @return the current colour of element
     */
    public Color getElemColor() {
        return m_colour;
    }

    /**
     * @return the array of colour of the point in ParticleEffect
     */
    public Color[] getPointColor(){
        return m_colourpoint;
    }

    /**
     * @param point
     * @return TRUE on success
     */
    public boolean setPoint(Point point) {
        boolean test = false;
        if (test) {
            System.out.println("DrawingElemPanel::setPoint() - " + m_PointCount + ", " + point.toString());
        }
        m_Points[getPointCount() ] = point;
        return true;
    }

    /**
     * @param mode
     * Use to switch the mode for drawing the other element
     * call by the JButton in the JFrame
     */
    public void setMode(String mode){
        m_mode = mode;
        System.out.println("DrawingElemPanel::setMode() - " + m_mode);
        DrawingHandler handler = new DrawingHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    /**
     * @param colour reset the array of colour of the point in ParticleEffect
     */
    public void setPointColor(Color colour){
        m_colourpoint[getPointCount()] = colour;
    }

    /**
     * @param colour reset the colour of element
     */
    public void setElemColor(Color colour){
        m_colour = colour;
    }

    /**
     * @param point reset the start point of line element
     */
    public void setStart(Point point){
        m_starting = point;
    }

    /**
     * @param point reset the end point of line element
     */
    public void setStop(Point point){
        m_stoping = point;
    }

    /**
     * @param line add the new line of ArrayList of all line
     */
    public void addLines(Line line){
        m_Line.add(line);
    }

    public void setLines(ArrayList<Line> line) {
        m_Line = line;
    }

    public void setAllPoint(Point[] point) {
        m_Points = point;
    }

    public void setPointCount(int count) {
        m_PointCount = count;
    }

    public void setAllPointColor(Color[] colour) {
        m_colourpoint = colour;
    }

    /**
     * Constructor:
     * set up GUI
     */
    public DrawingElemPanel(Account firstAccount, Account secondAccount){

        m_firstAccount = firstAccount;
        m_secondAccount = secondAccount;

        // If first account username <= second account username
        if (m_firstAccount.getUser().compareTo(m_secondAccount.getUser()) <= 0) {
            m_linefile = "drawingFiles\\line_" + (m_firstAccount.getUser() + "_" + m_secondAccount.getUser() + ".txt");
            m_particalfile = "drawingFiles\\partical_" + (m_firstAccount.getUser() + "_" + m_secondAccount.getUser() + ".txt");
        } else { // Else second account username > first account username
            m_linefile = "drawingFiles\\line_" + m_secondAccount.getUser() + "_" + (m_firstAccount.getUser() + ".txt");
            m_particalfile = "drawingFiles\\partical_" + (m_secondAccount.getUser() + "_" + m_firstAccount.getUser() + ".txt");
        }

        boolean test = false;
        if (test) {
            System.out.println("DrawingElemPanel::constructor() ");
        }

        loadFile(m_linefile, m_particalfile);
        repaint();
    }

    public void loadFile(String lineFile, String partFile) {
        ArrayList<Line> allLine = new ArrayList<Line>();
        Point[] allPoint = new Point[MAX_POINTS];
        Color[] allColor = new Color[MAX_POINTS];

        File inputFile = new File(lineFile);

        if (!inputFile.exists()) {
            return;
        }

        Scanner in = null;
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Can't open " + lineFile);
            System.exit(0);
        }
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Scanner in2 = new Scanner(line);
            String[] wholeLine = in2.nextLine().split(",");
            int x1 = Integer.parseInt(wholeLine[0]);
            int y1 = Integer.parseInt(wholeLine[1]);
            int x2 = Integer.parseInt(wholeLine[2]);
            int y2 = Integer.parseInt(wholeLine[3]);
            int r = Integer.parseInt(wholeLine[4]);
            int g = Integer.parseInt(wholeLine[5]);
            int b = Integer.parseInt(wholeLine[6]);
            Color newColuor = new Color(r, g, b);
            Line newLine = new Line(x1, y1, x2, y2, newColuor);
            allLine.add(newLine);
            in2.close();
        }
        in.close();
        setLines(allLine);
        File inputFile2 = new File(partFile);
        Scanner in3 = null;
        int counter = 0;
        try {
            in3 = new Scanner(inputFile2);
        } catch (FileNotFoundException e) {
            System.out.println("Can't open " + partFile);
            System.exit(0);
        }
        while (in3.hasNextLine()) {
            String line = in3.nextLine();
            Scanner in2 = new Scanner(line);
            String[] wholeLine = in2.nextLine().split(",");
            int x = Integer.parseInt(wholeLine[0]);
            int y = Integer.parseInt(wholeLine[1]);
            int r = Integer.parseInt(wholeLine[2]);
            int g = Integer.parseInt(wholeLine[3]);
            int b = Integer.parseInt(wholeLine[4]);
            Color newColour = new Color(r, g, b);
            allPoint[counter] = new Point(x, y);
            allColor[counter] = newColour;
            counter++;
            in2.close();
        }
        setAllPoint(allPoint);
        setAllPointColor(allColor);
        setPointCount(counter);
        in3.close();
    }


    /**
     * @param drawing add the ArrayList of all line
     * Use to save all line element which has drawn
     * throw IOException
     */
    public void saveLine(ArrayList<Line> drawing) throws IOException {
        File inputFile = new File(m_linefile);
        FileWriter writer = new FileWriter(inputFile);
        for (int i = 0; i < drawing.size(); i++) {
            writer.write(drawing.get(i).getX1() + "," + drawing.get(i).getY1() + "," + drawing.get(i).getX2()
                    + "," + drawing.get(i).getY2() + "," + drawing.get(i).getColour().getRed()
                    + "," + drawing.get(i).getColour().getGreen() + "," + drawing.get(i).getColour().getBlue()+"\n");
        }
        writer.close();
    }

    /**
     * @param drawing add the points of all ParticleEffect
     * @param drawingColour the colour of all points in ParticleEffect
     * Use to save all ParticleEffect element which has drawn
     * throw IOException
     */
    public void savePartical(Point[] drawing, Color[] drawingColour) throws IOException {
        File inputFile = new File(m_particalfile);
        FileWriter writer = new FileWriter(inputFile);
        for (int i = 0; i < this.getPointCount(); i++) {
            writer.write(drawing[i].x + "," + drawing[i].y + "," + drawingColour[i].getRed()
                    + "," + drawingColour[i].getGreen() + "," + drawingColour[i].getBlue()+"\n");
        }
        writer.close();
    }

    /**
     * Draw an oval in a OVAL_WIDTH-by-OVAL_HEIGHT bounding box
     * and all line which is saved
     * at specified location on window.  This method is called
     * from DrawingHandler::mouseDragged() or DrawingHandler::mouseReleased().
     */
    public void paintComponent( Graphics graphics ) {

        boolean test = false;
        if (test) {
            System.out.println("PaintPanel::paintComponent() " + graphics.toString());
        }
           /* clears drawing area */
        super.paintComponent( graphics );
	      /* draw all points in array and all line */
        for (int i = 0; i < this.getPointCount(); i++ ) {
            graphics.setColor(getPointColor()[i]);
            graphics.fillOval(getPoints()[i].x, /* upper-left x coord */
                    getPoints()[i].y, /* upper-left y coord */
                    OVAL_WIDTH , OVAL_HEIGHT);
        }

        for (int i = 0; i < m_Line.size(); i++){
            Line drawing = m_Line.get(i);
            graphics.setColor(m_Line.get(i).getColour());
            graphics.drawLine(drawing.getX1(), drawing.getY1(),
                    drawing.getX2(), drawing.getY2());

        }
    }

    /**
     * Private inner class for event handling of particle effect and lines.
     * It uses and implements the MouseListener and MouseMotionListener
     * interfaces.
     */
    private class DrawingHandler implements MouseListener, MouseMotionListener {

        /**
         * This method is defined in MouseMotionListener.
         * store drag coordinates and repaint
         */
        public void mouseDragged(MouseEvent event) {

            boolean test = false;
            if (test) {
                System.out.println("DrawingHandler::mouseDragged() " + event.toString());
            }
            if (m_mode.equals("Trace")) {
                if (getPointCount() < getPoints().length ) {
                    /* find and store point */
                    setPoint(event.getPoint());
	        		/* find and store the colour of the point */
                    setPointColor(getElemColor());
	        		/* write in txt file */
                    try{
                        savePartical(getPoints(), getPointColor());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
	        		/* increment number of points in array **/
                    incrementPointCount();
	        		/* repaint JFrame */
                    repaint();
                }
            }
        }

        public void mouseMoved(MouseEvent event) {

            boolean test = false;
            if (test) {
                System.out.println("DrawingHandler::mouseMoved() " + event.toString());
            }
	        /* reset the start point for drawing line */
            if(m_mode.equals("Line")){
                setStart(event.getPoint());
            }
        }

        public void mouseEntered(MouseEvent event) {

            boolean test = true;
            if (test) {
                System.out.println("DrawingHandler::mouseEntered() " + event.toString());
            }
        }

        public void mouseExited(MouseEvent event) {

            boolean test = true;
            if (test) {
                System.out.println("DrawingHandler::mouseExited() " + event.toString());
            }
        }

        public void mouseClicked(MouseEvent event) {

            boolean test = true;
            if (test) {
                System.out.println("DrawingHandler::mouseClicked() " + event.toString());
            }
	        /* reset the start point for drawing line */
            if(m_mode.equals("Line")){
                setStart(event.getPoint());
            }
        }

        public void mousePressed(MouseEvent event) {

            boolean test = true;
            if (test) {
                System.out.println("DrawingHandler::mousePressed() " + event.toString());
            }
        }

        public void mouseReleased(MouseEvent event) {

            boolean test = true;
            if (test) {
                System.out.println("DrawingHandler::mouseReleased() " + event.toString());
            }
            if(m_mode.equals("Line")){
                 /*When user released the mouse, save the end point*/
                setStop(event.getPoint());
	        	 /*Make the line between start point and end point*/
                Line newLine = new Line(m_starting.x, m_starting.y,
                        m_stoping.x, m_stoping.y, m_colour);
	        	 /* add it to the ArrayList of all lines */
                addLines(newLine);
	        	 /* write in txt file */
                try{
                    saveLine(getLines());
                } catch (IOException e){
                    e.printStackTrace();
                }
	        	 /* repaint JFrame */
                repaint();
	        	 /*Reset the start point for next line*/
                setStart(event.getPoint());
            }
        }

    }
}
