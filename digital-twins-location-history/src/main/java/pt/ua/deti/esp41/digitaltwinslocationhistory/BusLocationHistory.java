package pt.ua.deti.esp41.digitaltwinslocationhistory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Entity
public class BusLocationHistory {
    @Id @Getter @Setter private Long id;
    @Getter @Setter private String busId;
    @Getter @Setter private int locationId;
    @Getter @Setter private float longitude;
    @Getter @Setter private float latitude;
    
    public BusLocationHistory(){}

    public BusLocationHistory(Long id, String busId, int locationId, float longitude, float latitude)
    {
        this.id = id;
        this.busId = busId;
        this.locationId = locationId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
