import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Storage implements HrefCounter {

    private final List<String> usingHref = new ArrayList<>();
    private final List<String> result = new ArrayList<>();
    private final Connect connect = new Connect();
    private final Counter counter = new Counter();
    private int[] globalCount;
    /*
        Find all links from current URL and write them
        to List<String> href;
         */
    @Override
    public List<String> allLinksOnCurrentUrl(Document documentToRead) {
        List<String> hrefList = new ArrayList<>();
        Elements elements = documentToRead.getElementsByTag("a");
        for (Element e: elements) {
            if(!e.text().isEmpty()) {
                hrefList.add(e.attr("abs:href"));
            }
        }
        return hrefList;
    }

    /*
    Count matching for given word from start page link.
    Write them to the int globalCount.
     */
    @Override
    public void countStartPage(String url, List<String> wordToFind) {
        globalCount = counter.countMatchingWords(wordToFind, connect.connectionToUrl(url));
        System.out.println("For link: " + url + " " + "matches SQL " + globalCount);
    }

    /*
    From the list with all links from starting page, select random one to jump
    into this link, adding this link to List<String> usingHref, so we can exculde this link for futhure jump
    count matching words, after in result we write all links from current pages and exclude using one compare with
    usingHref list.
    After we taking recursive call for given numbers of jump into the links.
     */
    @Override
    public List<String> deepLinksCrawl(List<String> newLinks, List<String> wordToFind, int count) {
        int a = (int) (1 + Math.random() * (newLinks.size() - 1));
        String url = newLinks.get(a);
        usingHref.add(url);
        addingToGlobalCount(counter.countMatchingWords(wordToFind, connect.connectionToUrl(url)));
        //unnessesary code for showing in console
        System.out.println("For link: " + url + " " + "matches: ");
        for(int i=0; i<globalCount.length; i++) {
            System.out.println(globalCount[i]);
        }

        result.addAll(allLinksOnCurrentUrl(connect.connectionToUrl(url)));
        result.removeAll(usingHref);
        count--;
        if (count != 0) {
            deepLinksCrawl(result, wordToFind, count);
        }
        return result;
    }

    /*
    method for
     */
    public void addingToGlobalCount(int[] counters) {
        for (int i = 0; i < counters.length; i++) {
            int first = counters[i];
            int second = globalCount[i];
            globalCount[i] = first + second;
        }
    }

}
