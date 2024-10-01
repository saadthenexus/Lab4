public class FileReaderFactory {
    public static FileReader getFileReader(String filePath) {
        if (filePath.endsWith(".csv")) {
            return new CSVFileReader();
        } else if (filePath.endsWith(".json")) {
            return new JSONFileReader();
        } else if (filePath.endsWith(".xml")) {
            return new XMLFileReader();
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + filePath);
        }
    }
}

