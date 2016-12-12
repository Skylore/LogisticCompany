package tests;

import controller.AdminController;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import model.Product;
import model.Request;

/**
 * Created by Влад on 12.12.2016.
 */
public class TestAdminController {

    public static void main(String[] args) {

        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
        DataBase dataBase = new DataBase();
        AdminController admin = new AdminController(dataBase);

        positiveTestShowAllRequest(googleMapsAPI, dataBase, admin);
        negativeTestShowAllRequest(googleMapsAPI, dataBase, admin);
        testAddDepartment(googleMapsAPI, admin);

    }

    private static void testAddDepartment(GoogleMapsAPI googleMapsAPI, AdminController admin) {
        admin.addDepartment(googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "11"));
        admin.addDepartment(googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "12"));

        System.out.println("addDepartment() is " + (DataBase.getDepartments().size() == 7));
    }

    private static void negativeTestShowAllRequest(GoogleMapsAPI googleMapsAPI, DataBase dataBase, AdminController admin) {
        dataBase.getRequests().add(new Request(1, "shalamay.vlad44@gmail.com",
                new Product("iphone", 1, 1), 100,
                googleMapsAPI.findLocation("Україна", "Львів", "Словацького", "1"),
                googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "9")));

        dataBase.getDelivered().add(new Request(1, "shalamay.vlad44@gmail.com",
                new Product("ball", 1, 1), 5,
                googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "9"),
                googleMapsAPI.findLocation("Україна", "Львів", "Словацького", "1")));

        String res = admin.showRequestsInTheDepartment(2);

        System.out.println("negative showRequestInTheDepartment() is " + res.isEmpty());
    }

    private static void positiveTestShowAllRequest(GoogleMapsAPI googleMapsAPI, DataBase dataBase, AdminController admin) {

        dataBase.getRequests().add(new Request(1, "shalamay.vlad44@gmail.com",
                new Product("iphone", 1, 1), 100,
                googleMapsAPI.findLocation("Україна", "Львів", "Словацького", "1"),
                googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "9")));

        dataBase.getDelivered().add(new Request(1, "shalamay.vlad44@gmail.com",
                new Product("ball", 1, 1), 5,
                googleMapsAPI.findLocation("Украйна", "Київ", "Тампере", "9"),
                googleMapsAPI.findLocation("Україна", "Львів", "Словацького", "1")));

        String res = admin.showRequestsInTheDepartment(1);

        System.out.println("positive showRequestInTheDepartment() is " +
                (res.contains("iphone") && res.contains("ball")));
    }

}
