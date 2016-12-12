package tests;

import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;

public class TestGeolocation {

    public static void main(String[] args) {

        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

        Location location = googleMapsAPI.findLocation("Україна", "Київ", "Ніжинська", "29");
        Location location1 = googleMapsAPI.findLocation("Україна", "Київ", "Чколівський бульвар", "39");

        System.out.println(googleMapsAPI.getDistance(location, location1));
    }
}
