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
import com.thinxnet.lunchvoter.entity.Restaurant;
import com.thinxnet.lunchvoter.repo.RestaurantRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	private static Logger logger = LoggerFactory.getLogger("LunchVoterLogger");
	
	@Autowired
	RestaurantRepository restaurantRepo;
	
	@RequestMapping(value="/create", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<List<Restaurant>> create(@RequestBody Restaurant restaurant) {
		
		List<Restaurant> exisitingRestaurant = restaurantRepo.findByName(restaurant.getName());
		
		if(exisitingRestaurant.size() > 0) {
			return new ResponseEntity("Restaurant exist", HttpStatus.OK);
		}
		
		
		restaurantRepo.save(restaurant);
		List<Restaurant> restaurantList = restaurantRepo.findAll();
		return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT, consumes="application/json", produces="application/json")
	public ResponseEntity<List<Restaurant>> update(@RequestBody Restaurant restaurant) {
		restaurantRepo.save(restaurant);
		List<Restaurant> restaurantList = restaurantRepo.findAll();
		return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/delete/{restaurantId}", method=RequestMethod.DELETE, consumes="application/json", produces="application/json")
	public ResponseEntity<List<Restaurant>> delete(@PathVariable Long restaurantId) {
		restaurantRepo.deleteById(restaurantId);
		List<Restaurant> restaurantList = restaurantRepo.findAll();
		return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET, consumes="application/json", produces="application/json")
	public ResponseEntity<List<Restaurant>> list() {
		List<Restaurant> restaurantList = restaurantRepo.findAll();
		return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
	}
}
