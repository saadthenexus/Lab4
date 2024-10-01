import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            
            String filePath = "books.json"; 
            
            FileReader reader = FileReaderFactory.getFileReader(filePath);
        
            List<Book> books = reader.readBooks(filePath);

            BookAnalyzer analyzer = new BookAnalyzer(books);

            System.out.println("Total books read last year: " + analyzer.getTotalBooksReadLastYear());
            System.out.println("Books read each month: " + analyzer.getBooksReadEachMonth());
            System.out.println("Top 10 longest books: " + analyzer.getTop10LongestBooks());
            System.out.println("Most read genre: " + analyzer.getMostReadGenre());
            System.out.println("Average book length: " + analyzer.getAverageBookLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
