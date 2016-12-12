package tests;

import controller.ClientController;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import database.DataBase;
import model.Product;
import model.Request;

public class TestUserController {

    public static void main(String[] args) {

        System.out.println("testSendProductRequest() --> " + testSendProductRequest());
    }

    private static boolean testSendProductRequest() {

        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
        DataBase db = new DataBase();
        ClientController controller = new ClientController(db);

        Location location1 = googleMapsAPI.findLocation("Україна", "Київ", "Ревуцького", "7");
        Location location2 = googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "9");

        controller.sendProductRequest(new Product("SomeProduct", 10, 10), "mail" ,location1, location2);

        boolean res = db.getRequests().get(0).equals(new Request(0, "", new Product("SomeProduct", 10, 10),
                ((int) ((googleMapsAPI.getDistance(location1, location2) / 1000) * 20)) , location1, location2));

        return res;
    }



/*    private static boolean whereIsMyProduct() {

        DataBase db = new DataBase();
        UserController controller = new UserController(db);

        controller.whereIsMyProduct();

        return false;
    }*/
}
