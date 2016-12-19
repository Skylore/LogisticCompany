import com.google.gson.Gson;
import controller.ClientController;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import model.Product;
import model.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUserController {

    private GoogleMapsAPI googleMapsAPI;
    private DataBase db;
    private ClientController controller;
    private Location location1;
    private Location location2;

    @Before
    public void setUp() {
        googleMapsAPI = new GoogleMapsAPIImpl();
        db = new DataBase();
        controller = new ClientController(db);
        location1 = googleMapsAPI.findLocation("Україна", "Київ", "Ревуцького", "7");
        location2 = googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "9");

    }

    @Test
    public void testSendProductRequest() {

        controller.sendProductRequest(new Product("SomeProduct", 10, 10), "shalamay.vlad44@mail",
                location1, location2);

        Request request = new Request(1, "shalamay.vlad44@mail", new Product("SomeProduct", 10, 10),
                (int) ((googleMapsAPI.getDistance(location1, location2) / 1000 * 20)), location1, location2);

        Gson gson = new Gson();
        Assert.assertTrue(db.getRequests().get(0).equals(request));
    }

    @Test
    public void testWhereIsMyProduct() {

        controller.sendProductRequest(new Product("SomeProduct", 10, 10), "shalamay.vlad44@mail",
                location1, location2);
        Assert.assertEquals("Your product is awaiting", controller.whereIsMyProduct(0));
    }
}
