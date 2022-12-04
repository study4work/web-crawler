package view;

import connection.ConnectioToUrlImpl;
import connection.ConnectionToUrl;
import hrefCounter.HrefCounter;
import hrefCounter.HrefCounterImpl;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private final ConnectionToUrl connectioToUrlImpl;
    private final HrefCounter hrefCounterImpl;
    private final Scanner scanner;

    public View() {
        this.connectioToUrlImpl = new ConnectioToUrlImpl();
        this.hrefCounterImpl = new HrefCounterImpl();
        this.scanner = new Scanner(System.in);
    }

    public void run() throws IOException {
        System.out.println("Enter 1 to Start:");
        switch (scanner.next()) {
            case "1":
                System.out.println("Enter the url: ");
                String url = scanner.next();
                Document doc = connectioToUrlImpl.connectionToUrl(url);
                System.out.println("Enter the words 1: ");
                String word1 = scanner.next();
                System.out.println("Enter the words 2: ");
                String word2 = scanner.next();
                System.out.println("Enter the words 3: ");
                String word3 = scanner.next();
                System.out.println("Enter the words 4: ");
                String word4 = scanner.next();
                List<String> wordsToFind = new ArrayList<>();
                wordsToFind.add(word1);
                wordsToFind.add(word2);
                wordsToFind.add(word3);
                wordsToFind.add(word4);
                System.out.println("Enter count for deep links: ");
                String links = scanner.next();
                int i = Integer.parseInt(links);
                hrefCounterImpl.countStartPage(url, wordsToFind);
                hrefCounterImpl.deepLinksCrawl(hrefCounterImpl.allLinksOnCurrentUrl(doc), wordsToFind, i);
        }
    }
}
