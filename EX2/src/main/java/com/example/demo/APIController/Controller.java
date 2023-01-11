package com.example.demo.APIController;
import com.example.demo.sighter.Sight;
import com.example.demo.klsCrawler.KeelungSightsCrawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController//for JSON
//@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    @GetMapping(value = "sight")
    public Sight[] sightApi(@RequestParam(value = "",defaultValue = "七堵區") String zone)
    {
        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        Sight [] sights = crawler.getItems(zone);
        for(Sight si : sights)
        {
            System.out.println(si);
        }
        return sights;
    }
}
