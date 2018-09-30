package rotation;

public enum ShapeType {
    L_SHAPE,
    J_SHAPE,
    ROD,
    CUBE,
    T_SHAPE,
    Z_SHAPE,
    S_SHAPE;
    
    // making it static avoids copying it every time methods are called
    private static ShapeType[] allTypes = values();
    private static int numberTypes = allTypes.length;
    
    public ShapeType getRandomShapeType() {
        int randomIntInBound = (int) (Math.random() * numberTypes);
        return allTypes[randomIntInBound];
    }
    
}