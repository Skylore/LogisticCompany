package tests;

import database.DataBase;
import geolocation.controller.Location;
import model.Product;
import model.Request;
import model.WorkRequest;
import utils.Converter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by serhii on 12/18/16.
 */
public class TestConverter {


    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.addRequest(new Request(1,"sdf", new Product("pr", 23,23),123, new Location(), new Location()));
        dataBase.addRequest(new Request(2, "ssdfsdf", new Product("pr2", 231, 232), 1234, new Location(), new Location()));

        dataBase.addWorkRequest(new WorkRequest("worj1", "em", "sdfsdf", 2000));
        dataBase.addWorkRequest(new WorkRequest("worj2", "em2", "sdfsdfsdfsdf", 3000));

        String json = Converter.toJson(dataBase);
        System.out.println(json);

        try {
            PrintWriter pw = new PrintWriter("test.json");
            pw.print(json);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String allJson = Files.readAllLines(Paths.get("test.json")).stream().collect(Collectors.joining("\n"));
            System.out.println("FROM file");
            System.out.println(allJson);

            DataBase fromFileDb = Converter.fromJson(allJson, DataBase.class);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
