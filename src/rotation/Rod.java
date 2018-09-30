package rotation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

class Rod extends Shape {    

    public Rod() {
        super();
        quarter = Quarter.FIRST;
        // mainBox = new Box(5, 6);
        
        initBoxes(Color.RED);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);
        
    }  
    
    public Rod(Heap heap) {
        super(heap);
        quarter = Quarter.FIRST;
        // mainBox = new Box(5, 6);
        
        initBoxes(Color.RED);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);
    }
    

    
    @Override
    public Box getNewMainBox(Quarter newQuarter, 
                            Quarter previousQuarter,
                            Box oldMainBox) {
        
        if (previousQuarter == Quarter.FIRST && newQuarter == Quarter.SECOND) {
            oldMainBox.setX(oldMainBox.getX() - 1);
            oldMainBox.setY(oldMainBox.getY() - 1);
        }
        if (previousQuarter == Quarter.SECOND && newQuarter == Quarter.THIRD) {
            oldMainBox.setX(oldMainBox.getX() + 1);
            oldMainBox.setY(oldMainBox.getY() - 2);
        }
        if (previousQuarter == Quarter.THIRD && newQuarter == Quarter.FOURTH) {
            oldMainBox.setX(oldMainBox.getX() + 2);
            oldMainBox.setY(oldMainBox.getY() + 2);
        }
        if (previousQuarter == Quarter.FOURTH && newQuarter == Quarter.FIRST) {
            oldMainBox.setX(oldMainBox.getX() - 2);
            oldMainBox.setY(oldMainBox.getY() + 1);
        }
        return oldMainBox;
    }
    
    @Override
    public void initBoxesLocationMap() {
        boxesMap = new EnumMap<>(Quarter.class);
        
        // let's initialize array of summands
        Integer[][] first = new Integer[][]{
            { 0,  0}, 
            { 0, -1},
            { 0, -2},
            { 0, -3}            
        };
        
        Integer[][] second = new Integer[][]{
            { 0,  0},
            { 1,  0},
            { 2,  0},
            { 3,  0}            
        };
        
        Integer[][] third = new Integer[][]{
            { 0,  0},
            { 0,  1},
            { 0,  2},
            { 0,  3}
        };
                
        Integer[][] fourth = new Integer[][]{
            { 0,  0},
            {-1,  0},
            {-2,  0},
            {-3,  0}
        };
        
        // and finally let's put everything in EnumMap
        boxesMap.put(Quarter.FIRST, first);
        boxesMap.put(Quarter.SECOND, second);
        boxesMap.put(Quarter.THIRD, third);
        boxesMap.put(Quarter.FOURTH, fourth);
    }
   
    
    @Override
    public void rotate(Heap heap) {
        
        previousQuarter = quarter;
        quarter = quarter.next();
        mainBox = getNewMainBox(quarter, previousQuarter, mainBox);
        placeBoxes(quarter, mainBox);
        
        // box can be found out of the border after rotation, let's check it
        for (Box box : boxes) {
            if (box.getX() < 1) {
                mainBox.setX(mainBox.getX() + 1);
                placeBoxes(quarter, mainBox);
            }
            if (box.getX() > 10) {
                int gapFromRightBorder = box.getX() - 10;
                mainBox.setX(mainBox.getX() - gapFromRightBorder);
                placeBoxes(quarter, mainBox);
            }
        }
        
    }
    
        
}