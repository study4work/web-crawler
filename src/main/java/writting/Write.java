package writting;

import java.io.IOException;
import java.util.List;

public interface Write {

    void writToCSV(int[] arrayToWrite, List<String> wordsToFind, String url) throws IOException;
}
