package pt.ua.deti.esp41.digitaltwinsfrontend;

import lombok.Getter;

public class Location {
    @Getter private int locationID;
    @Getter private double latitude;
    @Getter private double longitude;

    public Location(int loc, double lat, double lng){
        this.locationID = loc;
        this.latitude = lat;
        this.longitude = lng;
    }
}
