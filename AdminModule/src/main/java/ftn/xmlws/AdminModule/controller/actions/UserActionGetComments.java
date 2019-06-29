package ftn.xmlws.AdminModule.controller.actions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ftn.xmlws.AdminModule.Comment;
import ftn.xmlws.AdminModule.GetUserCommentsResponse;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.client.UserClient;

@Controller
public class UserActionGetComments {

	private User user;
	private List<Comment> comments;

	@Autowired
	private UserClient userClient;

	public void setUser(User user) {
		this.user = user;
		this.comments = new ArrayList<Comment>();
	}

	public void action() {
		GetUserCommentsResponse response = userClient.getCommentsByUsersUsername(user.getUsername());
		this.comments = response.getComments();

		for (Comment comment : comments) {
			System.out.println(comment.getContentOfComment());
		}
	}

	public List<Comment> getComments() {
		return comments;
	}

}