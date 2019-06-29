package ftn.xmlws.AdminModule.controller.actions;

import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.view.UserFrame;

public class UserActionShowProfile {

	private User user;
	private UserFrame userFrame;

	public UserActionShowProfile(User user) {
		this.user = user;
	}

	public void action() {
		this.userFrame = new UserFrame();
		this.userFrame.setUser(user);
		this.userFrame.initFrame();
		this.userFrame.setVisible(true);

	}
}
