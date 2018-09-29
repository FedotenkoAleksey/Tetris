package rotation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/** The class is a component of any figure of tetris.
 * 
 * @author ALEXXX
 */ 
class Box {
    
    /**
     * SIZE is an actual constant size of a box.
     */
    private static final int SIZE = 20;
    
    /**
     * color is an initial color which is set to green here. It is typically reset
     * to some other color.
     */
    private Color color = Color.GREEN;
    
    /**
     * Coordinates x and y of the box.
     */
    private int x, y;

    /** Constructor of a Box with coordinates x and y.
     * 
     * @param x - x coordinate of the box
     * @param y - y coordinate of the box
     */
    public Box(int x, int y) {
        this.x = x * SIZE;
        this.y = y * SIZE;
    }
    
    /** Getter method 
     * 
     * @return the position of x from 1 to 10.
     */
    public int getX() {
        return x / SIZE;
    }
    
    /** Getter method
     * 
     * @return the position of y from 1 to 20.
     */
    public int getY() {
        return y / SIZE;
    }
    
    /** Getter method of SIZE constant
     * 
     * @return 
     */
    public int getSize() {
        return SIZE;
    }
    
    
    
    public void setX(int x) {
        this.x = x * SIZE; // makes coordinates 20 pixel matrix-based
        // TODO add some distance to make a box appear in the frame
    }
    
    public void setY(int y) {
        if (y <= 20) {
            this.y = y * SIZE; // makes coordinates 20 pixel matrix-based
            // TODO add some distance to make a box appear in the frame
        }
            
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x, y, SIZE, SIZE);
        
        // let's make darker shade
        int[] xPoints = {x, x + SIZE/5, x + SIZE/5, x + SIZE*4/5, x + SIZE, x};
        int[] yPoints = {y, y + SIZE/5, y + SIZE*4/5, y + SIZE*4/5, y + SIZE, y + SIZE};
        
        Polygon darkShade = new Polygon(xPoints, yPoints, 6);
        
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        
        int shade = 50;
        
        int newRed = 0;
        int newGreen = 0;
        int newBlue = 0;
        
        if (red-50 > 0) {
            newRed = red - 50;
        }
        if (green-50 > 0) {
            newGreen = green - 50;
        }
        if (blue-50 > 0) {
            newBlue = blue - 50;
        }
        
        Color darkerColor = new Color(newRed, newGreen, newBlue);
        g2.setColor(darkerColor);
        g2.fillPolygon(darkShade);
        
        // let's make lighter shade
        // let's make darker shade
        int[] xPointsLight = {x, x + SIZE, x + SIZE, x + SIZE*4/5, x + SIZE*4/5, x + SIZE/5};
        int[] yPointsLIght = {y, y, y + SIZE, y + SIZE*4/5, y + SIZE/5, y + SIZE/5};
        
        Polygon lightShade = new Polygon(xPointsLight, yPointsLIght, 6);
        
        int newRedLight = 255;
        int newGreenLight = 255;
        int newBlueLight = 255;
        
        if (red + 50 < 255) {
            newRedLight = red + 50;
        }
        if (green+50 < 255) {
            newGreenLight = green + 50;
        }
        if (blue+50 < 255) {
            newBlueLight = blue + 50;
        }
        
        Color lightColor = new Color(newRedLight, newGreenLight, newBlueLight);
        g2.setColor(lightColor);
        g2.fillPolygon(lightShade);
        
        
    }
    
}