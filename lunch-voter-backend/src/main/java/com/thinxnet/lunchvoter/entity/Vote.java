package com.thinxnet.lunchvoter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long voteId;
	
	private Long userId;
	
	private Long restaurantId;
	
	private Long eventId;
	
	public Vote() {}

	public Vote(Long voteId, Long userId, Long restaurantId, Long eventId) {
		super();
		this.voteId = voteId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.eventId = eventId;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "Vote [voteId=" + voteId + ", userId=" + userId + ", restaurantId=" + restaurantId + ", eventId="
				+ eventId + "]";
	}
}
