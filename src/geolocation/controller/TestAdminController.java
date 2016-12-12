package geolocation.controller;

import controller.AdminController;
import controller.ClientController;
import database.DataBase;
import model.Product;

/**
 * Created by Влад on 12.12.2016.
 */
public class TestAdminController {

    public static void main(String[] args) {

        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
        DataBase dataBase = new DataBase();
        AdminController admin = new AdminController(dataBase);
        ClientController client = new ClientController(dataBase);

        /*client.sendProductRequest(new Product("iphone", 1, 1), "shalamay.vlad44@gmail.com",
                GoogleMapsAPIImpl.fin)*/

    }

}
