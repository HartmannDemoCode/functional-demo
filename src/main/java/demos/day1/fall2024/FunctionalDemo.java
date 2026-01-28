package demos.day1.fall2024;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Purpose:
 *
 * @author: Thomas Hartmann
 */
public class FunctionalDemo {
    public static void main(String[] args) {
        new FunctionalDemo().run();

    }
    public void run(){
        String[] strs = {"Andy", "Beatrice", "Charles", "Dorthea", "Eric", "Beatles"};
        Predicate<String> myFilter = str -> str.startsWith("B");
        Function<String,String> myMapper = str -> str.toUpperCase();

        String[] result = filter(strs, myFilter);
        for (String string : result) {
            System.out.println(string);
        }

        String[] result2 = mapper(strs, myMapper);
        for (String string : result2) {
            System.out.println(string);
        }

        Consumer<String> myPrinter = str -> System.out.println(str);
        consumeArray(strs, myPrinter);

    }
    public void consumeArray(String[] strs, Consumer<String> consumer){
        for(String str: strs){
            consumer.accept(str);
        }

    }













    public String[] filter(String[] strs, Predicate<String> filterObj){
        List<String> filtered = new ArrayList();
        for (String str : strs) {
            if(filterObj.test(str)){
                filtered.add(str);
            }
        }
        return filtered.toArray(new String[0]);
    }

    public String[] mapper(String[] strs, Function<String,String> mapper){
        List<String> mapped = new ArrayList();
        for(String str: strs){
            mapped.add(mapper.apply(str));
        }
        return mapped.toArray(new String[0]);
    }


}
