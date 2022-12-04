package counter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class StatisticCounterImpl implements StatisticCounter {

    @Override
    public int[] countMatchingWords(List<String> wordToFind, Document documentToRead) {
        Element body = documentToRead.body();
        String[] strings = body.text().split(" ");
        int[] counter = new int[wordToFind.size()];
        int index = 0;
        int count = 0;
        for (String words : wordToFind) {
            for (String s : strings) {
                if (s.contains(words) || s.contains(words.toUpperCase()) || s.contains(words.toLowerCase())) {
                    counter[index] = ++count;
                }
            }
            if (index < wordToFind.size()) {
                count = 0;
                index++;
            }
        }
        return counter;
    }
}
