package pt.ua.deti.esp41.digitaltwinsaveragespeed;

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
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BusTimeController {

    public BusTimeController(RestTemplate restTemplate, BusTimeRepository repository){
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    private BusTime[] busTimes;
    private BusTimeRepository repository;

    // A logger, to send output to the log (the console, in this example).
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private RestTemplate restTemplate;

    @GetMapping("/average-speed/{busID}")
    public ResponseEntity<Double> getBusAverageSpeedById(@PathVariable(value = "busID") String busId) {
        //List<BusTime> busTimes = (List<BusTime>) repository.findByBusID(busId);
        List<BusTime> busTimes = (List<BusTime>) repository.findByBusId(busId);

        double speedSum = 0;
        for(BusTime time : busTimes) {
            System.out.println(time.getBusId() + " " + time.getSpeed());
            speedSum += time.getSpeed();
        }
        
        return ResponseEntity.ok().body(busTimes.size() == 0 ? 0.0 : speedSum/busTimes.size());
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
