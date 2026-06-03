public class Exercise12_MethodOverloading {
    // Add two integers
    public static int add(int a, int b) {
        return a + b;
    }
    
    // Add two doubles
    public static double add(double a, double b) {
        return a + b;
    }
    
    // Add three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public static void main(String[] args) {
        int sumInts = add(5, 10);
        double sumDoubles = add(4.5, 5.5);
        int sumThreeInts = add(2, 4, 6);
        
        System.out.println("Sum of 2 integers (5, 10): " + sumInts);
        System.out.println("Sum of 2 doubles (4.5, 5.5): " + sumDoubles);
        System.out.println("Sum of 3 integers (2, 4, 6): " + sumThreeInts);
    }
}
