package ftn.xmlws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.dto.UserDTO;
import ftn.xmlws.model.User;
import ftn.xmlws.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers() {

		List<UserDTO> users = userService.getAllUsers();

		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
		
		UserDTO user = userService.getUserByUsername(username);
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserNoDto(@PathVariable("username") String username) {
		
		User user = userService.getUserByUsernameNoDto(username);
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> registerUser(@RequestBody UserDTO userToReg) {

		boolean success = userService.registerUser(userToReg);
		
		if (!success) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.CREATED);	
		}
	}


	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userUpdated) {

		UserDTO user = userService.updateUser(userUpdated);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {

		userService.removeUser(username);

		return new ResponseEntity<>(HttpStatus.OK);
	}


	@RequestMapping(value = "/activate/{username}", method = RequestMethod.GET)
	public ResponseEntity<Void> activateUser(@PathVariable("username") String username) {

		boolean success = userService.activateUser(username);
		
		if(success)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/block/{username}", method = RequestMethod.GET)
	public ResponseEntity<Void> blockUser(@PathVariable("username") String username) {

		boolean success = userService.blockUser(username);

		if(success)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/unblock/{username}", method = RequestMethod.GET)
	public ResponseEntity<Void> unblockUser(@PathVariable("username") String username) {

		boolean success = userService.unblockUser(username);

		if(success)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/changeRole/{username}/{role}", method = RequestMethod.GET)
	public ResponseEntity<Void> changeRole(@PathVariable("username") String username, @PathVariable("role") String role) {
		
		boolean success = false;

		if(role.equals("ADMIN"))
			success = userService.changeToAdmin(username);
		else if(role.equals("AGENT"))
			success = userService.changeToAgent(username);
		
		if(success)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/*@RequestMapping(value = "/changePassword/{username}", method = RequestMethod.GET)
	public ResponseEntity<Void> changePassword(@PathVariable("username") String username,
			RequestBody PasswordChangeDTO) {

		boolean success = userService.unblockUser(username);

		if(success)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}*/
	
	
	
	



}
