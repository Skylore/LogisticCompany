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
    private Department department;

    public Request(int id, Product product, int price, Location from, Location to) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (id != request.id) return false;
        if (price != request.price) return false;
        if (product != null ? !product.equals(request.product) : request.product != null) return false;
        if (from != null ? !from.equals(request.from) : request.from != null) return false;
        return to != null ? to.equals(request.to) : request.to == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
