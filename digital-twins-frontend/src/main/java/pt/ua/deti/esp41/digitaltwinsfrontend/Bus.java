package pt.ua.deti.esp41.digitaltwinsfrontend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Entity
public class Bus {
    @Id @Getter @Setter private Long id;
    @Getter @Setter private String busId;
    @Getter @Setter private int locationId;
    @Getter @Setter private float heading;
    @Getter @Setter private float longitude;
    @Getter @Setter private float latitude;
    @Getter @Setter private int speed;
    @Getter @Setter private String ts;
    @Getter @Setter private String writeTime;

    public Bus(Long id, String busId, int locationId, float heading, float longitude,
                float latitude, int speed, String ts, String writeTime)
    {
        this.id = id;
        this.busId = busId;
        this.locationId = locationId;
        this.heading = heading;
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
        this.ts = ts;
        this.writeTime = writeTime;
    }

    //DO NOT ERASE OR CHANGE THIS CONSTRUCTOR - IT IS NEEDED FOR MYSQL, TOUCHING THIS WILL BREAK OUR HOMES
    public Bus(){}
}
