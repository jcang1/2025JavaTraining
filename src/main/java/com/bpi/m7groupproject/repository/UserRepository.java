/**
 * 
 */
package com.bpi.m7groupproject.repository;

import java.util.List;

import com.bpi.m7groupproject.model.*;

import jakarta.persistence.EntityManager;


/**
 * 
 */
public class UserRepository implements Repository<User, Long> {

	private final EntityManager em;

	public UserRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public User save(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
