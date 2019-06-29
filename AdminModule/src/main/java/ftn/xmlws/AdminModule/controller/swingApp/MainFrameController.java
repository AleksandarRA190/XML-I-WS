package ftn.xmlws.AdminModule.controller.swingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.view.mainFrame.MainFrame;

@Controller
public class MainFrameController {

	private MainFrame mainFrame;

	@Autowired
	public MainFrameController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		Singleton.getInstance().setMainFrame(mainFrame);
	}

	public void openFrame() {
		this.mainFrame.setVisible(true);
	}

	public void initFrame() {
		this.mainFrame.primaryInit();
	}

	public void setAdmin(User user) {
		this.mainFrame.setAdmin(user);
	}
}
