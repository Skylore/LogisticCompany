package controller;

import geolocation.controller.Location;
import model.Product;

public interface IClientController {

    int sendProductRequest(Product product, String email, Location from, Location to);

    String whereIsMyProduct(int id);

    Product getProduct(int id);

}
