/*
 * Contains services pertaining to the table users.
 */

package com.example.demo.services;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.DAO.User;
import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.ApiResponse;
import com.example.demo.mapper.UserBookMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
    private final UserBookMapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserBookMapper mapper,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
	
	public UserDTO searchById(Long id) {
		return mapper.toUserDTO(
				userRepository.findById(id).orElse(null)
				);
	}

	public UserDTO searchByName(String name) {
		return mapper.toUserDTO(
				userRepository.findByUsername(name).orElse(null)
				);
	}
	
	/*
	 * Method has been prepared for API ready but is not used in API.
	 * This was invoked in class DataInitializer for initial user creation. 
	 */
	public ApiResponse<UserDTO> createUser(String username, String password, String role) throws Exception {
		logger.info("called method createUser");
		User newUser = new User();
		UserDTO userDTO = null;
		String message = null;
		if (userRepository.findByUsername(username).orElse(null) != null) {
			message = "cannot create: existing username " + username;
			logger.warn(message);
			throw new Exception(message);
		} else {
			newUser.setUsername(username);
			newUser.setPassword(passwordEncoder.encode(password));
			if ("ADMIN".equalsIgnoreCase(role.trim()) || "USER".equalsIgnoreCase(role.trim())) {
				newUser.setRole(role);
				userDTO = mapper.toUserDTO(userRepository.save(newUser));
				userDTO.setPassword("REDACTED!");
				message = "Successful user creation";
			} else {
				message = "invalid role" + role;
				logger.warn(message);
				throw new Exception(message);
			}
		}
				
		return new ApiResponse<>(message, userDTO);
	}
	
	
}
