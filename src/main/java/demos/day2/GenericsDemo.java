package demos.day2;

/**
 * Purpose:
 *
 * @author: Thomas Hartmann
 */
public class GenericsDemo {
    public static void main(String[] args) {
        new GenericsDemo().run();
    }
    public void run(){
        IGenericsDemo<String> genericsStringDemo = new GenericsDemoImpl<>();
        IGenericsDemo<Person> genericsPersonDemo = new GenericsDemoImpl<>();

        Person p1 = new Person("Alice", 33);
        String s1 = "Hi";

        if(genericsPersonDemo.accept(p1)){
            genericsPersonDemo.set(p1);
        }
        if(genericsStringDemo.accept(s1)){
            genericsStringDemo.set(s1);
        }

        // Error: genericsPersonDemo.set(s1);

        System.out.println(genericsStringDemo.get());
        System.out.println(genericsPersonDemo.get());
    }

    private static class GenericsDemoImpl<T> implements IGenericsDemo<T> {
        private T t;
        public boolean accept(T t){
             return t.toString().length() > 3;
        }
        public T get(){
            return t;
        }
        public void set(T t){
            this.t = t;
        }
    }
    private static class Person {
        private String name;
        private int age;
        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }
        public String getName(){
            return name;
        }
        public int getAge(){
            return age;
        }
        @Override
        public String toString(){
            return this.name + " " + this.age;
        }
    }
}
