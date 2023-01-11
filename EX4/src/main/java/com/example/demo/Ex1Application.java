package com.example.demo;

import com.example.demo.Repository.SightRepository;
import com.example.demo.klsCrawler.KeelungSightsCrawler;
import com.example.demo.APIController.Controller;
import com.example.demo.sighter.Sight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@SpringBootApplication
public class Ex1Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(Ex1Application.class, args);
		applicationContext.start();
	}
	@Component
	public class EventHandler implements ApplicationListener<ContextStartedEvent> {
		@Autowired
		public SightRepository repository;

		@Override
		public void onApplicationEvent(ContextStartedEvent event){
			List<Sight> sightList;
			KeelungSightsCrawler crawler = new KeelungSightsCrawler();
			sightList = crawler.getItem();
			for (Sight s : sightList){
				System.out.println(s);
				repository.save(s);
			}
		}
	}
}
