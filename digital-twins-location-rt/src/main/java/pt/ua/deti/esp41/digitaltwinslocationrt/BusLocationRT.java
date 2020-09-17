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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Entity
public class BusLocationRT {
    
    
    @Id @Getter @Setter private Long id;
    @Getter @Setter private String busId;
    @Getter @Setter private int locationId;
    @Getter @Setter private float longitude;
    @Getter @Setter private float latitude;
    public BusLocationRT(){ }
    
     public BusLocationRT(Long id, String busId, int locationId, float longitude, float latitude)
    {
        this.id = id;
        this.busId = busId;
        this.locationId = locationId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    
}
