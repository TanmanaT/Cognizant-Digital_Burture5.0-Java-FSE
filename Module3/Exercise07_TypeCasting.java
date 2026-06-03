public class Exercise07_TypeCasting {
    public static void main(String[] args) {
        // Double to int casting (Narrowing Casting - Manual)
        double originalDouble = 9.78;
        int castedInt = (int) originalDouble;
        
        System.out.println("Original double: " + originalDouble);
        System.out.println("Casted int (Narrowing): " + castedInt);
        
        // Int to double casting (Widening Casting - Automatic)
        int originalInt = 15;
        double castedDouble = originalInt;
        
        System.out.println("Original int: " + originalInt);
        System.out.println("Casted double (Widening): " + castedDouble);
    }
}
