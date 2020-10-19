package com.thinxnet.lunchvoter.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.thinxnet.lunchvoter.entity.EventVote;

public interface VotesByEventRepository  extends JpaRepository<EventVote, Long>{
	
	
	@Modifying
	@Transactional
	@Query(value="SELECT V.VOTE_ID, V.EVENT_ID, V.RESTAURANT_ID, U.USER_ID , U.USER_NAME " + 
			"FROM VOTE  V " + 
			"JOIN USER U ON (V.USER_ID = U.USER_ID) " + 
			"JOIN RESTAURANT R ON (V.RESTAURANT_ID = R.ID) " + 
			"WHERE V.EVENT_ID = :eventId", nativeQuery = true)
	List<EventVote> findAllVotesByEvent(@Param("eventId") Long eventId);
	
}
