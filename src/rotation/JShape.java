package rotation;

import java.awt.Color;
import java.util.EnumMap;

class JShape extends Shape {
    
    
    public JShape () {
        super();
        quarter = Quarter.THIRD; // it appears from the third quarter
        // mainBox = new Box(17, 5);     
        
        // let's initialize boxes and give them any values which later will be
        // substituted with right values
        // TODO call fucntion initBoxes form abstract class
        initBoxes(Color.MAGENTA);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);        
        
    } // end of constructor
    
    public JShape(Heap heap) {
        super(heap);
        quarter = Quarter.THIRD; // it appears from the third quarter
        // mainBox = new Box(17, 5);     
        
        // let's initialize boxes and give them any values which later will be
        // substituted with right values
        // TODO call fucntion initBoxes form abstract class
        initBoxes(Color.MAGENTA);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);      
    }
    
    

    
    // TODO add counterclockwise logic
    @Override
    public Box getNewMainBox(Quarter newQuarter, 
                            Quarter previousQuarter,
                            Box oldMainBox) {
        
        if (previousQuarter == Quarter.FIRST && newQuarter == Quarter.SECOND) {
            oldMainBox.setX(oldMainBox.getX());
            oldMainBox.setY(oldMainBox.getY() + 2);
        }
        if (previousQuarter == Quarter.SECOND && newQuarter == Quarter.THIRD) {
            oldMainBox.setX(oldMainBox.getX() - 1);
            oldMainBox.setY(oldMainBox.getY());
        }
        if (previousQuarter == Quarter.THIRD && newQuarter == Quarter.FOURTH) {
            oldMainBox.setX(oldMainBox.getX() - 1);
            oldMainBox.setY(oldMainBox.getY() - 1);
        }
        if (previousQuarter == Quarter.FOURTH && newQuarter == Quarter.FIRST) {
            oldMainBox.setX(oldMainBox.getX() + 2);
            oldMainBox.setY(oldMainBox.getY() - 1);
        }
        return oldMainBox;
        
    } // end of method
    
    @Override
    public void initBoxesLocationMap() {
        boxesMap = new EnumMap<>(Quarter.class);
        
        // let's initialize array of summands
        Integer[][] first = new Integer[][]{
            { 0, 0}, 
            { 0, 1},
            { 0, 2},
            {-1, 2}            
        };
        
        Integer[][] second = new Integer[][]{
            { 0,  0},
            {-1,  0},
            {-2,  0},
            {-2, -1}            
        };
        
        Integer[][] third = new Integer[][]{
            { 0,  0},
            { 0, -1},
            { 0, -2},
            { 1, -2}
        };
                
        Integer[][] fourth = new Integer[][]{
            { 0,  0},
            { 1,  0},
            { 2,  0},
            { 2,  1}
        };
        
        // and finally let's put everything in EnumMap
        boxesMap.put(Quarter.FIRST, first);
        boxesMap.put(Quarter.SECOND, second);
        boxesMap.put(Quarter.THIRD, third);
        boxesMap.put(Quarter.FOURTH, fourth);
    }
    
    
}