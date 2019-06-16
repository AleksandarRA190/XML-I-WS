package ftn.xmlws.dto;

public class CommentDTO {
	
	private String contentOfComment;
	private boolean approvedComment;
	
	public CommentDTO() {
		
	}

	public String getContentOfComment() {
		return contentOfComment;
	}

	public void setContentOfComment(String contentOfComment) {
		this.contentOfComment = contentOfComment;
	}

	public boolean isApprovedComment() {
		return approvedComment;
	}

	public void setApprovedComment(boolean approvedComment) {
		this.approvedComment = approvedComment;
	}
	
	

}
