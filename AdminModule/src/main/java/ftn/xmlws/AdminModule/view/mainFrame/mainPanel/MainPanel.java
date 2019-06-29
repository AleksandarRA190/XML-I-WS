package ftn.xmlws.AdminModule.view.mainFrame.mainPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.view.mainFrame.table.TabbedPane;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private TabbedPane tabbedPane;

	public MainPanel() {
		super(new GridLayout(1, 1));
		this.tabbedPane = Singleton.getInstance().getTabbedPane();
		this.setLayout(new BorderLayout());
		this.add(tabbedPane);
	}
}
