package com.example.utill;

import com.example.domain.LogData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParseFile {
    public static List<LogData> parseLog(String filePath) throws IOException {
        List<LogData> logData = new ArrayList<>();
        // Read CSV file. For each row, instantiate and collect `DailyProduct`.
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
        return logData;
    }
}
