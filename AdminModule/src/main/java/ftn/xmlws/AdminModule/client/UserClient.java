package ftn.xmlws.AdminModule.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import ftn.xmlws.AdminModule.ActivateRequest;
import ftn.xmlws.AdminModule.ActivateResponse;
import ftn.xmlws.AdminModule.AddUserRequest;
import ftn.xmlws.AdminModule.AddUserResponse;
import ftn.xmlws.AdminModule.BlockRequest;
import ftn.xmlws.AdminModule.BlockResponse;
import ftn.xmlws.AdminModule.DeleteRequest;
import ftn.xmlws.AdminModule.DeleteResponse;
import ftn.xmlws.AdminModule.GetUserCommentsRequest;
import ftn.xmlws.AdminModule.GetUserCommentsResponse;
import ftn.xmlws.AdminModule.GetUsersRequest;
import ftn.xmlws.AdminModule.GetUsersResponse;
import ftn.xmlws.AdminModule.LoginDTO;
import ftn.xmlws.AdminModule.LoginRequest;
import ftn.xmlws.AdminModule.LoginResponse;
import ftn.xmlws.AdminModule.UnblockRequest;
import ftn.xmlws.AdminModule.UnblockResponse;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.reservations.ConfirmCommentRequest;
import ftn.xmlws.AdminModule.reservations.ConfirmCommentResponse;
import ftn.xmlws.AdminModule.reservations.DeleteCommentRequest;
import ftn.xmlws.AdminModule.reservations.DeleteCommentResponse;

public class UserClient extends WebServiceGatewaySupport {

	private final String uri = "http://localhost:9007/ws/mojusers";
	private final String uri2 = "http://localhost:9007/ws/reservations";

	public ActivateResponse activateUser(long id) {
		ActivateRequest request = new ActivateRequest();
		request.setId(id);
		return (ActivateResponse) getWebServiceTemplate().marshalSendAndReceive(uri, request);

	}

	public AddUserResponse addUser(User user) {
		AddUserRequest request = new AddUserRequest();
		request.setUser(user);
		return (AddUserResponse) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}

	public BlockResponse blockUser(String username) {
		BlockRequest request = new BlockRequest();
		request.setUsername(username);
		return (BlockResponse) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}

	public DeleteResponse deleteUser(String username) {
		DeleteRequest request = new DeleteRequest();
		request.setUsername(username);
		return (DeleteResponse) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}

	public GetUsersResponse getUsers() {
		GetUsersRequest request = new GetUsersRequest();
		request.setUsername("");
		return (GetUsersResponse) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}

	public LoginResponse loginUser(LoginDTO loginDTO) {

		LoginRequest request = new LoginRequest();
		request.setLoginDTO(loginDTO);
		return (LoginResponse) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}

	public UnblockResponse unblockUser(String username) {
		UnblockRequest request = new UnblockRequest();
		request.setUsername(username);
		return (UnblockResponse) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}

	public GetUserCommentsResponse getCommentsByUsersUsername(String username) {
		GetUserCommentsRequest request = new GetUserCommentsRequest();
		request.setUsername(username);
		return (GetUserCommentsResponse) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}

	public ConfirmCommentResponse confirmComment(Long id) {
		ConfirmCommentRequest request = new ConfirmCommentRequest();
		request.setIdReservation(id);
		return (ConfirmCommentResponse) getWebServiceTemplate().marshalSendAndReceive(uri2, request);
	}

	public DeleteCommentResponse deleteComment(Long id) {
		DeleteCommentRequest request = new DeleteCommentRequest();
		request.setIdReservation(id);
		return (DeleteCommentResponse) getWebServiceTemplate().marshalSendAndReceive(uri2, request);
	}

}
