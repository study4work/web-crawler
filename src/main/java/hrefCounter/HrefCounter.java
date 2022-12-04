package hrefCounter;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface HrefCounter {

    List<String> allLinksOnCurrentUrl(Document documentToRead);

    void countStartPage(String url, List<String> wordToFind) throws IOException;

    List<String> deepLinksCrawl(List<String> newLinks, List<String> wordToFind, int count) throws IOException;
}
