package pt.ua.deti.esp41.digitaltwinsbusroutes;

public class Bus {
    private Long id;
    private String busId;
    private int locationId;
    private float longitude;
    private float latitude;
    private int speed;

    public Bus(Long id, String busId, int locationId, float heading, float longitude,
               float latitude, int speed, String ts, String writeTime)
    {
        this.id = id;
        this.busId = busId;
        this.locationId = locationId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
    }

    public Long getId() {
        return id;
    }

    public String getBusId() {
        return busId;
    }

    public int getLocationId() {
        return locationId;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public int getSpeed() {
        return speed;
    }
}
