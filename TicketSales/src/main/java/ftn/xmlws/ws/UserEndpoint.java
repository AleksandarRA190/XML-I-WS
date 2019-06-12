package ftn.xmlws.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.projectxml.user.AddUserRequest;
import com.projectxml.user.AddUserResponse;
import com.projectxml.user.GetUsersRequest;
import com.projectxml.user.GetUsersResponse;

import ftn.xmlws.dto.UserDTO;
import ftn.xmlws.dto.Users;

@Endpoint
public class UserEndpoint {

	@Autowired
	private RestTemplate restTemplate;

	@PayloadRoot(namespace = "http://www.projectXml.com/user", localPart = "getUsersRequest")
	@ResponsePayload
	public GetUsersResponse processGetUsersRequest(@RequestPayload GetUsersRequest request) {
		GetUsersResponse response = new GetUsersResponse();

		Users users = restTemplate.getForObject("http://localhost:9006/users", Users.class);
		List<UserDTO> userDTOs = users.getUsers();
		System.out.println(userDTOs.size());
		for (UserDTO userDTO : userDTOs) {
			response.getUser().add(new com.projectxml.user.User(userDTO));
		}
		/*com.projectxml.user.User u = new com.projectxml.user.User();
		response.getUser().add(u);*/

		return response;
	}

	@PayloadRoot(namespace = "http://www.projectXml.com/user", localPart = "addUserRequest")
	@ResponsePayload
	public AddUserResponse processAddUserRequest(@RequestPayload AddUserRequest request) {
		AddUserResponse response = new AddUserResponse();

		/*com.projectxml.user.User userXml = request.getUser();

		UserDTO user = new UserDTO(userXml);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> requestHeader = new HttpEntity<>(user, headers);

		restTemplate.put("http://localhost:9006/users/register", requestHeader);*/
		response.setSuccess(true);

		return response;
	}

}
