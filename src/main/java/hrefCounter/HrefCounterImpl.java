package hrefCounter;

import connection.ConnectionToUrl;
import connection.ConnectioToUrlImpl;
import counter.StatisticCounter;
import counter.StatisticCounterImpl;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import writting.Write;
import writting.WriteImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HrefCounterImpl implements HrefCounter {

    private final List<String> usingHref = new ArrayList<>();
    private final List<String> result = new ArrayList<>();
    private final ConnectionToUrl connectioToUrlImpl = new ConnectioToUrlImpl();
    private final StatisticCounter statisticCounterImpl = new StatisticCounterImpl();
    private final Write write = new WriteImpl();
    private int[] globalCount;

    public int[] getGlobalCount() {
        return globalCount;
    }
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
    public void countStartPage(String url, List<String> wordToFind) throws IOException {
        globalCount = statisticCounterImpl.countMatchingWords(wordToFind, connectioToUrlImpl.connectionToUrl(url));
        write.writToCSV(globalCount, wordToFind, url);

        //unnessesary code for showing in console
        System.out.println("For link: " + url + " " + "matches SQL ");
        for (int i=0; i<globalCount.length; i++) {
            System.out.println(globalCount[i]);
        }
    }

    /*
    From the list with all links from starting page, select random one to jump
    into this link, adding this link to List<String> usingHref, so we can exculde this link for futhure jump
    count matching words, after in result we write all links from current pages and exclude using one compare with
    usingHref list.
    After we taking recursive call for given numbers of jump into the links.
     */
    @Override
    public List<String> deepLinksCrawl(List<String> newLinks, List<String> wordToFind, int count) throws IOException {
        int a = (int) (1 + Math.random() * (newLinks.size() - 1));
        String url = newLinks.get(a);
        usingHref.add(url);
//      addingToGlobalCount(statisticCounterImpl.countMatchingWords(wordToFind, connectioToUrlImpl.connectionToUrl(url)));
        globalCount = statisticCounterImpl.countMatchingWords(wordToFind, connectioToUrlImpl.connectionToUrl(url));
        write.writToCSV(globalCount, wordToFind, url);
        //unnessesary code for showing in console
        System.out.println("For link: " + url + " " + "matches: ");
        for (int i=0; i<globalCount.length; i++) {
            System.out.println(globalCount[i]);
        }

        result.addAll(allLinksOnCurrentUrl(connectioToUrlImpl.connectionToUrl(url)));
        result.removeAll(usingHref);
        count--;
        if (count != 0) {
            deepLinksCrawl(result, wordToFind, count);
        }
        return result;
    }

    /*
    method for add sum numbers to globalCount array
     */
    public void addingToGlobalCount(int[] counters) {
        for (int i = 0; i < counters.length; i++) {
            int first = counters[i];
            int second = globalCount[i];
            globalCount[i] = first + second;
        }
    }

}
