package model;

import geolocation.controller.Location;

public class Request {

    private int id;
    private String email;
    private Product product;
    private int price;
    private Location from;
    private Location to;
    private Department department;

    public Request(int id, String email, Product product, int price, Location from, Location to) {
        this.id = id;
        this.email = email;
        this.product = product;
        this.price = price;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Product getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Department getDepartment() {
        return department;
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

    @Override
    public String toString() {
        return "Request -> " +
                "id=" + id +
                ", email='" + email + '\'' +
                ", product=" + product.getName() +
                ", price=" + price +
                ", from=" + from +
                ", to=" + to +
                '\n';
    }
}
