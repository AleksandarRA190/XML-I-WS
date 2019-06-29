package ftn.xmlws.AdminModule.view.mainFrame.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.controller.actions.UserParseAction;

public class TreeModel extends DefaultTreeModel {

	private static final long serialVersionUID = 1L;
	UserParseAction parseAction = new UserParseAction();

	public TreeModel(DefaultMutableTreeNode rootNode) {
		super(rootNode);

		parseAction.action(rootNode);

		this.setRoot(Singleton.getInstance().getRootNode());

	}

	public void updateTreeModel(DefaultMutableTreeNode rootNode) {
		parseAction.action(rootNode);
		this.setRoot(Singleton.getInstance().getRootNode());
	}

	public void setTree(DefaultMutableTreeNode rootNode) {
		this.setRoot(parseAction.setTree(rootNode));

	}

}
