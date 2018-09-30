package rotation;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class TreeSetTest {
    public static void main(String[] args) {
        Set<Integer> tree = new TreeSet<>();
        tree.add(7);
        tree.add(3);
        tree.add(11);
        tree.add(2);
        tree.add(8);
        
        Iterator<Integer> iter = tree.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
                
    }
            
            
            
          
}