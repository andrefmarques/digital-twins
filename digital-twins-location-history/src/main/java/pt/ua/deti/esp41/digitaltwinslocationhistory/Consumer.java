package pt.ua.deti.esp41.digitaltwinslocationhistory;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private BusLocationHistoryRepository repository;

    @KafkaListener(topics = "esp41-buses", groupId = "esp41-location-history")
    public void consume(String message) {
        JSONObject jo = new JSONObject(message);

        BusLocationHistory busLocHist = new BusLocationHistory(
                (long) jo.getInt("id"),
                jo.getString("busId"),
                jo.getInt("locationId"),
                Float.parseFloat(jo.getString("longitude")),
                Float.parseFloat(jo.getString("latitude"))
        );

        repository.save(busLocHist);
    }

    @KafkaListener(topics = "esp41-manual-bus-distribution", groupId = "esp41-location-history")
    public void consumeManual(String message) {
        JSONObject jo = new JSONObject(message);

        BusLocationHistory busLocHist = new BusLocationHistory(
                (long) jo.getInt("id"),
                jo.getString("busId"),
                jo.getInt("locationId"),
                Float.parseFloat(jo.getString("longitude")),
                Float.parseFloat(jo.getString("latitude"))
        );

        repository.save(busLocHist);
    }
}
