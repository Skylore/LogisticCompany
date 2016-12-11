package model;

import geolocation.controller.Location;

import java.util.List;

public class Department {

    private int id;
    private Location location;
    private Department next;
    private List<Product> products;

    public Department(int id, Location location, Department next) {
        this.id = id;
        this.location = location;
        this.next = next;
    }

    public Department getNext() {
        return next;
    }

    public void setNext(Department next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
