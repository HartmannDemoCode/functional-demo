package demos.day1;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsDemo2025fall {
    public static void main(String[] args) {
        Function<Integer, Integer> doubler = x -> x*2;
        Function<Integer, Integer> addFive = x -> x+5;
        Function<Integer, Integer> combined = doubler.andThen(addFive);

        System.out.println(combined.apply(10)); // Should print 25

    }

    public void streamDemo(){

        String[] strings = new String[]{"Anna","Bent","Charlie","Berit"};
        List<String> stringsList = Arrays.asList(strings);
        List<String> filteredStrings = stringsList
                .stream()
                .filter(myString->myString.startsWith("B"))
                .collect(Collectors.toList());
        filteredStrings.forEach(element-> System.out.println(element));
        filteredStrings
                .stream()
                .map(x->x.toLowerCase())
                .collect(Collectors.toList()).forEach(x-> System.out.println(x));
    }
}
