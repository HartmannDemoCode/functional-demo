package demos.day1.fall2024;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

/**
 * Purpose:
 *
 * @author: Thomas Hartmann
 */
public class SolutionDay1 {
    public static void main(String[] args) {
        new SolutionDay1().run();
    }

    public void run() {
        List<Book> books = Arrays.asList(
                new Book("The Da Vinci Code", "Dan Brown", 2003, 689, 4),
                new Book("The Hobbit", "J.R.R. Tolkien", 1937, 310, 5),
                new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, 223, 5),
                new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 277, 3),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 218, 4),
                new Book("Pottery 101", "J.K. Rowling", 2007, 607, 4)
        );
        // find average rating of all books
        double avg = books.stream().map(book -> book.rating).reduce((a, b) -> a + b).get() / books.size();
        System.out.println("Average rating: " + avg);

        // find and display books published after "year"
        int YEAR = 2000;
        books.stream().filter(book -> book.publicationYear > YEAR).forEach(System.out::println);
        // sort books by rating desc
        books.stream().sorted((b1, b2) -> b2.rating - b1.rating).forEach(System.out::println);
        // find book with highest rating
        Book bestBook = books.stream().max((b1, b2) -> b1.rating - b2.rating).get();
        System.out.println("Best book: " + bestBook);
        // group by author
        Map<String, List<Book>> authorBooks = books.stream().collect(groupingBy(book -> book.author));
        authorBooks.forEach((author, book) -> System.out.println(book));
        // group by author and get avg rating
//        Map<String,Double> authorRatings2 = books.stream().collect(Collectors.toMap(book->book.author, averagingDouble(book->book.rating)));


        System.out.println("Grouping By:::::::::::::::");
        Map<String, List<Book>> result = books.stream().collect(groupingBy(book -> book.author));
        Map<String, Double> authorRatings = books.stream().collect(groupingBy(book -> book.author, averagingDouble(book -> book.rating)));

        Map<String, BookCollection> bookCollectionMap = books
                .stream()
                .collect(
                        groupingBy(book -> book.author // map key
                                , Collectors.collectingAndThen( // map value
                                        Collectors.toList(), // make list of the values
                                        bookList -> new BookCollection( // reference the list in lambda in order to create a BookCollection by each Author
                                                bookList
                                                        .stream()
                                                        .map(book -> book.title) //
                                                        .collect(Collectors.toList())
                                        )
                                )
                        )
                );

        authorRatings.forEach((author, avgRating) -> System.out.println(author + " has avarage rating of: " + avgRating));
        // calculate total number of pages for all books
        int pages = books.stream().mapToInt(book -> book.pages).sum();
        System.out.println("Total number of pages: " + pages);

        pages = books.stream().collect(Collectors.summingInt(book -> book.pages));
        System.out.println("Different way to sum pages: " + pages);

        bookCollectionMap.forEach((author, bookCollection) -> System.out.println(author + " has written: " + bookCollection.bookTitles));

    }

    public record Book(String title, String author, int publicationYear, int pages, int rating) {
    }

    public record BookCollection(List<String> bookTitles) {
    }
}
