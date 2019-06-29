package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

public class AccommodationCommentsDTO {
	
	private List<CommentDTO> comments = new ArrayList<>();
	private List<UserDTO> userDTOs = new ArrayList<>();
	
	public AccommodationCommentsDTO() {}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	public List<UserDTO> getUserDTOs() {
		return userDTOs;
	}

	public void setUserDTOs(List<UserDTO> userDTOs) {
		this.userDTOs = userDTOs;
	}

}
