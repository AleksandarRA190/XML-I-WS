package ftn.xmlws.AdminModule.view.mainFrame.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.springframework.stereotype.Component;

import ftn.xmlws.AdminModule.Singleton;

@Component
public class TreeView extends JTree {

	private static final long serialVersionUID = 1L;
	private TreeModel treeModel;
	private TreeCellRenderer treeCellRenderer;

	public TreeView() {

	}

	public void initTreeView(DefaultMutableTreeNode treeNode) {
		treeModel = new TreeModel(treeNode);
		treeModel.setTree(treeNode);
		this.setModel(treeModel);
		Singleton.getInstance().setTreeModel(treeModel);
		this.addMouseListener(new TreeMouseListener(this));
		this.treeCellRenderer = new TreeCellRenderer();
		this.setCellRenderer(this.treeCellRenderer);
		this.setCellEditor(new TreeCellEditor(this, this.treeCellRenderer));
		this.setEditable(false);

	}

	public TreeModel getTreeModel() {
		return treeModel;
	}

	public void setTreeModel(TreeModel treeModel) {
		this.treeModel = treeModel;
	}

	public TreeCellRenderer getTreeCellRenderer() {
		return treeCellRenderer;
	}

	public void setTreeCellRenderer(TreeCellRenderer treeCellRenderer) {
		this.treeCellRenderer = treeCellRenderer;
	}
}
