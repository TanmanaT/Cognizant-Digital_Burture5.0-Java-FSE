public class Exercise38_DecompileClass {
    private String secretKey = "SuperSecretKey123";
    
    public boolean checkSecret(String guess) {
        if (guess != null && guess.equals(secretKey)) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        Exercise38_DecompileClass decomp = new Exercise38_DecompileClass();
        boolean result = decomp.checkSecret("guess");
        System.out.println("Decompilation Demo. Code compiles successfully.");
        System.out.println("Result of secret check: " + result);
        System.out.println("\nInstructions to decompile:\n" +
                           "1. Compile this class: javac Exercise38_DecompileClass.java\n" +
                           "2. Use a Java decompiler tool (like CFR, JD-GUI, or Fernflower) to open 'Exercise38_DecompileClass.class'\n" +
                           "3. Notice how private fields (like secretKey) and logic conditions are fully reconstructed back to readable Java code!");
    }
}
