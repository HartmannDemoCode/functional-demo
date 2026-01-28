package demos.day1.fall2024;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose:
 *
 * @author: Thomas Hartmann
 */
public class JavaDDEx1 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Book 1", "Author 1", 1999, 300, 4),
                new Book("Book 2", "Author 1", 2001, 300, 3),
                new Book("Book 3", "Author 2", 2010, 300, 4),
                new Book("Book 4", "Author 2", 1990, 300, 5),
                new Book("Book 5", "Author 2", 1930, 300, 4)
                );
        int sum = books.stream()
                .map(book->book.rating)
                .reduce(0,(sumSoFar,actual)->sumSoFar+actual);
        System.out.println("SUM: "+sum);

        int sum2 = books.stream()
                .mapToInt(book->book.rating)
                .sum();
        System.out.println("SUM2: "+sum2);

        // find averag rating by author
        books.stream()
                .collect(Collectors.groupingBy(book->book.author, Collectors.averagingDouble(book->book.rating)))
                .forEach((author,avgRating)->System.out.println(author+" has average rating of: "+avgRating));


    }
    record Book(String  title, String author, int publicationYear, int pages, int rating){ }

}
