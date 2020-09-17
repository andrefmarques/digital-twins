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
import java.util.List;

@Controller
public class LoggerController {

    private Consumer consumer;

    public LoggerController(RestTemplate restTemplate, Consumer consumer){
        this.restTemplate = restTemplate;
        this.consumer = consumer;
    }

    // A logger, to send output to the log (the console, in this example).
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private RestTemplate restTemplate;

    @GetMapping("/logs")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("logList", consumer.getLogList());
        System.out.println("\n\n\n\n\n\n\nlogList = " + consumer.getLogList());
        return "logs";
    }

}