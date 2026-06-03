public class Exercise30_PatternMatchingSwitch {
    public static void printType(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return;
        }
        
        // standard Java 16/17 Pattern Matching for instanceof to make it compile out-of-the-box
        if (obj instanceof Integer i) {
            System.out.println("Object is an Integer: " + i);
        } else if (obj instanceof String s) {
            System.out.println("Object is a String: \"" + s + "\"");
        } else if (obj instanceof Double d) {
            System.out.println("Object is a Double: " + d);
        } else {
            System.out.println("Object is of type: " + obj.getClass().getName());
        }
    }
    
    /*
     * Note: In Java 21 (or Java 17 with --enable-preview enabled),
     * you can simplify this with Pattern Matching for Switch as follows:
     *
     * public static void printTypeSwitch(Object obj) {
     *     switch (obj) {
     *         case null         -> System.out.println("Object is null");
     *         case Integer i    -> System.out.println("Object is an Integer: " + i);
     *         case String s     -> System.out.println("Object is a String: " + s);
     *         case Double d     -> System.out.println("Object is a Double: " + d);
     *         default           -> System.out.println("Object is of type: " + obj.getClass().getName());
     *     }
     * }
     */
    
    public static void main(String[] args) {
        printType(100);
        printType("Hello, Java!");
        printType(3.14159);
        printType(true);
        printType(null);
    }
}
