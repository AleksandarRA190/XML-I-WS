package ftn.xmlws.AdminModule.controller.swingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ftn.xmlws.AdminModule.view.LoginFrame;

@Controller
public class LoginFrameController {

	private LoginFrame loginFrame;

	@Autowired
	public LoginFrameController(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public void openFrame() {
		this.loginFrame.setVisible(true);
	}
}
