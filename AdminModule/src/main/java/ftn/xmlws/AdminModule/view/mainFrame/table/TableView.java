package ftn.xmlws.AdminModule.view.mainFrame.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;

public class TableView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableView;
	private TableModel tableModel;

	private ArrayList<TableRow> rows;

	private ArrayList<Object> selectedRowData;
	private User selectedUser;

	private JPanel tablePanel;

	public TableView(ArrayList<TableRow> rows) {
		if (rows.isEmpty()) {
			this.rows = new ArrayList<TableRow>();
		} else {
			this.rows = rows;
		}

		this.selectedRowData = new ArrayList<Object>();
		this.tablePanel = new JPanel();
		this.selectedUser = new User();

		initTablePanel();

	}

	public void initTablePanel() {
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		initTable();
		this.tablePanel = new JPanel();
		this.tablePanel.setLayout(new BorderLayout());
		this.tablePanel.add(new JScrollPane(tableView), BorderLayout.CENTER);
		this.add(tablePanel, BorderLayout.CENTER);

	}

	public void initTable() {
		tableModel = new TableModel(rows);
		tableView = new JTable(tableModel);

		tableView.setFillsViewportHeight(true);
		tableView.setDefaultRenderer(String.class, new TableCellRenderer());
		tableView.setDefaultRenderer(Long.class, new TableCellRenderer());
		tableView.getColumn("Show profile").setCellRenderer(new TableButtonCellRenderer());
		tableView.addMouseListener(new TableButtonMouseListener(tableView));
		tableView.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				selectedRowData.clear();
				selectedUser = new User();
				if (!event.getValueIsAdjusting() && tableView.getSelectedRow() != -1) {
					for (int i = 0; i < tableModel.getColumns().length - 1; i++) {
						selectedRowData.add(tableView.getValueAt(tableView.getSelectedRow(), i));
						for (User user : Singleton.getInstance().getUsers()) {
							if (selectedRowData.get(i) instanceof Long) {
								long id = (long) selectedRowData.get(i);
								if (user.getId() == id) {
									selectedUser = user;
									tableModel.setSelectedUser(selectedUser);
								}
							}
						}
					}
				}
			}
		});

		tableView.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableView.setAutoscrolls(true);
		tableView.setPreferredScrollableViewportSize(
				new Dimension(Singleton.getInstance().getMainPanel().getWidth(), 600));

		Singleton.getInstance().setTableView(tableView);
	}

	public ArrayList<TableRow> getRows() {
		return rows;
	}

	public void setRows(ArrayList<TableRow> rows) {
		this.rows = rows;
	}

	public JTable getTableView() {
		return tableView;
	}

	public void setTableView(JTable tableView) {
		this.tableView = tableView;
	}

}
