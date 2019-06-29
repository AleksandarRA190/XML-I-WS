package ftn.xmlws.AdminModule.view.mainFrame.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.controller.actions.UserActionShowProfile;

public class TableModel extends AbstractTableModel implements TableModelListener {

	private static final long serialVersionUID = 1L;
	private Object[] columns = new Object[] { "ID", "First name", "Last name", "Username", "E-mail", "Enabled",
			"Blocked", "Deleted", "Show profile" };

	private Class<?>[] columnTypes = new Class<?>[] { Long.class, String.class, String.class, String.class,
			String.class, Boolean.class, Boolean.class, Boolean.class, JButton.class };
	private ArrayList<TableRow> rows;

	private User selectedUser;

	public TableModel(ArrayList<TableRow> rows) {
		if (rows.isEmpty()) {
			this.rows = new ArrayList<TableRow>();
		} else {
			this.rows = rows;
		}

		this.selectedUser = new User();

	}

	public Object[] getColumns() {
		return columns;
	}

	public void setColumns(Object[] columns) {
		this.columns = columns;
	}

	public ArrayList<TableRow> getRows() {
		return rows;
	}

	public void setRows(ArrayList<TableRow> rows) {
		this.rows = rows;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex != 8) {
			for (int i = 0; i < rows.size(); i++) {
				if (i == rowIndex) {
					Object value = rows.get(i).getListOfObjects().get(columnIndex);
					return value;
				}
			}
		} else {
			final JButton profile = new JButton("...");
			profile.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					UserActionShowProfile showProfile = new UserActionShowProfile(selectedUser);
					showProfile.action();

				}
			});
			return profile;
		}
		return null;
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return columnTypes[columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return (String) columns[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		for (int i = 0; i < rows.size(); i++) {
			if (i == rowIndex) {
				rows.get(i).getListOfObjects().set(columnIndex, aValue);
			}
		}

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		super.addTableModelListener(l);
	}

	@Override
	public void fireTableDataChanged() {
		// TODO Auto-generated method stub
		super.fireTableDataChanged();
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		super.removeTableModelListener(l);
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
