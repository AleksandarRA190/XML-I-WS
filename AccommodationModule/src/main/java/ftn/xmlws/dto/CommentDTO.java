package ftn.xmlws.dto;

public class CommentDTO {
	
	private String contentOfComment;
	private boolean approvedComment;
	private Long reservationId;
	private int rate;
	
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

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}


	

}
