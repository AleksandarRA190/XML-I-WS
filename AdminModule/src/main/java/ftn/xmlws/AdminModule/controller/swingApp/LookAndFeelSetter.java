package ftn.xmlws.AdminModule.controller.swingApp;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class LookAndFeelSetter {

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);

		} catch (Exception e) {
			System.out.println("nije ucitan LookAndFeel!");
		}
	}
}
