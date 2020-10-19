package com.thinxnet.lunchvoter.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinxnet.lunchvoter.entity.LunchEvent;

public interface LunchEventRepository extends JpaRepository<LunchEvent, Long> {
	
	
}