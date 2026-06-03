import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

class ReflectionTarget {
    public void sayHello(String name, int age) {
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }
    
    private void secretMethod() {
        System.out.println("This is a private secret method called via reflection!");
    }
}

public class Exercise39_ReflectionExample {
    public static void main(String[] args) {
        try {
            // Load class dynamically
            Class<?> clazz = Class.forName("ReflectionTarget");
            Object instance = clazz.getDeclaredConstructor().newInstance();
            
            System.out.println("Inspecting class: " + clazz.getName());
            
            // Print all declared methods and their parameters
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.print("- Method Name: " + method.getName() + " | Parameters: ");
                Parameter[] params = method.getParameters();
                if (params.length == 0) {
                    System.out.print("None");
                } else {
                    for (Parameter p : params) {
                        System.out.print(p.getType().getSimpleName() + " " + p.getName() + " ");
                    }
                }
                System.out.println();
            }
            
            System.out.println("\nDynamically invoking public method 'sayHello':");
            Method helloMethod = clazz.getMethod("sayHello", String.class, int.class);
            helloMethod.invoke(instance, "Alice", 25);
            
            System.out.println("\nDynamically invoking private method 'secretMethod' (bypassing access checks):");
            Method secretMethod = clazz.getDeclaredMethod("secretMethod");
            secretMethod.setAccessible(true); // Bypass private access modifier
            secretMethod.invoke(instance);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
