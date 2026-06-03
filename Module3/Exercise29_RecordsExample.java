import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Record definition
record Person(String name, int age) {}

public class Exercise29_RecordsExample {
    public static void main(String[] args) {
        // Create instances of the record
        Person p1 = new Person("Alice", 22);
        Person p2 = new Person("Bob", 17);
        Person p3 = new Person("Charlie", 30);
        Person p4 = new Person("David", 15);
        
        System.out.println("Record toString() demonstration:");
        System.out.println(p1);
        System.out.println(p2);
        
        // Put in list and filter by age >= 18 using Streams
        List<Person> people = Arrays.asList(p1, p2, p3, p4);
        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());
                                    
        System.out.println("\nAdults (age >= 18) filtered using streams:");
        for (Person p : adults) {
            System.out.println("- " + p.name() + " (Age: " + p.age() + ")");
        }
    }
}
