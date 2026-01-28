package demos.generics;

import java.util.ArrayList;
import java.util.List;

public class Demo{
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("hello");
//        Integer value = myList.get(0); // compilation error

//        List myList = new ArrayList();
//        myList.add("hello");
//        Integer value = (Integer) myList.get(0); // runtime classcastexception
        Box<Integer> intBox = new Box<>();
        intBox.setContent(42);

        Box<String> strBox = new Box<>();
        strBox.setContent("hello, world!");

        System.out.println(intBox.getContent()); // output: 42
        System.out.println(strBox.getContent()); // output: hello, world!

    }
    private interface MyInterface<T>{
        void setContent(T content);
        T getContent();
    }

    private static class Box<T> implements MyInterface<T>{
        private T content;

        public void setContent(T content) {
            this.content = content;
        }

        public T getContent() {
            return content;
        }
    }
}
