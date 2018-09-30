package rotation;

import java.awt.Color;
import java.util.EnumMap;

class ZShape extends Shape {
    
    public ZShape() {
        super();
        quarter = Quarter.FOURTH;
        // mainBox = new Box(10, 13);
        
        Color mediumGreen = new Color(0, 170, 0);
        
        initBoxes(mediumGreen);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);
    }
    
    public ZShape(Heap heap) {
        super(heap);
        quarter = Quarter.FOURTH;
        // mainBox = new Box(10, 13);
        
        Color mediumGreen = new Color(51, 230, 51);
        
        initBoxes(mediumGreen);
        initBoxesLocationMap();
        placeBoxes(quarter, mainBox);
    }
    
    /*
    @Override 
    public void placeBoxes(Quarter quarter, Box mainBox) {
        switch (quarter) {
            case FIRST:
                for (int i = 0; i < 4; i++) {
                    if (i == 3) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() + 2);
                        box.setY(mainBox.getY() + 1);                        
                        continue;
                    }
                    if (i == 2) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() + 1);
                        box.setY(mainBox.getY() + 1);                        
                        continue;
                    }
                    if (i == 1) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() + 1);
                        box.setY(mainBox.getY());                        
                        continue;
                    }
                    if (i == 0) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX());
                        box.setY(mainBox.getY());                        
                        continue;
                    }                   
                    
                } // end of loop
                
                break;
                
                
            case SECOND:
                for (int i = 0; i < 4; i++) {
                    if (i == 3) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() - 1);
                        box.setY(mainBox.getY() + 2);                        
                        continue;
                    }
                    if (i == 2) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() - 1);
                        box.setY(mainBox.getY() + 1);                        
                        continue;
                    }
                    if (i == 1) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX());
                        box.setY(mainBox.getY() + 1);                        
                        continue;
                    }
                    if (i == 0) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX());
                        box.setY(mainBox.getY());                        
                        continue;
                    }                   
                    
                } // end of loop
                
                break;
                
            case THIRD:
                for (int i = 0; i < 4; i++) {
                    if (i == 3) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() - 2);
                        box.setY(mainBox.getY() - 1);                        
                        continue;
                    }
                    if (i == 2) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() - 1);
                        box.setY(mainBox.getY() - 1);                        
                        continue;
                    }
                    if (i == 1) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() - 1);
                        box.setY(mainBox.getY());                        
                        continue;
                    }
                    if (i == 0) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX());
                        box.setY(mainBox.getY());                        
                        continue;
                    }                   
                    
                } // end of loop
                
                break;
                
            case FOURTH:
                for (int i = 0; i < 4; i++) {
                    if (i == 3) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() + 1);
                        box.setY(mainBox.getY() - 2);                        
                        continue;
                    }
                    if (i == 2) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX() + 1);
                        box.setY(mainBox.getY() - 1);                        
                        continue;
                    }
                    if (i == 1) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX());
                        box.setY(mainBox.getY() - 1);                        
                        continue;
                    }
                    if (i == 0) {
                        Box box = boxes.get(i);
                        box.setX(mainBox.getX());
                        box.setY(mainBox.getY());                        
                        continue;
                    }                   
                    
                } // end of loop
                
                break;
                
        } // end of switch statement
    } // end of method
    */
    
    @Override
    public Box getNewMainBox(Quarter newQuarter, 
                            Quarter previousQuarter,
                            Box oldMainBox) {
        
        if (previousQuarter == Quarter.FIRST && newQuarter == Quarter.SECOND) {
            oldMainBox.setX(oldMainBox.getX() + 1);
            oldMainBox.setY(oldMainBox.getY());
        }
        if (previousQuarter == Quarter.SECOND && newQuarter == Quarter.THIRD) {
            oldMainBox.setX(oldMainBox.getX() + 1);
            oldMainBox.setY(oldMainBox.getY() + 1);
        }
        if (previousQuarter == Quarter.THIRD && newQuarter == Quarter.FOURTH) {
            oldMainBox.setX(oldMainBox.getX() - 2);
            oldMainBox.setY(oldMainBox.getY() + 1);
        }
        if (previousQuarter == Quarter.FOURTH && newQuarter == Quarter.FIRST) {
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
            { 1, 0},
            { 1, 1},
            { 2, 1}            
        };
        
        Integer[][] second = new Integer[][]{
            { 0,  0},
            { 0,  1},
            {-1,  1},
            {-1,  2}            
        };
        
        Integer[][] third = new Integer[][]{
            { 0,  0},
            {-1,  0},
            {-1, -1},
            {-2, -1}
        };
                
        Integer[][] fourth = new Integer[][]{
            { 0,  0},
            { 0, -1},
            { 1, -1},
            { 1, -2}
        };
        
        // and finally let's put everything in EnumMap
        boxesMap.put(Quarter.FIRST, first);
        boxesMap.put(Quarter.SECOND, second);
        boxesMap.put(Quarter.THIRD, third);
        boxesMap.put(Quarter.FOURTH, fourth);
    }
}