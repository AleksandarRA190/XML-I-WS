package ftn.xmlws.AdminModule.controller.tree;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.controller.actions.UserActionShowProfile;
import ftn.xmlws.AdminModule.view.mainFrame.tree.TreeView;

public class ActionTreeFind {

	public void action(TreeView treeView, String searchText, DefaultMutableTreeNode treeNode) {

		TreePath treePath = find(treeNode, searchText);
		if (treePath != null) {

			treeView.setSelectionPath(treePath);
			treeView.scrollPathToVisible(treePath);
		}

	}

	private TreePath find(DefaultMutableTreeNode root, String s) {
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode node = e.nextElement();
			Object object = node.getUserObject();
			if (object instanceof User) {
				User tempUser = (User) object;
				String tempSearch = tempUser.getName() + " " + tempUser.getLastname();
				if (tempSearch.toUpperCase().contains(s.toUpperCase())) {
					UserActionShowProfile showProfile = new UserActionShowProfile(tempUser);
					showProfile.action();
					return new TreePath(node.getPath());

				}
			}
		}
		return null;
	}
}
