package demos.day1.exercises;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private int pages;
    private int rating;

}
