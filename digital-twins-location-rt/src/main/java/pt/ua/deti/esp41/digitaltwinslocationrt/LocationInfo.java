/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.esp41.digitaltwinslocationrt;

/**
 *
 * @author Tiago Fonseca
 */
public class LocationInfo {
      private int locationID;
    private float latitude;
    private float longitude;

    public LocationInfo(int locationID, float latitude, float longitude) {
        this.locationID = locationID;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLocationID() {
        return locationID;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
