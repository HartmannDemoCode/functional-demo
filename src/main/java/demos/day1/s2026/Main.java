package demos.day1.s2026;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IBox<String> boxForString = new Box<String>();
        IBox<Double> boxForDouble = new RandomBox<Double>();
        String person = "Henry";
        Double salary = 20000.0;

        boxForString.put(person);
        System.out.println("Person: "+boxForString.get());
        boxForDouble.put(salary);
        double doubleSalary = (double) boxForDouble.get()*2;
        System.out.println("Salary: "+boxForDouble.get());
        System.out.println("Double Salary: "+doubleSalary);
        List<String> strings = new ArrayList();
    }
}
