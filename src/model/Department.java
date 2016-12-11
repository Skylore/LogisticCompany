package model;

import geolocation.controller.Location;

/**
 * Created by Влад on 11.12.2016.
 */
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

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
