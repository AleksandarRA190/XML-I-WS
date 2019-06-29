package ftn.xmlws.AdminModule.view.mainFrame.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class TabbedPanePopupMenu extends JPopupMenu implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuItem closeTab;
	private JMenuItem closeOthers;
	private JMenuItem closeAllTabs;
	private JMenuItem closeTabsToRight;
	private JMenuItem closeTabsToLeft;

	private TabbedPane tabbedPane;
	private int number;

	public TabbedPanePopupMenu(TabbedPane tabbedPane, int number) {
		this.tabbedPane = tabbedPane;
		this.number = number;

		closeTab = new JMenuItem("Close tab");
		closeOthers = new JMenuItem("Close others");
		closeAllTabs = new JMenuItem("Close all tabs");
		closeTabsToRight = new JMenuItem("Close tabs to the right");
		closeTabsToLeft = new JMenuItem("Close tabs to the left");

		closeTab.addActionListener(this);
		// closeOthers.addActionListener(this);
		closeAllTabs.addActionListener(this);
//		closeTabsToRight.addActionListener(this);
//		closeTabsToLeft.addActionListener(this);

		this.add(closeTab);
		// this.add(closeOthers);
		// this.add(closeTabsToLeft);
		// this.add(closeTabsToRight);
		this.addSeparator();
		this.add(closeAllTabs);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(closeTab)) {
			tabbedPane.removeTabAt(number);
		} else if (event.getSource().equals(closeAllTabs)) {
			tabbedPane.removeAll();
		} else if (event.getSource().equals(closeTabsToRight)) {
			for (int i = 0; i < tabbedPane.getTabCount() - 1; i++) {
				if (i > number) {
					tabbedPane.removeTabAt(i);
				}
			}
		} else if (event.getSource().equals(closeTabsToLeft)) {
			for (int i = 0; i < tabbedPane.getTabCount() - 1; i++) {
				if (i < number) {
					tabbedPane.removeTabAt(i);
				}
			}
		} else if (event.getSource().equals(closeOthers)) {
			for (int i = 0; i < tabbedPane.getTabCount() - 1; i++) {
				if (i != number) {
					tabbedPane.removeTabAt(i);
				}
			}
		}
	}

}
