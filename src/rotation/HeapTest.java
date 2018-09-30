package rotation;

import java.util.Arrays;

class HeapTest {
    public static void main(String[] args) {
        int score = 11;
        int count = 36;
        
        double result = (((double)score * 10) / (double)(count * 4));
        
        int round = (int) (Math.round(result * 100.0));
        System.out.println(round);
        
    }
}