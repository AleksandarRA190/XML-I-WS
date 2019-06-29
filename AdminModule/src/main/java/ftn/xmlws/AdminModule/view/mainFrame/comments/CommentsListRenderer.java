package ftn.xmlws.AdminModule.view.mainFrame.comments;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SpringLayout;

import ftn.xmlws.AdminModule.Comment;
import ftn.xmlws.AdminModule.view.createFrames.SpringUtilities;

public class CommentsListRenderer extends JPanel implements ListCellRenderer<Object> {

	private static final long serialVersionUID = 1L;
	private JLabel commentLabel;
	private JLabel commentContentLabel;

	public CommentsListRenderer() {
		this.setLayout(new SpringLayout());
		this.setVisible(true);
		this.initComponents();
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		this.setOpaque(true);

	}

	public void initComponents() {
		this.commentLabel = new JLabel("Comment content: ", JLabel.TRAILING);
		this.commentContentLabel = new JLabel();
		this.commentLabel.setLabelFor(this.commentContentLabel);

	}

	public void addComponents() {
		this.add(commentLabel);
		this.add(commentContentLabel);

		SpringUtilities.makeCompactGrid(this, 1, 2, 6, 6, 6, 6);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		if (value instanceof Comment) {
			Comment comment = (Comment) value;
			this.commentContentLabel.setText(comment.getContentOfComment());
			addComponents();

			if (isSelected) {
				this.setBackground(Color.GREEN);
			} else {
				this.setBackground(Color.white);
			}
		}
		setEnabled(list.isEnabled());

		return this;
	}

}
