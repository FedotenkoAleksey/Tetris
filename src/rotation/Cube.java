package rotation;

import java.awt.Color;
import java.util.EnumMap;

class Cube extends Shape {
    
    public Cube() {
        super();
        quarter = Quarter.FIRST;
        // mainBox = new Box(6, 18);
        initBoxes(Color.BLUE);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);
    }
    
    public Cube (Heap heap) {
        super(heap);
        quarter = Quarter.FIRST;
        // mainBox = new Box(6, 18);
        initBoxes(Color.BLUE);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);
    }
    
 
    
    @Override
    public Box getNewMainBox(Quarter newQuarter, 
                            Quarter previousQuarter,
                            Box oldMainBox) {
        return oldMainBox;
    }
    
    @Override
    public void initBoxesLocationMap() {
        boxesMap = new EnumMap<>(Quarter.class);
        
        // let's initialize array of summands
        Integer[][] first = new Integer[][]{
            { 0, 0}, 
            { 0, 1},
            {-1, 1},
            {-1, 0}            
        };
        
        Integer[][] second = new Integer[][]{
            { 0, 0}, 
            { 0, 1},
            {-1, 1},
            {-1, 0}              
        };
        
        Integer[][] third = new Integer[][]{
            { 0, 0}, 
            { 0, 1},
            {-1, 1},
            {-1, 0}  
        };
                
        Integer[][] fourth = new Integer[][]{
            { 0, 0}, 
            { 0, 1},
            {-1, 1},
            {-1, 0}  
        };
        
        // and finally let's put everything in EnumMap
        boxesMap.put(Quarter.FIRST, first);
        boxesMap.put(Quarter.SECOND, second);
        boxesMap.put(Quarter.THIRD, third);
        boxesMap.put(Quarter.FOURTH, fourth);
    }
}