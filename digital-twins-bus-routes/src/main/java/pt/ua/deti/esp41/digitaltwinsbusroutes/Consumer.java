package pt.ua.deti.esp41.digitaltwinsbusroutes;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private BusLocationRepository repository;

    @KafkaListener(topics = "esp41-buses", groupId = "esp41-bus-routes")
    public void consume(String message) {
        JSONObject jo = new JSONObject(message);

        BusLocations busLocations = new BusLocations((long) jo.getInt("id"),
                jo.getString("busId"),
                jo.getInt("locationId"),
                Float.parseFloat(jo.getString("longitude")),
                Float.parseFloat(jo.getString("latitude"))
        );

        repository.save(busLocations);
    }
}
