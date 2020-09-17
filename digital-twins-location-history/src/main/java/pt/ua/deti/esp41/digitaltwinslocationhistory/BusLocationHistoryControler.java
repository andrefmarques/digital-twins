package pt.ua.deti.esp41.digitaltwinslocationhistory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BusLocationHistoryControler {

    public BusLocationHistoryControler(RestTemplate restTemplate, BusLocationHistoryRepository repository){
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    private BusLocationHistoryRepository repository;

    // A logger, to send output to the log (the console, in this example).
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private RestTemplate restTemplate;

    @GetMapping("/location-history/{busID}")
    public ResponseEntity<ArrayList<LocationInfo>> getBusLocationHistoryById(@PathVariable(value = "busID") String busId) {
        
        List<BusLocationHistory> busesLocHist = (List<BusLocationHistory>) repository.findByBusId(busId);
        ArrayList<LocationInfo> locationInfos = new ArrayList<>();
        
        for(BusLocationHistory busLocHist : busesLocHist) {
            locationInfos.add(new LocationInfo(busLocHist.getLocationId(), busLocHist.getLatitude(), busLocHist.getLongitude()));
        }
        
        System.out.println("location Info of bus " + busesLocHist.get(0).getBusId() + " : " + locationInfos);

        return ResponseEntity.ok().body(locationInfos);
    }

    @GetMapping("/location-history/all-ids")
    public ResponseEntity<HashSet<String>> getBusIDs() {

        List<BusLocationHistory> busesLocHist = (List<BusLocationHistory>) repository.findAll();
        HashSet<String> busIDs = new HashSet<>();

        for(BusLocationHistory busLocHist : busesLocHist) {
            busIDs.add(busLocHist.getBusId());
        }

        return ResponseEntity.ok().body(busIDs);
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
