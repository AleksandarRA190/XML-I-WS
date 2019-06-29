package ftn.xmlws.AdminModule.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.controller.actions.UserActionActivate;
import ftn.xmlws.AdminModule.controller.actions.UserActionBlock;
import ftn.xmlws.AdminModule.controller.actions.UserActionDelete;
import ftn.xmlws.AdminModule.controller.actions.UserActionUnblock;
import ftn.xmlws.AdminModule.controller.swingApp.UpdateTable;
import ftn.xmlws.AdminModule.controller.tree.ActionTreeUpdate;

public class OptionFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private String message;
	private String type;
	private String title;

	private User user;

	private JPanel mainPanel;

	private JPanel messagePanel;
	private JPanel buttonsPanel;

	private JLabel messageLabel;
	private JButton okButton;
	private JButton cancelButton;

	private Dimension buttonDim;

	public OptionFrame(String message, String type, String title, User user) {
		this.message = message;
		this.type = type;
		this.title = title;
		this.user = user;

		this.initFrame();
	}

	public void initFrame() {
		this.setTitle(title);
		this.setIconImage(Singleton.getInstance().getInitIcons().getAppImage());
		this.setPreferredSize(new Dimension(400, 150));
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initPanels();
	}

	public void initPanels() {
		this.mainPanel = new JPanel();
		this.messagePanel = new JPanel();
		this.buttonsPanel = new JPanel();

		this.buttonDim = new Dimension(100, 25);

		this.mainPanel.setLayout(new GridLayout(2, 1, 10, 40));

		this.messagePanel.setLayout(new BorderLayout());

		this.buttonsPanel.setLayout(new FlowLayout());

		this.initComponents();

		this.initActionListeners();

		this.mainPanel.add(messagePanel);
		this.mainPanel.add(buttonsPanel);

		this.add(mainPanel);
	}

	public void initComponents() {
		this.messageLabel = new JLabel();
		this.messageLabel.setText(message);

		this.messagePanel.add(messageLabel, BorderLayout.CENTER);

		this.okButton = new JButton("Ok");
		this.okButton.setPreferredSize(buttonDim);

		this.cancelButton = new JButton("Cancel");
		this.cancelButton.setPreferredSize(buttonDim);

		this.buttonsPanel.add(okButton);
		this.buttonsPanel.add(cancelButton);

	}

	public void initActionListeners() {
		if (this.type.equals("b")) {
			this.okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					UserActionBlock blockAction = (UserActionBlock) Singleton.getInstance().getContext()
							.getBean(UserActionBlock.class);
					blockAction.setUser(user);
					blockAction.action();

					if (blockAction.isSuccess()) {
						ActionTreeUpdate updateTree = new ActionTreeUpdate();
						updateTree.action();
						UpdateTable updateTable = new UpdateTable();
						updateTable.action();

						dispose();
					} else {
						dispose();
					}
				}
			});
		} else if (this.type.equals("u")) {
			this.okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					UserActionUnblock unblockAction = (UserActionUnblock) Singleton.getInstance().getContext()
							.getBean(UserActionUnblock.class);
					unblockAction.setUser(user);
					unblockAction.action();

					if (unblockAction.isSuccess()) {
						ActionTreeUpdate updateTree = new ActionTreeUpdate();
						updateTree.action();
						UpdateTable updateTable = new UpdateTable();
						updateTable.action();
						dispose();
					} else {
						dispose();
					}
				}
			});
		} else if (this.type.equals("a")) {
			this.okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					UserActionActivate activateAction = (UserActionActivate) Singleton.getInstance().getContext()
							.getBean(UserActionActivate.class);
					activateAction.setUser(user);
					activateAction.action();

					if (activateAction.isSuccess()) {
						ActionTreeUpdate updateTree = new ActionTreeUpdate();
						updateTree.action();
						UpdateTable updateTable = new UpdateTable();
						updateTable.action();
						dispose();
					} else {
						dispose();
					}
				}
			});
		} else if (this.type.equals("d")) {
			this.okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					UserActionDelete deleteAction = (UserActionDelete) Singleton.getInstance().getContext()
							.getBean(UserActionDelete.class);
					deleteAction.setUser(user);
					deleteAction.action();

					if (deleteAction.isSuccess()) {
						ActionTreeUpdate updateTree = new ActionTreeUpdate();
						updateTree.action();
						UpdateTable updateTable = new UpdateTable();
						updateTable.action();
						dispose();
					} else {
						dispose();
					}

				}
			});
		}

		this.cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

}
