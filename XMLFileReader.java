import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


class XMLFileReader implements FileReader {
    public List<Book> readBooks(String filePath) throws Exception {
        List<Book> books = new ArrayList<>();
        File inputFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("book");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                String author = eElement.getElementsByTagName("author").item(0).getTextContent();
                String genre = eElement.getElementsByTagName("genre").item(0).getTextContent();
                int pages = Integer.parseInt(eElement.getElementsByTagName("pages").item(0).getTextContent());
                String dateRead = eElement.getElementsByTagName("date_read").item(0).getTextContent();
                books.add(new Book(title, author, genre, pages, dateRead));
            }
        }
        return books;
    }
}

