package demos.day1.fall2024;

import java.util.LinkedList;
import java.util.List;

/**
 * Purpose:
 *
 * @author: Thomas Hartmann
 */
public class GenericDemo {
    public static void main(String[] args) {
        IDataStore<String> dataStore = new DataStoreImpl<>();
        dataStore.set("Hello World");
        String result = dataStore.get();
        System.out.println(result);
        List<String> list = new LinkedList<>();
    }
}
