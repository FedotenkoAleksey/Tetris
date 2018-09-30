package rotation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

abstract class Shape {
    List<Box> boxes;
    List<Box> heapBoxes;
    Quarter quarter;
    Quarter previousQuarter;
    Box mainBox;
    EnumMap<Quarter, Integer[][]> boxesMap;
    boolean isLeftPossible = true;
    boolean isRightPossible = true;
    boolean isFallling = true;
    boolean isPetrified = false;
    boolean isDiving = true;
    Heap heap;
    int [][] yHeapCoords;
    
    public Shape () {
        // shape appears in the middle top        
        mainBox = new Box(5, 1);        
    }
    
    public Shape (Heap heap) {
        mainBox = new Box(5, 1);
        this.heap = heap;
        yHeapCoords = heap.getYBasedCoord();
        heapBoxes = heap.getBoxes();
    }
    
    public void draw(Graphics2D g2) {        
        for (Box box : boxes) {
            box.draw(g2);
        }        
    }    
    
    // The method returns new mainBox after rotation. On the new mainBox
    // the rest of the shape is built
    public abstract Box getNewMainBox(Quarter newQuarter, 
                                    Quarter previousQuarter,
                                    Box oldMainBox);   
    
    // The method initializes ArrayList and all the boxes. It's called in
    // constructor
    public void initBoxes(Color color) {
        boxes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Box newBox = new Box(0, 0);
            newBox.setColor(color);
            boxes.add(newBox);
        }
    }
    
    // The method rotates the shape and watches against getting the shape
    // out of border
    // TODO: get rid of heap as a parameter, heap was introduced as instance variable
    public void rotate(Heap heap) {
        previousQuarter = quarter;
        quarter = quarter.next();
        mainBox = getNewMainBox(quarter, previousQuarter, mainBox);
        placeBoxes(quarter, mainBox);
        
        heapBoxes = heap.getBoxes();
        
        // box can be found out of the border after rotation, let's check it
        for (Box box : boxes) {
            // left border
            if (box.getX() < 1) {
                mainBox.setX(mainBox.getX() + 1);
                placeBoxes(quarter, mainBox);
            }
            // right border
            if (box.getX() > 10) {                
                mainBox.setX(mainBox.getX() - 1);
                placeBoxes(quarter, mainBox);
            }
            
            // TODO check the heap not to be on the way of rotation
            for (Box heapBox : heapBoxes) {
                if ((box.getY() == heapBox.getY()) && (box.getX() < heapBox.getX())) {
                    
                }
            }
        }
    }
    
    // the method initializes EnumMap which holds 2D Integer arrays (for each
    // possible Quarter)which in turn hold summands for mainBox to extrapolate
    // the rest of the boxes based on the mainBox coordinates
    public abstract void initBoxesLocationMap();
    
    
    // the method places boxes to the needed location based on the location of
    // mainBox and quarter it is turned
    public void placeBoxes(Quarter quarter, Box mainBox) {
        
        Integer[][] currentMapping = boxesMap.get(quarter);
        
        for (int i = 0; i < 4; i++) {
            Integer[] currBoxCoords = currentMapping[i];
            Box box = boxes.get(i);
            box.setX(mainBox.getX() + currBoxCoords[0]);
            box.setY(mainBox.getY() + currBoxCoords[1]);
        }        
        
    } // end of method
    
    public boolean hasTouched() {
        boolean isTouching = false;
        // check if shape touches the heap or the bottom
        outer : for (Box box : boxes) {
            // check bottom
            if ((box.getY() + 1) > 20) {                
                    isPetrified = true;    
                    heap.takeToHeap(this);
                    isTouching = true;
                    break;
            }
            inner : for (Box heapBox : heapBoxes) {
                if ((box.getX() == heapBox.getX()) && (box.getY() + 1 == heapBox.getY()) ) {                    
                    isPetrified = true;
                    isTouching = true;     
                    heap.takeToHeap(this);
                    break outer;       
                } // end of if statement
            } // end of inner loop       
         } // end of outer loop
        return isTouching;
    }
    
    public void fall(Heap heap) {
        /*
        isFallling = true;
        heapBoxes = heap.getBoxes();
        */
        
        isFallling = !hasTouched();
        /*
        
        // check if shape touches the heap or the bottom
        outer : for (Box box : boxes) {
            // check bottom
            if ((box.getY() + 1) > 20) {                
                    isPetrified = true;    
                    heap.takeToHeap(this);
                    isFallling = false;
                    break;
            }
            inner : for (Box heapBox : heapBoxes) {
                if ((box.getX() == heapBox.getX()) && (box.getY() + 1 == heapBox.getY()) ) {                    
                    isPetrified = true;
                    isFallling = false;     
                    heap.takeToHeap(this);
                    break outer;       
                } // end of if statement
            } // end of inner loop       
         } // end of outer loop
        */
        
        if (isFallling) {
            mainBox.setY(mainBox.getY() + 1);
            placeBoxes(quarter, mainBox);
        }        
            
    }
    
    
    
    public void dive(Heap heap) {
        isDiving = true;
        boolean prevValue = true; // debug line
        
        heapBoxes = heap.getBoxes();
        
        // get all the unique x shape values in natural ascending order
        Set<Integer> xBoxCoords = new TreeSet<>();    
        
        // the "lowest" part  of a shape
        int maxY = mainBox.getY();
        
        for (Box box : boxes) {
            xBoxCoords.add(box.getX());
            
            if (box.getY() > maxY) {
                maxY = box.getY();
            }
            
            
            /*
            for (Box heapBox : heapBoxes) {
                if ((box.getY() + 1) > heapBox.getY()) {
                    isDiving = false;
                }
            }
            if (box.getY() + 1 >= 20) {
                isDiving = false;
            }
            
            */
        } // end of loop
        
        // System.out.println("maxY: " + maxY);
        // System.out.println("x values: " + xBoxCoords);
        
        Iterator<Integer> iter = xBoxCoords.iterator();
        while (iter.hasNext()) {
            int x = iter.next();
            
            // if close to bottom -> brake
            if (maxY >= 19) {
                isDiving = false;
            }
            
            
            // this maxY check is done not to go out of array's bound
            if (maxY >= 2 && maxY <= 17 &&  yHeapCoords[x-1][maxY-2] == 1) { // maxY is actually maxY+1 it scans one step ahead in y direction
                // remember? arrays are zero-based, the box.getX() etc are not.
                isDiving = false;
            }
            
        }
        
        if (prevValue != isDiving) {
            System.out.println("The checking WORKED!");
        }
        
        
        if (hasTouched()) {
            isDiving = false;
        }
        
        if (isDiving) {
            mainBox.setY(mainBox.getY() + 1);
            placeBoxes(quarter, mainBox);
            // System.out.println(Arrays.deepToString(yHeapCoords));
        } 
            
    }
    
    public void leftMove(Heap heap) {
        List<Box> heapBoxes = heap.getBoxes();
        isLeftPossible = true;
        for (Box box : boxes) {
            // check if there is a border on the left
            if (box.getX() == 1) {
                isLeftPossible = false;
            }
            for (Box heapBox : heapBoxes) {
                // check if there is a heap on the left
                if ((box.getY() == heapBox.getY()) 
                    && (box.getX()-1 == heapBox.getX())) {
                    isLeftPossible = false;                    
                }                
            }
        }
        if (isLeftPossible) {
            mainBox.setX(mainBox.getX() - 1);
            placeBoxes(quarter, mainBox);
        }
        
    }
    
    public void rightMove(Heap heap) {
        List<Box> heapBoxes = heap.getBoxes();
        isRightPossible = true;
        for (Box box : boxes) {
            
            // check if there is border on the right
            if(box.getX() == 10) {
                isRightPossible = false;
            }
            for (Box heapBox : heapBoxes) {
                // check if there is a heap on the right
                if ((box.getY() == heapBox.getY()) 
                    && (box.getX()+1 == heapBox.getX())) {
                    isRightPossible = false;                    
                }                
            }
        }
        if (isRightPossible) {
            mainBox.setX(mainBox.getX() + 1);
            placeBoxes(quarter, mainBox);
        }
        
            
    }
    
    public void setMainBox(int x, int y) {
        this.mainBox.setX(x);
        this.mainBox.setY(y);
    }
    
    public List<Box> getBoxes() {
        return boxes;
    }
    
    public boolean getIsPetrified(){
        return isPetrified;
    }
}