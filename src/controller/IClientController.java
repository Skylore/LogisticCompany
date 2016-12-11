package controller;

import geolocation.controller.Location;
import model.Product;

/**
 * Created by Влад on 11.12.2016.
 */
public interface IClientController {

    int sendProductRequest(Product product, Location from, Location to);
    String whereIsMyProduct(int id);

}
