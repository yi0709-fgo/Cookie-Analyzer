package com.example.service;

import com.example.domain.LogData;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

import static com.example.parse.ParseFile.parseLog;

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


    public static List<String> process(String filePath, String targetDate, int topN) throws IOException {
        List<String> mostActiveCookies = new ArrayList<>();
        List<LogData> logDataList = parseLog(filePath);
        Map<String, Integer> counter = new HashMap<>();
        for(LogData logData:logDataList){
//            LocalDate logDate = Instant.parse(logData.getTimeStamp())
//                    .atZone(ZoneOffset.UTC)
//                    .toLocalDate();
            String date = logData.getTimeStamp().split("T")[0];
            if(date.equals(targetDate)){
                counter.put(logData.getCookie(),counter.getOrDefault(logData.getCookie(), 0) + 1);
            }
        }
        if(counter.isEmpty()){
            return mostActiveCookies;
        }
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(counter.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        int rank = 0;
        int preCount = -1;
        for(Map.Entry<String, Integer> entry:sorted){
            if(rank>=topN && preCount<entry.getValue()) break;
            mostActiveCookies.add(entry.getKey());
            if(preCount!=entry.getValue()){
                rank++;
                preCount = entry.getValue();
            }
        }
        return mostActiveCookies;
    }
}
