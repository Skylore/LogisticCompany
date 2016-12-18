package database;

import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import model.Product;
import model.Request;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Logger {

    private FileWriter requestFileWriter;
    private GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

    {
        try {
            requestFileWriter = new FileWriter("requests.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedWriter bufferedRequestWriter = new BufferedWriter(requestFileWriter);

    public void logRequest(Request request) {
        try {
            bufferedRequestWriter.write(request.getId() + " " + request.getEmail() + " " +
                    request.getProduct().getName() + " " + request.getProduct().getWeight() + " " +
                    request.getProduct().getSize() + " " + request.getPrice() + " " +
                    request.getFrom().toString().substring(1, request.getFrom().toString().length() - 1)
                    + " " + request.getTo().toString().substring(1, request.getTo().toString().length() - 1) + "\n");
            bufferedRequestWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Request> readRequest() {
        ArrayList<Request> res = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new File("requests.txt"));
            while (scanner.hasNext()) {
                String[] ln = scanner.nextLine().split(" ");
                res.add(new Request(Integer.valueOf(ln[0]), ln[1], new Product(ln[2], Integer.valueOf(ln[3]),
                        Integer.valueOf(ln[4])), Integer.valueOf(ln[5]), googleMapsAPI.findLocation(ln[6]),
                        googleMapsAPI.findLocation(ln[7])));
                requestFileWriter.write("");
                requestFileWriter.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
