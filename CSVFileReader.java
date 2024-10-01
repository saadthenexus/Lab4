import java.io.*;
import java.util.*;


public class CSVFileReader implements FileReader {
    public List<Book> readBooks(String filePath) throws IOException {
        List<Book> books = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            books.add(new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]));
        }
        reader.close();
        return books;
    }
}
