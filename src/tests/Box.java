/* The box class for Tetris figures */

package tests;

import java.awt.Color;

public class Box {
	private int x;
	private int y;
	private Color boxColor;

	public Box(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
    
    public void setX(int x) {
        this.x = x;
        System.out.println("X is set to: " + this.x);
    }
    
    public void setY(int y) {
        this.y = y;
        System.out.println("Y is set to: " + this.y);
    }

	public Color getColor() {
		return boxColor;
	}

	public void setColor(Color newColor) {
		this.boxColor = newColor;
	}
}