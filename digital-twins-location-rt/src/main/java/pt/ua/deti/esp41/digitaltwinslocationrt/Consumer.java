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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private BusLocationRTRepository repository;

    @KafkaListener(topics = "esp41-buses", groupId = "esp41-location-rt")
    public void consume(String message) {
        JSONObject jo = new JSONObject(message);

        BusLocationRT busLocRT = new BusLocationRT(
                (long) jo.getInt("id"),
                jo.getString("busId"),
                jo.getInt("locationId"),
                Float.parseFloat(jo.getString("longitude")),
                Float.parseFloat(jo.getString("latitude"))
        );

        repository.save(busLocRT);
    }

    @KafkaListener(topics = "esp41-manual-bus-distribution", groupId = "esp41-location-rt")
    public void consumeManual(String message) {
        JSONObject jo = new JSONObject(message);

        BusLocationRT busLocRT = new BusLocationRT(
                (long) jo.getInt("id"),
                jo.getString("busId"),
                jo.getInt("locationId"),
                Float.parseFloat(jo.getString("longitude")),
                Float.parseFloat(jo.getString("latitude"))
        );
    }
}
