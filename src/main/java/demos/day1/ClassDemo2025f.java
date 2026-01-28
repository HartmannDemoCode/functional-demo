package demos.day1;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ClassDemo2025f {
    @FunctionalInterface
    interface Filter{
        boolean validate(String input);
    }

    private static class MyFilterImplementation implements Filter{

        @Override
        public boolean validate(String input) {
            return input.startsWith("Hello ");
        }
    }


    public static void main(String[] args) {
        // Very long version
        Filter myImplementedFilter = new MyFilterImplementation();
        // Slightly shorter version
        Filter myFilter = new Filter(){
            @Override
            public boolean validate(String input) {
                return input.startsWith("Hello ");
            }
        };
        // Ultimately short version
        Filter myLambdaFilter = x -> x.startsWith("Hello");

    }


    private void functionalInterfaceDemo(){
        BiFunction<Integer, Integer, Integer> addition = (x, y) -> x+y;
        Integer result = addition.apply(5,7);
        System.out.println(result);
        System.out.println("####################");
        Consumer<String> printer = input -> System.out.println(input);
        printer.accept("Hello World");
        Predicate<Integer> checkAge = x -> x > 18;
        boolean checkedAge = checkAge.test(34);
        System.out.println(checkedAge);
    }
}
