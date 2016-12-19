package tests;

import database.DataBase;
import database.Converter;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import model.Product;
import model.Request;

public class TestLogger {

    public static void main(String[] args) {
/*
        DataBase dataBase = new DataBase();
        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
        Converter logger = new Converter();
        logger.logRequest(new Request(0, "mail", new Product("prod", 1, 2),
                100, googleMapsAPI.findLocation("Украина", "Киев", "Ревуцкого", "7"),
                googleMapsAPI.findLocation("Украина Киев Тампере 9")));

        System.out.println(logger.readRequest().get(0).toString());*/
    }
}
