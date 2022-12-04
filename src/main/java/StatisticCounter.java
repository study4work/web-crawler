import org.jsoup.nodes.Document;

import java.util.List;

public interface StatisticCounter {

    int[] countMatchingWords(List<String> wordToFind, Document documentToRead);
}
