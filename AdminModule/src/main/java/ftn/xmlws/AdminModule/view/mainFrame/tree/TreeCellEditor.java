package ftn.xmlws.AdminModule.view.mainFrame.tree;

import java.awt.event.ActionListener;

import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeCellEditor extends DefaultTreeCellEditor implements CellEditorListener, ActionListener {

	public TreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
		super(tree, renderer);
		addCellEditorListener(this);
	}

	@Override
	public void editingCanceled(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editingStopped(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}
}
