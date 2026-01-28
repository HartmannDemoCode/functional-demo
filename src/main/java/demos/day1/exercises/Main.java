package demos.day1.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("First book", "Hannah", 1999, 20, 1);
        Book book2 = new Book("Second book", "Peter", 1999, 20, 4);
        Book book3 = new Book("Third book", "Frederikke", 1999, 20, 3);
        Book book4 = new Book("Fourth book", "Bent", 1999, 20, 3);
        Book book5 = new Book("Fifth book", "Charlie", 1999, 20, 5);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        double avgResult = books
                .stream()
                .mapToInt(book->book.getRating())
                .average()
                .getAsDouble();

        double avgResult2 = books
                .stream()
//                .collect(Collectors.averagingDouble(Book::getRating));
                .collect(Collectors.averagingDouble(book->book.getRating()));

        System.out.println(avgResult);
        System.out.println(avgResult2);

    }
}
