package pt.ua.deti.esp41.digitaltwinsbusroutes;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BusLocationController {

    public BusLocationController(RestTemplate restTemplate, BusLocationRepository repository){
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    private BusLocations[] busLocations;
    private BusLocationRepository repository;
    private static final ManualBusProducer producer = new ManualBusProducer();

    // A logger, to send output to the log (the console, in this example).
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private RestTemplate restTemplate;

    @GetMapping("/bus-route/{busID}")
    public ResponseEntity<ArrayList<ArrayList<LocationInfo>>> getBusRoutesById(@PathVariable(value = "busID") String busId) {
        List<BusLocations> busLocations = (List<BusLocations>) repository.findByBusId(busId);

        ArrayList<ArrayList<LocationInfo>> routes = new ArrayList<>();
        ArrayList<Integer> currentLocations = new ArrayList<>();
        ArrayList<LocationInfo> locationInfos = new ArrayList<>();

        for(BusLocations busLocation : busLocations) {
            if(!currentLocations.contains(busLocation.getLocationId())) {
                currentLocations.add(busLocation.getLocationId());
                locationInfos.add(new LocationInfo(busLocation.getLocationId(), busLocation.getLatitude(), busLocation.getLongitude()));
            }
            else {
                routes.add(new ArrayList<>(locationInfos));
                currentLocations.clear();
                locationInfos.clear();
                currentLocations.add(busLocation.getLocationId());
                locationInfos.add(new LocationInfo(busLocation.getLocationId(), busLocation.getLatitude(), busLocation.getLongitude()));
            }
        }

        if(locationInfos.size() > 0) routes.add(new ArrayList<>(locationInfos));

        return ResponseEntity.ok().body(routes);
    }

    @PostMapping("/bus-route/add-bus-location/{ID}&{busID}&{locationID}&{lon}&{lat}&{speed}")
    @ResponseBody
    public void setBusRouteById(
            @PathVariable(value = "ID") Long id,
            @PathVariable(value = "busID") String busId,
            @PathVariable(value = "locationID") int locationId,
            @PathVariable(value = "lon") float longitude,
            @PathVariable(value = "lat") float latitude,
            @PathVariable(value = "speed") int speed
    ) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.append("id", id);
        jsonObject.append("busId", busId);
        jsonObject.append("locationId", locationId);
        jsonObject.append("longitude", longitude);
        jsonObject.append("latitude", latitude);
        jsonObject.append("speed", speed);

//        System.out.println("producer = " + producer);
//        producer.sendMessage("esp41-manual-bus-distribution", jsonObject.toString());
        repository.save(new BusLocations(id, busId, locationId, longitude, latitude));
    }

/*    @PostMapping(value = "/bus-route/add-bus-location", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public BusLocations setBusRouteById(@RequestBody Bus bus) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.append("id", bus.getId());
        jsonObject.append("busId", bus.getBusId());
        jsonObject.append("locationId", bus.getLocationId());
        jsonObject.append("longitude", bus.getLongitude());
        jsonObject.append("latitude", bus.getLatitude());
        jsonObject.append("speed", bus.getSpeed());

        producer.sendMessage("esp41-manual-bus-distribution", jsonObject.toString());

        BusLocations busLocations = new BusLocations(bus.getId(), bus.getBusId(), bus.getLocationId(), bus.getLongitude(), bus.getLatitude());
        repository.save(busLocations);
        return busLocations;
    }*/

        //25000
    //3600000
    //@Scheduled(fixedRate = 3600000)     //  1hour
    @Scheduled(fixedRate = 10000)         // 10 seconds
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        log.info("Database has "+repository.count() +" bus times");
    }

}
