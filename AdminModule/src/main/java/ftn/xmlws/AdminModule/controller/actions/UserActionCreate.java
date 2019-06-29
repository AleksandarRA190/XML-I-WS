package ftn.xmlws.AdminModule.controller.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ftn.xmlws.AdminModule.AddUserResponse;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.client.UserClient;

@Controller
public class UserActionCreate {

	@Autowired
	private UserClient userClient;
	private boolean success = false;
	private User user;

	public void action() {
		AddUserResponse response = userClient.addUser(user);
		this.success = response.isSuccess();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
