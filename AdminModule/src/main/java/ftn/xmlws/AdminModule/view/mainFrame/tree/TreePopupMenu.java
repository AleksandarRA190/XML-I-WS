package ftn.xmlws.AdminModule.view.mainFrame.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.controller.actions.UserActionBlock;
import ftn.xmlws.AdminModule.controller.actions.UserActionDelete;
import ftn.xmlws.AdminModule.controller.actions.UserActionShowProfile;
import ftn.xmlws.AdminModule.controller.actions.UserActionUnblock;

public class TreePopupMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;
	private JMenuItem showProfile;
	private JMenuItem blockUser;
	private JMenuItem unblockUser;
	private JMenuItem deleteUser;
	private User user;

	public TreePopupMenu(User user) {

		this.user = user;

		initItems();
		actions();

		this.add(showProfile);
		this.addSeparator();

		if (user.isBlocked()) {
			this.add(unblockUser);
		} else {
			this.add(blockUser);
		}

		this.add(deleteUser);

	}

	public void initItems() {
		this.showProfile = new JMenuItem("Show user profile");
		this.blockUser = new JMenuItem("Block user");
		this.deleteUser = new JMenuItem("Delete user");
		this.unblockUser = new JMenuItem("Unblock user");

	}

	public void actions() {
		this.showProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showProfile();
			}
		});

		this.blockUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				blockUser();
			}
		});

		this.deleteUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 deleteUser();

			}
		});

		this.unblockUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 unblockUser();
			}
		});
	}

	public void showProfile() {
		UserActionShowProfile userShowProfile = new UserActionShowProfile(this.user);
		userShowProfile.action();
	}

	public void blockUser() {
		UserActionBlock blockUser = (UserActionBlock) Singleton.getInstance().getContext()
				.getBean(UserActionBlock.class);
		blockUser.setUser(user);
		blockUser.action();
	}

	public void deleteUser() {
		UserActionDelete deleteUser = (UserActionDelete) Singleton.getInstance().getContext()
				.getBean(UserActionDelete.class);
		deleteUser.setUser(user);
		deleteUser.action();
	}

	public void unblockUser() {
		UserActionUnblock unblockUser = (UserActionUnblock) Singleton.getInstance().getContext()
				.getBean(UserActionUnblock.class);
		unblockUser.action();
	}
}
