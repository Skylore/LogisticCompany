package controller;

import geolocation.controller.Location;

/**
 * Created by Влад on 13.12.2016.
 */
public interface IBuilder extends IEmployee {

    void build(Location location);

}
