package ftn.xmlws.AdminModule.controller.swingApp;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.view.mainFrame.table.TableRow;

public class UpdateTable {

	public void action() {

		ArrayList<User> users = new ArrayList<User>();

		for (User user : Singleton.getInstance().getUsers()) {
			users.add(user);
		}

		try {
			Singleton.getInstance().getTabbedPane().removeAll();

			SwingUtilities.updateComponentTreeUI(Singleton.getInstance().getTabbedPane());
			SwingUtilities.updateComponentTreeUI(Singleton.getInstance().getMainPanel());
		} catch (Exception e) {

		}

	}

	public ArrayList<TableRow> parseUsers(ArrayList<User> users) {
		if (users.isEmpty()) {
			return new ArrayList<TableRow>();
		}

		ArrayList<TableRow> tempList = new ArrayList<TableRow>();

		for (User user : users) {
			TableRow newRow = new TableRow();
			newRow.getListOfObjects().add(user.getId());
			newRow.getListOfObjects().add(user.getName());
			newRow.getListOfObjects().add(user.getLastname());
			newRow.getListOfObjects().add(user.getUsername());
			newRow.getListOfObjects().add(user.getEmail());
			newRow.getListOfObjects().add(user.isEnabled());
			newRow.getListOfObjects().add(user.isBlocked());
			newRow.getListOfObjects().add(user.isDeleted());
			tempList.add(newRow);
		}

		return tempList;
	}
}
