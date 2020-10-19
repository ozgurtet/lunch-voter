package com.thinxnet.lunchvoter.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinxnet.lunchvoter.entity.Restaurant;
import java.lang.String;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	List<Restaurant> findByName(String name);
}