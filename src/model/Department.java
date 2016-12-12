package model;

import geolocation.controller.Location;

public class Department {

    private int id;
    private Location location;
    private Department next;

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
