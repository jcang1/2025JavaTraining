/*
 * Contains all the queries to access database specific for table users
 */

package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	
	/*
	 * out of the box CRUD methods
	 * save(User user)
	 * findById(Long id)
	 * findAll()
	 * deleteById(Long id)
	 * count()
	 * existsById(Long id)
	*/
	
}
