package ftn.xmlws.AdminModule.view.mainFrame.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ftn.xmlws.AdminModule.Singleton;

public class Tab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton closeTab;
	private JLabel labelTab;
	private JLabel iconLabel;
	private ImageIcon closeIcon;
	private String title;

	public Tab(String title) {
		this.title = title;
		this.setOpaque(false);

		this.initButton();
		this.initLabel();
		this.add(iconLabel, BorderLayout.WEST);
		this.add(labelTab, BorderLayout.CENTER);
		this.add(closeTab, BorderLayout.EAST);
	}

	public void initButton() {
		closeIcon = new ImageIcon("resources/closeIcon.png");
		Image close = closeIcon.getImage();
		close = close.getScaledInstance(17, 17, Image.SCALE_SMOOTH);
		closeIcon = new ImageIcon(close);
		closeTab = new JButton(closeIcon);
		Dimension btnDim = new Dimension(17, 17);
		closeTab.setBorderPainted(false);
		closeTab.setPreferredSize(btnDim);
		closeTab.setVisible(true);
		closeTab.addMouseListener(new TabMouseListener(Singleton.getInstance().getTabbedPane()));

	}

	public void initLabel() {
		this.labelTab = new JLabel(title);
		ImageIcon temp = Singleton.getInstance().getInitIcons().getGroupIcon();
		Image tempIcon = temp.getImage();
		tempIcon = tempIcon.getScaledInstance(17, 17, Image.SCALE_SMOOTH);
		temp = new ImageIcon(tempIcon);
		this.iconLabel = new JLabel(temp);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
