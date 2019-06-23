package ftn.xmlws.dto;

import java.util.ArrayList;
import java.util.List;

public class UserCommentsDTO {
	
private List<CommentDTO> comments = new ArrayList<>();
	
	public UserCommentsDTO() {}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

}
