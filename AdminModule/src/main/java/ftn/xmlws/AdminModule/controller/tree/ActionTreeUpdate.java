package ftn.xmlws.AdminModule.controller.tree;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.controller.actions.UserActionGet;

public class ActionTreeUpdate {

	public void action() {
		UserActionGet getAction = (UserActionGet) Singleton.getInstance().getContext().getBean(UserActionGet.class);
		getAction.action();

		Singleton.getInstance().getTreeModel()
				.updateTreeModel(new DefaultMutableTreeNode(Singleton.getInstance().getRoot()));

		SwingUtilities.updateComponentTreeUI(Singleton.getInstance().getMainFrame());
	}
}
