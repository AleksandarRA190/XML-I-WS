package ftn.xmlws.AdminModule.view.mainFrame.tree;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.model.local.ListOfUsers;
import ftn.xmlws.AdminModule.model.local.Root;
import ftn.xmlws.AdminModule.model.local.Users;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	private String searchString = "";

	public TreeCellRenderer() {

	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Object object = node.getUserObject();

		if (object instanceof Root) {
			Root root = (Root) object;
			this.setIcon(Singleton.getInstance().getInitIcons().getRootIcon());
			this.setText(root.getNameOfRoot());
		} else if (object instanceof ListOfUsers) {
			ListOfUsers list = (ListOfUsers) object;
			this.setOpenIcon(Singleton.getInstance().getInitIcons().getOpenIcon());
			this.setClosedIcon(Singleton.getInstance().getInitIcons().getCloseIcon());
			this.setText(list.getNameOfList());
		} else if (object instanceof Users) {
			Users users = (Users) object;
			this.setIcon(Singleton.getInstance().getInitIcons().getGroupIcon());
			this.setText(users.getNameOfList());
		} else if (object instanceof User) {
			User user = (User) object;
			this.setIcon(Singleton.getInstance().getInitIcons().getUserIcon());
			this.setText(user.getName() + " " + user.getLastname());
		}

		return this;

	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
