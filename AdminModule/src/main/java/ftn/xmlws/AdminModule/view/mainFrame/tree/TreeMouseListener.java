package ftn.xmlws.AdminModule.view.mainFrame.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.controller.actions.UserActionShowProfile;
import ftn.xmlws.AdminModule.controller.swingApp.DisplayTable;
import ftn.xmlws.AdminModule.model.local.Users;

public class TreeMouseListener extends MouseAdapter {

	private JTree tree;

	public TreeMouseListener(JTree tree) {
		this.tree = tree;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		TreePath path = tree.getPathForLocation(event.getX(), event.getY());
		if (path != null) {
			if (SwingUtilities.isLeftMouseButton(event)) {
				if (event.getClickCount() == 2) {
					doubleLeftMouseClick(path);
				}
			} else if (SwingUtilities.isRightMouseButton(event)) {
				if (event.getClickCount() == 1) {
					rightMouseClick(path, event);
				}
			}
		}

	}

	private void doubleLeftMouseClick(TreePath path) {
		tree.setSelectionPath(path);
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		Object object = treeNode.getUserObject();

		if (object instanceof User) {
			User user = (User) object;
			UserActionShowProfile userShowProfile = new UserActionShowProfile(user);

			userShowProfile.action();

		} else if (object instanceof Users) {
			Users users = (Users) object;
			if (!users.getUsers().isEmpty()) {

				DisplayTable displayTable = new DisplayTable(users.getUsers(), users.getNameOfList());
				displayTable.action();
			}

		}
	}

	private void rightMouseClick(TreePath path, MouseEvent event) {
		tree.setSelectionPath(path);
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		Object object = treeNode.getUserObject();

		if (object instanceof User) {
			User user = (User) object;
			TreePopupMenu treePopupMenu = new TreePopupMenu(user);
			treePopupMenu.show(tree.getComponentAt(event.getX(), event.getY()), event.getX(), event.getY());

		}
	}
}
