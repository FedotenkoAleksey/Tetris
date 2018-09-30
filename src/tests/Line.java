package tests;

import java.awt.Color;

public class Line extends Figure {
	Box[] boxes;
    Position position;

	public Line() {
		boxes = new Box[4];

		for (int i = 0; i < 4; i++) {
			boxes[i] = new Box(5, 9+i);
            boxes[i].setColor(Color.RED);
		}        
        
        position = Position.VERTICAL;
	}
    
    public void rotate() {
        // if posistion is VERTICAL rotate to HORIZONTAL
        
        // Debug: 
        System.out.println("The line is about to rotate!");
        System.out.println("Initial coords: ");
        System.out.print("Xs: ");
        for (int i = 0; i < 4; i++) {
            System.out.print(i + ": " + boxes[i].getX() + ", ");      
        }
        System.out.println();        
        System.out.print("Ys: ");
        for (int i = 0; i < 4; i++) {
            System.out.print(i + ": " + boxes[i].getY() + ", ");      
        }
        
        // end of Debug
        
        if (this.position == Line.Position.VERTICAL) {
            System.out.println("Initial position: " + position);
            int tempX = this.boxes[0].getX() - 1;
            int tempY = this.boxes[2].getY();
            
            // Debug:
            System.out.println("TempX:" + tempX + " TempY: " + tempY);
            
            for (int i = 0; i < this.boxes.length; i++) {
                this.boxes[i].setX(tempX + i);
                this.boxes[i].setY(tempY);
            }
            this.position = Line.Position.HORIZONTAL;
        } else {
        
        
            int tempX = this.boxes[1].getX();
            int tempY = this.boxes[0].getY() - 2;
            
            for (int i = 0; i < this.boxes.length; i++) {
                System.out.println("Inside the loop. i: " + i + " tempX: " + tempX + " tempY: " + tempY);
                this.boxes[i].setX(tempX);
                this.boxes[i].setY(tempY + i);
            }
            position = Line.Position.VERTICAL;
            
        }
        
        // Debug:         
        System.out.println("Subsequent position: " + position);
        System.out.println("Subsequent coords: ");
        System.out.print("Xs: ");
        for (int i = 0; i < 4; i++) {
            System.out.print(i + " " + boxes[i].getX() + ", ");      
        }
        System.out.println();        
        System.out.print("Ys: ");
        for (int i = 0; i < 4; i++) {
            System.out.print(i + " " + boxes[i].getY() + ", ");      
        }
        
        // end of Debug
        
    }
        
	

}