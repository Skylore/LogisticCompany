package model;

import geolocation.controller.Location;

/**
 * Created by Влад on 11.12.2016.
 */
public class Request {

    private int id;
    private Product product;
    private Location from;
    private Location to;

    public Request(Product product, Location from, Location to, int id) {
        this.product = product;
        this.from = from;
        this.to = to;
        this.id = id;
    }
}
