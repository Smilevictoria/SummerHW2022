package com.example.demo.APIController;
import com.example.demo.Repository.SightRepository;
import com.example.demo.servise.SightServise;
import com.example.demo.sighter.Sight;
import com.example.demo.klsCrawler.KeelungSightsCrawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//for JSON
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    @Autowired
    public SightServise service;

    @Autowired
    public SightRepository repository;
    
    @GetMapping("/sight/{zone}")
    public ResponseEntity <List<Sight>> GetSight(@PathVariable("zone") String zone)
    {
        List<Sight> sightList = service.getWhere(zone);
        return ResponseEntity.ok(sightList);
    }

   /* @GetMapping(value = "sight")
    public Sight[] sightApi(@RequestParam(value = "zone",defaultValue = "") String zone)
    {
        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        //Sight [] sights = crawler.getItems();
        for(Sight si : sights)
        {
            System.out.println(si);
            repository.save(si);
        }
        return sights;
    }*/

    @GetMapping("/sight")
    public List<Sight> GetSights(){
        return service.getAllWhere();
    }




}
