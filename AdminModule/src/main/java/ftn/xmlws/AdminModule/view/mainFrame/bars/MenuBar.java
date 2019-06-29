package ftn.xmlws.AdminModule.view.mainFrame.bars;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ftn.xmlws.AdminModule.view.createFrames.NewAdminFrame;
import ftn.xmlws.AdminModule.view.createFrames.NewAgentFrame;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private JMenu file;
	private JMenuItem exitItem;
	private JMenu add;
	private JMenuItem newAdmin;
	private JMenuItem newAgent;

	public MenuBar() {
		super();
		this.initMenuBar();

	}

	public void initMenuBar() {
		this.file = new JMenu("File");
		this.file.setMnemonic('F');

		this.add = new JMenu("New");
		this.add.setMnemonic('N');

		this.newAdmin = new JMenuItem("Admin");
		this.newAdmin.setMnemonic('d');

		this.newAgent = new JMenuItem("Agent");
		this.newAgent.setMnemonic('g');

		this.add.add(newAgent);
		this.add.add(newAdmin);

		this.file.add(add);
		this.file.addSeparator();

		this.exitItem = new JMenuItem("Exit");
		this.exitItem.setMnemonic('E');
		this.file.add(exitItem);
		this.add(file);

		this.initActionListeners();
	}

	public void initActionListeners() {
		this.newAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NewAdminFrame adminFrame = new NewAdminFrame();
				adminFrame.setVisible(true);
			}
		});

		this.newAgent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NewAgentFrame agentFrame = new NewAgentFrame();
				agentFrame.setVisible(true);
			}
		});

		this.exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
