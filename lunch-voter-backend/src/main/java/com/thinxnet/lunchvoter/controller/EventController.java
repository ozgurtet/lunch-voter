package com.thinxnet.lunchvoter.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.thinxnet.lunchvoter.entity.LunchEvent;
import com.thinxnet.lunchvoter.repo.LunchEventRepository;
import com.thinxnet.lunchvoter.repo.RestaurantRepository;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	LunchEventRepository eventRepo;
	
	@Autowired
	RestaurantRepository restaurantRepo;
	
	private static Logger logger = LoggerFactory.getLogger("LunchVoterLogger");
	
	@RequestMapping(value="/create", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<List<LunchEvent>> create(@RequestBody LunchEvent lunchEvent) {
		logger.info(lunchEvent.toString());
		eventRepo.save(lunchEvent);
		List<LunchEvent> eventList = eventRepo.findAll();
		return new ResponseEntity<List<LunchEvent>>(eventList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{eventId}", method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<List<LunchEvent>> delete(@PathVariable Long eventId) {
		
		if(eventRepo.existsById(eventId)) {
			eventRepo.deleteById(eventId);	
		}
		
		List<LunchEvent> eventList = eventRepo.findAll();
		return new ResponseEntity<List<LunchEvent>>(eventList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<LunchEvent>> list() {
		List<LunchEvent> eventList = eventRepo.findAll();
		return new ResponseEntity<List<LunchEvent>>(eventList, HttpStatus.OK);
	}
	
}
