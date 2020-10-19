package com.thinxnet.lunchvoter.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
import com.thinxnet.lunchvoter.entity.User;
import com.thinxnet.lunchvoter.repo.LunchEventRepository;
import com.thinxnet.lunchvoter.repo.RestaurantRepository;
import com.thinxnet.lunchvoter.repo.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger("LunchVoterLogger");
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RestaurantRepository restaurantRepo;
	
	@Autowired
	LunchEventRepository eventRepo;
	
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<List<User>> login(@RequestBody User user) {
		
		List<User> exisitingUser = userRepo.findByUserName(user.getUserName());
		
		if(exisitingUser.size() == 0) {
			return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
		}
		
		if(!exisitingUser.get(0).getPassword().equals(user.getPassword())) {
			return new ResponseEntity("Wrong User Cridential", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<User>>(exisitingUser, HttpStatus.OK);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.PUT, consumes="application/json", produces="application/json")
	public ResponseEntity<List<User>> save(@RequestBody User user) {
		
		List<User> exisitingUser = userRepo.findByUserName(user.getUserName());
		
		if(exisitingUser.size() > 0) {
			return new ResponseEntity(exisitingUser, HttpStatus.FOUND);
		}
		
		userRepo.save(user);
		List<User> userList = userRepo.findAll();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{userId}", method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<List<User>> delete(@PathVariable Long userId) {
		
		if(userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
		}
		
		List<User> userList = userRepo.findAll();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<User>> list() {
		List<User> userList = userRepo.findAll();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT, consumes="application/json", produces="application/json")
	public ResponseEntity<List<User>> update(@RequestBody User user) {
		userRepo.save(user);
		List<User> userList = userRepo.findAll();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	
}
