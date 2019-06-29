package ftn.xmlws.AdminModule.model.local;

import java.util.ArrayList;

import ftn.xmlws.AdminModule.User;

public class Users {

	private ArrayList<User> users;
	private String nameOfList;

	public Users(String name) {
		this.users = new ArrayList<User>();
		this.nameOfList = name;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public String getNameOfList() {
		return nameOfList;
	}

	public void setNameOfList(String nameOfList) {
		this.nameOfList = nameOfList;
	}

}
