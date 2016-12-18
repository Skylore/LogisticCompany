package geolocation.controller;

import java.io.Serializable;

public class Location implements Serializable{

    private String formattedAddress;
    private double lat;
    private double lng;
    private String placeId;
    private String label;

    public Location() {
    }

    public Location(double lat, double lng, String label) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
    }

    public Location(String formattedAddress, double lat, double lng) {
        this.formattedAddress = formattedAddress;
        this.lat = lat;
        this.lng = lng;
    }

    public Location(String formattedAddress, double lat, double lng, String placeId) {
        this.formattedAddress = formattedAddress;
        this.lat = lat;
        this.lng = lng;
        this.placeId = placeId;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", lat, lng);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Double.compare(location.lat, lat) != 0) return false;
        if (Double.compare(location.lng, lng) != 0) return false;
        if (formattedAddress != null ? !formattedAddress.equals(location.formattedAddress) : location.formattedAddress != null)
            return false;
        if (placeId != null ? !placeId.equals(location.placeId) : location.placeId != null) return false;
        return label != null ? label.equals(location.label) : location.label == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = formattedAddress != null ? formattedAddress.hashCode() : 0;
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}