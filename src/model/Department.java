package model;

import geolocation.controller.Location;

public class Department {

    private int id;
    private Location location;

    public Department(int id, Location location) {
        this.id = id;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Department ->" +
                "id=" + id +
                ", location=" + location.getFormattedAddress() +
                '\n';
    }
}
