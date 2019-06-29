package ftn.xmlws.AdminModule.view.mainFrame.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;

public class TableButtonMouseListener extends MouseAdapter {

	private JTable tableView;

	public TableButtonMouseListener(JTable tableView) {
		this.tableView = tableView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int column = tableView.getColumnModel().getColumnIndexAtX(e.getX());
		int row = e.getY() / tableView.getRowHeight();

		if (row < tableView.getRowCount() && row >= 0 && column < tableView.getColumnCount() && column >= 0) {
			Object value = tableView.getValueAt(row, column);
			if (value instanceof JButton) {

				((JButton) value).doClick();
			}
		}
	}

}
