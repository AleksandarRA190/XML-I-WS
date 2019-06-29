package ftn.xmlws.AdminModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ftn.xmlws.AdminModule.controller.actions.UserActionGet;
import ftn.xmlws.AdminModule.controller.swingApp.InitIcons;
import ftn.xmlws.AdminModule.controller.swingApp.LoginFrameController;
import ftn.xmlws.AdminModule.controller.swingApp.LookAndFeelSetter;

@SpringBootApplication
@EnableAutoConfiguration
public class AdminModuleApplication {

	public static void main(String[] args) {

		InitIcons initIcons = new InitIcons();
		initIcons.initIcons();
		Singleton.getInstance().setInitIcons(initIcons);
		LookAndFeelSetter.setLookAndFeel();

		ApplicationContext context = SpringApplication.run(AdminModuleApplication.class, args);
		Singleton.getInstance().setContext(context);

		UserActionGet getAction = (UserActionGet) context.getBean(UserActionGet.class);
		getAction.action();

		LoginFrameController loginFrameController = (LoginFrameController) context.getBean(LoginFrameController.class);
		loginFrameController.openFrame();

	}

}
