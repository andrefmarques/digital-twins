/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.esp41.digitaltwinslocationrt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Tiago Fonseca
 */
@Controller
public class BusLocationRTController {
    
    public BusLocationRTController(RestTemplate restTemplate, BusLocationRTRepository repository){
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    private BusLocationRTRepository repository;

    // A logger, to send output to the log (the console, in this example).
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private RestTemplate restTemplate;

    @GetMapping("/location-rt/{busID}")
    public ResponseEntity<LocationInfo> getBusLocationRTById(@PathVariable(value = "busID") String busID) {
        
       List<BusLocationRT> busesLocHist = (List<BusLocationRT>) repository.findByBusId(busID);
       
       ArrayList<LocationInfo> locationInfos = new ArrayList<>();
       LocationInfo lastKnownLocation = null;
        
        for(BusLocationRT busLocHist : busesLocHist) {
           // locationInfos.add(new LocationInfo(busLocHist.getLocationId(), busLocHist.getLatitude(), busLocHist.getLongitude()));
            lastKnownLocation = new LocationInfo(busLocHist.getLocationId(), busLocHist.getLatitude(), busLocHist.getLongitude());
          //  lastKnownLocation = locationInfos.get(0);
       }
       
        
        System.out.println("Real time location Info of bus " + busesLocHist.get(busesLocHist.size()-1).getBusId() + " : " + lastKnownLocation);

        return ResponseEntity.ok().body(lastKnownLocation);
    }

    //25000
    //3600000
    //@Scheduled(fixedRate = 3600000)     //  1hour
    @Scheduled(fixedRate = 10000)         // 10 seconds
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        log.info("Database has "+repository.count() +" bus times");
    }

}
