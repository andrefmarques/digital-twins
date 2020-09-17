package pt.ua.deti.esp41.digitaltwinsfrontend;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BusController {

    public BusController(RestTemplate restTemplate, BusRepository repository){
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    private Bus[] buses;
    private BusRepository repository;

    // A logger, to send output to the log (the console, in this example).
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private RestTemplate restTemplate;

    @GetMapping("/buses")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
//        Bus[] bus = restTemplate.getForObject("http://192.168.160.103:41001/average-speed/2", Bus[].class);

        String[] busIds = restTemplate.getForObject("http://192.168.160.103:41003/location-history/all-ids", String[].class);

        Map<String, Location[]> locationHistory = new HashMap<>();
        Map<String, Location> locationRT = new HashMap<>();
        Map<String, Double> averageSpeed = new HashMap<>();

        for (String id: busIds){
            Location[] l = restTemplate.getForObject("http://192.168.160.103:41003/location-history/"+id, Location[].class);
            locationHistory.put(id, l);

            Location l_rt = restTemplate.getForObject("http://192.168.160.103:41004/location-rt/"+id, Location.class);
            locationRT.put(id, l_rt);

            Double a_speed = restTemplate.getForObject("http://192.168.160.103:41001/average-speed/"+id, Double.class);
            averageSpeed.put(id, a_speed);
        }

        model.addAttribute("busIds", busIds);
        model.addAttribute("location_history", locationHistory);
        model.addAttribute("location_rt", locationRT);
        model.addAttribute("average_speed", averageSpeed);




        return "buses";
    }

    @GetMapping("/buses/{busID}")
    public ResponseEntity<Bus> getBusById(@PathVariable(value = "busID") String sbusID) throws NoBusException {
        Long busID = Long.parseLong(sbusID);
        Bus bus = repository.findById(busID).orElseThrow(() -> new NoBusException("Bus " + busID + " not found!"));
        return ResponseEntity.ok().body(bus);
    }

    //average speed
    //bus routes
    //location-history
    //location-rt

    
    //25000
    //3600000
//    @Scheduled(fixedRate = 3600000)     //  1hour
    @Scheduled(fixedRate = 10000)         // 10 seconds
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        //buses = restTemplate.getForObject("https://opensky-network.org/api/flights/all?begin=1517227200&end=1517230800", Bus[].class);
        //for (Bus bus : buses){
            //System.out.println(bus);
            //repository.save(bus);
        //}
        //log.info("Got "+  buses.length +" buses");
        log.info("Database has "+repository.count() +" buses");
    }

}
