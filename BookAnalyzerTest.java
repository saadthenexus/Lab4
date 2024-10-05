import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class BookAnalyzerTest {

    private List<Book> books;
    private BookAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        books = Arrays.asList(
            new Book("War and Peace", "Leo Tolstoy", "Historical", 1225, "2023-10-01"),
            new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 310, "2023-03-01"),
            new Book("Sapiens", "Yuval Noah Harari", "Non-Fiction", 443, "2023-07-08"),
            new Book("The Catcher in the Rye", "J.D. Salinger", "Classic", 214, "2023-01-20"),
            new Book("1984", "George Orwell", "Dystopian", 328, "2023-04-21"),
            new Book("Moby Dick", "Herman Melville", "Adventure", 585, "2023-08-15"),
            new Book("Pride and Prejudice", "Jane Austen", "Classic", 279, "2023-09-03"),
            new Book("The Road", "Cormac McCarthy", "Post-Apocalyptic", 241, "2023-12-02"),
            new Book("Crime and Punishment", "Fyodor Dostoevsky", "Psychological Fiction", 430, "2023-11-15"),
            new Book("The Alchemist", "Paulo Coelho", "Fiction", 208, "2023-03-23")
        );
        analyzer = new BookAnalyzer(books);
    }

    @Test
    void testGetTotalBooksReadLastYear() {
        int totalBooks = analyzer.getTotalBooksReadLastYear();
        assertEquals(10, totalBooks, "Should return 10 books read last year.");
    }

    @Test
    void testGetBooksReadEachMonth() {
        Map<String, Integer> booksPerMonth = analyzer.getBooksReadEachMonth();
        assertEquals(2, booksPerMonth.get("OCTOBER"));
        assertEquals(1, booksPerMonth.get("MARCH"));
    }

    @Test
    void testGetTop10LongestBooks() {
        List<String> top10Books = analyzer.getTop10LongestBooks();
        assertEquals(10, top10Books.size(), "There should be 10 books in the list.");
        assertEquals("War and Peace", top10Books.get(0), "The longest book should be 'War and Peace'.");
        assertEquals("Moby Dick", top10Books.get(1), "The second longest book should be 'Moby Dick'.");
    }

    @Test
    void testGetMostReadGenre() {
        String mostReadGenre = analyzer.getMostReadGenre();
        assertEquals("Classic", mostReadGenre, "The most read genre should be 'Classic'.");
    }

    @Test
    void testGetAverageBookLength() {
        double avgLength = analyzer.getAverageBookLength();
        assertEquals(426.3, avgLength, 0.1, "The average length of books should be approximately 426.3 pages.");
    }

    @Test
    void testEmptyBookList() {
        BookAnalyzer emptyAnalyzer = new BookAnalyzer(new ArrayList<>());
        assertEquals(0, emptyAnalyzer.getTotalBooksReadLastYear(), "Total books should be 0 for empty list.");
        assertEquals(0.0, emptyAnalyzer.getAverageBookLength(), "Average length should be 0 for empty list.");
        assertNull(emptyAnalyzer.getMostReadGenre(), "Most read genre should be null for empty list.");
    }

    @Test
    void testGetBooksReadInSpecificMonth() {
        Map<String, Integer> booksPerMonth = analyzer.getBooksReadEachMonth();
        assertEquals(1, booksPerMonth.get("MARCH"), "Should return 1 book read in March.");
        assertEquals(1, booksPerMonth.get("DECEMBER"), "Should return 1 book read in December.");
    }

    @Test
    void testTop10LongestBooksAreCorrect() {
        List<String> top10Books = analyzer.getTop10LongestBooks();
        assertEquals("War and Peace", top10Books.get(0), "The longest book should be 'War and Peace'.");
        assertEquals("Moby Dick", top10Books.get(1), "The second longest book should be 'Moby Dick'.");
        assertTrue(top10Books.contains("Sapiens"), "The list should contain 'Sapiens'.");
    }

    @Test
    void testMostReadGenreWithEqualCounts() {
        List<Book> booksWithEqualGenres = Arrays.asList(
            new Book("Book1", "Author1", "Fantasy", 300, "2023-01-01"),
            new Book("Book2", "Author2", "Fantasy", 200, "2023-02-01"),
            new Book("Book3", "Author3", "Adventure", 400, "2023-03-01"),
            new Book("Book4", "Author4", "Adventure", 500, "2023-04-01")
        );
        BookAnalyzer equalGenreAnalyzer = new BookAnalyzer(booksWithEqualGenres);
        String mostReadGenre = equalGenreAnalyzer.getMostReadGenre();
        assertTrue(mostReadGenre.equals("Fantasy") || mostReadGenre.equals("Adventure"),
                   "Most read genre should be either 'Fantasy' or 'Adventure' as both have equal counts.");
    }

    @Test
    void testBookReadingsAcrossMultipleGenres() {
        List<Book> books = Arrays.asList(
            new Book("Book1", "Author1", "Fantasy", 200, "2023-01-01"),
            new Book("Book2", "Author2", "Science Fiction", 300, "2023-02-01"),
            new Book("Book3", "Author3", "Adventure", 250, "2023-03-01")
        );
        BookAnalyzer analyzer = new BookAnalyzer(books);
        String mostReadGenre = analyzer.getMostReadGenre();
        assertEquals("Fantasy", mostReadGenre, "The most read genre should be 'Fantasy'.");
    }
}
