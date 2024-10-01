import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

class BookAnalyzer {
    private List<Book> books;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public BookAnalyzer(List<Book> books) {
        this.books = books;
    }

    public int getTotalBooksReadLastYear() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        return (int) books.stream()
            .filter(book -> LocalDate.parse(book.getDateRead(), formatter).isAfter(oneYearAgo))
            .count();
    }

    public Map<String, Integer> getBooksReadEachMonth() {
        Map<String, Integer> booksPerMonth = new HashMap<>();
        books.forEach(book -> {
            String month = LocalDate.parse(book.getDateRead(), formatter).getMonth().toString();
            booksPerMonth.put(month, booksPerMonth.getOrDefault(month, 0) + 1);
        });
        return booksPerMonth;
    }

    
    public List<String> getTop10LongestBooks() {
        return books.stream()
            .sorted((b1, b2) -> b2.getPages() - b1.getPages())
            .limit(10)
            .map(Book::getTitle)
            .collect(Collectors.toList());
    }

    public String getMostReadGenre() {
        return books.stream()
            .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("");
    }

    public double getAverageBookLength() {
        return books.stream()
            .mapToInt(Book::getPages)
            .average()
            .orElse(0.0);
    }
}

