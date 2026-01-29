package demos.day1.s2026;

public class Box<T> implements IBox<T>{
    private T element;

    public void put(T o){
        this.element = o;
    }

    public T get(){
        return element;
    }
}
