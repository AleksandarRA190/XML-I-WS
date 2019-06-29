package ftn.xmlws.AdminModule.model.local;

import java.util.ArrayList;
import java.util.List;

import ftn.xmlws.AdminModule.User;


public class Root {

	private ListOfUsers listOfUsers;
	private ListOfUsers listOfAgents;
	private List<User> allUsers;
	private String nameOfRoot;

	public Root() {

		this.listOfUsers = new ListOfUsers("Users");
		this.listOfAgents = new ListOfUsers("Agents");
		this.allUsers = new ArrayList<User>();
		this.nameOfRoot = "ROOT";
	}

	public ListOfUsers getListOfUsers() {
		return listOfUsers;
	}

	public void setListOfUsers(ListOfUsers listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	public String getNameOfRoot() {
		return nameOfRoot;
	}

	public void setNameOfRoot(String nameOfRoot) {
		this.nameOfRoot = nameOfRoot;
	}

	public ListOfUsers getListOfAgents() {
		return listOfAgents;
	}

	public void setListOfAgents(ListOfUsers listOfAgents) {
		this.listOfAgents = listOfAgents;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

}
