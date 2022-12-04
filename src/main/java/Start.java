import org.jsoup.nodes.Document;

import java.util.*;

public class Start {
    public static void main(String[] args) {

        Connect connect = new Connect();
        Storage storage = new Storage();
        Counter counter = new Counter();

        String url = "https://proselyte.net/tutorials/sql/introduction/";

        Document doc = connect.connectionToUrl(url);
        List<String> words = new ArrayList<>();
        words.add("SQL");
        words.add("БД");
        words.add("Drop");
        words.add("Select");

        storage.countStartPage(url, words);
        for(int i=0; i<storage.getGlobalCount().length; i++) {
            System.out.println(storage.getGlobalCount()[i]);
        }
        storage.deepLinksCrawl(storage.allLinksOnCurrentUrl(doc), words, 8);
    }
}
