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

import com.thinxnet.lunchvoter.entity.EventVote;
import com.thinxnet.lunchvoter.entity.Vote;
import com.thinxnet.lunchvoter.repo.VoteRepository;
import com.thinxnet.lunchvoter.repo.VotesByEventRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/vote")
public class VoteController {
	
	private static Logger logger = LoggerFactory.getLogger("LunchVoterLogger");
	
	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	VotesByEventRepository voteByEventRepository;
	
	@RequestMapping(value="/insert", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<Vote> saveVote(@RequestBody Vote vote) {
		Vote insertedVote = voteRepository.save(vote);
		return new ResponseEntity<Vote>(insertedVote, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE, consumes="application/json", produces="application/json")
	public ResponseEntity<List<Vote>> delete(@RequestBody Vote vote) {
		voteRepository.delete(vote);
		List<Vote> voteList = voteRepository.findAll();
		return new ResponseEntity<List<Vote>>(voteList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/listUserVote", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<List<Vote>> listUserVote(@RequestBody Vote vote) {
		 List<Vote> votes = voteRepository.findVotesByEventIdAndUserId(vote.getEventId(), vote.getUserId());
		
		 return new ResponseEntity<List<Vote>>(votes, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/listAllVotesByEvent", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<List<EventVote>> listAllVotesByEvent(@RequestBody Vote vote) {
		 List<EventVote> votes = voteByEventRepository.findAllVotesByEvent(vote.getEventId());
		 return new ResponseEntity<List<EventVote>>(votes, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteUserVote/{userId}/{eventId}", method=RequestMethod.DELETE, consumes="application/json", produces="application/json")
	public ResponseEntity<List<Vote>> deleteUserVote(@PathVariable Long userId, @PathVariable Long eventId) {
		System.out.println(userId + "-" + eventId);
		voteRepository.deleteVoteByEventIdUsername(eventId, userId);
		List<Vote> voteList = voteRepository.findAll();
		return new ResponseEntity<List<Vote>>(voteList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteAllUserVotes/{eventId}", method=RequestMethod.DELETE, consumes="application/json", produces="application/json")
	public ResponseEntity<List<Vote>> deleteUserVote(@PathVariable Long eventId) {
		voteRepository.deleteAllVoteByEventId(eventId);
		List<Vote> voteList = voteRepository.findAll();
		return new ResponseEntity<List<Vote>>(voteList, HttpStatus.OK);
	}
	
}
