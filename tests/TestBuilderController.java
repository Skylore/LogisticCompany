import controller.BuilderController;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestBuilderController {

    private static GoogleMapsAPI googleMapsAPI;
    private static DataBase dataBase;
    private static BuilderController builder;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUp(){
        googleMapsAPI = new GoogleMapsAPIImpl();
        dataBase = new DataBase();
        builder = new BuilderController(dataBase);
    }

    @Test
    public void negativeCheckIn() throws IllegalAccessException {
        exception.expect(IllegalAccessException.class);
        builder.checkIn("12345");
    }

    @Test
    public void positiveCheckIn() throws IllegalAccessException {
        builder.checkIn("builderPass");
    }

    @Test
    public void testBuild() {

        builder.build(googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "11"));
        builder.build(googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "12"));

        Assert.assertTrue(DataBase.getDepartments().size() == 7);
    }

}
