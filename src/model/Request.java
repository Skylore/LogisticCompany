package model;

import geolocation.controller.Location;

/**
 * Created by Влад on 11.12.2016.
 */
public class Request {

    private int id;
    private Product product;
    private int price;
    private Location from;
    private Location to;

    public Request(int id, Product product, int price, Location from, Location to) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.from = from;
        this.to = to;
    }
}
