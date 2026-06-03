import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercise27_LambdaExpressions {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Orange");
        list.add("Apple");
        list.add("Banana");
        list.add("Mango");
        list.add("Grapes");
        
        System.out.println("Before sorting: " + list);
        
        // Sorting using a lambda expression for descending length, or simply standard alphabetical
        Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
        System.out.println("After sorting alphabetically: " + list);
        
        // Sorting by string length using lambda expression
        Collections.sort(list, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("After sorting by string length: " + list);
    }
}
