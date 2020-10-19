package com.thinxnet.lunchvoter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.thinxnet.lunchvoter.entity.EventVote;
import com.thinxnet.lunchvoter.entity.Vote;
import java.lang.Long;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	
	@Query(value = "SELECT * FROM Vote v WHERE v.EVENT_ID = ?1 AND v.USER_ID = ?2", nativeQuery = true)
	List<Vote> findVotesByEventIdAndUserId(Long eventId, Long userId);
	
	
	@Query(value = "SELECT * FROM Vote v WHERE v.EVENT_ID = ?1", nativeQuery = true)
	List<Vote> findVotesByEventId(Long eventId);

	@Modifying
	@Transactional
	@Query("delete from Vote v where v.eventId=:eventId AND v.userId=:userId")
	void deleteVoteByEventIdUsername(@Param("eventId") Long eventId, @Param("userId") Long userId);
	
	@Modifying
	@Transactional
	@Query("delete from Vote v where v.eventId=:eventId")
	void deleteAllVoteByEventId(@Param("eventId") Long eventId);
	
}