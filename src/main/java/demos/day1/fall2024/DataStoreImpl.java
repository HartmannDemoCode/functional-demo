package demos.day1.fall2024;

/**
 * Purpose:
 *
 * @author: Thomas Hartmann
 */
public class DataStoreImpl<T> implements IDataStore<T> {

    T t;

    @Override
    public T get() {
        return t;
    }

    @Override
    public void set(T t) {
        this.t = t;
    }
}
