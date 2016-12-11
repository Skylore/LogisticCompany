package controller;

import geolocation.controller.Location;
import model.DataBase;
import model.Product;
import model.Request;


public class ClientController implements IClientController{

    private DataBase dataBase;
    private static int id = 0;

    public ClientController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int sendProductRequest(Product product, Location from, Location to) {

        dataBase.getRequests().add(new Request(product, from, to, id));
        return id++;
    }

    @Override
    public String whereIsMyProduct(int id) {
        return null;
    }
}
