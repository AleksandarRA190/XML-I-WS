package ftn.xmlws.AdminModule.controller.swingApp;

import java.awt.Image;

import javax.swing.ImageIcon;

public class InitIcons {

	private ImageIcon openIcon;
	private ImageIcon closeIcon;
	private ImageIcon rootIcon;
	private ImageIcon userIcon;
	private ImageIcon groupIcon;

	private Image appImage;
	private Image userImage;
	private Image groupImage;

	public InitIcons() {

	}

	public void initIcons() {

		openIcon = initIcon(openIcon, "icons/opened.png");
		closeIcon = initIcon(closeIcon, "icons/closed.png");
		rootIcon = initIcon(rootIcon, "icons/root.png");
		userIcon = initIcon(userIcon, "icons/user.png");
		groupIcon = initIcon(groupIcon, "icons/group.png");

		appImage = initImage(appImage, "icons/app.png");
		userImage = initImage(userImage, "icons/user.png");
		groupImage = initImage(userImage, "icons/group.png");
	}

	public ImageIcon initIcon(ImageIcon imageIcon, String uri) {

		imageIcon = new ImageIcon(uri);
		Image temp = imageIcon.getImage();
		temp = temp.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(temp);

		return imageIcon;
	}

	public Image initImage(Image image, String uri) {
		ImageIcon imageIcon = new ImageIcon(uri);
		Image icon = imageIcon.getImage();
		image = icon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		return image;
	}

	public ImageIcon getOpenIcon() {
		return openIcon;
	}

	public void setOpenIcon(ImageIcon openIcon) {
		this.openIcon = openIcon;
	}

	public ImageIcon getCloseIcon() {
		return closeIcon;
	}

	public void setCloseIcon(ImageIcon closeIcon) {
		this.closeIcon = closeIcon;
	}

	public ImageIcon getRootIcon() {
		return rootIcon;
	}

	public void setRootIcon(ImageIcon rootIcon) {
		this.rootIcon = rootIcon;
	}

	public ImageIcon getGroupIcon() {
		return groupIcon;
	}

	public void setGroupIcon(ImageIcon groupIcon) {
		this.groupIcon = groupIcon;
	}

	public ImageIcon getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(ImageIcon userIcon) {
		this.userIcon = userIcon;
	}

	public Image getAppImage() {
		return appImage;
	}

	public void setAppImage(Image appImage) {
		this.appImage = appImage;
	}

	public Image getUserImage() {
		return userImage;
	}

	public void setUserImage(Image userImage) {
		this.userImage = userImage;
	}

	public Image getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(Image groupImage) {
		this.groupImage = groupImage;
	}

}