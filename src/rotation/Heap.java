package rotation;

// Heap is all the tetris blocks which have hit the floor
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The class Head is all the tetris blocks which have hit the floor.
 *
 * @author ALEXXX
 */
class Heap {

    List<Box> boxes;
    int[][] heapCoords;
    int[][] yAllignedHeapCoords;
    List<Integer> completedLines;
    Score score;

    public Heap(Score score) {
        initHeap();
        this.score = score;
    }

    public void initHeap() {
        boxes = new ArrayList<>();
        heapCoords = new int[20][10];
        yAllignedHeapCoords = new int[10][20];
        recalculateHeapCoords();
        completedLines = new ArrayList<>();
    }

    /**
     * The method takes all the boxes from the shape to the heap.
     *
     * @param shape
     * @return nothing
     */
    public void takeToHeap(Shape shape) {
        List<Box> shapeBoxes = shape.getBoxes();
        // add all boxes of a shape to a heap
        for (Box box : shapeBoxes) {
            boxes.add(box);
            if (box.getY() < 1) {
                System.out.println("GAME OVER!!!");
                score.setGameOver();
            } else {
                recalculateHeapCoords();
            }

        }
        System.out.println("One more in the heap!");

        // check if any lines are complete
        updateComplemetedLines();
        if (completedLines.size() == 0) {
            System.out.println("No complete lines...");
        } else {
            System.out.println("Lines: " + completedLines + " are complete!!!");
            deleteLine();

        }
        checkCoordsByBoxes(); // debug line 
        // printHeapCoords(); // debug line to visualize heapCoords
    }

    public void draw(Graphics2D g2) {
        for (Box box : boxes) {
            box.draw(g2);
        }
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public int[][] getHeapCoords() {
        return heapCoords;
    }

    /**
     * The method checks if any line is completed and returns a list of Y coords
     * of lines which are completed. If no line is completed, it returns an
     * empty list.
     */
    public void updateComplemetedLines() {
        completedLines.clear();

        outer:
        for (int y = 0; y < 20; y++) {
            inner:
            for (int x = 0; x < 10; x++) {
                if (heapCoords[y][x] == 0) {
                    break inner;
                }
                // reaches this line only if there are no zeros

                // it only reaches here if x == 9
                // TODO: without the following lines method generated wrong
                // results. Actually it generated 10 times the line which was
                // really complete and some other lines
                if (x == 9) {
                    completedLines.add(y + 1); // + 1 to make it non zero-based
                }
            }
        }

    }

    /**
     * The debugging method prints out all the coordinates of 2D heapCoords.
     */
    public void printHeapCoords() {
        System.out.println(Arrays.deepToString(heapCoords));
    }

    public void printBoxesCoords() {
        int boxCount = 0;
        for (Box box : boxes) {
            boxCount++;
            System.out.println("Box #: " + boxCount + " X: " + box.getX()
                    + " Y: " + box.getY());
        }
    }

    /**
     * The method deletes line if it is completed. After deletion it levels down
     * all the boxes which are above the line to delete. And the method updates
     * the values of heapCoords.
     */
    public void deleteLine() {

        // take the first value and delete this line after it recursively 
        // call deleteLine() again
        if (completedLines.size() > 0) {
            int lineToDelete = completedLines.get(0);

            // scenario only if line is line
            System.out.println("Before delete: ");
            // printBoxesCoords();// debug line
            // printHeapCoords();

            // Never use for each loop while modifying the list. It can 
            // lead to unexpected results.
            Iterator<Box> boxRemoveIterator = boxes.iterator();

            // let's iterate through all the boxes
            while (boxRemoveIterator.hasNext()) {
                Box box = boxRemoveIterator.next();

                // remove the box if it is in the lineToDelete
                if (box.getY() == lineToDelete) {
                    // and update heapCoords
                    heapCoords[box.getY() - 1][box.getX() - 1] = 0;
                    boxRemoveIterator.remove();
                }
            } // end of while
            score.incrementScore();

            // iterate through boxes one more time and level down all the 
            // boxes which are "higher" than lineToDelete
            Iterator<Box> boxLevelDownIterator = boxes.iterator();
            while (boxLevelDownIterator.hasNext()) {
                Box box = boxLevelDownIterator.next();
                // level down the box
                if (box.getY() < lineToDelete) {
                    // update heapCoords
                    // nullify old y
                    heapCoords[box.getY() - 1][box.getX() - 1] = 0;
                    // and save new y
                    heapCoords[box.getY()][box.getX() - 1] = 1;
                    box.setY(box.getY() + 1);
                }
            } // end of while

            /*
            
            System.out.println("");
            System.out.println("After delete: ");
            // printBoxesCoords();
            printHeapCoords();
            
             */
            completedLines.remove(0);

        } // end of if statement

        recalculateHeapCoords();

        // if there are other completed lines delete them, too
        if (completedLines.size() > 0) {
            deleteLine();
        }

    } // end of delete method

    public void checkCoordsByBoxes() {
        int[][] correctCoords = new int[20][10];

        // let's populate correctCoords with values based on boxes arraylist
        for (Box box : boxes) {
            int tempX = box.getX();
            int tempY = box.getY();

            correctCoords[tempY - 1][tempX - 1] = 1;
        }

        if (Arrays.deepEquals(correctCoords, heapCoords)) {
            System.out.println("The heapCoords is true!");
        } else {
            System.out.println("False! heapCoords is FALSE!!!");
        }

    }

    public void recalculateHeapCoords() {
        int[][] newCoords = new int[20][10]; // TODO get by with one 2-dimentional array to avoid memory leak
        int[][] newYBasedCoords = new int[10][20]; // // TODO get by with one 2-dimentional array to avoid memory leak

        // let's populate correctCoords with values based on boxes arraylist
        for (Box box : boxes) {
            int tempX = box.getX();
            int tempY = box.getY();

            newCoords[tempY - 1][tempX - 1] = 1;
            newYBasedCoords[tempX - 1][tempY - 1] = 1;
        }

        heapCoords = newCoords;
        yAllignedHeapCoords = newYBasedCoords;
    }

    public int[][] getYBasedCoord() {
        return yAllignedHeapCoords;
    }

} // end of class
