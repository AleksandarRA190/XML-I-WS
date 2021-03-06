package ftn.xmlws.rest;

import java.util.List;

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

import ftn.xmlws.dto.ChangePasswordDTO;
import ftn.xmlws.dto.LoginDTO;
import ftn.xmlws.dto.ReservationDTO;
import ftn.xmlws.dto.UserDTO;
import ftn.xmlws.dto.UserReservationsDTO;
import ftn.xmlws.dto.Users;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers() {

		Users users = restTemplate.getForObject("http://user-service/users", Users.class);

		return new ResponseEntity<>(users.getUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {

		UserDTO user = restTemplate.getForObject("http://user-service/users/" + username, UserDTO.class);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> loginUser(@RequestBody LoginDTO userToLog) {

		UserDTO user = restTemplate.postForObject("http://user-service/users/login", userToLog, UserDTO.class);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> registerUser(@RequestBody UserDTO userToReg) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> request = new HttpEntity<>(userToReg, headers);

		restTemplate.put("http://user-service/users/register", request);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userUpdated) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> request = new HttpEntity<>(userUpdated, headers);

		UserDTO user = restTemplate.postForObject("http://user-service/users/update", request, UserDTO.class);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {

		String url = "http://user-service/users/" + username;
		restTemplate.delete(url);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/activate/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> activateUser(@PathVariable("id") Long id) {

		restTemplate.getForObject("http://user-service/users/activate/" + id, Void.class);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/block/{username}", method = RequestMethod.GET)
	public ResponseEntity<Void> blockUser(@PathVariable("username") String username) {

		restTemplate.getForObject("http://user-service/users/block/" + username, Void.class);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/unblock/{username}", method = RequestMethod.GET)
	public ResponseEntity<Void> unblockUser(@PathVariable("username") String username) {

		restTemplate.getForObject("http://user-service/users/unblock/" + username, Void.class);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/changeRole/{username}/{role}", method = RequestMethod.GET)
	public ResponseEntity<Void> changeRole(@PathVariable("username") String username,
			@PathVariable("role") String role) {

		restTemplate.getForObject("http://user-service/users/changeRole/" + username + "/" + role, Void.class);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getByUser/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<ReservationDTO>> getUserReservations(@PathVariable("username") String username) {

		UserReservationsDTO response = restTemplate.getForObject("http://user-service/users/getByUser/" + username,
				UserReservationsDTO.class);

		return new ResponseEntity<>(response.getReservations(), HttpStatus.OK);

	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordDTO changePass) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ChangePasswordDTO> request = new HttpEntity<>(changePass, headers);
		Boolean response = restTemplate.postForObject("http://user-service/users/changePassword", request,
				Boolean.class);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
