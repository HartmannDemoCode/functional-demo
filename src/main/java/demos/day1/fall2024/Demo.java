package demos.day1.fall2024;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


/**
 * Purpose:
 *
 * @author: Thomas Hartmann
 */
public class Demo implements IPrint {
    public void printName(String name){
        System.out.println(name);
    }

    public void printAnyThing(IPrint printer){
        printer.printName("Henritte");
    }
    public void printAnyThing2(IPrint printer, String topic){
        printer.printName(topic);
    }

    public int additionOldSchool(int a, int b){return a+b;}

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.printName("Holger");
        Consumer<String> printNameWithLambda = (String name) -> System.out.println(name);
        IPrint printWithLambda2 = (String name) -> System.out.println(name);
        printWithLambda2.printName("Jesper");

        // med Lambda
        MyFunction addition = (a, b) -> a + b;
        MyFunction substraction = (a, b) -> {
            System.out.print(a);
            return a - b;
        };

        System.out.println(addition.doOperate(3, 5));  // Output: 8
        System.out.println(substraction.doOperate(8, 5));  // Output: 3
        System.out.println(demo.additionOldSchool(3,5));  // Output: 8

        // Using lambda with as parameter to method
        demo.printAnyThing(printWithLambda2);
        demo.printAnyThing((name)-> System.out.println(name));
        demo.printAnyThing2(printWithLambda2, "Her printer vi denne linje");
        System.out.println("-------------------------------------------------");
        demo.printAnyThing2((str)-> System.out.println(str), "Her printer vi denne linje");

        // Streams
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        list.stream().forEach(fruit->System.out.println(fruit));
        long count = list.stream().count();
        System.out.println(count);
        List<Person> persons = Arrays.asList(
                new Person("Holger",33),
                new Person("Frida", 22),
                new Person("Erik", 88)
        );
        List<Integer> nameLengths = persons.stream()
                .map(person->person.name.length())
                .toList();
        persons.stream()
                .filter(person->person.age>50)
                .toList()
                .forEach(p->System.out.println(p));

        List<Person> sortedPersons = persons.stream().sorted((p1,p2)-> p1.age-p2.age).toList();
        List<Person> sortedPersonsByName = persons.stream().sorted((p1,p2)-> p1.name.compareTo(p2.name)).toList();
        sortedPersons.forEach(System.out::println);
        sortedPersonsByName.forEach(System.out::println);



    }
    interface MyFunction {
        int doOperate(int a, int b);
    }

    private static class Person{
        String name;
        int age;
        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
