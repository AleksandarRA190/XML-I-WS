package ftn.xmlws.AdminModule.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.controller.actions.UserActionLogin;

@Component
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTextField usernameField;
	private JPasswordField passwordField;

	private JLabel usernameLabel;
	private JLabel passwordLabel;

	private JButton okButton;
	private JButton cancelButton;

	public LoginFrame() {
		initFrame();
	}

	public void initFrame() {
		this.setSize(300, 150);
		this.setTitle("Admin Application: Login Frame");
		this.setIconImage(Singleton.getInstance().getInitIcons().getAppImage());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.initPanel();

		this.add(mainPanel);
	}

	public void initPanel() {
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(null);
		this.initComponents();

		usernameLabel.setBounds(10, 10, 80, 25);
		mainPanel.add(usernameLabel);

		usernameField.setBounds(100, 10, 160, 25);
		mainPanel.add(usernameField);

		passwordLabel.setBounds(10, 40, 80, 25);
		mainPanel.add(passwordLabel);

		passwordField.setBounds(100, 40, 160, 25);
		mainPanel.add(passwordField);

		okButton.setBounds(10, 80, 80, 25);
		mainPanel.add(okButton);

		cancelButton.setBounds(180, 80, 80, 25);
		mainPanel.add(cancelButton);

	}

	public void initComponents() {
		this.usernameLabel = new JLabel("Username:");
		this.usernameField = new JTextField(20);

		this.passwordLabel = new JLabel("Password:");
		this.passwordField = new JPasswordField(20);

		this.okButton = new JButton("Ok");
		this.cancelButton = new JButton("Cancel");

		initActionListeners();

	}

	public void initActionListeners() {
		this.passwordField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserActionLogin loginAction = (UserActionLogin) Singleton.getInstance().getContext()
						.getBean(UserActionLogin.class);
				String pass = new String(passwordField.getPassword());
				loginAction.action(usernameField.getText(), pass.toString());
				dispose();
			}
		});

		this.okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserActionLogin loginAction = (UserActionLogin) Singleton.getInstance().getContext()
						.getBean(UserActionLogin.class);
				String pass = new String(passwordField.getPassword());
				loginAction.action(usernameField.getText(), pass.toString());
				dispose();
			}
		});

		this.cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
