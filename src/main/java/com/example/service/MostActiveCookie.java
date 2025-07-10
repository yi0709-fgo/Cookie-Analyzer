package com.example.service;

import com.example.domain.LogData;
import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;

import static com.example.utill.Tools.parseLog;

public class MostActiveCookie {


    public static List<String> process(String filePath, String targetDate) throws IOException {
        List<String> mostActiveCookies = new ArrayList<>();
        List<LogData> logDataList = parseLog(filePath);
        Map<String, Integer> counter = new HashMap<>();
        for(LogData logData:logDataList){
            String date = logData.getTimeStamp().split("T")[0];
            if(date.equals(targetDate)){
                counter.put(logData.getCookie(),counter.getOrDefault(logData.getCookie(), 0) + 1);
            }
        }
        if(counter.isEmpty()){
            return mostActiveCookies;
        }

        int maxCount = Collections.max(counter.values());
        mostActiveCookies = counter.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .toList();

        return mostActiveCookies;
    }
}
