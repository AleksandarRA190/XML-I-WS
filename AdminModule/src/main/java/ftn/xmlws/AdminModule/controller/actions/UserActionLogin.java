package ftn.xmlws.AdminModule.controller.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ftn.xmlws.AdminModule.LoginDTO;
import ftn.xmlws.AdminModule.LoginResponse;
import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.client.UserClient;
import ftn.xmlws.AdminModule.controller.swingApp.MainFrameController;

@Controller
public class UserActionLogin {

	@Autowired
	private UserClient userClient;

	public void action(String username, String password) {
		User tempUser = new User();
		LoginDTO login = new LoginDTO();
		login.setUsername(username);
		login.setPassword(password);
		login.setRole("ADMINISTRATOR");

		try {
			LoginResponse response = userClient.loginUser(login);
			tempUser = response.getUser();

			System.out.println("LOGGED USER!");
			System.out.println(tempUser.getUsername());
			System.out.println(tempUser.getName());
			System.out.println(tempUser.getLastname());

			if (tempUser.isDeleted() || tempUser.isBlocked()) {
				System.out.println("BLOKIRAN ILI OBRISAN!");
				System.exit(0);
			} else {
				Singleton.getInstance().setLoggedAdmin(tempUser);
				MainFrameController mainFrameController = (MainFrameController) Singleton.getInstance().getContext()
						.getBean(MainFrameController.class);
				mainFrameController.setAdmin(tempUser);
				mainFrameController.initFrame();
				mainFrameController.openFrame();

			}

		} catch (Exception e) {
			System.out.println("PUKO USER");
		}
	}

}
