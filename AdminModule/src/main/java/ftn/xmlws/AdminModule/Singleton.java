package ftn.xmlws.AdminModule;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.tree.DefaultMutableTreeNode;

import org.springframework.context.ApplicationContext;

import ftn.xmlws.AdminModule.controller.swingApp.InitIcons;
import ftn.xmlws.AdminModule.model.local.Root;
import ftn.xmlws.AdminModule.view.mainFrame.MainFrame;
import ftn.xmlws.AdminModule.view.mainFrame.mainPanel.MainPanel;
import ftn.xmlws.AdminModule.view.mainFrame.table.TabbedPane;
import ftn.xmlws.AdminModule.view.mainFrame.tree.TreeModel;
import ftn.xmlws.AdminModule.view.mainFrame.tree.TreeView;

public class Singleton {

	private static Singleton instance = new Singleton();

	private MainFrame mainFrame;
	private Root root;
	private TreeView treeView;
	private TreeModel treeModel;
	private DefaultMutableTreeNode rootNode;
	private ApplicationContext context;
	private List<User> users = new ArrayList<User>();
	private TabbedPane tabbedPane;
	private MainPanel mainPanel;
	private JTable tableView;
	private User loggedAdmin;
	private InitIcons initIcons;

	private Singleton() {

	}

	public static Singleton getInstance() {
		return instance;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public TreeView getTreeView() {
		return treeView;
	}

	public void setTreeView(TreeView treeView) {
		this.treeView = treeView;
	}

	public TreeModel getTreeModel() {
		return treeModel;
	}

	public void setTreeModel(TreeModel treeModel) {
		this.treeModel = treeModel;
	}

	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(DefaultMutableTreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public TabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(TabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JTable getTableView() {
		return tableView;
	}

	public void setTableView(JTable tableView) {
		this.tableView = tableView;
	}

	public User getLoggedAdmin() {
		return loggedAdmin;
	}

	public void setLoggedAdmin(User loggedAdmin) {
		this.loggedAdmin = loggedAdmin;
	}

	public InitIcons getInitIcons() {
		return initIcons;
	}

	public void setInitIcons(InitIcons initIcons) {
		this.initIcons = initIcons;
	}
}