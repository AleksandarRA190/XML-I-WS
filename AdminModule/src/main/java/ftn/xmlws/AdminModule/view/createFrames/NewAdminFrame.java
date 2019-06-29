package ftn.xmlws.AdminModule.view.createFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ftn.xmlws.AdminModule.Address;
import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.controller.actions.UserActionCreate;
import ftn.xmlws.AdminModule.controller.swingApp.UpdateTable;
import ftn.xmlws.AdminModule.controller.tree.ActionTreeUpdate;

public class NewAdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel bottomPanel;

	// personal data
	private JLabel nameLabel;
	private JTextField nameField;

	private JLabel lastnameLabel;
	private JTextField lastnameField;

	private JLabel emailLabel;
	private JTextField emailField;

	private JLabel usernameLabel;
	private JTextField usernameField;

	private JLabel passwordLabel;
	private JPasswordField passwordField;

	private JLabel confirmPasswordLabel;
	private JPasswordField confirmPasswordField;

	private JLabel activateLabel;
	private JCheckBox activateBox;

	private JLabel blockedLabel;
	private JCheckBox blockedBox;

	// address data
	private JLabel countryLabel;
	private JComboBox<String> countryField;

	private JLabel cityLabel;
	private JTextField cityField;

	private JLabel postalCodeLabel;
	private JTextField postalCodeField;

	private JLabel streetLabel;
	private JTextField streetField;

	private JLabel numberLabel;
	private JTextField numberField;

	private JLabel apartmentNumberLabel;
	private JTextField apartmentNumberField;

	private JLabel longitudeLabel;
	private JTextField longitudeField;

	private JLabel latitudeLabel;
	private JTextField latitudeField;

	private JButton okButton;
	private JButton cancelButton;

	private User newAdmin;
	private final Vector<String> countries = new Vector<String>();

	private Dimension labelDim;
	private Dimension buttonDim;
	private Dimension fieldDim;

	public NewAdminFrame() {
		this.newAdmin = new User();
		this.getAllCountries();
		this.initPanels();
		this.initComponents();
		this.setIconImage(Singleton.getInstance().getInitIcons().getAppImage());
		this.setTitle("New Admin Frame");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(800, 350));
		this.setContentPane(this.mainPanel);
		this.pack();
	}

	public void initPanels() {
		this.mainPanel = new JPanel(new BorderLayout());
		this.leftPanel = new JPanel(new SpringLayout());
		this.rightPanel = new JPanel(new SpringLayout());

		this.bottomPanel = new JPanel();

		this.mainPanel.setOpaque(true);
		this.leftPanel.setOpaque(true);
		this.rightPanel.setOpaque(true);
		this.bottomPanel.setOpaque(true);
	}

	public void initComponents() {

		this.labelDim = new Dimension(100, 25);
		this.buttonDim = new Dimension(100, 25);
		this.fieldDim = new Dimension(200, 25);

		this.nameLabel = new JLabel("First name: ", JLabel.TRAILING);
		this.nameField = new JTextField(10);
		this.nameLabel.setLabelFor(nameField);

		this.nameLabel.setPreferredSize(labelDim);
		this.nameField.setPreferredSize(fieldDim);

		this.leftPanel.add(nameLabel);
		this.leftPanel.add(nameField);

		this.lastnameLabel = new JLabel("Last name: ", JLabel.TRAILING);
		this.lastnameField = new JTextField(10);
		this.lastnameLabel.setLabelFor(lastnameField);

		this.lastnameLabel.setPreferredSize(labelDim);
		this.lastnameField.setPreferredSize(fieldDim);

		this.leftPanel.add(lastnameLabel);
		this.leftPanel.add(lastnameField);

		this.emailLabel = new JLabel("E-mail: ", JLabel.TRAILING);
		this.emailField = new JTextField(10);
		this.emailLabel.setLabelFor(emailField);

		this.emailLabel.setPreferredSize(labelDim);
		this.emailField.setPreferredSize(fieldDim);

		this.leftPanel.add(emailLabel);
		this.leftPanel.add(emailField);

		this.usernameLabel = new JLabel("Username: ", JLabel.TRAILING);
		this.usernameField = new JTextField(10);
		this.usernameLabel.setLabelFor(usernameField);

		this.usernameLabel.setPreferredSize(labelDim);
		this.usernameField.setPreferredSize(fieldDim);

		this.leftPanel.add(usernameLabel);
		this.leftPanel.add(usernameField);

		this.passwordLabel = new JLabel("Password: ", JLabel.TRAILING);
		this.passwordField = new JPasswordField(10);
		this.passwordLabel.setLabelFor(passwordField);

		this.passwordLabel.setPreferredSize(labelDim);
		this.passwordField.setPreferredSize(fieldDim);

		this.leftPanel.add(passwordLabel);
		this.leftPanel.add(passwordField);

		this.confirmPasswordLabel = new JLabel("Confirm password: ", JLabel.TRAILING);
		this.confirmPasswordField = new JPasswordField(10);
		this.confirmPasswordLabel.setLabelFor(confirmPasswordField);

		this.confirmPasswordLabel.setPreferredSize(labelDim);
		this.confirmPasswordField.setPreferredSize(fieldDim);

		this.leftPanel.add(confirmPasswordLabel);
		this.leftPanel.add(confirmPasswordField);

		this.activateLabel = new JLabel("Activated: ", JLabel.TRAILING);
		this.activateBox = new JCheckBox();
		this.activateLabel.setLabelFor(activateBox);

		this.activateLabel.setPreferredSize(labelDim);
		this.activateBox.setPreferredSize(fieldDim);

		this.leftPanel.add(activateLabel);
		this.leftPanel.add(activateBox);

		this.blockedLabel = new JLabel("Blocked: ", JLabel.TRAILING);
		this.blockedBox = new JCheckBox();
		this.blockedLabel.setLabelFor(blockedBox);

		this.blockedLabel.setPreferredSize(labelDim);
		this.blockedBox.setPreferredSize(fieldDim);

		this.leftPanel.add(blockedLabel);
		this.leftPanel.add(blockedBox);

		SpringUtilities.makeCompactGrid(this.leftPanel, 8, 2, 6, 6, 6, 6);

		this.mainPanel.add(this.leftPanel, BorderLayout.WEST);

		// address data
		this.countryLabel = new JLabel("Country: ", JLabel.TRAILING);
		this.countryField = new JComboBox<String>(countries);
		this.countryLabel.setLabelFor(countryField);

		this.countryLabel.setPreferredSize(labelDim);
		this.countryField.setPreferredSize(fieldDim);

		this.rightPanel.add(countryLabel);
		this.rightPanel.add(countryField);

		this.cityLabel = new JLabel("City: ", JLabel.TRAILING);
		this.cityField = new JTextField(10);
		this.cityLabel.setLabelFor(cityField);

		this.cityLabel.setPreferredSize(labelDim);
		this.cityField.setPreferredSize(fieldDim);

		this.rightPanel.add(cityLabel);
		this.rightPanel.add(cityField);

		this.postalCodeLabel = new JLabel("Postal code: ", JLabel.TRAILING);
		this.postalCodeField = new JTextField();
		this.postalCodeLabel.setLabelFor(postalCodeField);

		this.postalCodeLabel.setPreferredSize(labelDim);
		this.postalCodeField.setPreferredSize(fieldDim);

		this.rightPanel.add(postalCodeLabel);
		this.rightPanel.add(postalCodeField);

		this.streetLabel = new JLabel("Street: ", JLabel.TRAILING);
		this.streetField = new JTextField(10);
		this.streetLabel.setLabelFor(streetField);

		this.streetLabel.setPreferredSize(labelDim);
		this.streetField.setPreferredSize(fieldDim);

		this.rightPanel.add(streetLabel);
		this.rightPanel.add(streetField);

		this.numberLabel = new JLabel("Number: ", JLabel.TRAILING);
		this.numberField = new JTextField(10);
		this.numberLabel.setLabelFor(numberField);

		this.numberLabel.setPreferredSize(labelDim);
		this.numberField.setPreferredSize(fieldDim);

		this.rightPanel.add(numberLabel);
		this.rightPanel.add(numberField);

		this.apartmentNumberLabel = new JLabel("Apartment number: ", JLabel.TRAILING);
		this.apartmentNumberField = new JTextField();
		this.apartmentNumberLabel.setLabelFor(apartmentNumberField);

		this.apartmentNumberLabel.setPreferredSize(labelDim);
		this.apartmentNumberField.setPreferredSize(fieldDim);

		this.rightPanel.add(apartmentNumberLabel);
		this.rightPanel.add(apartmentNumberField);

		this.longitudeLabel = new JLabel("Longitude: ", JLabel.TRAILING);
		this.longitudeField = new JTextField(10);
		this.longitudeLabel.setLabelFor(longitudeField);

		this.longitudeLabel.setPreferredSize(labelDim);
		this.longitudeField.setPreferredSize(fieldDim);

		this.rightPanel.add(longitudeLabel);
		this.rightPanel.add(longitudeField);

		this.latitudeLabel = new JLabel("Latitude: ", JLabel.TRAILING);
		this.latitudeField = new JTextField(10);
		this.latitudeLabel.setLabelFor(latitudeField);

		this.latitudeLabel.setPreferredSize(labelDim);
		this.latitudeField.setPreferredSize(fieldDim);

		this.rightPanel.add(latitudeLabel);
		this.rightPanel.add(latitudeField);

		SpringUtilities.makeCompactGrid(this.rightPanel, 8, 2, 6, 6, 6, 6);

		this.mainPanel.add(this.rightPanel, BorderLayout.EAST);

		this.okButton = new JButton("Ok");
		this.cancelButton = new JButton("Cancel");

		this.okButton.setPreferredSize(buttonDim);
		this.cancelButton.setPreferredSize(buttonDim);

		this.bottomPanel.add(okButton);
		this.bottomPanel.add(cancelButton);

		this.initActionListeners();

		this.mainPanel.add(this.bottomPanel, BorderLayout.SOUTH);
	}

	public void initActionListeners() {
		this.okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (validation() && validationPassword()) {

					newAdmin.setName(nameField.getText());
					newAdmin.setLastname(lastnameField.getText());
					newAdmin.setUsername(usernameField.getText());
					newAdmin.setEmail(emailField.getText());
					newAdmin.setBlocked(blockedBox.isSelected());
					newAdmin.setEnabled(activateBox.isSelected());
					newAdmin.setDeleted(false);
					String password = new String(passwordField.getPassword());
					newAdmin.setPassword(password);
					newAdmin.setRole("ADMINISTRATOR");
					Address tempAddress = new Address();
					tempAddress.setCountry(countryField.getSelectedItem().toString());
					tempAddress.setCity(cityField.getText());
					tempAddress.setNumber(numberField.getText());
					tempAddress.setPostalCode(Integer.parseInt(postalCodeField.getText()));
					tempAddress.setStreet(streetField.getText());
					tempAddress.setApartmentNumber(apartmentNumberField.getText());
					tempAddress.setLatitude(Double.parseDouble(latitudeField.getText()));
					tempAddress.setLongitude(Double.parseDouble(longitudeField.getText()));

					newAdmin.setAddress(tempAddress);

					UserActionCreate createAction = (UserActionCreate) Singleton.getInstance().getContext()
							.getBean(UserActionCreate.class);
					createAction.setUser(newAdmin);
					createAction.action();

					if (createAction.isSuccess()) {
						ActionTreeUpdate treeUpdate = new ActionTreeUpdate();
						treeUpdate.action();

						UpdateTable tableUpdate = new UpdateTable();
						tableUpdate.action();
						JOptionPane.showMessageDialog(null, "Successfully!", "Create Agent!",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Unsuccessful. Try again!", "Create Agent Error!",
								JOptionPane.ERROR_MESSAGE);

					}

				}

			}
		});
	}

	public void getAllCountries() {
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale locale = new Locale("", countryCode);
			this.countries.add(locale.getDisplayCountry(locale));
		}
	}

	@SuppressWarnings("deprecation")
	public boolean validation() {

		if (nameField.getText().equals("")) {
			this.nameField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		} else if (lastnameField.getText().equals("")) {
			this.lastnameField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		} else if (usernameField.getText().equals("")) {
			this.usernameField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		} else if (passwordField.getText().equals("")) {
			this.passwordField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		} else if (confirmPasswordField.getText().equals("")) {
			this.confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		} else if (emailField.getText().equals("") || !emailField.getText().contains("@")) {
			this.emailField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		} else if (cityField.getText().equals("")) {
			this.cityField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		} else if (postalCodeField.getText().equals("")) {
			this.postalCodeField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		} else if (streetField.getText().equals("")) {
			this.streetField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		} else if (numberField.getText().equals("")) {
			this.numberField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		}
		return true;
	}

	public boolean validationPassword() {
		String password = new String(this.passwordField.getPassword());
		String confirmPassword = new String(this.confirmPasswordField.getPassword());

		if (password.equals(confirmPassword)) {
			this.passwordField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
			this.confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
			return true;
		} else {
			this.passwordField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			this.confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			return false;
		}

	}
}
