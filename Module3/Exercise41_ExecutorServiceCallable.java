import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FactorialTask implements Callable<Long> {
    private final int number;
    
    public FactorialTask(int number) {
        this.number = number;
    }
    
    @Override
    public Long call() throws Exception {
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}

public class Exercise41_ExecutorServiceCallable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Long>> futures = new ArrayList<>();
        
        try {
            int[] tasks = {5, 6, 7, 8, 9};
            System.out.println("Submitting factorial tasks to ExecutorService...");
            
            for (int num : tasks) {
                Callable<Long> task = new FactorialTask(num);
                Future<Long> future = executor.submit(task);
                futures.add(future);
            }
            
            System.out.println("Collecting results...");
            for (int i = 0; i < tasks.length; i++) {
                int number = tasks[i];
                Future<Long> future = futures.get(i);
                try {
                    // Block until the result is computed
                    Long result = future.get();
                    System.out.println("Factorial of " + number + " is: " + result);
                } catch (Exception e) {
                    System.out.println("Error calculating factorial of " + number + ": " + e.getMessage());
                }
            }
        } finally {
            executor.shutdown();
        }
        System.out.println("ExecutorService shutdown completed.");
    }
}
