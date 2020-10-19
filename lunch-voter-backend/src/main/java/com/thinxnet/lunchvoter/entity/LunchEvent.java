package com.thinxnet.lunchvoter.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "event")
public class LunchEvent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long   id;
	
	private String eventtitle;
	
	private String eventdetail;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Timestamp eventstartdate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Timestamp eventexpiredate;
	
	protected LunchEvent() {}

	public LunchEvent(Long id, String eventTitle, String eventDetail, Timestamp eventStartDate,
			Timestamp eventExpireDate) {
		super();
		this.id = id;
		this.eventtitle = eventTitle;
		this.eventdetail = eventDetail;
		this.eventstartdate = eventStartDate;
		this.eventexpiredate = eventExpireDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventTitle() {
		return eventtitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventtitle = eventTitle;
	}

	public String getEventDetail() {
		return eventdetail;
	}

	public void setEventDetail(String eventDetail) {
		this.eventdetail = eventDetail;
	}

	public Timestamp getEventStartDate() {
		return eventstartdate;
	}

	public void setEventStartDate(Timestamp eventStartDate) {
		this.eventstartdate = eventStartDate;
	}

	public Timestamp getEventExpireDate() {
		return eventexpiredate;
	}

	public void setEventExpireDate(Timestamp eventExpireDate) {
		this.eventexpiredate = eventExpireDate;
	}

	@Override
	public String toString() {
		return "LunchEvent [id=" + id + ", eventTitle=" + eventtitle + ", eventDetail=" + eventdetail
				+ ", eventStartDate=" + eventstartdate + ", eventExpireDate=" + eventexpiredate + "]";
	}
}
