package pt.ua.deti.esp41.digitaltwinsaveragespeed;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.json.JSONObject;

@Service
public class Consumer {

    @Autowired
    private BusTimeRepository repository;

    @KafkaListener(topics = "esp41-buses", groupId = "esp41-average-speed")
    public void consume(String message) {
        JSONObject jo = new JSONObject(message);

        BusTime busTime = new BusTime((long) jo.getInt("id"),
                jo.getString("busId"),
                jo.getString("speed").equals("") ? 0 : jo.getInt("speed")
        );

        repository.save(busTime);
    }

    @KafkaListener(topics = "esp41-manual-bus-distribution", groupId = "esp41-average-speed")
    public void consumeManual(String message) {
        JSONObject jo = new JSONObject(message);

        BusTime busTime = new BusTime((long) jo.getInt("id"),
                jo.getString("busId"),
                jo.getString("speed").equals("") ? 0 : jo.getInt("speed")
        );

        repository.save(busTime);
    }
}
