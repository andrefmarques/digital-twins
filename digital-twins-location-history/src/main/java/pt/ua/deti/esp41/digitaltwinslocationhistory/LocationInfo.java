package pt.ua.deti.esp41.digitaltwinslocationhistory;

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
