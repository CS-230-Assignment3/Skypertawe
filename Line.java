import java.awt.Color;

public class Line {

    /** The x and y position of the start point of a line*/
    private int m_x1;
    private int m_y1;
    /** The x and y position of the end point of a line*/
    private int m_x2;
    private int m_y2;
    /** The colour of a line*/
    private Color m_colour;

    /**
     * Constructor:
     * save the data of line
     */
    public Line(int x1, int y1, int x2, int y2, Color colour){
        this.m_x1 = x1;
        this.m_y1 = y1;
        this.m_x2 = x2;
        this.m_y2 = y2;
        this.m_colour = colour;
    }

    /**
     * @return the x position of the start point of the line
     */
    public int getX1(){
        return m_x1;
    }

    /**
     * @return the y position of the start point of the line
     */
    public int getY1(){
        return m_y1;
    }

    /**
     * @return the x position of the end point of the line
     */
    public int getX2(){
        return m_x2;
    }

    /**
     * @return the y position of the end point of the line
     */
    public int getY2(){
        return m_y2;
    }

    /**
     * @return the colour of the line
     */
    public Color getColour(){
        return m_colour;
    }

    /**
     * @param newX1 reset x position of the start point of the line
     */
    public void setX1(int newX1){
        m_x1 = newX1;
    }

    /**
     * @param newY1 reset y position of the start point of the line
     */
    public void setY1(int newY1){
        m_y1 = newY1;
    }

    /**
     * @param newX2 reset x position of the end point of the line
     */
    public void setX2(int newX2){
        m_x2 = newX2;
    }

    /**
     * @param newY2 reset y position of the end point of the line
     */
    public void setY2(int newY2){
        m_y2 = newY2;
    }

    /**
     * @param newColour reset the colour of the line
     */
    public void setColour(Color newColour){
        m_colour = newColour;
    }

}
