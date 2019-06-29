package ftn.xmlws.AdminModule.controller.swingApp;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.view.mainFrame.table.Tab;
import ftn.xmlws.AdminModule.view.mainFrame.table.TabbedPane;
import ftn.xmlws.AdminModule.view.mainFrame.table.TableRow;
import ftn.xmlws.AdminModule.view.mainFrame.table.TableView;

public class DisplayTable {

	private ArrayList<User> users;
	private TabbedPane tabbedPane;
	private Tab tab;
	private TableView tableView;
	private String title;

	private ArrayList<TableRow> rows;

	public DisplayTable(ArrayList<User> users, String title) {
		this.tabbedPane = Singleton.getInstance().getTabbedPane();
		this.users = users;
		this.title = title;
		this.rows = new ArrayList<TableRow>();
	}

	public void action() {

		this.rows = parseUsers(users);
		tableView = new TableView(this.rows);
		tab = new Tab(title);
		tabbedPane.addTab(null, tableView);
		tabbedPane.setTabComponentAt(Singleton.getInstance().getTabbedPane().getTabCount() - 1, tab);
		tabbedPane.setSelectedIndex(Singleton.getInstance().getTabbedPane().getTabCount() - 1);

		Singleton.getInstance().setTabbedPane(tabbedPane);
		SwingUtilities.updateComponentTreeUI(Singleton.getInstance().getTabbedPane());
		SwingUtilities.updateComponentTreeUI(Singleton.getInstance().getMainPanel());
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
