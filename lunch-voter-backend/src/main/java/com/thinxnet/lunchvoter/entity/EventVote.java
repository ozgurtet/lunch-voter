package com.thinxnet.lunchvoter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventVote")
public class EventVote {
	
	@Id
	private Long voteId;
		
	private Long eventId;

	private Long restaurantId;
	
	private Long userId;
	
	private String userName;
	
	public EventVote() {}

	public EventVote(Long voteId, Long eventId, Long restaurantId, Long userId, String userName) {
		super();
		this.voteId = voteId;
		this.eventId = eventId;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.userName = userName;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "EventVote [voteId=" + voteId + ", eventId=" + eventId + ", restaurantId=" + restaurantId + ", userId="
				+ userId + ", userName=" + userName + "]";
	}
}
