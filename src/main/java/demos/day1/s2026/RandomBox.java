package demos.day1.s2026;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBox<T> implements IBox<T>{
    List<T> tList = new ArrayList<T>();

    @Override
    public void put(T t) {
        tList.add(t);
    }

    @Override
    public T get() {
        Random random = new Random();
        int index = random.nextInt(tList.size());
        return tList.get(index);
    }
}
