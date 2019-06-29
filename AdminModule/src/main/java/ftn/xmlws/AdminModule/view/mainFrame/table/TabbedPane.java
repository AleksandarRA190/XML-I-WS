package ftn.xmlws.AdminModule.view.mainFrame.table;

import javax.swing.JTabbedPane;

public class TabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	public TabbedPane() {
		this.setVisible(true);
		this.setTabPlacement(TOP);
		this.addMouseListener(new TabMouseListener(this));

	}
}
