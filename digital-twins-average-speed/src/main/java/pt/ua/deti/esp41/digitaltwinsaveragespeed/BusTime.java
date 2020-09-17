package pt.ua.deti.esp41.digitaltwinsaveragespeed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Entity
public class BusTime {
    @Id @Getter @Setter private Long id;
    @Getter @Setter private String busId;
    @Getter @Setter private int speed;

    public BusTime(Long id, String busId, int speed)
    {
        this.id = id;
        this.busId = busId;
        this.speed = speed;
    }

    public BusTime(){}
}
