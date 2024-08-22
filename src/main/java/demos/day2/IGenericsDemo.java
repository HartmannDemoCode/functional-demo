package demos.day2;

public interface IGenericsDemo<T> {
    boolean accept(T t);
    T get();
    void set(T t);
}
