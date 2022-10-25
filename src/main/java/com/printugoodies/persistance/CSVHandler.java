package com.printugoodies.persistance;

// Java program to illustrate
// for Writing Data in CSV file

import java.io.*;
import java.util.*;

import com.opencsv.*;

public class CSVHandler {


    public static void addDataToCSV(String row) {
        // first create file object for file placed at location
        // specified by filepath

File file= new File(System.getProperty("user.home")+"\\Desktop\\data.csv");
        try {
            // create FileWriter object with file as parameter

            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();


FileReader fileReader= new FileReader(file);
            CSVReader reader = new CSVReaderBuilder(fileReader).withCSVParser(parser)
                    .build();

            List<String[]> rows = reader.readAll();
            // create CSVWriter with ';' as separator


            // create a List which contains Data

            row = rows.size() + 1 + " " + row;


            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);


            String[] rowdata = row.split(" ");
            rows.add(rowdata);


            writer.writeAll(rows);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

