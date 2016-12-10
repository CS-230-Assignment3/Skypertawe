import java.awt.Color;

public class Line {

    private int m_x1;
    private int m_x2;
    private int m_y1;
    private int m_y2;
    private Color m_colour;

    public Line(int x1, int y1, int x2, int y2, Color colour) {
        this.m_x1 = x1;
        this.m_y1 = y1;
        this.m_x2 = x2;
        this.m_y2 = y2;
        this.m_colour = colour;
    }

    public int getX1() {
        return m_x1;
    }

    public int getY1() {
        return m_y1;
    }

    public int getX2() {
        return m_x2;
    }

    public int getY2() {
        return m_y2;
    }

    public Color getColour() {
        return m_colour;
    }

}
