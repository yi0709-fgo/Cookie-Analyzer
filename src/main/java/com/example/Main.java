package com.example;

import com.example.service.MostActiveCookie;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //parse cli
        Options options = new Options();

        options.addOption("f", true, "Input file (e.g. cookie_log.csv)");
        options.addOption("d", true, "Date (e.g. 2018-12-09)");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        try {
            CommandLine cmd = parser.parse(options, args);

            String file = cmd.getOptionValue("f");
            String date = cmd.getOptionValue("d");
            //parse file
            List<String> cookies = MostActiveCookie.process(file,date);
            for(String cookie:cookies){
                System.out.println(cookie);
            }

        } catch (ParseException | IOException e) {
            System.out.println("args format error: " + e.getMessage());
            formatter.printHelp("java -jar yourapp.jar -f <filename> -d <date>", options);
            System.exit(1);
        }


    }

}
