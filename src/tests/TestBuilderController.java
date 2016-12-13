package tests;

import controller.BuilderController;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;

/**
 * Created by Влад on 13.12.2016.
 */
public class TestBuilderController {

    public static void main(String[] args) {

        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
        DataBase dataBase = new DataBase();
        BuilderController builder = new BuilderController(dataBase);

        testBuild(googleMapsAPI, builder);
        positiveTestCheckIn(builder);
        negativeTestCheckIn(builder);
        testChekOut(builder);


    }

    private static void testChekOut(BuilderController builder) {
        builder.checkIn("builderPass");
        builder.checkOut();
        System.out.println("checkOut() is " + !builder.isInSystem());
    }

    private static void negativeTestCheckIn(BuilderController builder) {
        builder.checkIn("123245");
        System.out.println("negative checkIn() is " + !builder.isInSystem());
    }

    private static void positiveTestCheckIn(BuilderController builder) {
        builder.checkIn("builderPass");
        System.out.println("positive checkIn() is " + builder.isInSystem());
    }

    private static void testBuild(GoogleMapsAPI googleMapsAPI, BuilderController builder) {
        builder.build(googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "11"));
        builder.build(googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "12"));

        System.out.println("build() is " + (DataBase.getDepartments().size() == 7));
    }
}
