package employee;

import controller.CourierController;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import model.Product;
import model.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDelivery {

    private static GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
    private DataBase dataBase;

    @Before
    public void setUp(){
        dataBase = new DataBase();
    }

    @Test
    public void testDelivery(){
        Location location = googleMapsAPI.findLocation("Україна", "Київ", "Ревуцького", "7");
        Location location1 = googleMapsAPI.findLocation("Україна", "Київ", "Тампере", "9");

        Request request = new Request("shalamay.vlad44@gmail.com",
                new Product("iphone", 1, 2), 1000, location, location1);
        dataBase.addRequest("shalamay.vlad44@gmail.com", new Product("iphone", 1, 2),
                1000, location, location1);
        new CourierController(dataBase).deliver("iphone");

        Assert.assertFalse(dataBase.getRequests().values().contains(request));
    }
}
