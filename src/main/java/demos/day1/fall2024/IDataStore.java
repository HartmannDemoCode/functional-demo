package demos.day1.fall2024;

public interface IDataStore<T> {
    T get();
    void set(T t);
}
