class MessagePrinter implements Runnable {
    private String message;
    
    public MessagePrinter(String message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Message: " + message + " (Iteration " + i + ")");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
        }
    }
}

public class Exercise26_ThreadCreation {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MessagePrinter("Hello from Thread 1"), "PrinterThread-1");
        Thread thread2 = new Thread(new MessagePrinter("Welcome from Thread 2"), "PrinterThread-2");
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Both threads have completed execution.");
    }
}
