package pt.ua.deti.esp41.digitaltwinsfrontend;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.json.JSONObject;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @Getter private List<String> logList = new ArrayList<>();
    @Getter private List<String> systemList = new ArrayList<>();
    @Getter private List<Bus> busList = new ArrayList<>();
    
    @Autowired
    private BusRepository repository;
    
    @KafkaListener(topics = "esp41-buses", groupId = "esp41-frontend")
    public void consume(String message){
        JSONObject jo = new JSONObject(message);

        Bus bus = new Bus(Long.valueOf(jo.getInt("id")),
                            jo.getString("busId"),
                            jo.getInt("locationId"),
                            Float.parseFloat(jo.getString("heading")),
                            Float.parseFloat(jo.getString("longitude")),
                            Float.parseFloat(jo.getString("latitude")),
                            jo.getString("speed").equals("") ? 0 : jo.getInt("speed"),
                            jo.getString("ts"),
                            jo.getString("writeTime")
                        );

//        repository.save(bus);
        busList.add(bus);



        System.out.println("BUS: "+message);
    /*
        System.out.println(Long.valueOf(jo.getInt("id")));
        System.out.println(jo.getString("busId"));
        System.out.println(jo.getInt("locationId"));
        System.out.println(Float.parseFloat(jo.getString("heading")));
    */
//        logger.info(String.format("BUS FROM PYTHON -> Consumed Message -> %s",message));
    }

    @KafkaListener(topics = "esp41-logs", groupId = "esp41-frontend")
    public void consumeLogs(String message){
        if (logList.size() > 1000){
            logList.remove(0);
        }
        logList.add(message);
    }

    @KafkaListener(topics = "esp41-system", groupId = "esp41-frontend")
    public void consumeSystem(String message){
        systemList.add(message);
    }
}