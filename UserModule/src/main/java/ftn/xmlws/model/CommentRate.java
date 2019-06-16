package ftn.xmlws.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.datatype.XMLGregorianCalendar;

@Entity
public class CommentRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private boolean approvedComment;
	private String contentOfComment;
	
	@Transient
    private XMLGregorianCalendar commentDate;
    
	@Column(name = "From_date")
	private LocalDateTime commentDateTime;
	
	private int ocena;
	
	public CommentRate() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isApprovedComment() {
		return approvedComment;
	}

	public void setApprovedComment(boolean approvedComment) {
		this.approvedComment = approvedComment;
	}

	public String getContentOfComment() {
		return contentOfComment;
	}

	public void setContentOfComment(String contentOfComment) {
		this.contentOfComment = contentOfComment;
	}

	public XMLGregorianCalendar getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(XMLGregorianCalendar commentDate) {
		this.commentDate = commentDate;
	}

	public LocalDateTime getCommentDateTime() {
		return commentDateTime;
	}

	public void setCommentDateTime(LocalDateTime commentDateTime) {
		this.commentDateTime = commentDateTime;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	
	
	
	
	
	
}
