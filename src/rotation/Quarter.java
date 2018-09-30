package rotation;

public enum Quarter {
    FIRST,
    SECOND,
    THIRD,
    FOURTH;
    
    // making it private static avoids array copying each time next() is called
    private static Quarter[] vals = values();
    
    // method returns next enum element
    public Quarter next() {
        return vals[(this.ordinal()+1) % vals.length];
    }
}