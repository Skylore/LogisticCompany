package controller;

import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import model.DataBase;
import model.Product;
import model.Request;


public class ClientController implements IClientController{

    public static final int PRICE_BY_KILOMETER = 20;

    private GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
    private DataBase dataBase;
    private static int id = 0;

    public ClientController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int sendProductRequest(Product product, Location from, Location to) {

        double allDistance = googleMapsAPI.getDistance(from, to) / 1000;

        dataBase.addRequest(new Request(id, product,
                ((int) (allDistance * PRICE_BY_KILOMETER)), from, to));

        return id++;
    }

    @Override
    public String whereIsMyProduct(int id) {
        return null;
    }
}
