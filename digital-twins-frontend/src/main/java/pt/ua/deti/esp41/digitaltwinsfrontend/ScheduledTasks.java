package pt.ua.deti.esp41.digitaltwinsfrontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;


//@Component
public class ScheduledTasks {
    public ScheduledTasks (RestTemplate restTemplate){
//        this.restTemplate = restTemplate;
    }

//    // A logger, to send output to the log (the console, in this example).
//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//    private RestTemplate restTemplate;
//
//    @Scheduled(fixedRate = 25000)
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//        Flight[] flights = restTemplate.getForObject("https://opensky-network.org/api/flights/all?begin=1517227200&end=1517230800", Flight[].class);
//        for (Flight flight :flights){
//            log.info(flight.toString());
//        }
//        log.info(flights.toString());
//    }
}
