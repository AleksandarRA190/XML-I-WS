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

import com.projectxml.mojuser.ActivateRequest;
import com.projectxml.mojuser.ActivateResponse;
import com.projectxml.mojuser.AddUserRequest;
import com.projectxml.mojuser.AddUserResponse;
import com.projectxml.mojuser.BlockRequest;
import com.projectxml.mojuser.BlockResponse;
import com.projectxml.mojuser.Comment;
import com.projectxml.mojuser.DeleteRequest;
import com.projectxml.mojuser.DeleteResponse;
import com.projectxml.mojuser.GetUserCommentsRequest;
import com.projectxml.mojuser.GetUserCommentsResponse;
import com.projectxml.mojuser.GetUsersRequest;
import com.projectxml.mojuser.GetUsersResponse;
import com.projectxml.mojuser.LoginRequest;
import com.projectxml.mojuser.LoginResponse;
import com.projectxml.mojuser.UnblockRequest;
import com.projectxml.mojuser.UnblockResponse;
import com.projectxml.mojuser.User;

import ftn.xmlws.dto.CommentDTO;
import ftn.xmlws.dto.UserCommentsDTO;
import ftn.xmlws.dto.UserDTO;
import ftn.xmlws.dto.Users;

@Endpoint
public class MojUserEndpoint {

	@Autowired
	private RestTemplate restTemplate;

	@PayloadRoot(namespace = "http://www.projectXml.com/mojuser", localPart = "getUsersRequest")
	@ResponsePayload
	public GetUsersResponse processGetUsersRequest(@RequestPayload GetUsersRequest request) {
		GetUsersResponse response = new GetUsersResponse();

		Users users = restTemplate.getForObject("http://localhost:9006/users", Users.class);
		List<UserDTO> userDTOs = users.getUsers();
		System.out.println(userDTOs.size());
		for(UserDTO u : userDTOs) {
			User kor = new User(u);
			System.out.println(kor.toString());
			response.getUser().add(kor);
		}
			
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.projectXml.com/mojuser", localPart = "getUserCommentsRequest")
	@ResponsePayload
	public GetUserCommentsResponse processGetUserCommentsRequest(@RequestPayload GetUserCommentsRequest request) {
		GetUserCommentsResponse response = new GetUserCommentsResponse();

		UserCommentsDTO comments = restTemplate.getForObject("http://localhost:9006/users/getUserComments/"+request.getUsername(), UserCommentsDTO.class);
		List<CommentDTO> commentDTOs = comments.getComments();
		for(CommentDTO c : commentDTOs) {
			Comment comment = new Comment();
			comment.setApprovedComment(c.isApprovedComment());
			comment.setContentOfComment(c.getContentOfComment());
			comment.setIdReservation(c.getReservationId());
			response.getComments().add(comment);
		}
			
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.projectXml.com/mojuser", localPart = "addUserRequest")
	@ResponsePayload
	public AddUserResponse processAddUserRequest(@RequestPayload AddUserRequest request) {
		AddUserResponse response = new AddUserResponse();

		User userToRegister = request.getUser();

		System.out.println(request.getUser().getName());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<User> requestHeader = new HttpEntity<>(userToRegister, headers);
		restTemplate.put("http://localhost:9006/users/register", requestHeader);
		response.setSuccess(true);

		return response;
	}
	
	@PayloadRoot(namespace = "http://www.projectXml.com/mojuser", localPart = "deleteRequest")
	@ResponsePayload
	public DeleteResponse processDeleteRequest(@RequestPayload DeleteRequest request) {
		DeleteResponse response = new DeleteResponse();
		
		String url = "http://localhost:9006/users/"+request.getUsername();
		restTemplate.delete(url);
		response.setSuccess(true);
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.projectXml.com/mojuser", localPart = "activateRequest")
	@ResponsePayload
	public ActivateResponse processActivateRequest(@RequestPayload ActivateRequest request) {
		ActivateResponse response = new ActivateResponse();
		
		restTemplate.getForObject("http://localhost:9006/users/activate/"+request.getId(),Void.class);
		response.setSuccess(true);
		
		return response;
	}

	@PayloadRoot(namespace = "http://www.projectXml.com/mojuser", localPart = "blockRequest")
	@ResponsePayload
	public BlockResponse processBlockRequest(@RequestPayload BlockRequest request) {
		BlockResponse response = new BlockResponse();
		
		restTemplate.getForObject("http://localhost:9006/users/block/"+request.getUsername(),Void.class);
		response.setSuccess(true);
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.projectXml.com/mojuser", localPart = "unblockRequest")
	@ResponsePayload
	public UnblockResponse processUnblockRequest(@RequestPayload UnblockRequest request) {
		UnblockResponse response = new UnblockResponse();
		
		restTemplate.getForObject("http://localhost:9006/users/unblock/"+request.getUsername(),Void.class);
		response.setSuccess(true);
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.projectXml.com/mojuser", localPart = "loginRequest")
	@ResponsePayload
	public LoginResponse processLoginRequest(@RequestPayload LoginRequest request) {
		LoginResponse response = new LoginResponse();
		
		UserDTO user = restTemplate.postForObject("http://localhost:9006/users/login",request.getLoginDTO(),UserDTO.class);
		response.setUser(user);
		
		return response;
	}
	
}
