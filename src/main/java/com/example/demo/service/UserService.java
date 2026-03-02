package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.DAO.User;
import com.example.demo.DTO.UserDTO;
import com.example.demo.repository.UserRepository;
@Service
public class UserService {
	
	private final UserRepository repo;
	
	public UserService(UserRepository repo) {
		this.repo = repo;
	}
	
	public UserDTO searchById(Long id) {
		return toDto(repo.findById(id).orElse(null));
	}

	public UserDTO searchByName(String name) {
		return toDto(repo.findByUserName(name));
	}
	
	private UserDTO toDto(User u) {
		UserDTO dto = new UserDTO();
		dto.setId(u.getId());
		dto.setName(u.getName());
		return dto;
	}
	
	public User toEntity(UserDTO u) {
		User entity = new User();
		entity.setId(u.getId());
		entity.setName(u.getName());
		return entity;
	}
}
