package com.example.parse;

import com.example.domain.LogData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParseFile {
    public static List<LogData> parseLog(String filePath) throws IOException {
        List<LogData> logData = new ArrayList<>();
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                Path path = Paths.get(filePath);

                BufferedReader reader = Files.newBufferedReader(path);
                Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
                for (CSVRecord record : records) {
                    LogData data = new LogData();
                    String cookie = record.get( "cookie" );
                    String timestamp = record.get( "timestamp" );
                    data.setCookie(cookie);
                    data.setTimeStamp(timestamp);
                    logData.add(data);
                }
            }
        }

        // Read CSV file. For each row, convert to Logdata.

        return logData;
    }
}
