package demos.generics;

import java.util.*;

class Animal {
    void speak() { System.out.println("Some animal sound"); }
}

class Dog extends Animal {
    void speak() { System.out.println("Woof!"); }
}

class Cat extends Animal {
    void speak() { System.out.println("Meow!"); }
}

public class WildCardDemo {
    public static void main(String[] args) {
        // Dog List
        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog());

        // -------- ? extends -------- Read Only
        List<? extends Animal> animals = dogList;

        Animal a = animals.get(0); // ✅ OK: safe to read as Animal
        a.speak();

        // animals.add(new Dog()); // ❌ Compile error
        // animals.add(new Cat()); // ❌ Compile error

        // -------- ? super -------- WriteOnly
        List<? super Dog> superDogs = new ArrayList<>();
        superDogs.add(new Dog()); // ✅ OK: can add Dog
        // superDogs.add(new Cat()); // ❌ Compile error, Cat is not a Dog

        Object o = superDogs.get(0); // ✅ OK: but only as Object
        // Dog d = superDogs.get(0); // ❌ Compile error
    }
}


