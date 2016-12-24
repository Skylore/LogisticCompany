package employee;

import controller.BuilderController;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBuilderController {

    private static GoogleMapsAPI googleMapsAPI;
    private static DataBase dataBase;
    private static BuilderController builder;

    @BeforeClass
    public static void setUp(){
        googleMapsAPI = new GoogleMapsAPIImpl();
        dataBase = new DataBase();
        builder = new BuilderController(dataBase);
    }

    @Test(expected =  IllegalAccessException.class)
    public void negativeCheckIn() throws IllegalAccessException {
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

        Assert.assertEquals(7, DataBase.getDepartments().size());
    }

}
