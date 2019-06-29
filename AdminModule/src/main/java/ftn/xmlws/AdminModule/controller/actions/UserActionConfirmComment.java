package ftn.xmlws.AdminModule.controller.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ftn.xmlws.AdminModule.Comment;
import ftn.xmlws.AdminModule.client.UserClient;
import ftn.xmlws.AdminModule.reservations.ConfirmCommentResponse;

@Controller
public class UserActionConfirmComment {

	private Comment comment;
	private boolean success = false;

	@Autowired
	private UserClient userClient;

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public void action() {
		ConfirmCommentResponse response = userClient.confirmComment(comment.getIdReservation());
		this.success = response.isSuccess();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}