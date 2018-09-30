package rotation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

class LShape extends Shape {    
    
    public LShape() {
        super();
        quarter = Quarter.THIRD; // initial quarter in which it appears
        // mainBox = new Box(11, 5);       
        // let's initialize boxes and give them any values which later will be
        // substituted with right values
        Color darkOrange = new Color(255, 165, 0);
        initBoxes(darkOrange);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);        
    } // end of constructor
    
    // the same constructor as am empty constructor but with Heap reference
    public LShape(Heap heap) {
        super(heap);
        quarter = Quarter.THIRD; // initial quarter in which it appears
        // mainBox = new Box(11, 5);       
        // let's initialize boxes and give them any values which later will be
        // substituted with right values
        Color darkOrange = new Color(255, 165, 0);
        initBoxes(darkOrange);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);   
    }
    
        
  
        
    // TODO add counterclockwise logic
    @Override
    public Box getNewMainBox(Quarter newQuarter, 
                            Quarter previousQuarter,
                            Box oldMainBox) {
        
        if (newQuarter == Quarter.SECOND && previousQuarter == Quarter.FIRST) {
            oldMainBox.setX(oldMainBox.getX() + 2);
            oldMainBox.setY(oldMainBox.getY() + 1);
        }
        if (newQuarter == Quarter.THIRD && previousQuarter == Quarter.SECOND) {
            oldMainBox.setX(oldMainBox.getX() - 1);
            oldMainBox.setY(oldMainBox.getY() + 1);
        }
        if (newQuarter == Quarter.FOURTH && previousQuarter == Quarter.THIRD) {
            oldMainBox.setX(oldMainBox.getX() - 1);
            oldMainBox.setY(oldMainBox.getY());
        }
        if (newQuarter == Quarter.FIRST && previousQuarter == Quarter.FOURTH) {
            oldMainBox.setX(oldMainBox.getX());
            oldMainBox.setY(oldMainBox.getY() - 2);
        }
        return oldMainBox;
        
        
    }
    
    @Override
    public void initBoxesLocationMap() {
        boxesMap = new EnumMap<>(Quarter.class);
        
        // let's initialize array of summands
        Integer[][] first = new Integer[][]{
            { 0, 0}, 
            { 0, 1},
            { 0, 2},
            { 1, 2}            
        };
        
        Integer[][] second = new Integer[][]{
            { 0,  0},
            {-1,  0},
            {-2,  0},
            {-2,  1}            
        };
        
        Integer[][] third = new Integer[][]{
            { 0,  0},
            { 0, -1},
            { 0, -2},
            {-1, -2}
        };
                
        Integer[][] fourth = new Integer[][]{
            { 0,  0},
            { 1,  0},
            { 2,  0},
            { 2, -1}
        };
        
        // and finally let's put everything in EnumMap
        boxesMap.put(Quarter.FIRST, first);
        boxesMap.put(Quarter.SECOND, second);
        boxesMap.put(Quarter.THIRD, third);
        boxesMap.put(Quarter.FOURTH, fourth);
    }
}