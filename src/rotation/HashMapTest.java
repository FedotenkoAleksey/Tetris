package rotation;

import java.util.Arrays;
import java.util.EnumMap;

class HashMapTest {
    public static void main(String[] args) {
        EnumMap<Quarter, String> eMap = 
            new EnumMap<>(Quarter.class);
        
        
        
        eMap.put(Quarter.FIRST, "first");
        eMap.put(Quarter.SECOND, "second");
        eMap.put(Quarter.THIRD, "third");
        eMap.put(Quarter.FOURTH, "fourth");
        
        // print the map
        System.out.println(eMap);
        
        // get value for Quarter.THIRD
        String value = eMap.get(Quarter.THIRD);
        
        System.out.println(value);
        
        EnumMap<Quarter, Integer[][]> boxesMap = new EnumMap<>(Quarter.class);
        
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
        
        boxesMap.put(Quarter.FIRST, first);
        boxesMap.put(Quarter.SECOND, second);
        boxesMap.put(Quarter.THIRD, third);
        boxesMap.put(Quarter.FOURTH, first);
        
        
        
        Quarter myQuarter = Quarter.THIRD;
        
        Integer[][] currentArray = boxesMap.get(myQuarter);
        System.out.println(Arrays.deepToString(currentArray));
        
        
        int [][] testMutli = new int[20][10];
        
        
        System.out.println(Arrays.deepToString(testMutli));
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        System.out.println(Arrays.deepToString(multi));       
        
        
        System.out.println("[0]" + Arrays.toString(multi[0]));
        System.out.println("[1]" + Arrays.toString(multi[1]));
        System.out.println("[2]" + Arrays.toString(multi[2]));
        System.out.println("[3]" + Arrays.toString(multi[3]));
        */
    }
}