package ftn.xmlws.AdminModule.view.mainFrame.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.plaf.TabbedPaneUI;

public class TabMouseListener extends MouseAdapter {

	private TabbedPane tabbedPane;

	public TabMouseListener(TabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public void mousePressed(MouseEvent event) {
		TabbedPaneUI pane = tabbedPane.getUI();
		int number = pane.tabForCoordinate(tabbedPane, event.getX(), event.getY());

		if (number != -1) {
			tabbedPane.setSelectedIndex(number);
			if (SwingUtilities.isRightMouseButton(event)) {
				if (event.getClickCount() == 1) {
					rightMouseClick(tabbedPane, pane, event, number);
				}
			} else if (SwingUtilities.isLeftMouseButton(event) && event.getComponent() instanceof JButton) {
				if (event.getClickCount() == 1) {
					leftMouseClick(tabbedPane, number, event);
				}
			}
		}
	}

	public void rightMouseClick(TabbedPane tabbedPane, TabbedPaneUI pane, MouseEvent event, int number) {
		if (number != -1) {
			TabbedPanePopupMenu popupMenu = new TabbedPanePopupMenu(tabbedPane, number);
			popupMenu.show(tabbedPane.getTabComponentAt(number), tabbedPane.getTabComponentAt(number).getWidth() / 2,
					tabbedPane.getTabComponentAt(number).getHeight() / 2);
		}
	}

	public void leftMouseClick(TabbedPane tabbedPane, int number, MouseEvent event) {
		if (number != -1) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
		}
	}
}
