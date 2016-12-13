package utils;

import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;

public class LocationFactory {

    public static Location create(String country, String city, String street, String houseNum) {

        Location location = null;

        try {
        location = new GoogleMapsAPIImpl().findLocation(country, city, street, houseNum);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("wrong argument\nlocation has not been initialized");
        }

        return location;
    }
}
