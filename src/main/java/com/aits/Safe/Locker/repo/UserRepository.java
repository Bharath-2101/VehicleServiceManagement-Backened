package com.aits.Safe.Locker.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aits.Safe.Locker.entity.User;
import com.aits.Safe.Locker.entity.User.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	List<User> findByRole(Role role);
	List<User> findByEmailOrMobile(String email , String mobile);

}
