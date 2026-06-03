import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.reflect.Method;

public class Exercise40_VirtualThreads {
    public static void main(String[] args) {
        System.out.println("Running Virtual Threads demo under JDK: " + System.getProperty("java.version"));
        
        int threadCount = 10000; // Using 10,000 tasks for performance comparison
        long startTime = System.currentTimeMillis();
        
        ExecutorService executor = null;
        boolean isVirtual = false;
        
        try {
            // Attempt to load the virtual thread executor using reflection to maintain compilation safety on JDK 17
            Method newVirtualThreadPerTaskExecutor = Executors.class.getMethod("newVirtualThreadPerTaskExecutor");
            executor = (ExecutorService) newVirtualThreadPerTaskExecutor.invoke(null);
            isVirtual = true;
            System.out.println("Successfully initialized Java 21+ Virtual Thread Executor!");
        } catch (Exception e) {
            System.out.println("Virtual Threads are not supported natively in this JDK (< 21).");
            System.out.println("Falling back to standard platform Cached Thread Pool...");
            executor = Executors.newCachedThreadPool();
        }
        
        try {
            for (int i = 0; i < threadCount; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    // Print progress for every 2000th thread to prevent console flooding
                    if (taskId % 2000 == 0) {
                        System.out.println("Task " + taskId + " running on: " + Thread.currentThread());
                    }
                    try {
                        Thread.sleep(10); // Simulate light I/O sleep
                    } catch (InterruptedException ex) {
                        // ignore
                    }
                });
            }
        } finally {
            executor.shutdown();
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Dispatched " + threadCount + " tasks in " + (endTime - startTime) + " ms.");
        System.out.println("\nStandard Java 21 syntax for direct invocation:\n" +
                           "Thread.startVirtualThread(() -> {\n" +
                           "    System.out.println(\"Hello from virtual thread\");\n" +
                           "});");
    }
}
