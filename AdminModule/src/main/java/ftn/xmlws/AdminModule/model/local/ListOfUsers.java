package ftn.xmlws.AdminModule.model.local;

public class ListOfUsers {

	private Users blockedUsers;
	private Users enabledUsers;
	private Users requirements;
	private String nameOfList;

	public ListOfUsers(String type) {
		this.blockedUsers = new Users("Blocked " + type);
		this.enabledUsers = new Users("Enabled " + type);
		this.requirements = new Users("Requirements " + type);
		this.nameOfList = type;
	}

	public Users getBlockedUsers() {
		return blockedUsers;
	}

	public void setBlockedUsers(Users blockedUsers) {
		this.blockedUsers = blockedUsers;
	}

	public Users getEnabledUsers() {
		return enabledUsers;
	}

	public void setEnabledUsers(Users enabledUsers) {
		this.enabledUsers = enabledUsers;
	}

	public String getNameOfList() {
		return nameOfList;
	}

	public void setNameOfList(String nameOfList) {
		this.nameOfList = nameOfList;
	}

	public Users getRequirements() {
		return requirements;
	}

	public void setRequirements(Users requirements) {
		this.requirements = requirements;
	}
}
