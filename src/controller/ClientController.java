package controller;

import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import database.DataBase;
import model.Product;
import model.Request;

import java.awt.*;

public class ClientController implements IClientController{

    private static final int PRICE_BY_KILOMETER = 20;

    private GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
    private DataBase dataBase;
    private static int id = 0;

    public ClientController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int sendProductRequest(Product product,String email, Location from, Location to) {

        double allDistance = googleMapsAPI.getDistance(from, to) / 1000;

        dataBase.addRequest(new Request(id, email, product,
                ((int) (allDistance * PRICE_BY_KILOMETER)), from, to));

        return id++;
    }

    @Override
    public String whereIsMyProduct(int id) {

        for (Request o : dataBase.getRequests()) {
            if (o.getId() == id) {
                return "Your product is awaiting";
            }
        }

        for (Request o : dataBase.getDelivered()) {
            if (o.getId() == id) {
                return "Your product delivered";
            }
        }

        return "Your product is delivering";
    }
}
