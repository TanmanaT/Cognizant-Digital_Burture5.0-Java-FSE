class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

public class Exercise18_InheritanceExample {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Dog myDog = new Dog();
        
        System.out.print("Animal: ");
        myAnimal.makeSound();
        
        System.out.print("Dog: ");
        myDog.makeSound();
    }
}
