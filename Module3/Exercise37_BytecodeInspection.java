public class Exercise37_BytecodeInspection {
    public int calculateSum(int a, int b) {
        int result = a + b;
        return result;
    }
    
    public static void main(String[] args) {
        Exercise37_BytecodeInspection inspector = new Exercise37_BytecodeInspection();
        int sum = inspector.calculateSum(15, 25);
        System.out.println("Computed sum: " + sum);
        System.out.println("\nInstructions to inspect bytecode:\n" +
                           "1. Open a terminal in this directory.\n" +
                           "2. Run: javac Exercise37_BytecodeInspection.java\n" +
                           "3. Run: javap -c Exercise37_BytecodeInspection\n" +
                           "4. Observe the JVM instructions (iload, iadd, ireturn, etc.) in the output.");
    }
}
