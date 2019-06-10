package ftn.xmlws.dto;

public class ChangePasswordDTO {
	
	private String oldPassword;
	private String newPassword;
	private String username;
	
	public ChangePasswordDTO() {}

	public ChangePasswordDTO(String oldPassword, String newPassword, String username) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
