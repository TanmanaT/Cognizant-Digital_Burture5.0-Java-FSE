public class Exercise08_OperatorPrecedence {
    public static void main(String[] args) {
        int expression1 = 10 + 5 * 2;
        int expression2 = (10 + 5) * 2;
        int expression3 = 10 - 3 + 2;
        
        System.out.println("Result of '10 + 5 * 2': " + expression1);
        System.out.println("Explanation: Multiplication '*' has higher precedence than addition '+'. So, 5 * 2 is evaluated first (10), and then 10 + 10 = 20.\n");
        
        System.out.println("Result of '(10 + 5) * 2': " + expression2);
        System.out.println("Explanation: Parentheses '()' have the highest precedence. So, 10 + 5 is evaluated first (15), and then 15 * 2 = 30.\n");
        
        System.out.println("Result of '10 - 3 + 2': " + expression3);
        System.out.println("Explanation: Operators '-' and '+' have the same precedence, so they are evaluated from left to right (associativity). First 10 - 3 = 7, then 7 + 2 = 9.");
    }
}
