package ftn.xmlws.AdminModule.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.xmlws.AdminModule.Comment;
import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.controller.actions.UserActionGetComments;
import ftn.xmlws.AdminModule.view.mainFrame.comments.CommentsFrame;

@Component
public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private User user;
	private JPanel mainPanel;

	private JButton closeButton;
	private JButton commentsButton;

	private JLabel nameLabel;
	private JLabel lastnameLabel;
	private JLabel emailLabel;
	private JLabel usernameLabel;
	private JLabel addressLabel;

	private JTextField name;
	private JTextField lastname;
	private JTextField email;
	private JTextField username;
	private JTextField address;

	private JButton blockButton;
	private JButton unblockButton;
	private JButton enableButton;
	private JButton deleteButton;

	private Dimension labelDim;
	private Dimension buttonDim;
	private Dimension fieldDim;

	private List<Comment> comments;

	@Autowired
	public UserFrame() {
		this.user = new User();
		this.comments = new ArrayList<Comment>();

	}

	public void setUser(User user) {
		this.user = user;
	}

	public void initFrame() {
		this.setTitle("Profile: " + user.getUsername());
		this.setPreferredSize(new Dimension(400, 300));
		this.setIconImage(Singleton.getInstance().getInitIcons().getUserImage());
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.initPanel();

	}

	public void initPanel() {
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new GridLayout(8, 3, 10, 40));

		this.initComponents();
		this.add(mainPanel);
	}

	public void initComponents() {
		this.mainPanel = new JPanel();

		this.labelDim = new Dimension(100, 25);
		this.buttonDim = new Dimension(100, 25);
		this.fieldDim = new Dimension(200, 25);

		// buttons

		// labels left
		// name
		this.nameLabel = new JLabel("First name:");
		this.nameLabel.setPreferredSize(labelDim);

		this.name = new JTextField();
		this.name.setText(user.getName());
		this.name.setEnabled(false);
		this.name.setPreferredSize(fieldDim);

		this.nameLabel.setLabelFor(name);
		this.mainPanel.add(nameLabel);
		this.mainPanel.add(name);

		// lastname
		this.lastnameLabel = new JLabel("Last name:");
		this.lastnameLabel.setPreferredSize(labelDim);

		this.lastname = new JTextField();
		this.lastname.setText(user.getLastname());
		this.lastname.setEnabled(false);
		this.lastname.setPreferredSize(fieldDim);

		this.mainPanel.add(lastnameLabel);
		this.mainPanel.add(lastname);

		// email
		this.emailLabel = new JLabel("E-mail:");
		this.emailLabel.setPreferredSize(labelDim);

		this.email = new JTextField();
		this.email.setText(user.getEmail());
		this.email.setEnabled(false);
		this.email.setPreferredSize(fieldDim);
		this.mainPanel.add(emailLabel);
		this.mainPanel.add(email);

		// username
		this.usernameLabel = new JLabel("Username:");
		this.usernameLabel.setPreferredSize(labelDim);

		this.username = new JTextField();
		this.username.setText(user.getUsername());
		this.username.setEnabled(false);
		this.username.setPreferredSize(fieldDim);

		this.mainPanel.add(usernameLabel);
		this.mainPanel.add(username);

		// address
		this.addressLabel = new JLabel("Address:");
		this.addressLabel.setPreferredSize(labelDim);

		this.address = new JTextField();
		this.address.setText(user.getAddress().getStreet() + " " + user.getAddress().getNumber() + ", "
				+ user.getAddress().getCity());
		this.address.setEnabled(false);
		this.address.setPreferredSize(fieldDim);

		this.mainPanel.add(addressLabel);
		this.mainPanel.add(address);

		this.blockButton = new JButton("Block");
		this.blockButton.setPreferredSize(buttonDim);

		this.unblockButton = new JButton("Unblock");
		this.unblockButton.setPreferredSize(buttonDim);

		this.mainPanel.remove(unblockButton);
		JPanel blockPanel = new JPanel();
		blockPanel.setLayout(new FlowLayout());
		blockPanel.add(blockButton);

		this.mainPanel.remove(blockButton);
		JPanel unblockPanel = new JPanel();
		unblockPanel.setLayout(new FlowLayout());
		unblockPanel.add(unblockButton);

		if (user.isBlocked()) {
			this.blockButton.setEnabled(false);
			this.unblockButton.setEnabled(true);
			this.mainPanel.add(blockPanel);
			this.mainPanel.add(unblockPanel);
		} else {
			this.blockButton.setEnabled(true);
			this.unblockButton.setEnabled(false);
			this.mainPanel.add(blockPanel);
			this.mainPanel.add(unblockPanel);
		}

		this.enableButton = new JButton("Activate");
		this.enableButton.setPreferredSize(buttonDim);
		JPanel enablePanel = new JPanel();
		enablePanel.setLayout(new FlowLayout());
		enablePanel.add(enableButton);

		if (user.isEnabled()) {
			this.enableButton.setEnabled(false);
		} else {
			this.enableButton.setEnabled(true);
		}

		this.mainPanel.add(enablePanel);

		this.deleteButton = new JButton("Delete");
		this.deleteButton.setPreferredSize(buttonDim);
		JPanel deletePanel = new JPanel();
		deletePanel.setLayout(new FlowLayout());
		deletePanel.add(deleteButton);
		this.mainPanel.add(deletePanel);

		this.commentsButton = new JButton("Comments");
		this.commentsButton.setPreferredSize(buttonDim);

		JPanel commentsPanel = new JPanel();
		commentsPanel.setLayout(new FlowLayout());
		commentsPanel.add(commentsButton);
		this.mainPanel.add(commentsPanel);

		this.closeButton = new JButton("Close");
		this.closeButton.setPreferredSize(buttonDim);
		JPanel closePanel = new JPanel();
		closePanel.setLayout(new FlowLayout());
		closePanel.add(closeButton);
		this.mainPanel.add(closePanel);

		this.initActionListeners();

	}

	public void initActionListeners() {

		this.blockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Are you sure you want to block this user:\n" + user.getName() + " "
						+ user.getLastname();

				OptionFrame blockFrame = new OptionFrame(message, "b", "Block User:" + user.getUsername(), user);
				blockFrame.setVisible(true);
				dispose();

			}
		});

		this.unblockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String message = "Are you sure you want to unblock this user:\n" + user.getName() + " "
						+ user.getLastname();

				OptionFrame unblockFrame = new OptionFrame(message, "u", "Unblock User:" + user.getUsername(), user);
				unblockFrame.setVisible(true);
				dispose();

			}
		});

		this.enableButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Are you sure you want to activate this user:\n" + user.getName() + " "
						+ user.getLastname();

				OptionFrame activateFrame = new OptionFrame(message, "a", "Activate User:" + user.getUsername(), user);
				activateFrame.setVisible(true);
				dispose();

			}
		});

		this.deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Are you sure you want to delete this user:\n" + user.getName() + " "
						+ user.getLastname();

				OptionFrame deleteFrame = new OptionFrame(message, "d", "Delete User:" + user.getUsername(), user);
				deleteFrame.setVisible(true);
				dispose();

			}
		});

		this.commentsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserActionGetComments getCommnets = (UserActionGetComments) Singleton.getInstance().getContext()
						.getBean(UserActionGetComments.class);
				getCommnets.setUser(user);
				getCommnets.action();

				if (getCommnets.getComments().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selected user does not have any comments!",
							"Comments information.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					comments = getCommnets.getComments();

					ArrayList<Comment> temp = new ArrayList<Comment>();

					for (Comment comment : comments) {
						if (!comment.isApprovedComment()) {
							temp.add(comment);
						}
					}

					CommentsFrame commentsFrame = new CommentsFrame(temp, user);
					commentsFrame.setVisible(true);
				}
			}
		});
		this.closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserActionGetComments comments = Singleton.getInstance().getContext()
						.getBean((UserActionGetComments.class));
				comments.setUser(user);
				comments.action();
				dispose();
			}
		});

	}

}
