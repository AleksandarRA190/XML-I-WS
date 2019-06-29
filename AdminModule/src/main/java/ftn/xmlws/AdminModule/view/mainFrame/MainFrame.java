package ftn.xmlws.AdminModule.view.mainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import org.springframework.stereotype.Component;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.controller.tree.ActionTreeFind;
import ftn.xmlws.AdminModule.model.local.Root;
import ftn.xmlws.AdminModule.view.mainFrame.bars.MenuBar;
import ftn.xmlws.AdminModule.view.mainFrame.bars.ToolBar;
import ftn.xmlws.AdminModule.view.mainFrame.mainPanel.MainPanel;
import ftn.xmlws.AdminModule.view.mainFrame.table.TabbedPane;
import ftn.xmlws.AdminModule.view.mainFrame.tree.TreeView;

@Component
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private MenuBar menuBar;

	private ToolBar toolBar;

	private MainPanel mainPanel;
	private TreeView treeView;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private Root root;

	private JPanel searchPanel;
	private JTextField searchField;
	private JButton searchButton;

	private DefaultMutableTreeNode treeNode;
	private TabbedPane tabbedPane;

	private User admin;

	public MainFrame() {

	}

	public void primaryInit() {
		this.root = new Root();
		this.root = Singleton.getInstance().getRoot();
		this.treeNode = new DefaultMutableTreeNode(root);
		this.initFrame();

	}

	public void initFrame() {

		this.initComponents();
		// this.setLayout(new BorderLayout());
		this.setJMenuBar(menuBar);
		this.setIconImage(Singleton.getInstance().getInitIcons().getAppImage());
		this.setTitle("Admin Application: " + this.admin.getUsername());
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.add(toolBar, BorderLayout.NORTH);

	}

	public void initComponents() {

		this.initTabbedPane();

		this.toolBar = new ToolBar();

		this.treeView = new TreeView();
		this.treeView.initTreeView(treeNode);
		Singleton.getInstance().setTreeView(treeView);
		Singleton.getInstance().setRootNode(treeNode);

		this.menuBar = new MenuBar();
		/*
		 * this.toolBar = new ToolBar(); this.statusBar = new StatusBar();
		 */
		this.mainPanel = new MainPanel();
		Singleton.getInstance().setMainPanel(mainPanel);

		this.searchButton = new JButton("S");
		this.searchField = new JTextField();
		this.searchPanel = new JPanel();
		this.searchPanel.setPreferredSize(new Dimension(25, 25));
		this.searchField.setPreferredSize(new Dimension(20, 25));
		this.searchField.setToolTipText("Searching by first name or last name.");
		this.searchField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ActionTreeFind actionTreeFind = new ActionTreeFind();
				actionTreeFind.action(Singleton.getInstance().getTreeView(), searchField.getText(),
						Singleton.getInstance().getRootNode());
			}

		});

		this.searchButton.setPreferredSize(new Dimension(25, 25));
		this.searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ActionTreeFind actionTreeFind = new ActionTreeFind();
				actionTreeFind.action(Singleton.getInstance().getTreeView(), searchField.getText(),
						Singleton.getInstance().getRootNode());

			}
		});

		this.searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		this.searchPanel.add(searchField);
		this.searchPanel.add(searchButton);

		this.scrollPane = new JScrollPane(treeView);

		JPanel treeSide = new JPanel();
		treeSide.setLayout(new BorderLayout());
		treeSide.add(scrollPane, BorderLayout.CENTER);
		treeSide.add(searchPanel, BorderLayout.PAGE_END);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeSide, mainPanel);
		splitPane.setDividerLocation(250);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.2);
		getContentPane().add(splitPane, BorderLayout.CENTER);

	}

	public void initTabbedPane() {
		tabbedPane = new TabbedPane();
		Singleton.getInstance().setTabbedPane(tabbedPane);
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}
}