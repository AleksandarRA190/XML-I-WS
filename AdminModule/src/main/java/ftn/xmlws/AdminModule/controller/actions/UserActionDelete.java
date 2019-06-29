package ftn.xmlws.AdminModule.controller.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ftn.xmlws.AdminModule.DeleteResponse;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.client.UserClient;

@Controller
public class UserActionDelete {

	private User user;
	private boolean success = false;
	@Autowired
	private UserClient userClient;

	public void setUser(User user) {
		this.user = user;
	}

	public void action() {
		DeleteResponse response = userClient.deleteUser(user.getUsername());
		this.success = response.isSuccess();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
