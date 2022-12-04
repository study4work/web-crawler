package writting;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteImpl implements Write{

    @Override
    public void writToCSV(int[] arrayToWrite, List<String> wordsToFind, String url) throws IOException {
        String[] file = new String[arrayToWrite.length];
        for (int i = 0; i < file.length; i++) {
            file[i] = wordsToFind.get(i) + " :" + arrayToWrite[i] ;
        }
        file[0] =  "for link: " + url + "  " + file[0];
        String csv = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        //Write the record to file
        writer.writeNext(file);
        //close the writer
        writer.close();
    }
}
