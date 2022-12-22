package counter;

import connection.ConnectioToUrlImpl;
import connection.ConnectionToUrl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class StatisticCounterImplTest {

    private final String url = "https://proselyte.net/tutorials/sql/introduction/";
    private final ConnectionToUrl connection = new ConnectioToUrlImpl();
    private final StatisticCounter counter = new StatisticCounterImpl();
    private final List<String> words = Arrays.asList("SQL", "БД", "select", "drop");

    @Test
    public void countMatchingWordsTest() {
       int[] result = counter.countMatchingWords(words, connection.connectionToUrl(url));
        Assertions.assertTrue(result.length != 0);
    }

}