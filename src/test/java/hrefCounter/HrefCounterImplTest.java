package hrefCounter;

import connection.ConnectioToUrlImpl;
import connection.ConnectionToUrl;
import counter.StatisticCounter;
import counter.StatisticCounterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class HrefCounterImplTest {

    private final String url = "https://proselyte.net/tutorials/sql/introduction/";
    private final ConnectionToUrl connection = new ConnectioToUrlImpl();
    private final StatisticCounter counter = new StatisticCounterImpl();
    private final HrefCounterImpl hrefCounter = new HrefCounterImpl();
    private final List<String> words = Arrays.asList("SQL", "БД", "select", "drop");

    @Test
    public void allLinksOnCurrentUrlTest() {
        List<String> result = hrefCounter.allLinksOnCurrentUrl(connection.connectionToUrl(url));
        int[] countFromResultFirstLink = counter.countMatchingWords(words, connection.connectionToUrl(result.get(0)));
        Assertions.assertTrue(countFromResultFirstLink.length != 0);
    }

    @Test
    public void allLinksOnStartUrlTest() {
        List<String> result = hrefCounter.allLinksOnCurrentUrl(connection.connectionToUrl(url));
        Assertions.assertTrue(result.size() != 0);
    }

    @Test
    public void countStartPage() throws IOException {
        List<String> result = hrefCounter.deepLinksCrawl(hrefCounter.allLinksOnCurrentUrl(connection.connectionToUrl(url)), words, 8);
        Assertions.assertTrue(result.size() != 0);
    }

}