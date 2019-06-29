package ftn.xmlws.AdminModule.controller.actions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ftn.xmlws.AdminModule.GetUsersResponse;
import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.client.UserClient;
import ftn.xmlws.AdminModule.model.local.Root;

@Controller
public class UserActionGet {

	@Autowired
	private UserClient userClient;

	public void action() {
		GetUsersResponse response = userClient.getUsers();

		List<User> users = new ArrayList<User>();
		Root root = new Root();

		users = response.getUser();

		if (!users.isEmpty()) {
			Singleton.getInstance().setUsers(users);

			root.setAllUsers(users);

		}

		Singleton.getInstance().setRoot(root);

	}
}
