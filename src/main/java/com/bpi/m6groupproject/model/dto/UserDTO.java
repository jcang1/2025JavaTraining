package com.bpi.m6groupproject.model.dto;

import com.bpi.m6groupproject.model.User;

public class UserDTO {
	private Long id;
	private String userName;
	
	public UserDTO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return this.userName;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.userName = name;
	}
	

	public UserDTO(User userEntity) {
		this.setId(userEntity.getId());
		this.setName(userEntity.getName());
	}
	
	public User toEntity() {
		User userToEntity = new User();
		userToEntity.setId(this.id);
		userToEntity.setName(this.userName);
		
		return userToEntity;
	}
}
