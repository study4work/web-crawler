package connection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ConnectioToUrlImpl implements ConnectionToUrl {

    @Override
    public Document connectionToUrl(String url) {
        try {
            Connection con=Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true);
            return con.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
