import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

public class JSONFileReader implements FileReader {
    public List<Book> readBooks(String filePath) throws IOException, JSONException {
        List<Book> books = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));
        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString.append(line);
        }
        reader.close();

        JSONArray jsonArray = new JSONArray(jsonString.toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookJson = jsonArray.getJSONObject(i);
            books.add(new Book(
                bookJson.getString("title"),
                bookJson.getString("author"),
                bookJson.getString("genre"),
                bookJson.getInt("pages"),
                bookJson.getString("date_read")
            ));
        }
        return books;
    }
}
