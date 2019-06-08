package ftn.xmlws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ftn.xmlws.dto.LoginDTO;
import ftn.xmlws.dto.UserDTO;


@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers() {

		List<UserDTO> users = userService.getAllUsers();

		return new ResponseEntity<>(users, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
		
		UserDTO user = restTemplate.getForObject("http://localhost:9006/users/"+username, UserDTO.class);
	
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserDTO> loginUser(@RequestBody LoginDTO userToLog) {
		
		UserDTO user = restTemplate.getForObject("http://localhost:9006/users/"+userToLog.getUsername(), UserDTO.class);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else if(!user.getPassword().equals(userToLog.getPassword())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> registerUser(@RequestBody UserDTO userToReg) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> request = new HttpEntity<>(userToReg,headers);
		
		restTemplate.put("http://localhost:9006/users/register", request);
		
		return new ResponseEntity<>(HttpStatus.OK);	
	}


	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userUpdated) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> request = new HttpEntity<>(userUpdated,headers);
		
		UserDTO user = restTemplate.postForObject("http://localhost:9006/users/update",request,UserDTO.class);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {

		String url = "http://localhost:9006/users/"+username;
		restTemplate.delete(url);

		return new ResponseEntity<>(HttpStatus.OK);
	}


	@RequestMapping(value = "/activate/{username}", method = RequestMethod.GET)
	public ResponseEntity<Void> activateUser(@PathVariable("username") String username) {

		restTemplate.getForObject("http://localhost:9006/users/activate/"+username,Void.class);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/block/{username}", method = RequestMethod.GET)
	public ResponseEntity<Void> blockUser(@PathVariable("username") String username) {

		restTemplate.getForObject("http://localhost:9006/users/block/"+username,Void.class);
		
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/unblock/{username}", method = RequestMethod.GET)
	public ResponseEntity<Void> unblockUser(@PathVariable("username") String username) {

		restTemplate.getForObject("http://localhost:9006/users/unblock/"+username,Void.class);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeRole/{username}/{role}", method = RequestMethod.GET)
	public ResponseEntity<Void> changeRole(@PathVariable("username") String username, @PathVariable("role") String role) {
		
		restTemplate.getForObject("http://localhost:9006/users/changeRole/"+username+"/"+role,Void.class);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
