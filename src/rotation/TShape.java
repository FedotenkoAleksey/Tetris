package rotation;

import java.awt.Color;
import java.util.EnumMap;

class TShape extends Shape {
    
    public TShape() {
        super();
        quarter = Quarter.FIRST;
        // mainBox = new Box(5, 13);
        
        initBoxes(Color.LIGHT_GRAY);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);
    }
    
    public TShape(Heap heap) {
        super(heap);
        quarter = Quarter.FIRST;
        // mainBox = new Box(5, 13);
        
        initBoxes(Color.LIGHT_GRAY);
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
            oldMainBox.setY(oldMainBox.getY() - 1);
        }
        if (previousQuarter == Quarter.SECOND && newQuarter == Quarter.THIRD) {
            oldMainBox.setX(oldMainBox.getX());
            oldMainBox.setY(oldMainBox.getY());
        }
        if (previousQuarter == Quarter.THIRD && newQuarter == Quarter.FOURTH) {
            oldMainBox.setX(oldMainBox.getX() + 1);
            oldMainBox.setY(oldMainBox.getY());
        }
        if (previousQuarter == Quarter.FOURTH && newQuarter == Quarter.FIRST) {
            oldMainBox.setX(oldMainBox.getX() - 1);
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
            {-1, -1},
            { 0, -1},
            { 1, -1}            
        };
        
        Integer[][] second = new Integer[][]{
            { 0,  0},
            { 1, -1},
            { 1,  0},
            { 1,  1}            
        };
        
        Integer[][] third = new Integer[][]{
            { 0,  0},
            { 1,  1},
            { 0,  1},
            {-1,  1}
        };
                
        Integer[][] fourth = new Integer[][]{
            { 0,  0},
            {-1,  1},
            {-1,  0},
            {-1, -1}
        };
        
        // and finally let's put everything in EnumMap
        boxesMap.put(Quarter.FIRST, first);
        boxesMap.put(Quarter.SECOND, second);
        boxesMap.put(Quarter.THIRD, third);
        boxesMap.put(Quarter.FOURTH, fourth);
    }
}