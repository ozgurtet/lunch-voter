package com.thinxnet.lunchvoter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thinxnet.lunchvoter.entity.User;
import java.lang.String;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByUserName(String username);
	
}