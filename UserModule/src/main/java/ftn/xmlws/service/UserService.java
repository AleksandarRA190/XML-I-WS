package ftn.xmlws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.dto.UserDTO;
import ftn.xmlws.enums.Role;
import ftn.xmlws.model.Address;
import ftn.xmlws.model.User;
import ftn.xmlws.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public boolean isRegistered(String username) {
		
		User user = this.userRepository.findByUsername(username);
		
		if (user != null)
			if(!user.isDeleted())
				return true;
		
		return false;
	}
	
	public List<UserDTO> getAllUsers() {
		List<User> list = new ArrayList<User>();
		this.userRepository.findAll().forEach(list::add);
		
		List<UserDTO> listDTO = new ArrayList<>();
		for(User user : list) {
			if(!user.isDeleted())
				listDTO.add(new UserDTO(user));
		}
		
		return listDTO;
	}

	public void removeUser(String username) {
		
		if (this.isRegistered(username)) {
			User user = userRepository.findByUsername(username);
			user.setDeleted(true);
			this.userRepository.save(user);
		}
		
	}

	public UserDTO updateUser(UserDTO userDTO) {
		
		if (!this.isRegistered(userDTO.getUsername())) {
			return null;
		}
		
		User user = this.userRepository.getOne(userDTO.getId());
		
		user.setLastname(userDTO.getLastname());
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
		user.setName(userDTO.getName());
		user.setBussinesRegistrationNumber(userDTO.getBussinesRegistrationNumber());
		//adresa ce se menjati preko adres servisa, da se torke ne bi gomilale za jednog korisnika
		
		this.userRepository.save(user);
		
		return userDTO;
	}
	
	public UserDTO getUserByUsername(String username) {
		
		if (!this.isRegistered(username)) {
			return null;
		}
		
		User user = this.userRepository.findByUsername(username);
		
		return new UserDTO(user);
	}
	
	public boolean registerUser(UserDTO userToReg) {
		
		if (this.isRegistered(userToReg.getUsername())) {
			return false;
		}

		User user = new User(userToReg);
		
		Address address = new Address(userToReg.getAddress());
		user.setAddress(address);
		
		this.userRepository.save(user);
		
		return true;
	}
	
	public boolean activateUser(String username) {

		if (!this.isRegistered(username)) {
			return false;
		}
		
		User user = this.userRepository.findByUsername(username);
		user.setEnabled(true);
		this.userRepository.save(user);
		
		return true;
	}
	
	public boolean blockUser(String username) {
		
		if (!this.isRegistered(username)) {
			return false;
		}
		
		User user = this.userRepository.findByUsername(username);
		user.setBlocked(true);
		this.userRepository.save(user);
		
		return true;
	}
	
	public boolean unblockUser(String username) {
		
		if (!this.isRegistered(username)) {
			return false;
		}
		
		User user = this.userRepository.findByUsername(username);
		user.setBlocked(false);
		this.userRepository.save(user);
		
		return true;
	}
	
	public boolean changeToAdmin(String username) {
		
		if (!this.isRegistered(username)) {
			return false;
		}
		
		User user = this.userRepository.findByUsername(username);
		user.setRole(Role.ADMINISTRATOR);
		this.userRepository.save(user);
		
		return true;
	}
	
	public boolean changeToAgent(String username) {
		
		if (!this.isRegistered(username)) {
			return false;
		}
		
		User user = this.userRepository.findByUsername(username);
		user.setRole(Role.AGENT);
		this.userRepository.save(user);
		
		return true;
	}
	
	public boolean changePassword() {
		// To be implemented
		return true;
	}
	
	public boolean addAccommodation() {
		// To be implemented, calling a microservice
		return true;
	}

	public User getUserByUsernameNoDto(String username) {
		
		if (!this.isRegistered(username)) {
			return null;
		}
		
		return this.userRepository.findByUsername(username);
	}
	
}
