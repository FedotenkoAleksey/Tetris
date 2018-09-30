package rotation;

/**
 * The class is a static factory which has a single static method createShape.
 *
 * @author ALEXXX
 */
class ShapeFactory {

    /** The method creates Shapes given a ShapeType enum and a 
     * Heap as parameters.
     * 
     * @param shapeType ShapeType enum
     * @param heap Heap object to know when a shape touches a Heap
     * @return 
     */
    public static Shape createShape(ShapeType shapeType, Heap heap) {
        Shape shape = null;

        switch (shapeType) {
            case L_SHAPE:
                shape = new LShape(heap);
                break;

            case J_SHAPE:
                shape = new JShape(heap);
                break;

            case ROD:
                shape = new Rod(heap);
                break;

            case T_SHAPE:
                shape = new TShape(heap);
                break;

            case CUBE:
                shape = new Cube(heap);
                break;

            case Z_SHAPE:
                shape = new ZShape(heap);
                break;

            case S_SHAPE:
                shape = new SShape(heap);
                break;
        } // end of switch statement

        return shape;
    }
}
