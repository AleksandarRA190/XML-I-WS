package ftn.xmlws.AdminModule.view.mainFrame.bars;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.view.createFrames.NewAdminFrame;
import ftn.xmlws.AdminModule.view.createFrames.NewAgentFrame;

public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private JButton newAdmin;
	private JButton newAgent;

	public ToolBar() {
		super(SwingConstants.HORIZONTAL);

		this.initButtons();
		this.initActionListeners();

		this.setFloatable(true);
	}

	public void initButtons() {

		ImageIcon temp = Singleton.getInstance().getInitIcons().getGroupIcon();
		Image tempIcon = temp.getImage();
		tempIcon = tempIcon.getScaledInstance(17, 17, Image.SCALE_SMOOTH);
		temp = new ImageIcon(tempIcon);

		this.newAdmin = new JButton("Admin");
		this.newAdmin.setIcon(temp);
		this.newAdmin.setToolTipText("Create new administrator.");

		this.newAgent = new JButton("Agent");
		this.newAgent.setIcon(temp);
		this.newAgent.setToolTipText("Create new agent.");

		this.add(newAdmin);
		this.add(newAgent);

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
	}
}
