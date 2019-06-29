package ftn.xmlws.AdminModule.controller.actions;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.model.enums.Role;
import ftn.xmlws.AdminModule.model.local.ListOfUsers;
import ftn.xmlws.AdminModule.model.local.Root;
import ftn.xmlws.AdminModule.model.local.Users;

public class UserParseAction {

	public void action(DefaultMutableTreeNode rootNode) {

		List<User> tempList = new ArrayList<User>();

		Object object = rootNode.getUserObject();

		if (object instanceof Root) {
			Root root = (Root) object;

			root.getListOfAgents().getBlockedUsers().getUsers().clear();
			root.getListOfAgents().getEnabledUsers().getUsers().clear();
			root.getListOfAgents().getRequirements().getUsers().clear();

			root.getListOfUsers().getBlockedUsers().getUsers().clear();
			root.getListOfUsers().getEnabledUsers().getUsers().clear();
			root.getListOfUsers().getRequirements().getUsers().clear();

			initList(tempList, root.getAllUsers());

			for (User user : tempList) {
				if (user.getRole().equals(Role.REGISTERED_USER.toString())) {

					// enabled users
					if (user.isEnabled() && !user.isDeleted() && !user.isBlocked()) {
						root.getListOfUsers().getEnabledUsers().getUsers().add(user);
					} else if (user.isBlocked() && user.isEnabled()) {
						root.getListOfUsers().getBlockedUsers().getUsers().add(user);
					} else if (!user.isEnabled() && !user.isDeleted() && !user.isBlocked()) {
						root.getListOfUsers().getRequirements().getUsers().add(user);
					}

				} else if (user.getRole().equals(Role.AGENT.toString())) {
					// enabled agents
					if (user.isEnabled() && !user.isDeleted() && !user.isBlocked()) {
						root.getListOfAgents().getEnabledUsers().getUsers().add(user);

					} else if (user.isBlocked() && user.isEnabled()) {
						root.getListOfAgents().getBlockedUsers().getUsers().add(user);
					} else if (!user.isEnabled() && !user.isDeleted() && !user.isBlocked()) {
						root.getListOfAgents().getRequirements().getUsers().add(user);
					}

				}
			}

			DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(root);
			drawTree(treeNode);
			Singleton.getInstance().setRootNode(treeNode);
			Singleton.getInstance().setRoot(root);

		}
	}

	public DefaultMutableTreeNode setTree(DefaultMutableTreeNode rootNode) {
		drawTree(rootNode);

		return rootNode;
	}

	public void drawTree(DefaultMutableTreeNode node) {
		Root root = (Root) node.getUserObject();

		DefaultMutableTreeNode agentNode = new DefaultMutableTreeNode(root.getListOfAgents());
		drawFolders(agentNode);
		node.add(agentNode);

		DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(root.getListOfUsers());
		drawFolders(userNode);
		node.add(userNode);

	}

	public void drawFolders(DefaultMutableTreeNode node) {
		ListOfUsers agents = (ListOfUsers) node.getUserObject();

		DefaultMutableTreeNode blockedAgents = new DefaultMutableTreeNode(agents.getBlockedUsers());
		drawAgents(blockedAgents);
		node.add(blockedAgents);
		DefaultMutableTreeNode enabledAgents = new DefaultMutableTreeNode(agents.getEnabledUsers());
		drawAgents(enabledAgents);
		node.add(enabledAgents);
		DefaultMutableTreeNode requestsAgents = new DefaultMutableTreeNode(agents.getRequirements());
		drawAgents(requestsAgents);
		node.add(requestsAgents);

	}

	public void drawAgents(DefaultMutableTreeNode node) {
		Users agents = (Users) node.getUserObject();
		for (User user : agents.getUsers()) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(user);
			node.add(newNode);
		}

	}

	public void drawUsers(DefaultMutableTreeNode node) {
		Users users = (Users) node.getUserObject();
		for (User user : users.getUsers()) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(user);
			node.add(newNode);
		}
	}

	public void initList(List<User> tempList, List<User> users) {
		for (User UserDTO : users) {
			tempList.add(UserDTO);
		}
	}

}
