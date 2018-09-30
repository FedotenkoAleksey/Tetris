package rotation;

import java.awt.Color;
import java.util.EnumMap;

class SShape extends Shape {    
    
    public SShape() {
        super();
        quarter = Quarter.SECOND; // initial quarter
        // mainBox = new Box(18, 13);        
        Color aqua = new Color(0, 255, 255);
        initBoxes(aqua);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);
    }
    
    public SShape(Heap heap) {
        super(heap);
        quarter = Quarter.SECOND; // initial quarter
        // mainBox = new Box(18, 13);        
        Color aqua = new Color(0, 255, 255);
        initBoxes(aqua);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);
    }
    
    
    
    @Override
    public Box getNewMainBox(Quarter newQuarter, 
                            Quarter previousQuarter,
                            Box oldMainBox) {
        
        if (previousQuarter == Quarter.FIRST && newQuarter == Quarter.SECOND) {
            oldMainBox.setX(oldMainBox.getX());
            oldMainBox.setY(oldMainBox.getY() + 2);
        }
        if (previousQuarter == Quarter.SECOND && newQuarter == Quarter.THIRD) {
            oldMainBox.setX(oldMainBox.getX() - 2);
            oldMainBox.setY(oldMainBox.getY() - 1);
        }
        if (previousQuarter == Quarter.THIRD && newQuarter == Quarter.FOURTH) {
            oldMainBox.setX(oldMainBox.getX() + 1);
            oldMainBox.setY(oldMainBox.getY() - 1);
        }
        if (previousQuarter == Quarter.FOURTH && newQuarter == Quarter.FIRST) {
            oldMainBox.setX(oldMainBox.getX() + 1);
            oldMainBox.setY(oldMainBox.getY());
        }
        return oldMainBox;
    }
    
    
    @Override
    public void initBoxesLocationMap() {
        boxesMap = new EnumMap<>(Quarter.class);
        
        // let's initialize array of summands
        Integer[][] first = new Integer[][]{
            { 0, 0}, 
            {-1, 0},
            {-1, 1},
            {-2, 1}            
        };
        
        Integer[][] second = new Integer[][]{
            { 0,  0},
            { 0, -1},
            {-1, -1},
            {-1, -2}            
        };
        
        Integer[][] third = new Integer[][]{
            { 0,  0},
            { 1,  0},
            { 1, -1},
            { 2, -1}
        };
                
        Integer[][] fourth = new Integer[][]{
            { 0,  0},
            { 0,  1},
            { 1,  1},
            { 1,  2}
        };
        
        // and finally let's put everything in EnumMap
        boxesMap.put(Quarter.FIRST, first);
        boxesMap.put(Quarter.SECOND, second);
        boxesMap.put(Quarter.THIRD, third);
        boxesMap.put(Quarter.FOURTH, fourth);
    }
}