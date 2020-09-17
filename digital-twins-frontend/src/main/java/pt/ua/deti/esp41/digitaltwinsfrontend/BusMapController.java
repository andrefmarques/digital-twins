
package pt.ua.deti.esp41.digitaltwinsfrontend;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class BusMapController {
    public BusMapController(RestTemplate restTemplate, BusRepository repository){
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    private Bus[] buses;
    private BusRepository repository;

    // A logger, to send output to the log (the console, in this example).
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private RestTemplate restTemplate;
    
    @GetMapping("/buses-map")
    public String busesMap(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

        String[] busIds = restTemplate.getForObject("http://192.168.160.103:41003/location-history/all-ids", String[].class);

        Map<String, Location> locationRT = new HashMap<>();

        for (String id: busIds){
            Location l_rt = restTemplate.getForObject("http://192.168.160.103:41004/location-rt/"+id, Location.class);
            locationRT.put(id, l_rt);
        }

        String[] testBusIds = {"0000-2818","0000-2219","0000-2816","0000-2733","0000-2655","0000-2534","0000-2111","0000-2222","0000-2345"};
        Map<String, Location> testLocationRT = new HashMap<>();
        Location l_rt1 = new Location(1,41.1695,-8.64285);
        Location l_rt2 = new Location(2,41.1660,-8.63729);
        Location l_rt3 = new Location(3,41.1770,-8.64125);
        Location l_rt4 = new Location(4,41.1610,-8.64316); 
        Location l_rt5 = new Location(5,41.1829,-8.6293); 
        Location l_rt6 = new Location(6,41.1888,-8.61976);
        Location l_rt7 = new Location(7,41.1558,-8.61256);
        Location l_rt8 = new Location(8,41.1488,-8.61300);
        Location l_rt9 = new Location(9,41.1599,-8.62760);

        testLocationRT.put(testBusIds[0], l_rt1);
        testLocationRT.put(testBusIds[1], l_rt2);
        testLocationRT.put(testBusIds[2], l_rt3);
        testLocationRT.put(testBusIds[3], l_rt4);
        testLocationRT.put(testBusIds[4], l_rt5);
        testLocationRT.put(testBusIds[5], l_rt6);
        testLocationRT.put(testBusIds[6], l_rt7);
        testLocationRT.put(testBusIds[7], l_rt8);
        testLocationRT.put(testBusIds[8], l_rt9);


        model.addAttribute("busIds", testBusIds);
        model.addAttribute("location_rt", testLocationRT);

        //model.addAttribute("busIds", busIds);
        //model.addAttribute("location_rt", locationRT);

        return "buses-map";
    }
}
